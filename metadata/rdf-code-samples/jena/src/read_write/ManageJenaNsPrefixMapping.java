// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents an rdf graph as model in Jena
*/

package read_write;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.riot.*;
import org.apache.jena.shared.*;

import java.io.*;
import java.util.*;

/*
** Name space Ns in RDFXML representatons of model namespace
** as output, as mechanism for fine grained query control,
** for example person/organization profiles with different versions of vcard spec
** should have specific namespace in model 
** print contents of vcard4 to console, System.out.print()
** write content of vcard4 to file, System.out
*/
public class ManageJenaNsPrefixMapping extends Object {
	
	// some defnintions
	static String personURI	= "http://agw.org/citizen-scientist"; // URI string
	static String givenName = "Citizen"; // first name
	static String familyName = "Scientist"; // last name
	static String fullName 	= givenName + " " + familyName; // full name, first name and second name
	static String nickName = "Zensci"; // nick name
	static String orgName = "Anthropogenic Global Warming"; // organization name
	static String honorificPrefix = "Mr"; // 
	
	public static void main (String args[]) {
		
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		// create a dataset as vcard subject predicate object 
		// to populate the model with
		populateModelWithVcardData(model);
		
		// print the vcard statements to the console, 
		printStatementsToConsol(model);
		
		// print to vcard to console as "RDF/XML", with default j.0 ns prefix
		printRdfXmlToConsole(model); 
		
		// manage namespace ns prefix mapping
		// change from default j.0 to vcard version
		manageJenaNsPrefixMapping(model);
		
		// print the vcard to the console as "RDF/XML", with vcard version ns prefix
		printRdfXmlToConsole2(model); // the preferred way to write output
		
		// output the vcard to file, with vcard version ns prefix
		writeToFile(model);
		
	}
	
	private static void manageJenaNsPrefixMapping(Model model) {

		// does the model have ns mappings?
		boolean hasNoMappings = model.hasNoMappings();
		System.out.println("model has no mappings is " + hasNoMappings);
		
		// the number of ns prefixes
		int prefixCount = model.numPrefixes();
		System.out.println("model has prefix count of " + prefixCount);

		String prefixName = "vcard-4.0";
		String prefixURI = "http://www.w3.org/2006/vcard/ns#";

		try {
			model.setNsPrefix(prefixName, prefixURI);
			System.out.println("ns prefix set " + prefixName + " " + prefixURI); // info
		} catch(PrefixMapping.IllegalPrefixException e) {
			System.err.println("IllegalPrefixException " + e);
		}
		
		// remove any existing bindings for the default namespace
		// to only use custiom prefix remove default j.0 notation
		// as seen above the default prefix is not included in calculating
		// hasNoMappings or prefixCount
		model.removeNsPrefix("j.0");

		// does the model have ns mappings?
		hasNoMappings = model.hasNoMappings();
		System.out.println("model has no mappings is " + hasNoMappings);

		// the number of ns prefixes
		prefixCount = model.numPrefixes();
		System.out.println("model has prefix count of " + prefixCount);
		
		// does the model now have mappings?
		if (!model.hasNoMappings()) {
			
			// 
			Map<String, String> prefixMap = model.getNsPrefixMap(); // value of prefixes
			Iterator<Map.Entry<String, String>> iter = prefixMap.entrySet().iterator();

			
			while (iter.hasNext()) {
				System.out.println(iter.next().toString());
			}
		}
	
	}
	
	private static void populateModelWithVcardData(Model model) {
		
		// create the resource
		// use cascading coding sytle to add properties to 
		// resource in model
		Resource cit_sci = model.createResource(personURI)
			.addProperty(VCARD4.fn, fullName)
			.addProperty(VCARD4.n, 
				model.createResource()
					.addProperty(VCARD4.given_name, givenName)
					.addProperty(VCARD4.family_name, familyName)
					.addProperty(VCARD4.honorific_prefix, honorificPrefix));
		
		// add the property
		cit_sci.addProperty(VCARD4.nickname, nickName);
		
		// add the property
		cit_sci.addProperty(VCARD4.organization_name, orgName);
		
	}
	
