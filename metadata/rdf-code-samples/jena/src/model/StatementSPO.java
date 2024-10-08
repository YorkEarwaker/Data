// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents an rdf graph as model in Jena
*/

package model;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/*
** print contents of vcard4 to console, System.out.print()
** print each rdf statement of vcard in SPO order
*/
public class StatementSPO extends Object {
	
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
		
		// get model stattement list and the list iterator
		StmtIterator iter = model.listStatements();
		
		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
			Statement stmt		= iter.nextStatement();	// get next statement
			Resource  subject	= stmt.getSubject();	// get the subject
			Property  predicate = stmt.getPredicate();	// get the predicate
			RDFNode	  object    = stmt.getObject(); 	// get the object
			
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

}