from rdflib import Graph, Literal, RDF, URIRef
# rdflib knows about quite a fiew popular namespaces, like W3C ontologies, schema.org, etc
from rdflib.namespace import FOAF, XSD

# Create a Graph
g = Graph()

# Create an RDF URI node to use as the subject for multiple triples
cit_sci = URIRef("http://anthropogenicglobalwarming.org/citizen-scientist")

# Add triples using store's add() method.
g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.nick, Literal("Zensci", lang="en")))
g.add((cit_sci, FOAF.name, Literal("Citizen Scientist")))
g.add((cit_sci, FOAF.mbox, URIRef("mailto:citizen.scientist@anthropogenicglobalwarming.org")))

# Add another person
cit_dev = URIRef("http://anthropogenicglobalwarming.org/citizen-developer")

# Add triples using store's add() method.
g.add((cit_dev, RDF.type, FOAF.Person))
g.add((cit_dev, FOAF.nick, Literal("Bitwise", datatype=XSD.string)))
g.add((cit_dev, FOAF.name, Literal("Citizen Developer")))
g.add((cit_dev, FOAF.mbox, Literal("mailto:citizen.developer@anthropogenicglobalwarming.org")))

# Add another person
cit_arc = URIRef("http://anthropogenicglobalwarming.org/citizen-architect")

# Add triples using store's add() method.
g.add((cit_arc, RDF.type, FOAF.Person))
g.add((cit_arc, FOAF.nick, Literal("Arch", lang="en"))) # a literal can only have one of datatype or language, not both.
g.add((cit_arc, FOAF.nick, Literal("Arco", lang="es")))
g.add((cit_arc, FOAF.nick, Literal("Arc", lang="fr")))
g.add((cit_arc, FOAF.nick, Literal("Bogen", lang="gd")))
g.add((cit_arc, FOAF.nick, Literal("Āchi", lang="jp")))
g.add((cit_arc, FOAF.nick, Literal("Kemer", lang="tr")))
g.add((cit_arc, FOAF.nick, Literal("Арка", lang="ru")))
g.add((cit_arc, FOAF.nick, Literal("Арка", lang="ua")))
g.add((cit_arc, FOAF.name, Literal("Citizen Architect")))
g.add((cit_arc, FOAF.mbox, Literal("mailto:citizen.architect@anthropogenicglobalwarming.org")))

# Iterate over triples in store and print them out.
print("--- printing raw triples ---")
for s, p, o in g:
    print((s, p, o))
    
# For each foaf:Person in the store, print out their mbox property's value.
print("--- printing mboxes ---")
for person in g.subjects(RDF.type, FOAF.Person):
    for mbox in g.objects(person, FOAF.mbox):
        print(mbox)
        
# Bind the FOAF namespace to a prefix for more readable output
g.bind("foaf", FOAF)

# print all the data in the Notation3 format
print("--- printing mboxes ---")
print(g.serialize(format='n3'))