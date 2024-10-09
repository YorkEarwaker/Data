// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents a graph as model in Jena
*/

package read_write;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.riot.*;
import org.apache.jena.shared.*;

//import java.nio.file.*; // not current used, reminder to do so
import java.io.*;
import java.util.*;

/*
** work in progress, writeToFile2
** 
** https://jena.apache.org/documentation/io/rdf-output.html, retrieved, 08:47 08/10/2024, 
*/
public class WriteToFileModel2 extends Object {
	
	
	public static void main (String args[]) {
		
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		
		// create a dataset as vcard subject predicate object 
		// to populate the model with
		populateModelWithVcardData(model);
		
		// manage namespace ns prefix mapping
		// change from default j.0 to vcard version
		manageJenaNsPrefixMapping(model);
		
		// output the vcard to file, with vcard version ns prefix
		writeToFile2(model);
		
	}

	private static void manageJenaNsPrefixMapping(Model model) {
		
		showStatusNsPrefixMapping(model); // info
		
		String prefixName = "vcard-4.0";
		String prefixURI = "http://www.w3.org/2006/vcard/ns#"; // https://www.w3.org/TR/vcard-rdf/#namespacedeclarations retrieved 14:25, 09/10/2024

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
		
		showStatusNsPrefixMapping(model); // info
		
	}
	
	/*
	** output state of PrefixMapping ps
	** print any namespaces ns in ps
	*/
	private static void showStatusNsPrefixMapping(Model model) { // info

		// does the model have ns mappings?
		boolean hasNoMappings = model.hasNoMappings();
		System.out.println("model has no mappings is " + hasNoMappings); // info

		// the number of ns prefixes
		int prefixCount = model.numPrefixes();
		System.out.println("model has prefix count of " + prefixCount); // info
		
		// does the model now have mappings?
		if (!model.hasNoMappings()) {
			
			// 
			Map<String, String> prefixMap = model.getNsPrefixMap(); // value of prefixes
			Iterator<Map.Entry<String, String>> iter = prefixMap.entrySet().iterator();
			
			while (iter.hasNext()) {
				System.out.println(iter.next().toString()); // info
			}
		}
	}
	
	/*
	** add some vcard-4.0 data to the rdf graph model
	*/
	private static void populateModelWithVcardData(Model model) {
		
		// some defnintions for VCARD4 
		// https://www.w3.org/TR/vcard-rdf/
		final String personURI	= "http://agw.org/citizen-scientist"; // URI string
		final String givenName = "Citizen"; // first name
		final String familyName = "Scientist"; // last name
		final String fullName 	= givenName + " " + familyName; // full name, first name and second name
		final String nickName = "Zensci"; // nick name
		final String orgName = "Anthropogenic Global Warming"; // organization name
		final String honorificPrefix = "Mr";
		
		//final int everyOneIsaNumber = 1357911131;
		//final String citizenId = "foo:bha:id";
		
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
		
		//Property citizenNumber = model.createProperty(citizenId);
		//Literal cit_sci_id = model.createTypedLiteral(everyOneIsaNumber);
		
		// add the property
		//cit_sci.addProperty(citizenNumber, cit_sci_id);
		
	}
	
	private static void printRdfXmlToConsole3(Model model, Object obj) {
		
		// <todo: how set properties for the writer?>
		// <todo: how to configure RDFDataMgr? with xmlbase for example. >
		
		if (obj instanceof Lang) {
			//System.out.println("Lang it is " + obj.toString()); // debug
			RDFDataMgr.write(System.out, model, (Lang) obj); // output RDFXML to console
		} else if (obj instanceof RDFFormat) {
			//System.out.println("Lang it is " + obj.toString()); // debug
			RDFDataMgr.write(System.out, model, (RDFFormat) obj); // output RDFXML to console
		} else {
			System.err.println("Expected Lang or RDFFormat, obj is " + obj.getClass()); // debug
		}
		
	}
	
