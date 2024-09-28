from rdflib import Graph, URIRef, Literal, BNode
from rdflib.namespace import FOAF, RDF

g = Graph()
g.bind("foaf", FOAF)

#Add demo data
cit_sci = URIRef("http://anthropogenicglobalwarming/people/citizen-scientist")

g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.name, Literal("Citizen Scientist")))
g.add((cit_sci, FOAF.age, Literal(38)))

# To get a single value, use 'value'
print(f"before set new value {g.value(cit_sci, FOAF.age)}")

# To change a single of value, use 'set'
g.set((cit_sci, FOAF.age, Literal(39)))
print(f"after set new value {g.value(cit_sci, FOAF.age)}")