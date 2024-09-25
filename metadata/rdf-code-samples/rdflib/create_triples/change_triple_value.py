from rdflib import Graph, URIRef, Literal
from rdflib.namespace import FOAF

g = Graph()

cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")

g.add((cit_sci, FOAF.age, Literal(42)))
print(f"Citizen Scientist is {g.value(cit_sci, FOAF.age)}")
# prints; Citizen Scientist is 42

g.set((cit_sci, FOAF.age, Literal(43))) # replaces 42 set above
print(f"Citizen Scientist is now {g.value(cit_sci, FOAF.age)}")
# prints; Citizen Scientist is now 43