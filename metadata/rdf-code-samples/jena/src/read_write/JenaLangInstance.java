package read_write;

import org.apache.jena.riot.Lang;

/*
	** https://jena.apache.org/documentation/io/
	** retrieved 13:46, 12/10/2024
	** wip
	** <todo: consider Enum instead of Map>
	** command line
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
	** https://javadoc.io/doc/org.apache.jena/jena-arq/latest/org.apache.jena.arq/org/apache/jena/riot/RDFLanguages.html
	** retrieved 14:14, 12/10/2024 
	** 
	** <todo: find difinitive Jena file extension source for queries in enum, is there an internal repository class? where is it?>
	** <todo: case where no Jena file extension has yet be found.>
	** <todo: case where there is overloading with two or more file extensions for same language>
	** <todo: a separte enum with other file extentions unrealated to file IO, query, comressed file, xml/xsd/..., other w3c, others, >
*/
public enum JenaLangInstance {
	
	// constructors
	CSV (Lang.CSV), // not listed Jena supported extension? // <todo: deal with this case>
	JSONLD (Lang.JSONLD), 
	JSONLD11 (Lang.JSONLD11), // not Jena supported extension // not listed Jena supported extension? // ???? will be unsupported after Jena5 // <todo: deal with this case>
	N3 (Lang.N3), // .n3 is supported but only as a synonym for Turtle, from Jena I\O link above
	NQ (Lang.NQ),
	NQUADS (Lang.NQUADS),
	NT (Lang.NT),
	NTRIPLES (Lang.NTRIPLES),
	RDFJSON (Lang.RDFJSON),
	RDFNULL (Lang.RDFNULL), // not Jena supported extension // not listed Jena supported extension? // <todo: deal with this case>
	RDFPROTO (Lang.RDFPROTO), // also .rpb, // <todo: deal with this case>
	RDFRAW (Lang.RDFRAW), // not Jena supported extension // not listed Jena supported extension? // <todo: deal with this case>
	RDFTHRIFT (Lang.RDFTHRIFT), // also .rt, // <todo: deal with this case>
	RDFXML (Lang.RDFXML), // also .owl, consider .rdfxml???, // <todo: deal with this case>
	SHACLC (Lang.SHACLC), // also .shc, https://jena.apache.org/documentation/shacl/ <todo: deal with this case>
	TRIG (Lang.TRIG), 
	TRIX (Lang.TRIX),
	TSV (Lang.TSV), // not listed Jena supported extension? // <todo: deal with this case>
	TTL (Lang.TTL), 
	TURTLE (Lang.TURTLE);
	
	private final Lang langInstance;
	
	// constructor
	private JenaLangInstance(Lang langInstance) {
		this.langInstance = langInstance;
	}
	
	// public JenaLangFileExtension getJenaLangFileExtension(String langFileExtention) {
		// return JenaLangFileExtension.valueOf(langFileExtention);
	// }


	public Lang getValue() {
		return(langInstance);
	}

}