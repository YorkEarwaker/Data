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
** Two letter codes used for natural languages
** https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes
** retrieved 17/10/2024
**
** <todo: three letter codes for natural languages, ISO 639-2, now largly replace by 636-3 but refereneced widly in other standards like IEFT BCP 47>
**
** <todo: three letter codes for natural languages, ISO 639-3>
**
** Three letter codes used for natural language collections
** https://en.wikipedia.org/wiki/List_of_ISO_639-5_codes
** retrieved 10:33 19/10/2024
**
** Codes for the representation of scripts
** https://en.wikipedia.org/wiki/ISO_15924
** retrieved 10:49 19/10/2024
**
** Two letter codes used for countries
** https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
** retrieved 10:23 19/10/2024
**
** <todo: Three digit geographical region codes, for countries regions, UN M.49>
**
** IETF BCP 47 language tag - maintained by IANA language tag registry
** Four letter internet language codes, an amalgum of ISO 639, ISO 15924, ISO 3166-1, UN M.49, 
** https://en.wikipedia.org/wiki/IETF_language_tag
** retrieved 10:53 19/10/2024
**
** Java code. Separate issue from IRI's as strings.
** Java variable names (can't? or) should avoid containg non ASCII characters? Like Greek el letters.
** <todo: Find definitive source. Should Java code be ASCII compliant as best practice? apparently not but with significant caviats.>
** <todo: What is the 'safe' Unicode charater set for Java code?>
** Unicode normalization is done by Java on identifiers in Java code. The Java standard.
** UTF-8 is often used to save Java source files. Cross tool set support, ide's, .... Third party integration of development and support environments, 
** Code readability and maintainability is also an important issue with non standard character sets. NFR's the ilities. dev ops, dev SEC ops, security, interoperability, ...
**
** https://jena.apache.org/documentation/notes/iri.html
** retrieved 18:15 17/10/2024 
** 
** https://en.wikipedia.org/wiki/Internationalized_Resource_Identifier
** retrieved 09:27 18/10/2024
**
** https://datatracker.ietf.org/doc/html/rfc3987
** retrieved 12:32 18/10/2024
**
** https://en.wikipedia.org/wiki/Unicode_equivalence#Normal_forms
** retrieved 09:38 18/10/2024
** 
** https://unicode.org/reports/tr15/
** retrieved 09:40 18/10/2024
**
** https://en.wikipedia.org/wiki/Percent-encoding
** retrieved 09:46 18/10/2024
**
** https://www.w3.org/wiki/UriSchemes
** retrieved 10:14 18/10/2024
**
** https://en.wikipedia.org/wiki/Punycode
** retrieved 13:00 18/10/2024
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
		
		Model m = ModelFactory.createDefaultModel();
		
		// namespace α
		String nsA = "http://agw.org/thingkind/ns#"; 
		
		// namespace β
		String nsB = "http://anthropogenicglobalwarming.org/thingkind/ns#"; 
		
		/* 
		** **
		** Troubleshooting todo's
		** <todo: IRI Parsing before validation, parse with IRIImpl methods, >
		** <todo: IRI Validation before handing off to Jean m.write for internal validation>
		** is this a resource definition issue with # fragments? 
		** is this a Prefix Mapping issue? 
		** **
		*/
		
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
		
	}
	
	
}