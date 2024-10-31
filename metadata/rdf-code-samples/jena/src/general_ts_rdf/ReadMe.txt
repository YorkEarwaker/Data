RDF Connection
https://jena.apache.org/documentation/rdfconnection/
JDoc 
https://jena.apache.org/documentation/javadoc/rdfconnection/org.apache.jena.rdfconnection/module-summary.html
Fuseki 1.1.1 JDoc
https://javadoc.io/doc/org.apache.jena/jena-fuseki/1.1.1/index.html

<todo: W3C links to SPARQL 1.1; query, update, graph store, protocol>
<todo: generic connection to datastore, preliminary fuseki, >
<todo: install other rdf triple store, .war file? with jetty? to separate from fuseki tomcat instance, >
<todo: access other rdf triple store through a generic Jena RDF Connection, >

## // tried multiple different combination of path than show here, 
## // <todo: consider test class for comprehensive path test, but would be deployment and environment dependent>
## //static final String localWarFuseki = new String("http://localhost:8080/fuseki/#/dataset/helloWorld/query"); // org.apache.jena.query.QueryException: Endpoint returned Content-Type: text/html which is not recognized for SELECT queries.
## //static final String localWarFuseki = new String("http://localhost:8080/fuseki/#/dataset/helloWorld/query?force=true"); //?force=true, from varoius Stacktoverflow.com postings, still exception thrown
	
## .war Success with endpoint; "http://localhost:8080/fuseki/helloWorld"
## helloWorld in memory dataset was created manually in fuseki UI 
## 
## ** Action plan. 1.
## ** Command line instance of Fuseki,
## ** Create Fuseki configuration file e.g my_fuseki_config.ttl 
## ** config file to specifically list RDF datasets
## ** config file to specify ; services, endpionts, datasets for this specific Fuseki instance.
## ** 
## ** Action plan. 2.
## ** Create new .war file 
## ** Include in new .war file new configuration file
## ** and any other changes TBD requried.
