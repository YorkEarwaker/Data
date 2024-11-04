from rdflib import Namespace, Graph, Dataset, URIRef, Literal
from rdflib.namespace import RDF, FOAF
# from rdflib.plugins.stores.sparlqlstore import SPARQLUpdateStore # suggested not to use, Use Dataset instead
from rdflib.plugins.stores.sparqlconnector import SPARQLConnector
from rdflib.graph import DATASET_DEFAULT_GRAPH_ID as default

# Fuseki in memory useage, 
# Fuseki apache-jena-fuseki-5.2.0 war to Tomcat
# <todo: how create an in memory dataset (store) in Fuseki with rdflib>
# <todo: consider wrapping call to command line via Python to create Fuseki in mem ds>

schemeDomainPath = "http://localhost:8080/fuseki" # localhost .war deoployed to Tomcat
dataSetInstanceEs = "/holaMundo" # <todo: try to create programatically via api not via Fuseki cmdln or UI>
dataSetInstanceEn = "/helloWorld" # already created via Fuseki war UI, pre populated with tutle dataset
dataSetUrl = schemeDomainPath + dataSetInstanceEn
sparqlEndpoint = dataSetUrl + "/sparql"
sparqlUpdate = dataSetUrl + "/update"
graphStore = dataSetUrl + "/data"

userName = ""
passWord = ""

# Circular issue with accessing Fuseki
# Graph requires Store argument i.e. Fuseki but Fuseki is not a store supported OOTB

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

# Graph
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.html#rdflib.graph.Graph
# 

# Store
# https://rdflib.readthedocs.io/en/latest/apidocs/rdflib.plugins.stores.html
# no default Fuseki plugin store support
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.html#rdflib.store.Store

# SPARQLConnector
# https://rdflib.readthedocs.io/en/latest/apidocs/rdflib.plugins.stores.html#module-rdflib.plugins.stores.sparqlconnector
# this class deals with nitty gritty details of talking to a SPARQL server

# <todo: can connection be used as store value to graph, g = Graph(connection, identifier="some-string")>
connection = SPARQLConnector( dataSetUrl, username = userName, password = passWord )

query = """SELECT ?s ?p ?o WHERE { ?s ?p ?o }"""

# <todo: exception handling here HTTPError>
result = connection.query(query) # success!

for s, p, o in result :
    print(f"subject {s, p, o}")

# \x5cdebugpy\x5cadapter/../..\x5cdebugpy\x5clauncher' '51740' '--' 'C:\x5cUsers\x5cyorke\x5cDocuments\x5cdev\x5cexamples\x5cdata\x5cmetadata\x5crdf-code-samples\x5crdflib\x5cpersistence\x5cpersist_usage_fuseki.py' subject (rdflib.term.URIRef('http://agw.org/people/citizen-candlestickmaker'), rdflib.term
# .URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.Literal('Citizen CandleStickMaker'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-candlestickmaker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name'), rdflib.term.Literal('Anthropogenic Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-candlestickmaker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib.term.Literal('Wicks'))        
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-candlestickmaker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b0'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-candlestickmaker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b1'))
# subject (rdflib.term.BNode('b2'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b2'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Physicist'))
# subject (rdflib.term.BNode('b2'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-biologist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.Literal('Citizen Biologist'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-biologist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name'), rdflib.term.Literal('Anthropogenic 
# Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-biologist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib.term.Literal('Fungi'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-biologist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b3'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-biologist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b4'))
# subject (rdflib.term.BNode('b5'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b5'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Physicist'))
# subject (rdflib.term.BNode('b5'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b6'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b6'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Butcher'))
# subject (rdflib.term.BNode('b6'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b0'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b0'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Candlestick Maker'))
# subject (rdflib.term.BNode('b0'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b3'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b3'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Biologist'))
# subject (rdflib.term.BNode('b3'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b4'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b4'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Biologist'))
# subject (rdflib.term.BNode('b4'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b1'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b1'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Candlestick Maker'))
# subject (rdflib.term.BNode('b1'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-baker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.Literal('Citizen Baker'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-baker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name'), rdflib.term.Literal('Anthropogenic Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-baker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib.term.Literal('Buns'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-baker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b7'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-baker'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b8'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-physicist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.Literal('Citizen Physicist'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-physicist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name'), rdflib.term.Literal('Anthropogenic 
# Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-physicist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib.term.Literal('Strange'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-physicist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b2'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-physicist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNode('b5'))
# subject (rdflib.term.BNode('b9'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b9'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Butcher'))
# subject (rdflib.term.BNode('b9'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b10'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b10'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Chemist'))
# subject (rdflib.term.BNode('b10'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b7'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b7'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Baker'))
# subject (rdflib.term.BNode('b7'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b8'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b8'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Baker'))
# subject (rdflib.term.BNode('b8'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.BNode('b11'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#honorific-prefix'), rdflib.term.Literal('Mr'))
# subject (rdflib.term.BNode('b11'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#family-name'), rdflib.term.Literal('Chemist'))
# subject (rdflib.term.BNode('b11'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#given-name'), rdflib.term.Literal('Citizen'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-chemist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.Literal('Citizen Chemist'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-chemist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name'), rdflib.term.Literal('Anthropogenic Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-chemist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib..term.Literal('ElMental'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-chemist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNNode('b10'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-chemist'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNNode('b11'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-butcher'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#fn'), rdflib.term.LLiteral('Citizen Butcher'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-butcher'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#organization-name')), rdflib.term.Literal('Anthropogenic Global Warming'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-butcher'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#nickname'), rdflib..term.Literal('Links'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-butcher'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNNode('b6'))
# subject (rdflib.term.URIRef('http://agw.org/people/citizen-butcher'), rdflib.term.URIRef('http://www.w3.org/2006/vcard/ns#n'), rdflib.term.BNNode('b9'))