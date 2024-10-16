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
import java.lang.reflect.*;
import java.util.*;

//import read_write.JenaLangFileExtension;

/*
** work in progress, writeToFile2
** 
** https://jena.apache.org/documentation/io/rdf-output.html, 
** retrieved, 08:47 08/10/2024, 
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
		writeToFileAllForms(model);
		
	}
	
	/*
	** Constructor
	** <todo: constructor('s)>
	*/
	WriteToFileModel2 () {
			
	}
	
	/*
	** set vcard-4.0 as ns in prefixMapping
	*/
	private static void manageJenaNsPrefixMapping(Model model) {
		
		showStatusNsPrefixMapping(model); // info
		
		// <todo: consider properties file (or read in csv, etc) for name space data>
		String prefixName = "vcard-4.0";
		String prefixUri = "http://www.w3.org/2006/vcard/ns#"; 
		
		setJenaNsPrefixMapping(model, prefixName, prefixUri);
		
		// remove any existing bindings for the default namespace
		// to only use custiom prefix remove default j.0 notation
		// the default prefix is not included in calculating prefixMapping
		// hasNoMappings or prefixCount
		//model.removeNsPrefix("j.0");
		
		showStatusNsPrefixMapping(model); // info
		
	}
	
	/* 
	** PrefixMapping, https://javadoc.io/doc/org.apache.jena/jena-core/latest/org.apache.jena.core/org/apache/jena/shared/PrefixMapping.html
	** retrieved 12:31, 11/10/2024
	** 
	** the #, hash/pound, symbole is required. Otherwise Jena uses default j.0 ns
	** if the # is not provided and the prefix j.0 is removed, see below, Jena creates a j.1 prefix 
	** https://www.w3.org/TR/vcard-rdf/#namespacedeclarations 
	** retrieved 14:25, 09/10/2024
	*/
	private static void setJenaNsPrefixMapping(Model model, String prefixName, String prefixUri) {

		try {
			model.setNsPrefix(prefixName, prefixUri);
			System.out.println("ns prefix set " + prefixName + " " + prefixUri); // info
		} catch(PrefixMapping.IllegalPrefixException e) {
			System.err.println("IllegalPrefixException " + e);
		}
		
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
	** Packgage, https://javadoc.io/doc/org.apache.jena/jena-core/latest/org.apache.jena.core/org/apache/jena/rdf/model/package-summary.html
	** Class, https://javadoc.io/doc/org.apache.jena/jena-core/latest/org.apache.jena.core/org/apache/jena/rdf/model/Model.html 
	** retrieved 12:39, 11/10/2024
	*/
	private static void populateModelWithVcardData(Model model) {
		
		// <todo: consider properties file (or csv file) for vcard data>
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
		//final String citizenId = "agent-id";
		//final String agwOrgNS = "xmlns:agw-org";
		
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
		
		//
		//Property citizenNumber = model.createProperty(namespace, citizenId);
		//Literal cit_sci_id = model.createTypedLiteral(everyOneIsaNumber);
		
		// add the property
		//cit_sci.addProperty(citizenNumber, cit_sci_id);
		
	}
	
	/*
	 ** Object obj, must be either class Lang or class RDFFormat
	 */
	private static void printRdfGraphOut(Model model, Object obj) {
		
		// <todo: how set properties for the writer?>
		// <todo: how to configure RDFDataMgr? with xmlbase for example. >
		
		//model.
		
		//RDFLanguages <todo: test of output type, or perhaps another enum of rdfformats to lang?>
		// RDFLanguages.fileExtToLang(...)
		// RDFLanguages.filenameToLang(...)

		if (obj instanceof Lang) {
			System.console().printf("Lang it is " + obj.toString()); // debug
			RDFDataMgr.write(System.out, model, (Lang) obj); // output RDFXML to console
		} else if (obj instanceof RDFFormat) {
			System.console().printf("Lang it is " + obj.toString()); // debug
			RDFDataMgr.write(System.out, model, (RDFFormat) obj); // output RDFXML to console
		} else {
			//<todo: throw exception, illegal argument>
			System.err.println("Expected Lang or RDFFormat, obj is " + obj.getClass()); // debug
			throw new IllegalArgumentException();
		}
		
	}
	
	/*
	** wip work in progress 
	** <todo: as many output combinations as are practicle>
	*/
	private static void writeToFileAllForms(Model model) {
		
		// <todo: cycle through RDFFormat and Lang >
		Collection<String> constantsCollection = new ArrayList<String>(40);
		writeCollectionToFile(model, constantsCollection, RDFFormat.class);

		constantsCollection.clear();
		System.console().printf("after clearing constantsCollection size is " + constantsCollection.size() + "\n");

		collectClassConstants(constantsCollection, Lang.class);
		writeCollectionToFile(model, constantsCollection, Lang.class);
		
	}

	private static void writeCollectionToFile(Model model, Collection<String> constantsCollection, Class<?> clazz) {
		
		collectClassConstants(constantsCollection, clazz);

		// <todo: iterate about this, changing filename and output formats>
		System.out.println("writeCollectionToFile constantsCollection is " + constantsCollection.isEmpty()); // debug
		
		String fileExtension = new String(""); // to get from enum, 
		String fileName = new String("vcard-4.0-");
		// required as static calls not working due to call stack variable state issue in state machine, 
		WriteToFileModel2 writeToFileModel2 = new WriteToFileModel2(); 
		
		String className = clazz.getName();  // get name of Lang/RDFFormat for conditional test 
		Object theOutputFormInstance = null; // the Lang/RDFFormat variable to be passed to the RDFWriter
		boolean hasReigsteredWriter = false; // whether a REFWriter is registred for Lang/RDFFormat value
		
		//Iterator iter = constantsCollection.iterator();
		for (String outPutForm : constantsCollection) {
			
			fileExtension = "";
			fileName = "vcard-4.0-";
			hasReigsteredWriter = false;
			theOutputFormInstance = null;
			
			if (className == Lang.class.getName()) {
				
				//<todo: try catch>
				JenaLangInstance jenaLangInstance = JenaLangInstance.valueOf(outPutForm);
				hasReigsteredWriter = RDFWriterRegistry.contains(jenaLangInstance.getValue());
				theOutputFormInstance = jenaLangInstance.getValue();
				
				/* theOutputFormInstance = LangBuilder.create()
					.langName("Lang." + outPutForm)
					.build(); */
			} else if (className == RDFFormat.class.getName()) {
				
				//<todo: try catch>
				JenaRDFFormatInstance jenaRDFFormatInstance = JenaRDFFormatInstance.valueOf(outPutForm);
				hasReigsteredWriter = RDFWriterRegistry.contains(jenaRDFFormatInstance.getValue());
				theOutputFormInstance = jenaRDFFormatInstance.getValue();
				
				/* String classForNameArg = className + "." + outPutForm;
				//theOutputFormInstance =  
				try {
					Class<?> t = Class.forName(classForNameArg);
					
					Class<?> theClassName = Class.forName(classForNameArg);
					RDFFormat obj = (RDFFormat) theClassName.getDeclaredConstructor().newInstance();
				} catch (ClassNotFoundException e) {

				} */
			} else {
				// do something like throw an exception
			}
			
			// do stuff to write is out if there is a registered RDF Writer
			if (hasReigsteredWriter) {
				
				System.console().printf("outPutForm is " + outPutForm + "\n"); // debug
			
				fileExtension = writeToFileModel2.getFileExtension(outPutForm);
				//System.console().printf("*** fileExtension is " + fileExtension + "\n");
			
				// set the output directory and file
				// java.io 
				//fileName = "vcard-lang-turtle.ttl"; // name the output file p for preferred
				fileName = writeToFileModel2.createFileName(fileName, clazz, outPutForm, fileExtension);
			
				createFile(fileName); // create the output stream
				System.console().printf("*** fileName is " + fileName + "\n");
				
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
				printRdfGraphOut(model, theOutputFormInstance); // the preferred way to write output
		
				resetOutputStream(); // from file to console
	
				// print the vcard to the console
				printRdfGraphOut(model, theOutputFormInstance); // the preferred way to write output
			
			
			}
		}
	}
	
	/*
	**
	** https://jena.apache.org/documentation/io/ 
	** retrieved 10:45, 09/10/2024
	**
	** Lang language and syntax, which has a related RDFFormat serialisation format
	** choosing a specific Lang, Jena internally maps that to a relevant format
	** A lang is usually tied to a specific format
	** RDFFormat is a serialisation format
	** An RDFFormat can be used by multiple language systaxes, RDFXML for example
	** 	** 	** 
	** command line tool
	** <todo: is the internal map of file extensions public? how to access for coding? >
	** file extention    language name
	** .ttl                     Turtle
	** .nt                      N-Triples
	** .nq                      N-Quads
	** .trig                    TriG
	** .rdf                     RDF/XML
	** .owl                     RDF/XML
	** .jsonld                  JSON-LD
	** .trdf                    RDF Thrift
	** .rt                      RDF Thrift
	** .rpb                     RDF Protobuf
	** .pbrdf                   RDF Protobuf
	** .rj                      RDF/JSON
	** .trix                    TriX
	** 	** 	**  
	** Jena writer name         RIOT RDFFormat
	** "TURTLE"                 TURTLE
	** "TTL"                    TURTLE
	** "Turtle"                 TURTLE
	** "N-TRIPLES"              NTRIPLES
	** "N-TRIPLE"               NTRIPLES
	** "NT"                     NTRIPLES
	** "JSON-LD"                JSONLD
	** "RDF/XML-ABBREV"         RDFXML
	** "RDF/XML"                RDFXML_PLAIN
	** "N3"                     N3
	** "RDF/JSON"               RDFJSON
	** 	** 	** 
	** streaming only??? possibly not relevant for write?
	** assume this the default format of the language
	** RDFFormat	            Lang shortcut
	** RDFFormat.TURTLE_BLOCKS  Lang.TURTLE
	** RDFFormat.TURTLE_FLAT	
	** RDFFormat.TRIG_BLOCKS    Lang.TRIG
	** RDFFormat.TRIG_FLAT
	** RDFFormat.NTRIPLES_UTF8  Lang.NTRIPLES
	** RDFFormat.NTRIPLES_ASCII
	** RDFFormat.NQUADS_UTF8    Lang.NQUADS
	** RDFFormat.NQUADS_ASCII
	** RDFFormat.TRIX           Lang.TRIX
	** RDFFormat.RDF_THRIFT     Lang.RDFTHRIFT
	** RDFFormat.RDF_PROTO      Lang.RDFPROTO
	** 	** 	** 
	** Normal Printing
	** RDFFormat or Lang        Default
	** TURTLE                   Turtle, pretty printed
	** TTL                      Turtle, pretty printed
	** NTRIPLES                 N-Triples, UTF-8
	** TRIG                     TriG, pretty printed
	** NQUADS                   N-Quads, UTF-8
	** JSONLD                   JSON-LD, pretty printed
	** RDFXML                   RDF/XML, pretty printed
	** RDFJSON
	** TRIX
	** RDFTHRFT                 RDF Binary Thrift
	** RDFPROTO                 RDF Binary Protobuf
	** 	** 	** 
	** Pretty printed
	** RDFFormat                Same as
	** TURTLE_PRETTY            TURTLE, TTL
	** TRIG_PRETTY              TRIG
	** RDFXML_PRETTY            RDFXML_ABBREV, RDFXML
	** 	** 	** 
	** Streamed Blocks
	** RDFFormat
	** TURTLE_BLOCKS
	** TRIG_BLOCKS
	** 	** 	** 
	** Line printed 
	** RDFFormat
	** TURTLE_FLAT
	** TRIG_FLAT           
	** 	** 	** 
	** Turtle and Trig format options
	** Context setting                  Cmd line               Values
	** RIOT.symTurtleDirectiveStyle     “ttl:directiveStyle”   “sparql”, “rdf11”, “at”, “n3”
	** RIOT.symTurtleIndentStyle        “ttl:indentStyle”      “wide”, “long”
	** RIOT.symTurtleOmitBase           “ttl:omitBase”         “true”, “false”
	** 	** 	** 
	** Directive Style                  Effect
	** “sparql”, “rdf11”	            Use PREFIX and BASE in output.
	** “at”, “rdf10”                    Use @prefix and @base in output.
	** unset                            Use PREFIX and BASE in output.
	** 	** 	** 
	** N-Triples and N-Quads
	** RDFFormat        Other names
	** NTRIPLE          NTRIPLE, NT, NTRIPLES_UTF8
	** NQUADS           NQUADS, NQ, NQUADS_UTF8
	** NTRIPLES_ASCII
	** NQUADS_ASCII
	** 	** 	** 
	** JSON-LD
	** Caution. This section describes features that may be removed.
	** Jena uses different third party processors for JSON-LD 1.0 and JSON-LD 1.1.
	** This section describes support for passing configuration to the JSON-LD 1.0 processor only. It does not apply to the JSON-LD 1.1 processor.
	** It is planned that support for JSON-LD 1.0 will be removed in Jena5.
	** The project is looking for contributions for passing framing configuration to the JSON-LD 1.1 processor, which is titanium-json-ld.
	** JSON-LD output is supported, in its various flavors (“compacted”, “expanded”, “flattened”, “framed”), by using one of the following RDFFormats:
	** RDFFormat
	** JSONLD_EXPAND_PRETTY
	** JSONLD_EXPAND_FLAT
	** JSONLD_COMPACT_PRETTY
	** JSONLD_COMPACT_FLAT
	** JSONLD_FLATTEN_PRETTY
	** JSONLD_FLATTEN_FLAT
	** JSONLD_FRAME_PRETTY
	** JSONLD_FRAME_FLAT
	** The default registration for JSONLD is JSONLD_PRETTY. JSONLD_PRETTY is identical to JSONLD_COMPACT_PRETTY.
	** 	** 	** 
	** RDF Binary
	** RDFFormat
	** RDF_THRIFT
	** RDF_THRIFT_VALUES
	** RDF_PROTO
	** RDF_PROTO_VALUES
	** RDF_THRIFT_VALUES and RDF_PROTO_VALUES are variants where numeric values are written as values, 
	** not as lexical format and datatype. See the description of RDF Binary. for discussion.
	** 	** 	** 
	** RDF/XML
	** RDFFormat	  Other names                    Jena writer name
	** RDFXML         RDFXML_PRETTY, RDF_XML_ABBREV	 “RDF/XML-ABBREV”
	** RDFXML_PLAIN                                  “RDF/XML”
	**	** 	** 
	** https://javadoc.io/doc/org.apache.jena/jena-arq/latest/org.apache.jena.arq/org/apache/jena/riot/RDFLanguages.html
	** retrieved 10:45, 09/10/2024
	**
	** Modifier and Type                Field                Description
	** static final Lang                JSONLD               The standard JSON-LD registration.
	** static final Lang                JSONLD11
	** static final Lang                N3                   N3 (treat as Turtle)
	** static final Lang                NQ                   Alternative constant NQUADS
	** static final Lang                NQUADS               N-Quads
	** static final Lang                NT                   Alternative constant for NTRIPLES
	** static final Lang                NTRIPLES             N-Triples
	** static final Lang                RDFJSON              RDF/JSON.
	** static final Lang                RDFNULL              The "null" language
	** static final Lang                RDFPROTO
	** static final Lang                RDFRAW               Output-only language for a StreamRDF (for development)
	** static final Lang                RDFTHRIFT            The RDF syntax RDF Thrift
	** static final Lang                RDFXML               RDF/XML
	** static final Lang                SHACLC               SHACL Compact Syntax (2020-07-01)
	** static final String              strLangCSV
	** static final String              strLangJSONLD
	** static final String              strLangJSONLD11
	** static final String              strLangN3
	** static final String              strLangNQuads
	** static final String              strLangNTriples
	** static final String              strLangRDFJSON
	** static final String              strLangRDFPROTO
	** static final String              strLangRDFTHRIFT
	** static final String              strLangRDFXML
	** static final String              strLangTriG
	** static final String              strLangTriX
	** static final String              strLangTSV
	** static final String              strLangTurtle
	** static final Lang                TEXT                 Text
	** static final Lang                TRIG                 TriG
	** static final Lang                TRIX                 TriX
	** static final Lang                TTL                  Alternative constant for TURTLE
	** static final Lang                TURTLE               Turtle
	**	** 	** 
	** https://javadoc.io/doc/org.apache.jena/jena-arq/latest/org.apache.jena.arq/org/apache/jena/riot/RDFFormat.html
	** retrieved 10:45, 09/10/2024
	**
	** Modifier and Type                Field                Description
	** static final RDFFormatVariant    ABBREV               RDF/XML ABBREV variant
	** static final RDFFormatVariant    ASCII                Use ASCII output (N-triples, N-Quads)
	** static final RDFFormatVariant    BLOCKS               Print in blocks, typically all triples with the same subject in an incoming triple/quad stream
	** static final RDFFormatVariant    FLAT                 Print out one per line
	** static final RDFFormat           JSONLD	
	** static final RDFFormat           JSONLD_FLAT	
	** static final RDFFormat           JSONLD_PLAIN
	** static final RDFFormat           JSONLD_PRETTY
	** static RDFFormat                 JSONLD11             JSON LD 1.1 default form - multi-line JSON
	** static RDFFormat                 JSONLD11_FLAT        JSON LD 1.1 - single-line JSON
	** static RDFFormat                 JSONLD11_PLAIN       JSON LD 1.1 - multi-line JSON
	** static RDFFormat                 JSONLD11_PRETTY      JSON LD 1.1 - multi-line JSON - prefixes and native types.
	** static final RDFFormatVariant    LONG                 Print with fixed indentation width and linebreaks after each sequence element
	** static final RDFFormat           NQ                   N-Quads - RDF 1.1 form - UTF-8
	** static final RDFFormat           NQUADS               N-Quads - RDF 1.1 form - UTF-8
	** static final RDFFormat           NQUADS_ASCII         N-Quads - Use ASCII
	** static final RDFFormat           NQUADS_UTF8          N-Quads in UTF-8
	** static final RDFFormat           NT                   N-Triples - RDF 1.1 form - UTF-8
	** static final RDFFormat           NTRIPLES             N-Triples - RDF 1.1 form - UTF-8
	** static final RDFFormat           NTRIPLES_ASCII       N-Triples - Use ASCII
	** static final RDFFormat           NTRIPLES_UTF8        N-Triples in UTF-8
	** static final RDFFormatVariant	PLAIN                Plain printing variant
	** static final RDFFormatVariant	PRETTY               Pretty printing variant
	** static final RDFFormat           RDF_PROTO            RDF Protobuf output.
	** static final RDFFormat           RDF_PROTO_VALUES     A variant of an an RDFFormat that uses value encoding (e.g.
	** static final RDFFormat           RDF_THRIFT           RDF Thrift output.
	** static final RDFFormat           RDF_THRIFT_VALUES    A variant of an an RDFFormat that uses value encoding (e.g.
	** static final RDFFormat           RDFJSON	
	** static final RDFFormat           RDFNULL              The "null" output format (a sink that prints nothing, usually quite efficiently)
	** static final RDFFormat           RDFRAW               Stream-only output format for development - flushes every line.
	** static final RDFFormat           RDFXML
	** static final RDFFormat           RDFXML_ABBREV
	** static final RDFFormat           RDFXML_PLAIN
	** static final RDFFormat           RDFXML_PRETTY
	** static final RDFFormat           SHACLC               SHACL Compact Syntax
	** static final RDFFormat           TRIG                 TriG - default form
	** static final RDFFormat           TRIG_BLOCKS          TriG - write in blocks of triples, with same subject, no nested object or RDF lists
	** static final RDFFormat           TRIG_FLAT            TriG - one line per triple
	** static final RDFFormat           TRIG_LONG            TriG - with fixed indentation width and linebreaks after each sequence element
	** static final RDFFormat           TRIG_PRETTY          TriG - pretty form
	** static final RDFFormat           TRIX	
	** static final RDFFormat           TTL                  Turtle - short name
	** static final RDFFormat           TURTLE               Turtle - default form
	** static final RDFFormat           TURTLE_BLOCKS        Turtle - write in blocks of triples, with same subject, no nested object or RDF lists
	** static final RDFFormat           TURTLE_FLAT          Turtle - one line per triple
	** static final RDFFormat           TURTLE_LONG          Turtle - with fixed indentation width and linebreaks after each sequence element
	** static final RDFFormat           TURTLE_PRETTY        Turtle - pretty form
	** static final RDFFormatVariant    UTF8                 Use UTF-8 output (N-triples, N-Quads)
	** static final RDFFormatVariant    ValueEncoding        Variant for RDF Thrift using values
	**	** 	** 
	** https://javadoc.io/doc/org.apache.jena/jena-arq/latest/org.apache.jena.arq/org/apache/jena/riot/Lang.html
	** retrieved 10:45, 09/10/2024
	**
	** Modifier and Type                Field                Description
	** static Lang                      CSV                  "CSV" - Used in various ways.
	** static Lang                      JSONLD               JSON-LD.
	** static Lang                      JSONLD11             JSONLD 1.1
	** static Lang                      N3                   N3 (treat as Turtle)
	** static Lang                      NQ                   Alternative constant NQUADS
	** static Lang                      NQUADS               N-Quads
	** static Lang                      NT                   Alternative constant for NTRIPLES
	** static Lang                      NTRIPLES             N-Triples
	** static Lang                      RDFJSON              RDF/JSON.
	** static Lang                      RDFNULL              The "null" language
	** static Lang                      RDFPROTO             The RDF syntax RDF Thrift
	** static Lang                      RDFRAW               Output-only language for a StreamRDF (for development)
	** static Lang                      RDFTHRIFT            The RDF syntax RDF Thrift
	** static Lang                      RDFXML               RDF/XML
	** static Lang                      SHACLC               SHACL Compact Syntax (2020-07-01)
	** static Lang                      TRIG                 TriG
	** static Lang                      TRIX                 TriX
	** static Lang                      TSV                  "TSV" - Used in various ways.
	** static Lang                      TTL                  Alternative constant for TURTLE
	** static Lang                      TURTLE               Turtle
	*/
	private static void collectClassConstants(Collection<String> constantsCollection, Class<?> clazz) {
		
		//<todo: implement reflection>
		//<todo: implement enum in a separate method, could this be dynamic?>

		String className = clazz.getName();

		if (className == Lang.class.getName()) {
			System.console().printf("** collectClassConstants, " + className + " \n"); // debug
			getStaticConstants(constantsCollection, clazz);
		} else if (className == RDFFormat.class.getName()) {
			System.console().printf("** collectClassConstants, " + className + " \n"); // debug
			getStaticFinalConstants(constantsCollection, clazz);
		} else {
			//<todo: throw exception, illegal argument
			System.err.println("Expected Lang or RDFFormat, obj is " + className + " \n"); // error
			throw new IllegalArgumentException();
		}

	}
	
	private static void getStaticConstants(Collection<String> constantsCollection, Class<?> clazz) {

		//<todo: add try catch>
		Field[] declaredFields = clazz.getDeclaredFields();
		System.console().printf("** getStaticConstants, declaredFields size is " + declaredFields.length + " \n"); // debug
		List<Field> staticFields = new ArrayList<Field>();
		for (Field field : declaredFields) {
			
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				staticFields.add(field);
			}
		}

		System.console().printf("** getStaticConstants, staticFields size is " + staticFields.size() + " \n"); // debug

		// <todo: resize constantsCollection?>
		// int arrayInitialCapacity = staticFields.size(); 
		// try {
			// constantsCollection = new ArrayList<String>(arrayInitialCapacity);
		// } catch (IllegalArgumentException e) {
			// System.err.println("IllegalArgumentException thrown " + e);
		// }

		for (Field field : staticFields) {
			constantsCollection.add(field.getName());
			System.console().printf("** getStaticConstants, field name is " + field.getName() + " \n"); // debug
		}
		System.console().printf("** getStaticConstants, constantsCollection size is " + constantsCollection.size() + " \n"); // debug
	}

	private static void getStaticFinalConstants(Collection<String> constantsCollection, Class<?> clazz) {

		//<todo: add try catch>
		Field[] declaredFields = clazz.getDeclaredFields();
		System.console().printf("** getStaticFinalConstants, declaredFields size is " + declaredFields.length + " \n"); // debug
		List<Field> staticFinalFields = new ArrayList<Field>();
		for (Field field : declaredFields) {
			
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &
				java.lang.reflect.Modifier.isFinal(field.getModifiers()) &
				(clazz == field.getType())) {
					
				staticFinalFields.add(field);
			}
		}

		System.console().printf("** getStaticFinalConstants, staticFinalFields size is " + staticFinalFields.size() + " \n"); // debug

		// int arrayInitialCapacity = staticFinalFields.size(); 
		// try {
			// Collection<String> foo = new ArrayList<String>(arrayInitialCapacity);
			// constantsCollection = foo;
		// } catch (IllegalArgumentException e) {
			// System.err.println("IllegalArgumentException thrown " + e);
		// }

		for (Field field : staticFinalFields) {
			constantsCollection.add(field.getName());
			System.console().printf("** getStaticFinalConstants, field name is " + field.getName() + " \n"); // debug
		}
		System.console().printf("** getStaticFinalConstants, constantsCollection size is " + constantsCollection.size() + " \n"); // debug
	}
	
	/*
	** wip
	** <todo: consider Enum instead of Map>
	** file extention    language name
	** .ttl                     Turtle
	** .nt                      N-Triples
	** .nq                      N-Quads
	** .trig                    TriG
	** .rdf                     RDF/XML
	** .owl                     RDF/XML
	** .jsonld                  JSON-LD
	** .trdf                    RDF Thrift
	** .rt                      RDF Thrift
	** .rpb                     RDF Protobuf
	** .pbrdf                   RDF Protobuf
	** .rj                      RDF/JSON
	** .trix                    TriX
	** **
	** https://javadoc.io/doc/org.apache.jena/jena-arq/latest/org.apache.jena.arq/org/apache/jena/riot/Lang.html
	** retrieved 10:45, 09/10/2024
	**
	** Modifier and Type                Field                Description
	** static Lang                      CSV                  "CSV" - Used in various ways.
	** static Lang                      JSONLD               JSON-LD.
	** static Lang                      JSONLD11             JSONLD 1.1
	** static Lang                      N3                   N3 (treat as Turtle)
	** static Lang                      NQ                   Alternative constant NQUADS
	** static Lang                      NQUADS               N-Quads
	** static Lang                      NT                   Alternative constant for NTRIPLES
	** static Lang                      NTRIPLES             N-Triples
	** static Lang                      RDFJSON              RDF/JSON.
	** static Lang                      RDFNULL              The "null" language
	** static Lang                      RDFPROTO             The RDF syntax RDF Thrift
	** static Lang                      RDFRAW               Output-only language for a StreamRDF (for development)
	** static Lang                      RDFTHRIFT            The RDF syntax RDF Thrift
	** static Lang                      RDFXML               RDF/XML
	** static Lang                      SHACLC               SHACL Compact Syntax (2020-07-01)
	** static Lang                      TRIG                 TriG
	** static Lang                      TRIX                 TriX
	** static Lang                      TSV                  "TSV" - Used in various ways.
	** static Lang                      TTL                  Alternative constant for TURTLE
	** static Lang                      TURTLE               Turtle
	** 
	** Needs looking at again.
	** Not robust, needs logic for other inputs other than from values of Lang or RDFFormat
	*/
	private String getFileExtension(String outPutForm) {
	
		String fileExtension = new String();
		String languageArgument = new String();
		
		/* RDFLanguages does not have, at current understanding, sufficient capability in Lang determination 
		Lang langAsName = RDFLanguages.nameToLang(outPutForm);
		Lang langAsRecource = RDFLanguages.resourceNameToLang(outPutForm);
		// RDFLanguages.fileExtToLang(...)
		// RDFLanguages.filenameToLang(...)
		*/
		
		// see if the value matches RDFFormat if it does then return the respective Lang from the enum.
		// This might be a good test for default parsers and writers
		try {
			JenaRDFFormatLang jenaRDFFormatLang = JenaRDFFormatLang.valueOf(outPutForm);
			languageArgument = jenaRDFFormatLang.getValue();
		} catch (IllegalArgumentException e) {
			System.console().printf("IllegalArgumentException jenaRDFFormatLang is " + e + "\n"); // info
			languageArgument = outPutForm;
		}
		
		// get the file extension from the enum.
		try {
			JenaLangFileExtension jenaLangFileExtension = JenaLangFileExtension.valueOf(languageArgument);
			fileExtension = jenaLangFileExtension.getValue();
			System.console().printf("JenaLangFileExtension is " + fileExtension + "\n"); // info
		} catch (IllegalArgumentException e) {
			System.console().printf("IllegalArgumentException jenaLangFileExtension is " + e + "\n"); // info
		}

		return fileExtension;
	
	}

	/*
	** Create the file name
	*/
	private String createFileName(String fileName, Class<?> clazz, String outPutForm, String fileExtension) {
		
		System.console().printf("** createFileName the fileExtension is " + fileExtension + "\n"); // info
		fileName = fileName + clazz.getSimpleName() + "-" + outPutForm.toLowerCase() + "" + fileExtension;
		System.console().printf("createFileName is " + fileName + "\n"); // info
		
		return fileName;
		
	}
			
	/*
	** <todo: try java.nio.file >
	** <todo: consider logging too! SLF4J & Logback, logging introduced time critical issues, >
	** <todo: consider java time ciritcal lib like Javolution? no due dilligence yet! >
	** static method fine here as no variable, like fileName, is changed where change is relevant
	** to logic further up call stack chain. For example a change to parameter fileName here would not
	** be recognised further up in the state machine call stack .
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