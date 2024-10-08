// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents an rdf graph as model in Jena
*/

package model;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

/*
** legacy vcard ontology, to model SPO statements
*/
public class GraphAsModel extends Object {

	// some defnintions
	static String personURI	= "http://agw.org/citizen-scientist"; // URI string
	static String fullName 	= "Citizen Scientist"; // first name and second name

	public static void main (String args[]) {
		
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();

		// create the resource
		Resource cit_sci = model.createResource(personURI);

		// add the property
		cit_sci.addProperty(VCARD.FN, fullName);
	
	}

}