	private static void printStatementsToConsol(Model model) {
		
		// get model stattement list and the list iterator
		StmtIterator iter = model.listStatements();
		
				// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
			Statement stmt      = iter.nextStatement(); // get next statement
			Resource  subject   = stmt.getSubject();    // get the subject
			Property  predicate = stmt.getPredicate();  // get the predicate
			RDFNode   object    = stmt.getObject();     // get the object
			
			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");
			
			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {
				// object is a literal
				System.out.print(" \"" + object.toString() + "\"");
			}
			
			System.out.println(" .");
		}
	}
		
	private static void printRdfXmlToConsole(Model model) {
		
		// Set the RDF/XMl writer format and options
		RDFFormat rdfFormat = RDFFormat.RDFXML;

		// create a map to hold some properties
		Map<String, Object> properties = new HashMap<>();

		// populate the properties hashmap
		properties.put("xmlbase", "http://agw.org/"); // optional
		properties.put("showXmlDeclaration", true); // optional
		
		// create a writer instance 
		RDFWriter writer = RDFWriter.create()
			.format(rdfFormat)
			.source(model)
			.set(SysRIOT.sysRdfWriterProperties, properties)
			.build();
		
		// write the model to the console
		writer.output(System.out);
	
	}

	private static void printRdfXmlToConsole2(Model model) {
		
		// <todo: how set properties for the writer?>
		// <todo: how to configure RDFDataMgr? with xmlbase for example. >
				
		RDFDataMgr.write(System.out, model, RDFFormat.RDFXML); // output RDFXML to console

	}
	
	private static void writeToFile(Model model) {
		
		String fileName = null; // instantiate, not necessary but polite
		/* fileName = "vcard-ns-l.rdf"; // name of the output file, l for legacy
		createFile(fileName); // create the output stream
		
		// now write the model in XML form to file
		// the legacy way to write RDF data
		// model.write()
		// Jena writer name; "TURTLE", "TTL", "Turtle", "N-TRIPLES", "N-TRIPLE", "NT", "JSON-LD", "RDF/XML-ABBREV", "RDF/XML", "N3", "RDF/JSON"
		model.write(System.out); // defaults to "RDF/XML" */
		
		// set the output directory and file
		// java.io 
		fileName = "vcard-ns-p.rdf"; // name the output file p for preferred
		createFile(fileName); // create the output stream
		
		// now write
		// the preferred way to write RDF data
		// provides many more formatting options
		// Apache Jen RIOT, 
		// RDFDataMgr.write(OutputStream, Model, Lang) or 
		// RDFDataMgr.write(OutputStream, Dataset, Lang)
		// RDFDataMgr.write(OutputStream, Model, RDFFormat) or 
		// RDFDataMgr.write(OutputStream, Dataset, RDFFormat)
		// RIOT RDFFormat; TURTLE, NTRIPLES, JSONLD, RDFXML, RDFXML_PLAIN, N3, RDFJSON
		RDFDataMgr.write(System.out, model, RDFFormat.RDFXML); // select RDFXML for comparison
		
	}
	
	/*
	** <todo: try java.nio.file >
	** <todo: consider logging too! SLF4J & Logback, logging introduced time critical issues, >
	** <todo: consider java time ciritcal lib like Javolution? no due dilligence yet! >
	*/
	private static void createFile(String fileName) {
		
		String filePath = "src/read_write/output/" + fileName;
		OutputStream fileOut = OutputStream.nullOutputStream();
		PrintStream ps;
		File file;
		
		// <todo: refactor with check to see if file already exists>
		// not sure what this will achieve,
		// perhaps 'read' in the file as opposed to create a new one?
		// likely unecessary as we don't care in this example if
		// the file is overwritten.
		// if (!new File(filePath).exists())
		// 		// Create the file
		// } else {
		//		// Handle the case where the file already exists
		// }

		try {
			file = new File(filePath);
			file.createNewFile();
			fileOut = new FileOutputStream(file);
			ps = new PrintStream(fileOut);
			System.setOut(ps);
		} catch(FileNotFoundException e) {
			System.err.println("FileNotFoundException thrown: " + e);
		} catch(SecurityException e) {
			System.err.println("SecurityException thrown: " + e);
		} catch (IOException e) {
			System.err.println("IOException thrown: " + e);
		} catch (NullPointerException e) {
			System.err.println("NullPointerException thrown: " + e);
		}

	}
}