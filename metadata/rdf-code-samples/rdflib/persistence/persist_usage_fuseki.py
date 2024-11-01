from rdflib import Namespace, Graph
from rdflib.namespace import RDF, FOAF
from rdflib.plugins.stores.sparlqlstore import SPARQLUpdateStore
from rdflib.graph import DATASET_DEFAULT_GRAPH_ID as default


# Fuseki in memory useage, 
# <todo: how create an in memory dataset (store) in Fuseki with rdflib>
# ConjunctiveGraph depricated. Use Dataset instead. 
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.html#rdflib.Dataset

schemeDomainPath = "http://localhost:8080/fuseki" # localhost .war deoployed to Tomcat
dataSetInstance = "/holaMundo" # <todo: try to create programatically via api not via Fuseki cmdln or UI>
dataSetUrl = schemeDomainPath + dataSetInstance
sparqlEndpoint = dataSetUrl + "/sparql"
sparqlUpdate = dataSetUrl + "/update"
graphStore = dataSetUrl + "/data"

# Plugin stores
# https://rdflib.readthedocs.io/en/stable/plugin_stores.html 








