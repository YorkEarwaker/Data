//https://jena.apache.org/tutorials/rdf_api.html#ch-Introduction
package read_write;

import org.apache.jena.rdf.model.*; // Model
import org.apache.jena.riot.*; // RDFDataMgr

import java.io.*; // InputStream

/*
** Read a file into a model as graph
** 
** https://jena.apache.org/documentation/io/rdf-output.html,
** retrieved 17/10/2024
*/
public class ReadToModelFile extends Object {
	
	static final String inputFileName = new String("vcard-4.0-rdfxml-db-01.rdf");
	static final String pathName = new String("src/read_write/input/");
	static final String inputSource = pathName + inputFileName;
	
	public static void main(String args[]) {
	
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
	
		// use the RDFDataMgr to find the input file
		InputStream in = RDFDataMgr.open( inputSource );
		if ( in == null ) {
			throw new IllegalArgumentException("File: " + inputSource + " not found.");
		}
		
		// read the RDF/XML file
		// second argument is a URI used to resovle relative URI in the input data, 
		// but the input file data has non relatie URI's to resolve so it can be null
		model.read(in, null);
		
		// write it to standard out
		model.write( System.out ); // the legacy way, the favoured way is RDFWriter
	
	}
	
}