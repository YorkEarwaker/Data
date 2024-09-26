from rdflib import URIRef, Graph
from rdflib.namespace import RDF, FOAF

g = Graph()

for s, p, o in g:
    if not (s, p, o) in g:
        raise Exception("Iterator / Contains Protocols are Broken!!")

cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")

g.add((cit_sci, RDF.type, FOAF.Person))

if (cit_sci, RDF.type, FOAF.Person) in g:
    print("This graph knows that Citizen Scientist is a person!")

if (cit_sci, None, None) in g:
    print("This graph contains triples about Citizen Scientist!")