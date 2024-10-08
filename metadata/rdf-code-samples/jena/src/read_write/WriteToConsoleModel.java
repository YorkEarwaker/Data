// from https://jena.apache.org/tutorials/rdf_api.html

/* The Model Interface 
** represents an rdf graph as model in Jena
*/

package read_write;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;
import org.apache.jena.riot.*;
//import org.apache.jena.shared.*; // not used, model prefix mapping

//import java.io.*; // not used, out to file
import java.util.*;

/*
** Name space Ns in RDFXML representatons of model namespace
** as output, as mechanism for fine grained query control,
** for example person/organization profiles with different versions of vcard spec
** should have specific namespace in model 
** print contents of vcard4 to console, System.out.print()
** write content of vcard4 to file, System.out
*/
public class WriteToConsoleModel extends Object {
	
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
		
		// create a dataset as vcard subject predicate object 
		// to populate the model with
		populateModelWithVcardData(model);
		
		// print the vcard statements to the console, 
		printStatementsToConsol(model);
		
		// print to console as "RDF/XML", with default j.0 ns 
		printRdfXmlToConsole(model); 
		
	}
	
	private static void printRdfXmlToConsole(Model model) {
		
		// Set the RDF/XMl writer format and options
		RDFFormat rdfFormat = RDFFormat.RDFXML;

		// create a map to hold some properties
		Map<String, Object> properties = new HashMap<>();

		// populate the properties hashmap
		properties.put("xmlbase", "http://agw.org/"); // optional
		properties.put("showXmlDeclaration", true); // optional
		
		// create a writer instance 
		RDFWriter writer = RDFWriter.create()
			.format(rdfFormat)
			.source(model)
			.set(SysRIOT.sysRdfWriterProperties, properties)
			.build();
		
		// write the model to the console
		writer.output(System.out);
		
	}
	
	private static void populateModelWithVcardData(Model model) {
		
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
		
	}
	
	private static void printStatementsToConsol(Model model) {
		
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