// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents a graph as model in Jena
*/

package read_write;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.riot.*;

//import java.nio.file.*; // not current used, reminder to do so
import java.io.*;

/*
** https://jena.apache.org/documentation/io/rdf-output.html, retrieved, 08:47 08/10/2024, 
*/
public class WriteToFileModel extends Object {

	// some defnintions for VCARD4 
	// https://www.w3.org/TR/vcard-rdf/
	static String personURI	= "http://agw.org/citizen-scientist"; // URI string
	static String givenName = "Citizen"; // first name
	static String familyName = "Scientist"; // last name
	static String fullName 	= givenName + " " + familyName; // full name, first name and second name
	static String nickName = "Zensci"; // nick name
	static String orgName = "Anthropogenic Global Warming"; // organization name
	static String honorificPrefix = "Mr";
	
	public static void main (String args[]) {
		
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
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
		
		// <todo: break into seperate methods, card instantiation, card to file on 'disk'.>
		
		// set the output directory and file
		// java.io 
		String fileName = null; // instantiate, not necessary but polite
		fileName = "vcard-l.rdf"; // name of the output file, l for legacy
		createFile(fileName); // create the output stream
		
		// now write the model in XML form to file
		// the legacy way to write RDF data
		// model.write()
		// Jena writer name; "TURTLE", "TTL", "Turtle", "N-TRIPLES", "N-TRIPLE", "NT", "JSON-LD", "RDF/XML-ABBREV", "RDF/XML", "N3", "RDF/JSON"
		model.write(System.out); // defaults to "RDF/XML"
		
		// set the output directory and file
		// java.io 
		fileName = "vcard-p.rdf"; // name the output file p for preferred
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