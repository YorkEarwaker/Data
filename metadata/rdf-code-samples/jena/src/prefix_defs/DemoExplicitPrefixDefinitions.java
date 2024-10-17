//https://jena.apache.org/tutorials/rdf_api.html#ch-Introduction
package prefix_defs;

import org.apache.jena.rdf.*; // Model
import org.apache.jena.rdf.model.*; // Resource, Property


/*
** Manage some explicit Prefix definitions
** Use the Greek Alphabet (el) as bespoke namespaces
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
** https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes
** retrieved 17/10/2024
**
** variabl names can't contain greek el letters. 
**
** https://jena.apache.org/documentation/notes/iri.html
** retrieved 18:15 17/10/2024 
** 
*/
public class DemoExplicitPrefixDefinitions extends Object {
	
	public static void main(String args[]) {
		
		Model m = ModelFactory.createDefaultModel();
		
		// namespace α
		String nsA = "http://agw/thing-kind#"; 
		
		// namespace β
		String nsB = "http://anthropogenicglobalwarming/thing-kind#"; 
		
		// create a resource in namespace α
		Resource radix = m.createResource( nsA + "radix" ); // root en, radix la
		
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
		m.add( radix, P, x ).add( radix, P, y ).add( y, Q, z );
		
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