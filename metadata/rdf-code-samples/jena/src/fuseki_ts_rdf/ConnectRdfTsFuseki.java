package fuseki_ts_rdf;

import org.apache.jena.rdfconnection.*;

/*
** RDF Connection specifically for Fuseki
** Connect to RDF triple store Apache Fuseki with Jena RDF Connection: SPARQL operations API, using HTTP and SPARQL protocols
*/
public class ConnectRdfTsFuseki extends Object {
	
	// make these top level static final constants available to all class methods?
	//static final String localWarFuseki = new String("http://localhost:8080/fuseki/#/dataset"); // Tomcat, up and running
	static final String localWarFuseki = new String("http://localhost:8080/fuseki/helloWorld"); // manually created in Fuseki UI
	static final String localCmdFuseki = new String("http://localhost:3030/dataset"); // <todo: create cmdline local instance>
	
	public static void main(String arg[]) {
		
		System.out.println("ConnectRdfTsFuseki in "); // info, todo comment out
		
		makeRdfConnectionTsFuseki();
		
		System.out.println("ConnectRdfTsFuseki out "); // info, todo comment out
		
	}
	
	/*
	** Default constructor
	*/
	public ConnectRdfTsFuseki() {
		// nothing to instatiate at this time
	}
	
	/*
	** round trip handling of blank nodes, settings tuned to Fuseki
	** https://jena.apache.org/documentation/rdfconnection/#fuseki-specific-connection
	*/
	public static void makeRdfConnectionTsFuseki() {
		
		System.out.println("makeRdfConnectionTsFuseki in "); // info, todo comment out
		
		String schemeDomainPath = localWarFuseki;
		
		try {
			// 
			RDFConnectionRemoteBuilder builder = RDFConnectionFuseki.create()
				.destination(schemeDomainPath);
				
			System.out.println("Stub <todo: do something like load data or read data or both. > "); // info, todo reaplace
		}
		catch (Exception e) {
			System.err.println("Exception is " + e);
		}
		
		System.out.println("makeRdfConnectionTsFuseki out "); // info, todo comment out
	}
	// Fuseki connection to local instance; 1 local tomcat .war instnace, 2 local cmd line instance
	
}
