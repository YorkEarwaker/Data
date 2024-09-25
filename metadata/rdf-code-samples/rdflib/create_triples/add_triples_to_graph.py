from rdflib import Graph, URIRef, Literal, BNode
from rdflib.namespace import FOAF, RDF

g = Graph()
g.bind("foaf", FOAF)

cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")
cit_com_fcr = BNode() # a GUID is generated

name = Literal("Citizen Scientist")
age = Literal(24)

g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.name, name))
g.add((cit_sci, FOAF.age, age))
g.add((cit_sci, FOAF.knows, cit_com_fcr))
g.add((cit_com_fcr, RDF.type, FOAF.Person))
g.add((cit_com_fcr, FOAF.name, Literal("Citizen Communications Officer")))

print(g.serialize())

