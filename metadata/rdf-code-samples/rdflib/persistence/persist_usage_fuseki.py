from rdflib import Namespace, Graph, Dataset, URIRef, Literal
from rdflib.namespace import RDF, FOAF
# from rdflib.plugins.stores.sparlqlstore import SPARQLUpdateStore # suggested not to use, Use Dataset instead
from rdflib.graph import DATASET_DEFAULT_GRAPH_ID as default

# Fuseki in memory useage, 
# <todo: how create an in memory dataset (store) in Fuseki with rdflib>

schemeDomainPath = "http://localhost:8080/fuseki" # localhost .war deoployed to Tomcat
dataSetInstance = "/holaMundo" # <todo: try to create programatically via api not via Fuseki cmdln or UI>
dataSetUrl = schemeDomainPath + dataSetInstance
sparqlEndpoint = dataSetUrl + "/sparql"
sparqlUpdate = dataSetUrl + "/update"
graphStore = dataSetUrl + "/data"

# Plugin stores
# https://rdflib.readthedocs.io/en/stable/plugin_stores.html 
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.plugins.stores.html#rdflib.plugins.stores.sparqlstore.SPARQLStore
# recommend using Dataset instead, which is motivated by the SPARQL 1.1
# Fuseki/TDB has a flag for specifying that the default graph is the union of all graphs 
# (tdb:unionDefaultGraph in the Fuseki config).
# Warning. By default the SPARQL Store does not support blank-nodes!
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.plugins.stores.html#rdflib.plugins.stores.sparqlstore.SPARQLUpdateStore
# In favor of the SPARQL 1.1 motivated Dataset, we advise against using this with ConjunctiveGraphs, 
# as it reads and writes from and to the “default graph”. 
# Exactly what this means depends on the endpoint and can result in confusion.
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.html#conjunctive-graph
# Warning. ConjunctiveGraph depricated. Use Dataset instead. 

# Dataset
# https://www.w3.org/TR/sparql11-query/#rdfDataset
# An RDF Dataset represents a collection of graphs. The default graph and zero or more named graphs.
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.html#rdflib.Dataset
# Quads. Also Triples? assume so at this time.






