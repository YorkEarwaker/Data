package read_write;

import org.apache.jena.riot.RDFFormat;

	/*
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
	*/
public enum JenaRDFFormatInstance {
	
	// constructors
	
	JSONLD (RDFFormat.JSONLD),                       // not listed Jena supported extension? // <todo: deal with this case>
	JSONLD_FLAT (RDFFormat.JSONLD_FLAT),             // 
	JSONLD_PLAIN (RDFFormat.JSONLD_PLAIN),           // 
	JSONLD_PRETTY (RDFFormat.JSONLD_PRETTY),         // 
	JSONLD11 (RDFFormat.JSONLD11),                   // JSON LD 1.1 default form - multi-line JSON
	JSONLD11_FLAT (RDFFormat.JSONLD11_FLAT),         // JSON LD 1.1 - single-line JSON
	JSONLD11_PLAIN (RDFFormat.JSONLD11_PLAIN),       // JSON LD 1.1 - multi-line JSON
	JSONLD11_PRETTY (RDFFormat.JSONLD11_PRETTY),     // JSON LD 1.1 - multi-line JSON - prefixes and native types.
	NQ (RDFFormat.NQ),                               // N-Quads - RDF 1.1 form - UTF-8
	NQUADS (RDFFormat.NQUADS),                       // N-Quads - RDF 1.1 form - UTF-8
	NQUADS_ASCII (RDFFormat.NQUADS_ASCII),           // N-Quads - Use ASCII
	NQUADS_UTF8 (RDFFormat.NQUADS_UTF8),             // N-Quads in UTF-8
	NT (RDFFormat.NT),                               // N-Triples - RDF 1.1 form - UTF-8
	NTRIPLES (RDFFormat.NTRIPLES),                   // N-Triples - RDF 1.1 form - UTF-8
	NTRIPLES_ASCII (RDFFormat.NTRIPLES_ASCII),       // N-Triples - Use ASCII
	NTRIPLES_UTF8 (RDFFormat.NTRIPLES_UTF8),         // N-Triples in UTF-8
	RDF_PROTO (RDFFormat.RDF_PROTO),                 // RDF Protobuf output.
	RDF_PROTO_VALUES (RDFFormat.RDF_PROTO_VALUES),   // A variant of an an RDFFormat that uses value encoding (e.g.
	RDF_THRIFT (RDFFormat.RDF_THRIFT),               // RDF Thrift output.
	RDF_THRIFT_VALUES (RDFFormat.RDF_THRIFT_VALUES), // A variant of an an RDFFormat that uses value encoding (e.g.
	RDFJSON (RDFFormat.RDFJSON),	                 // 
	RDFNULL (RDFFormat.RDFNULL),                     // The "null" output format (a sink that prints nothing, usually quite efficiently)
	RDFRAW (RDFFormat.RDFRAW),                       // Stream-only output format for development - flushes every line.
	RDFXML (RDFFormat.RDFXML),                       // 
	RDFXML_ABBREV (RDFFormat.RDFXML_ABBREV),         // 
	RDFXML_PLAIN (RDFFormat.RDFXML_PLAIN),           // 
	RDFXML_PRETTY (RDFFormat.RDFXML_PRETTY),         // 
	SHACLC (RDFFormat.SHACLC),                       // SHACL Compact Syntax
	TRIG (RDFFormat.TRIG),                           // TriG - default form
	TRIG_BLOCKS (RDFFormat.TRIG_BLOCKS),             // TriG - write in blocks of triples, with same subject, no nested object or RDF lists
	TRIG_FLAT (RDFFormat.TRIG_FLAT),                 // TriG - one line per triple
	TRIG_LONG (RDFFormat.TRIG_LONG),                 // TriG - with fixed indentation width and linebreaks after each sequence element
	TRIG_PRETTY (RDFFormat.TRIG_PRETTY),             // TriG - pretty form
	TRIX (RDFFormat.TRIX),	                         // 
	TTL (RDFFormat.TTL),                             // Turtle - short name
	TURTLE (RDFFormat.TURTLE),                       // Turtle - default form
	TURTLE_BLOCKS (RDFFormat.TURTLE_BLOCKS),         // Turtle - write in blocks of triples, with same subject, no nested object or RDF lists
	TURTLE_FLAT (RDFFormat.TURTLE_FLAT),             // Turtle - one line per triple
	TURTLE_LONG (RDFFormat.TURTLE_LONG),             // Turtle - with fixed indentation width and linebreaks after each sequence element
	TURTLE_PRETTY (RDFFormat.TURTLE_PRETTY);         // Turtle - pretty form
	
	private final RDFFormat rdfFormatInstance;
	
	// constructor
	private JenaRDFFormatInstance(RDFFormat rdfFormatInstance) {
		this.rdfFormatInstance = rdfFormatInstance;
	}

	public RDFFormat getValue() {
		return(rdfFormatInstance);
	}
	
}