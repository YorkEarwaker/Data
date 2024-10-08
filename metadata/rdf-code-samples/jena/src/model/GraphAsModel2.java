// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents an rdf graph as model in Jena
*/

package model;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/*
** https://www.w3.org/TR/vcard-rdf/, 2014, retrieved, 09:04 08/10/2024
** newer vcard4 ontology, to model SPO statements
*/
public class GraphAsModel2 extends Object {

	// some defnintions for VCARD4 
	static String personURI	= "http://agw.org/citizen-scientist"; // URI string
	static String givenName = "Citizen"; // first name
	static String familyName = "Scientist"; // last name
	static String fullName 	= givenName + " " + familyName; // full name, first name and second name
	static String nickName = "Zensci"; // nick name
	static String orgName = "Anthropogenic Global Warming"; // organization name
	//static String sweetHeart = "That would be nice"; // Sweetheart, VCARD 2014? == VCARD4 yes, not a string! isa relation type! isa resource?  
	//static String spouse = "None, that would be nice too"; // Spouse, as above, relation type, 
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
		
		// classes
		// related type
		// if no resource to add, so create blank Node as standin? 
		// did not seem to allow blank Node at first attempt
		// struggling to find examples, Acquaintance, Agent, Child, Colleague, Contact, Coresident, Coworker,  
		// Crush, Date, Emergency, Friend, Kin, Me, Met, Muse, Neighbor, Parent, Sibling, Spouse, Sweetheart
		// circle back to this
		// add the property
		// cit_sci.addProperty(VCARD4.Sweetheart, ?); 
		
		// add the property
		//cit_sci.addProperty(VCARD4.Spouse, ?);
	}

}