	/*
	** wip work in progress 
	** <todo: as many output combinations as are practicle>
	*/
	private static void writeToFile2(Model model) {
		
		String fileName = null; // instantiate, not necessary but polite
		
		// https://jena.apache.org/documentation/io/ retrieved 10:45, 09/10/2024
		// Lang language and syntax, which has a related RDFFormat serialisation format
		// choosing a specific Lang, Jena internally maps that to a relevant format
		// A lang is usually tied to a specific format
		// RDFFormat is a serialisation format
		// An RDFFormat can be used by multiple language systaxes, RDFXML for example
		
		/*
		** command line tool
		** <todo: is the internal map of file extensions public? how to access for coding? >
		** file extention    language name
           .ttl              Turtle
           .nt               N-Triples
           .nq               N-Quads
           .trig             TriG
           .rdf              RDF/XML
           .owl              RDF/XML
           .jsonld           JSON-LD
           .trdf             RDF Thrift
           .rt               RDF Thrift
           .rpb              RDF Protobuf
           .pbrdf            RDF Protobuf
           .rj               RDF/JSON
           .trix             TriX
		*/
		
		/*
		**
		** Jena writer name	RIOT RDFFormat
          "TURTLE"          TURTLE
          "TTL"             TURTLE
          "Turtle"          TURTLE
          "N-TRIPLES"       NTRIPLES
          "N-TRIPLE"        NTRIPLES
          "NT"              NTRIPLES
          "JSON-LD"         JSONLD
          "RDF/XML-ABBREV"  RDFXML
          "RDF/XML"         RDFXML_PLAIN
          "N3"              N3
          "RDF/JSON"        RDFJSON
		*/
		
		/*
		** streaming only??? possibly not relevant for write?
		** assume this the default format of the language
		** RDFFormat	            Lang shortcut
           RDFFormat.TURTLE_BLOCKS  Lang.TURTLE
           RDFFormat.TURTLE_FLAT	
           RDFFormat.TRIG_BLOCKS    Lang.TRIG
           RDFFormat.TRIG_FLAT
           RDFFormat.NTRIPLES_UTF8  Lang.NTRIPLES
           RDFFormat.NTRIPLES_ASCII
           RDFFormat.NQUADS_UTF8    Lang.NQUADS
           RDFFormat.NQUADS_ASCII
           RDFFormat.TRIX           Lang.TRIX
           RDFFormat.RDF_THRIFT     Lang.RDFTHRIFT
           RDFFormat.RDF_PROTO      Lang.RDFPROTO
		*/
		
		/*
		** Normal Printing
		** RDFFormat or Lang	Default
           TURTLE               Turtle, pretty printed
           TTL                  Turtle, pretty printed
           NTRIPLES             N-Triples, UTF-8
           TRIG                 TriG, pretty printed
           NQUADS               N-Quads, UTF-8
           JSONLD               JSON-LD, pretty printed
           RDFXML               RDF/XML, pretty printed
           RDFJSON
           TRIX
           RDFTHRFT             RDF Binary Thrift
           RDFPROTO             RDF Binary Protobuf
		*/
		
		/*
		** Pretty printed
		** RDFFormat       Same as
           TURTLE_PRETTY   TURTLE, TTL
           TRIG_PRETTY     TRIG
           RDFXML_PRETTY   RDFXML_ABBREV, RDFXML
		*/
		
		/*
		** Streamed Blocks
		** RDFFormat
           TURTLE_BLOCKS
           TRIG_BLOCKS
		*/
		
		/*
		** Line printed 
		** RDFFormat
           TURTLE_FLAT
           TRIG_FLAT           
		*/
		
		/*
		** Turtle and Trig format options
		** Context setting                  Cmd line               Values
           RIOT.symTurtleDirectiveStyle     “ttl:directiveStyle”   “sparql”, “rdf11”, “at”, “n3”
           RIOT.symTurtleIndentStyle        “ttl:indentStyle”      “wide”, “long”
           RIOT.symTurtleOmitBase           “ttl:omitBase”         “true”, “false”
 
         ** Directive Style                 Effect
           “sparql”, “rdf11”	            Use PREFIX and BASE in output.
           “at”, “rdf10”                    Use @prefix and @base in output.
           unset                            Use PREFIX and BASE in output.
		*/
		
		/*
		** N-Triples and N-Quads
		** RDFFormat        Other names
           NTRIPLE          NTRIPLE, NT, NTRIPLES_UTF8
           NQUADS           NQUADS, NQ, NQUADS_UTF8
		   NTRIPLES_ASCII
           NQUADS_ASCII
		*/
		
		/*
		** JSON-LD
		** Caution. This section describes features that may be removed.
		** Jena uses different third party processors for JSON-LD 1.0 and JSON-LD 1.1.
		** This section describes support for passing configuration to the JSON-LD 1.0 processor only. It does not apply to the JSON-LD 1.1 processor.
		** It is planned that support for JSON-LD 1.0 will be removed in Jena5.
		** The project is looking for contributions for passing framing configuration to the JSON-LD 1.1 processor, which is titanium-json-ld.
		**  JSON-LD output is supported, in its various flavors (“compacted”, “expanded”, “flattened”, “framed”), by using one of the following RDFFormats:
		** RDFFormat
		   JSONLD_EXPAND_PRETTY
		   JSONLD_EXPAND_FLAT
		   JSONLD_COMPACT_PRETTY
		   JSONLD_COMPACT_FLAT
		   JSONLD_FLATTEN_PRETTY
		   JSONLD_FLATTEN_FLAT
		   JSONLD_FRAME_PRETTY
		   JSONLD_FRAME_FLAT
		** The default registration for JSONLD is JSONLD_PRETTY. JSONLD_PRETTY is identical to JSONLD_COMPACT_PRETTY.
		*/

		/*
		** RDF Binary
		** RDFFormat
		   RDF_THRIFT
		   RDF_THRIFT_VALUES
		   RDF_PROTO
		   RDF_PROTO_VALUES
		** RDF_THRIFT_VALUES and RDF_PROTO_VALUES are variants where numeric values are written as values, 
		** not as lexical format and datatype. See the description of RDF Binary. for discussion.
		*/
		
		/*
		** RDF/XML
		** RDFFormat	  Other names                    Jena writer name
		   RDFXML         RDFXML_PRETTY, RDF_XML_ABBREV	 “RDF/XML-ABBREV”
		   RDFXML_PLAIN                                  “RDF/XML”
		*/
		
		// <todo: iterate about this, changing filename and output formats>
		// <todo: cycle through RDFFormat and Lang >		
		
		// set the output directory and file
		// java.io 
		fileName = "vcard-lang-turtle.ttl"; // name the output file p for preferred
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
		// example RDFDataMgr.write(System.out, model, Lang.RDFXML); // select RDFXML for comparison
		
		// print the vcard to the file
		printRdfXmlToConsole3(model, Lang.TURTLE); // the preferred way to write output
		
		resetOutputStream();
		
		// print the vcard to the console
		printRdfXmlToConsole3(model, Lang.TURTLE); // the preferred way to write output
		
		
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
			fileOut = new FileOutputStream(file); // redirect out, to file
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
	
	private static void resetOutputStream() {
		
		OutputStream fostream  = OutputStream.nullOutputStream();
		PrintStream ps;
		//FileOutputStream fostream;

		try {
			fostream = new FileOutputStream(FileDescriptor.out); // reset default out, to console
			ps = new PrintStream(fostream);
			System.setOut(ps);
		} catch(SecurityException e) {
			System.err.println("SecurityException thrown: " + e);
		} catch (NullPointerException e) {
			System.err.println("NullPointerException thrown: " + e);
		}
	}

}