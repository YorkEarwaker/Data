//https://jena.apache.org/tutorials/rdf_api.html#ch-Introduction
package prefix_defs;

import org.apache.jena.rdf.*; // Model
import org.apache.jena.rdf.model.*; // Resource, Property

/*
** Issues with org.apache.jena.irix.IRIException: Not an RDF IRI: <z>
** IDE issue? Try in Eclipse perhaps the issue is VS Code related?
** 
** Manage some explicit Prefix definitions
** Consider; Cyrillic (), Korean (), Japanese (), Chinese (zh), other non ASCII? for IRI's .
** First instance attempt to use non ASCII Greek (el) Alphabet, as bespoke namespaces
** Due to its continued use in mathematics and use of mathematics in hard sciences
** 
** Αα   Alpha     Νν    Nu
** Ββ   Beta      Ξξ	Xi
** Γγ   Gamma     Οο    Omicron
** Δδ   Delta     Ππ    Pi
** Εε   Epsilon   Ρρ    Rho
** Ζζ   Zeta      Σσς   Sigma
** Ηη   Eta       Ττ    Tau
** Θθ   Theta     Υυ    Upsilon
** Ιι   Iota      Φφ    Phi
** Κκ   Kappa     Χχ    Chi
** Λλ   Lambda	  Ψψ    Psi
** Μμ   Mu        Ωω    Omega
** https://en.wikipedia.org/wiki/Greek_alphabet
**
** ISO 639-1 alpha, Two letter codes used for natural languages
** https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes
** retrieved 17/10/2024
**
** <todo: ISO 639-2 alpah, three letter codes for natural languages, now largly replace by 636-3 but refereneced widly in other standards like IEFT BCP 47>
**
** <todo: ISO 639-3 alpha, three letter codes for natural languages, >
**
** ISO 639-5 alpha?, Three letter codes used for natural language collections
** https://en.wikipedia.org/wiki/List_of_ISO_639-5_codes
** retrieved 10:33 19/10/2024
**
** ISO 15924 alpha, Codes for the representation of scripts, writing systems, character sets and glyphs, 
** https://en.wikipedia.org/wiki/ISO_15924
** retrieved 10:49 19/10/2024
**
** ISO 3166-1 alpha-2, Two letter codes used for countries
** https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
** retrieved 10:23 19/10/2024
**
** <todo: ISO 3166-1 alpha-3, Three letter codes for countries>
**
** ISO 3166-1 numeric, Three number codes use for countries and independent territories
** https://en.wikipedia.org/wiki/ISO_3166-1_numeric
** retrieved 10:13 20/10/2024
**
** UN M.49, Three digit geographical region codes, Standard Counrty or Area Codes for Statistical Use (Series M, No. 49),
** https://en.wikipedia.org/wiki/UN_M49
** retrieved 09:29 20/10/2024
**
** IETF BCP 47 language tag, - maintained by IANA language tag registry
** Four letter internet language codes, an amalgum of ISO 639, ISO 15924, ISO 3166-1, UN M.49, 
** https://en.wikipedia.org/wiki/IETF_language_tag
** retrieved 10:53 19/10/2024
**
** <todo: CLDR, Common Locae Data Repository, Unicode, operating system and appliation, >
**
** Unicde
** https://en.wikipedia.org/wiki/Unicode_equivalence#Normal_forms
** retrieved 09:38 18/10/2024
** 
** Unicode
** https://unicode.org/reports/tr15/
** retrieved 09:40 18/10/2024
**
** <todo: URI/IRI authority, IDN International Domain Name, >
**
** IRI Jena, Very incomplete JDoc, Not much in user documentation either, 
** https://jena.apache.org/documentation/notes/iri.html
** retrieved 18:15 17/10/2024 
** 
** IRI overview, WP
** https://en.wikipedia.org/wiki/Internationalized_Resource_Identifier
** retrieved 09:27 18/10/2024
**
** IRI IETF RFC 3987, 
** https://datatracker.ietf.org/doc/html/rfc3987
** retrieved 12:32 18/10/2024
**
** <todo: URI IEFT RFC 3986, >
**
** URI/IRI
** https://en.wikipedia.org/wiki/Percent-encoding
** retrieved 09:46 18/10/2024
**
** URI/IRI scheme
** https://www.w3.org/wiki/UriSchemes
** retrieved 10:14 18/10/2024
**
** URI/IRI
** https://en.wikipedia.org/wiki/Punycode
** retrieved 13:00 18/10/2024
**
** ** **
** Java code. Separate character encoding issue from IRI's. 
** Java variable names (can't? or) should avoid containg non ASCII characters? Like Greek el letters.
** <todo: Find definitive source. Should Java code be ASCII compliant as best practice? apparently not but with significant caviats.>
** <todo: What is the 'safe' Unicode charater set for Java code?>
** Unicode normalization is done by Java on identifiers in Java code. The Java standard.
** UTF-8 is often used to save Java source files. Cross tool set support, ide's, .... Third party integration of development and support environments, 
** Code readability and maintainability is also an important issue with non standard character sets. NFR's the ilities. dev ops, dev SEC ops, security, interoperability, ...
** 
** ** **
** What are precise IRI form rules, in general strict, in Jena, for RDF
** Also related issue with IRI and Unicode normalization equavalent normal forms
** 
** scheme:[//authority]path[?query][#fragment]
**
** scheme - about, file, http, ftp, irc, pop3, that is some URI type, see IANA scheme directory for comprehensive listing, many schemes are IETF standard proposals/recomendations
** authority - domain name / ip address
** path - a relative path to a resource
** query - an optional part which may contain paramaters as arguments to a web service, it is not part of the resource identification hierarchy (it is non hierarchical)
** fragment - an optional part which can be used to locate specific parts of / within the resource / document 
** 
*/
public class DemoExplicitPrefixDefinitions extends Object {
	
