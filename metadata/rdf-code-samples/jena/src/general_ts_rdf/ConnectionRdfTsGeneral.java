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
	
	// tried multiple different combination of path than show here, 
	// <todo: consider test class for comprehensive path test, but would be deployment and environment dependent>
	//static final String localWarFuseki = new String("http://localhost:8080/fuseki/#/dataset/helloWorld/query"); // org.apache.jena.query.QueryException: Endpoint returned Content-Type: text/html which is not recognized for SELECT queries.
	static final String localWarFuseki = new String("http://localhost:8080/fuseki/#/dataset/helloWorld/query?force=true"); //?force=true, from varoius Stacktoverflow.com postings, still exception thrown
	static final String localCmdFuseki = new String("http://localhost:3030/dataset"); // <todo: create cmdline local instance>
	
	public static void main(String args[]) {
		
		System.out.println("ConnectionRdfTsGeneral in ");
		
		// Comment out one or other instance to toggle coding style
		makeRdfConnectionTsGeneral(CodeStyle.TRY_RESOURCE);
		
		makeRdfConnectionTsGeneral(CodeStyle.FUNCTIONAL);
		
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
	** Remediation plan. 1.
	** Command line instance of Fuseki,
	** Create Fuseki configuration file e.g my_fuseki_config.ttl
	** config file to specifically list RDF datasets
	** config file to specify ; services, endpionts, datasets for this specific Fuseki instance.
	** 
	** Remdiation plan. 2.
	** Create new .war file when this issue has been resolved.
	** Include in new .war file configuration file
	** and any other changes TBD requried.
	*/ 
	private static void makeRdfConnectionTsGenTryResource(String schemeDomainPath) {
		
		System.out.println("makeRdfConnectionTsGenTryResource in "); // info, todo comment out
		
		String dataInputPath = new String("src/general_ts_rdf/input/data.ttl");
		
		// this code section very similar to documentation example
		// <todo: try RDFRemoteConnection instated!>
		try ( RDFConnection conn = RDFConnection.connect(schemeDomainPath) ) {
			conn.load(dataInputPath);
			conn.querySelect("SELECT DISTINCT ?s { ?s ?p ?o }", (qs) -> {
				Resource subject = qs.getResource("s") ;
				System.out.println( "Subject: " + subject ) ;
			}) ;
			
		}
		
		System.out.println("makeRdfConnectionTsGenTryResource out "); // info, todo comment out
		
		/*
		** Currently returns Exception
		** Likely cause default configuration of Fuseki .war file
		** Poor documentation on end to end usage. That is not joined up.
		** 
		** In exception stacktrace had to add and additional '\' to '*\/*;q=0.1]}' for this comment section.
		** 
		** ConnectionRdfTsGeneral in 
		** makeRdfConnectionTsGeneral in 
		** makeRdfConnectionTsGeneral codeStyle is TRY_RESOURCE
		** makeRdfConnectionTsGenTryResource in
		** Exception in thread "main" org.apache.jena.query.QueryException: Endpoint returned Content-Type: text/html which is not recognized for SELECT queries.
		** Status code 200 OK, Method GET, Request Headers: {Accept=[application/sparql-results+json, application/sparql-results+xml;q=0.9, text/tab-separated-values;q=0.7, text/csv;q=0.5,application/json;q=0.2,application/xml;q=0.2,*\/*;q=0.1]}
		** Body (extracted with charset UTF-8): <!DOCTYPE html>
		** <!--
		**    Licensed to the Apache Software Foundation (ASF) under one or more
		**    contributor license agreements.  See the NOTICE file distributed with
		**    this work for additional information regarding copyright ownership.
		**    The ASF licenses this file to You under the Apache License, Version 2.0
		**    (the "License"); you may not use this file except in compliance with
		**    the License.  You may obtain a copy of the License at
		** 
		**        http://www.apache.org/licenses/LICENSE-2.0
		** 
		**    Unless required by applicable law or agreed to in writing, software
		**    distributed under the License is distributed on an "AS IS" BASIS,
		**    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		**    See the License for the specific language governing permissions and
		**    limitations under the License.
		** -->
		** <html lang="en">
		**   <head>
		**     <meta charset="utf-8">
		**     <meta http-equiv="X-UA-Compatible" content="IE=edge">
		**     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		**     <l...
		**         at org.apache.jena.sparql.exec.http.QueryExecHTTP.raiseException(QueryExecHTTP.java:488)
		**         at org.apache.jena.sparql.exec.http.QueryExecHTTP.execRowSet(QueryExecHTTP.java:204)
		**         at org.apache.jena.sparql.exec.http.QueryExecHTTP.select(QueryExecHTTP.java:164)
		**         at org.apache.jena.sparql.exec.QueryExecutionAdapter.execSelect(QueryExecutionAdapter.java:108)
		**         at org.apache.jena.sparql.exec.QueryExecutionCompat.execSelect(QueryExecutionCompat.java:95)
		**         at org.apache.jena.rdfconnection.RDFConnection.lambda$querySelect$2(RDFConnection.java:223)
		**         at org.apache.jena.system.Txn.exec(Txn.java:77)
		**         at org.apache.jena.system.Txn.executeRead(Txn.java:115)
		**         at org.apache.jena.rdfconnection.RDFConnection.querySelect(RDFConnection.java:221)
		**         at general_ts_rdf.ConnectionRdfTsGeneral.makeRdfConnectionTsGenTryResource(ConnectionRdfTsGeneral.java:72)
		**         at general_ts_rdf.ConnectionRdfTsGeneral.makeRdfConnectionTsGeneral(ConnectionRdfTsGeneral.java:49)
		**         at general_ts_rdf.ConnectionRdfTsGeneral.main(ConnectionRdfTsGeneral.java:22)
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
		}
		
		System.out.println("makeRdfConnectionTsGenFunctional out "); // info, todo comment out
		
		/*
		** Currently returns Exception - note call stack is different for this style
		** Likely cause default configuration of Fuseki .war file
		** Poor documentation on end to end usage. That is not joined up.
		** 
		** In exception stacktrace had to add and additional '\' to '*\/*;q=0.1]}' for this comment section.
		**
		** ConnectionRdfTsGeneral in 
		** makeRdfConnectionTsGeneral in 
		** makeRdfConnectionTsGeneral codeStyle is FUNCTIONAL
		** makeRdfConnectionTsGenFunctional in
		** Exception is org.apache.jena.query.QueryException: Endpoint returned Content-Type: text/html which is not recognized for SELECT queries.
		** Status code 200 OK, Method GET, Request Headers: {Accept=[application/sparql-results+json, application/sparql-results+xml;q=0.9, text/tab-separated-values;q=0.7, text/csv;q=0.5,application/json;q=0.2,application/xml;q=0.2,*\/*;q=0.1]}
		** Body (extracted with charset UTF-8): <!DOCTYPE html>
		** <!--
		**    Licensed to the Apache Software Foundation (ASF) under one or more
		**    contributor license agreements.  See the NOTICE file distributed with
		**    this work for additional information regarding copyright ownership.
		**    The ASF licenses this file to You under the Apache License, Version 2.0
		**    (the "License"); you may not use this file except in compliance with
		**    the License.  You may obtain a copy of the License at
		** 
		**        http://www.apache.org/licenses/LICENSE-2.0
		** 
		**    Unless required by applicable law or agreed to in writing, software
		**    distributed under the License is distributed on an "AS IS" BASIS,
		**    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		**    See the License for the specific language governing permissions and
		**    limitations under the License.
		** -->
		** <html lang="en">
		**   <head>
		**     <meta charset="utf-8">
		**     <meta http-equiv="X-UA-Compatible" content="IE=edge">
		**     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		**     <l...
		** makeRdfConnectionTsGenFunctional out 
		** makeRdfConnectionTsGeneral out
		** ConnectionRdfTsGeneral out
		*/
		
	}
	
}