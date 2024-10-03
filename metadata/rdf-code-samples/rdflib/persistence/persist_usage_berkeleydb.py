# ##
# Stores shipped with core REFLib
# Memory - not persistent!
# BerkeleyDB - on disk persistent via Python's berkelydb package, Oracle
# SPARQLStore - a read-only wrapper around a remote SPARQl Query endpoint
# SPARQLUpdateStore - a read-write wrapper around a remote SPARQL query/update endpoint pair
# ##
# Additional store impls available in RDFLib extension projects
# rdflib-sqlalchemy - a store which supports a wide-variety of RDBMS backends
# rdflib - leveldb - a store on top of Google's LevelDB key-value store
# rdflib-kyotocabinet - a store on top of the Kyoto Cabinet key-value store.

# RDFLib provideand abstract Store API for persistence of RDF and Notation 3.
# In most cases passing the name of the store to the Graph constructor is enough.

from rdflib import Graph, URIRef
# https://rdflib.readthedocs.io/en/stable/_modules/rdflib/plugin.html
# https://rdflib.readthedocs.io/en/stable/_modules/examples/berkeleydb_example.html
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.plugins.stores.html
# https://rdflib.readthedocs.io/en/stable/apidocs/rdflib.plugins.stores.html#module-rdflib.plugins.stores.berkeleydb
# not sure about this 
# https://rdflib.readthedocs.io/en/latest/_modules/rdflib/plugins/stores/berkeleydb.html
# 

# <todo: work in progress, BerkeleyDB no longer included by default with python 3.x, >
# <todo: consider download source code of BerkeleyDB and compile locally >
# <todo: consider other options for RDFLib like call to Jena TBD,  *** work on this option first case>
# <todo: continue with python db interaction with RDFLib after Jena, >
# <todo: consider other embedded and key value stores like SQLLite, LMDB, >
# see also ReadMe.txt in this directory

lmdb = URIRef("https://www.symas.com/lmdb")
lmdb_release = URIRef("https://lmdb.readthedocs.io/en/release/")
sqlite = URIRef("https://sqlite.org/")


graph = Graph(store = 'BerkeleyDB')

# Most stores offering on-disk persistence will need to be opened before reading or writing.
# When persisting a triplestore, rather than a ConjuntiveGraph quadstore, you need to specify an identifier with you can open the graph

graph = Graph('BerkeleyDB', identifier='agwgraph')

# rdflib docs example
# first time create a store:
# graph.open('/home/user/data/myRDFLibStore', create=True)
graph.open('C://dev/sandbox/data/rdflib/agwRDFLibStore', create=True)

# work with the graph:
agwdata = """
    PREFIX : <https://anthropogenicglobalwarming.org/>
    
    :a :b :c .
    :d :e :f .
    :d :g :h .
"""

graph.parse(data=agwdata, format="ttl")

# when finished
graph.close()