	public static void main(String args[]) {
		
		Model model = ModelFactory.createDefaultModel();
		
		// Trouble shoot IRI issues from Jena docs example
		exploreJeanIRIApproaches(model);
		
		// Jena user docs example
		demoJenaDocumentationExample(model); // currently thows an iri exception
	}
	
	/*
	** From the Jena documentation with some minor changes for learning purposes
	** Tried several changes to the Jean example code including URI/IRI string contents
	** Likely something small causing the exception thrown but no idea at present what that might be.
	** is this a resource definition issue with # fragments? 
	** is this a Prefix Mapping issue? 
	*/
	private static void demoJenaDocumentationExample(Model m) {
		
		// namespace α
		String nsA = "http://agw.org/thingkind/ns#"; 
		
		// namespace β
		String nsB = "http://anthropogenicglobalwarming.org/thingkind/ns#"; 
		
		// create a resource in namespace α
		//Resource radix = m.createResource( nsA + "radix" ); // root en, radix la, as a fragment
		Resource nonradix = m.createResource( nsA ); // with no fragment
		
		// create a property in namespace α - Alpha
		Property P = m.createProperty( nsA + "P" );
		
		// create a property in namespace β -  Beta
		Property Q = m.createProperty( nsB + "Q" );
		
		// create a resource in namespace α - Alpha
		Resource x = m.createResource( nsA + "x" );
		
		// create a resource in namespace α - Alpha
		Resource y = m.createResource( nsA + "y" );
		
		// create a resource in namespace α - Alpha
		Resource z = m.createResource( nsA = "z" );
		
		// chain some additions to the model together for brevity
		//m.add( radix, P, x ).add( radix, P, y ).add( y, Q, z ); // with radix string as fragment
		m.add( nonradix, P, x ).add( nonradix, P, y ).add( y, Q, z );
		
		// no bespoke prefixes have been added to the PrefixMapping
		System.out.println( "# -- no special prefixes defined" );
		
		// show the model default prefixes 
		m.write( System.out ); // org.apache.jena.irix.IRIException: Not an RDF IRI: <z>
		
		// to set 'nsA' to the PrefixMapping
		System.out.println( "# -- nsA defined" );
		
		// set bespoke shortname 'nsA' for bespoke namespace 'nsA' to the models PrefixMapping
		m.setNsPrefix( "nsA", nsA );
		
		// show the effects of 'nsA' beening set
		// show the model with modified prefixes, addition of namespace nsα 
		m.write( System.out );
		
		// to set 'blue' to the PrefixMapping
		System.out.println( "# -- nsA and blue defined" );
		
		// set bespoke shortname 'blue' for bespoke namespace 'nsB' to to the models PrefixMapping
		m.setNsPrefix( "blue", nsB );
		
		// 
		m.write( System.out );
		
		/*
		exploreJeanIRIApproaches, in 
		exploreJeanIRIApproaches, out 
		# -- no special prefixes defined
		<rdf:RDFException in thread "main" org.apache.jena.irix.IRIException: Not an RDF IRI: <z>
        at org.apache.jena.irix.IRIs.reference(IRIs.java:43)
        at org.apache.jena.irix.IRIs.checkEx(IRIs.java:63)
        at org.apache.jena.rdfxml.xmloutput.impl.BaseXMLWriter.checkURI(BaseXMLWriter.java:844)
        at org.apache.jena.rdfxml.xmloutput.impl.BaseXMLWriter.relativize(BaseXMLWriter.java:790)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wURIreference(Unparser.java:922)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wURIreference(Unparser.java:926)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wResourceAttr(Unparser.java:988)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wResourceNodeIDAttr(Unparser.java:962)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wIdRefAttrOpt(Unparser.java:935)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyEltCompact(Unparser.java:414)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyElt(Unparser.java:381)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyEltStar(Unparser.java:852)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wTypedNodeOrDescriptionLong(Unparser.java:838)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wTypedNodeOrDescription(Unparser.java:769)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wDescription(Unparser.java:727)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wObj(Unparser.java:683)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyEltValueObj(Unparser.java:578)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyEltValue(Unparser.java:523)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyElt(Unparser.java:386)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wPropertyEltStar(Unparser.java:852)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wTypedNodeOrDescriptionLong(Unparser.java:838)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wTypedNodeOrDescription(Unparser.java:769)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wDescription(Unparser.java:727)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wObj(Unparser.java:683)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wObjStar(Unparser.java:359)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.wRDF(Unparser.java:340)
        at org.apache.jena.rdfxml.xmloutput.impl.Unparser.write(Unparser.java:244)
        at org.apache.jena.rdfxml.xmloutput.impl.RDFXML_Abbrev.writeBody(RDFXML_Abbrev.java:127)
        at org.apache.jena.rdfxml.xmloutput.impl.BaseXMLWriter.writeXMLBody(BaseXMLWriter.java:471)
        at org.apache.jena.rdfxml.xmloutput.impl.BaseXMLWriter.write(BaseXMLWriter.java:447)
        at org.apache.jena.rdfxml.xmloutput.impl.BaseXMLWriter.write(BaseXMLWriter.java:434)
        at org.apache.jena.riot.adapters.AdapterRDFWriter.write(AdapterRDFWriter.java:64)
        at org.apache.jena.riot.RDFWriter.write$(RDFWriter.java:256)
        at org.apache.jena.riot.RDFWriter.output(RDFWriter.java:215)
        at org.apache.jena.riot.RDFWriter.output(RDFWriter.java:158)
        at org.apache.jena.riot.RDFWriterBuilder.output(RDFWriterBuilder.java:207)
        at org.apache.jena.riot.adapters.RDFWriterRIOT.write(RDFWriterRIOT.java:87)
        at org.apache.jena.rdf.model.impl.ModelCom.write(ModelCom.java:243)
        at prefix_defs.DemoExplicitPrefixDefinitions.demoJenaDocumentationExample(DemoExplicitPrefixDefinitions.java:180)
        at prefix_defs.DemoExplicitPrefixDefinitions.main(DemoExplicitPrefixDefinitions.java:135)
		*/
		
	}
	
