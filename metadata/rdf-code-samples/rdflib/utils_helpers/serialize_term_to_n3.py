from rdflib import Graph, URIRef, Literal
from rdflib.namespace import FOAF

# A URIRef
person = URIRef("http://xmlns.com/foaf/0.1/Person")
print(person.n3())

# Simplifying the output with a namespace prefix:
g = Graph()
g.bind("foaf", FOAF)

print(person.n3(g.namespace_manager))

# A typed literal
l = Literal(2)
print(l.n3())

# Simplifying the output with a namespace prefix
# XSD is built in, so no need to bind() it!
print(l.n3(g.namespace_manager)) # print was not in docs so likely some other defects a consequence of docs errors