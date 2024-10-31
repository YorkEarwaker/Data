package general_ts_rdf;

import org.apache.jena.rdfconnection.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Resource;

/*
** RDF Connection to some triple store
** Connect to RDF triple store general, which might be any triple store, with Jena RDF Connection: SPARQL operations API, using HTTP and SPARQL protocols
** 
** <todo: use generic means to access local Fuseki rdf triple store instance, >
** <todo: use generic means to access A N Other rdf tirple store instance, DBPedia? Other online, another class? >
** <todo: attempt to access non native rdf store with with sparql 1.1 over http, >
*/
public class ConnectionRdfTsGeneral extends Object {
	
	static final String localWarFuseki = new String("http://localhost:8080/fuseki/helloWorld"); // Success! helloWorld dataset had been created manually, <todo: create helloWorld programatically, >
	static final String localCmdFuseki = new String("http://localhost:3030/dataset"); // <todo: create cmdline local instance>
	
	public static void main(String args[]) {
		
		System.out.println("ConnectionRdfTsGeneral in ");
		
		// Comment out one or other instance to toggle coding style
		makeRdfConnectionTsGeneral(CodeStyle.FUNCTIONAL); // success
		
		makeRdfConnectionTsGeneral(CodeStyle.TRY_RESOURCE); // success
		
		System.out.println("ConnectionRdfTsGeneral out ");
		
		
	}
	
	/*
	** constructor
	*/
	public ConnectionRdfTsGeneral() {
		//nothing to instantiate
	}
	
	// Fuseki connection to local instance; 1 local tomcat .war instnace, 2 local cmd line instance
	
	// dbpedia connection, remote https://www.dbpedia.org/resources/sparql/
	
	public static void makeRdfConnectionTsGeneral(CodeStyle codeStyle) {
		
		System.out.println("makeRdfConnectionTsGeneral in "); // info, todo comment out
		
		System.out.println("makeRdfConnectionTsGeneral codeStyle is " + codeStyle);
		
		String schemeDomainPath = localWarFuseki;
		
		switch (codeStyle) {
			case CodeStyle.FUNCTIONAL:
			//case 1; // does not work
				// esier to read and follow logic
				makeRdfConnectionTsGenFunctional(schemeDomainPath);
				break;
			case CodeStyle.TRY_RESOURCE:
				// compressed code style
				makeRdfConnectionTsGenTryResource(schemeDomainPath);
				break;
		}
		
		System.out.println("makeRdfConnectionTsGeneral out "); // info, todo comment out
		
	}
	
	/*
	** example of try resource style
	** https://jena.apache.org/documentation/rdfconnection/
	** 
	*/ 
	private static void makeRdfConnectionTsGenTryResource(String schemeDomainPath) {
		
		System.out.println("makeRdfConnectionTsGenTryResource in "); // info, todo comment out
		
		String dataInputPath = new String("src/general_ts_rdf/input/data.ttl"); // science specialist data
		
		// this code section very similar to documentation example
		// <todo: try RDFRemoteConnection instead. >
		try ( RDFConnection conn = RDFConnection.connect(schemeDomainPath) ) {
			conn.load(dataInputPath);
			conn.querySelect("SELECT DISTINCT ?s { ?s ?p ?o }", (qs) -> {
				Resource subject = qs.getResource("s") ;
				System.out.println( "Subject: " + subject ) ;
			}) ;
			
		} // <todo: consider catch statement here? but no catch shown in documentation for this style, >
		
		System.out.println("makeRdfConnectionTsGenTryResource out "); // info, todo comment out
		
		/*
		** Success! 
		** with schemeDomainPath = http://localhost:8080/fuseki/helloWorld
		** 
		**
		** ConnectionRdfTsGeneral in 
		** makeRdfConnectionTsGeneral in 
		** makeRdfConnectionTsGeneral codeStyle is TRY_RESOURCE
		** makeRdfConnectionTsGenTryResource in
		** Subject: http://agw.org/people/citizen-candlestickmaker
		** Subject: http://agw.org/people/citizen-biologist
		** Subject: _:079933eeeb609d75a8ccb71c8d74dbec
		** Subject: _:6e32fbf27742036946892c4c4e5d15ae
		** Subject: _:f948f4913965874f7cfc2351e6e429af
		** Subject: _:027cdee73e30eec6459afd1f581449a8
		** Subject: http://agw.org/people/citizen-baker
		** Subject: http://agw.org/people/citizen-physicist
		** Subject: _:bc2c080cf360b9168867d50a3507267d
		** Subject: _:241a5fef77671d8d1241eb0e8945fec0
		** Subject: http://agw.org/people/citizen-chemist
		** Subject: http://agw.org/people/citizen-butcher
		** makeRdfConnectionTsGenTryResource out
		** makeRdfConnectionTsGeneral out
		** ConnectionRdfTsGeneral out
		** 		
		*/
		
	}
	
	/*
	** Functional style RDFConnection
	*/
	private static void makeRdfConnectionTsGenFunctional(String schemeDomainPath) {
		
		System.out.println("makeRdfConnectionTsGenFunctional in "); // info, todo comment 
		
		String dataInputPath = new String("src/general_ts_rdf/input/data1.ttl"); // Rub a dub dub, data1

		//System.out.println("Stub <todo: create functional general rdf triple store connection implementation. > ");
		try {
			
			RDFConnection conn = RDFConnection.connect(schemeDomainPath) ;
			conn.load(dataInputPath) ;
			QueryExecution qExec = conn.query("SELECT DISTINCT ?s { ?s ?p ?o }") ;
			ResultSet rs = qExec.execSelect() ;
			while (rs.hasNext()) {
				QuerySolution qs = rs.next() ;
				Resource subject = qs.getResource("s") ;
				System.out.println("Subject: " + subject) ;
			}
			qExec.close() ;
			conn.close() ;
			
		} catch (QueryException e) {
			System.err.println("QueryException is " + e);
		} catch (Exception e) {
			System.err.println("Uknown Exception is " + e);
		}
		
		System.out.println("makeRdfConnectionTsGenFunctional out "); // info, todo comment out
		
		/*
		** Success! 
		** with schemeDomainPath = http://localhost:8080/fuseki/helloWorld
		** 
		**
		** ConnectionRdfTsGeneral in 
		** makeRdfConnectionTsGeneral in 
		** makeRdfConnectionTsGeneral codeStyle is FUNCTIONAL
		** makeRdfConnectionTsGenFunctional in
		** Subject: http://agw.org/people/citizen-candlestickmaker
		** Subject: _:70d84bbb674ff1ba7daeeed860b2907d
		** Subject: _:b0cebfb8f89fe69fa5c5f0654a16ec54
		** Subject: http://agw.org/people/citizen-baker
		** Subject: _:a63edf3e4647c23c418d26dce781680b
		** Subject: http://agw.org/people/citizen-butcher
		** makeRdfConnectionTsGenFunctional out
		** makeRdfConnectionTsGeneral out
		** ConnectionRdfTsGeneral out
		*/
		
	}
	
}