	/* 
	** **
	** Troubleshooting todo's, to explore IRI exception thrown in Jena example doc code
	** see researh links above in class comments
	** 
	** IRIFactory, 
	** IRI Construction base IRI or relative resoved to give absolute IRI, 
	** IRI Validation against IRI URI specs, 
	** Violations of specs, Must Should tatements from specs, 
	** Minting Violations stricture for IRI created by Jena app as opposed to more lenient for those passed from elsehwere, 
	**
	** <todo: IRI Parsing before validation, parse with IRIImpl methods, >
	** <todo: IRI Validation before handing off to Jean m.write for internal validation>
	** **
	** IRIFactory. Create IRIFactory instance
	** IRI Classes. Construct IRI from string. Use factories create() or construct () methods.
	** Viloation & ViolationCodes interfaces. Validate IRI against given standards, 
	** 
	** ** **
	** IRIFactory.iriImplementation(); Jena IRI implmentation has stricter checking in line with IRI spec RFC 3987
	** IRIFactory.uriImplementation(); Jena URI implementation has only US-ASCII checking in line with URI spec RFC 3986
	** IRIFactory.semanticWebImplementation(); Jena Semantic Web implementation has conservative checking in line with applications for Semantic Web spec
	** IRIFactory.jenaImplementation(); Jean implementation used only by the Jena team, 
	** 
	*/
	private static void exploreJeanIRIApproaches(Model model) {
		
		System.out.println("exploreJeanIRIApproaches, in "); // debug
		
		// <todo: use Jena IRI code things here, likely several sub methods for different approaches>
		// <todo: create an IRI that does not throw a - Not an RDF IRI - excpetion.>
		
		System.out.println("exploreJeanIRIApproaches, out "); // debug
		
	}
	
	
}