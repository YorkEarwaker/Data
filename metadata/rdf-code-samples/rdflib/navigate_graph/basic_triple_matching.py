from rdflib import Graph, Literal, URIRef, BNode
from rdflib.namespace import RDF, FOAF

g = Graph()

# some_foaf.ttl , assume TBL's is foaf.ttl when parsed
#g.parse("http://www.w3.org/People/Berners-Lee/card")

# ##
# set up some graph FOAF things as some_foaf.ttl thing
g.bind("foaf", FOAF)

cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")
cit_com_fcr = BNode() # a GUID is generated
cit_dev = URIRef("http://anthropogenicglobalwarming.org/people/citizen-developer")

name = Literal("Citizen Scientist")
age = Literal(24)

g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.name, name))
g.add((cit_sci, FOAF.name, Literal("Jar Jar Binks")))
g.add((cit_sci, FOAF.name, Literal("Peter Rabbit")))
g.add((cit_sci, FOAF.age, age))
g.add((cit_sci, FOAF.knows, cit_com_fcr))
g.add((cit_com_fcr, RDF.type, FOAF.Person))
g.add((cit_com_fcr, FOAF.name, Literal("Citizen Communications Officer")))
g.add((cit_dev, RDF.type, FOAF.Person))
g.add((cit_dev, FOAF.name, Literal("Citizen Developer")))
g.add((cit_dev, FOAF.age, Literal(43)))
g.add((cit_dev, FOAF.knows, cit_sci))

# ##
# The triple matching starts here

# find all subjects (s) of type (rdf:type) person (foaf:Person)
for s, p, o in g.triples((None, RDF.type, FOAF.Person)):
    print(f"{s} is a person")
    
# find all subjects of any type
for s, p, o in g.triples((None, RDF.type, None)):
    print(f"{s} is a {o}")
    
# create a graph
cit_sci_graph = Graph()

# add all triples with subject 'citizen-scientist'
cit_sci_graph += g.triples((cit_sci, None, None))

print(f"{len(cit_sci_graph)} is length of cit_sci_graph") # length output proves the triples have been added.

for s, p, o in cit_sci_graph.triples((None, FOAF.age, None)):
    print(f"{s} is {o} years old")

for person in g.subjects(RDF.type, FOAF.Person):
    print("{} is a person".format(person))

# get any name of bob
name = g.value(cit_sci, FOAF.name)

print(f"{name} is a name")

# get the one person that knows citizen-scientist and raise an exception if more are found
person = g.value(predicate=FOAF.knows, object=cit_sci, any=False)

print(f"{person} is a person who knowns {cit_sci}")

print(g.serialize())