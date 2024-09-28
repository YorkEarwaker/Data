from rdflib import Graph, URIRef, Literal, BNode
from rdflib.namespace import FOAF, RDF

# Python allows slicing arrays with a slice object, a triple of start, stop and step-size
# 2, 5, 8 are printed, in case below
for i in range(20)[2:9:3]:
    print(i) 
    
g = Graph()
g.bind("foaf", FOAF)

#Add demo data
cit_sci = URIRef("http://anthropogenicglobalwarming/people/citizen-scientist")
cit_dev = URIRef("http://anthropogenicglobalwarming/people/citizen-developer")
cit_com_fcr = URIRef("http://anthropogenicglobalwarming/people/citizen-communications-officer")
tim = URIRef("http://www.w3.org/People/Berners-Lee/card")
#jay = URIRef("http://www.w3.org/People/Kishigami/card") # not actual card address
#alexandra = URIRef("http://www.w3.org/People/Lacourba/card") # not actual card address

print(f"tim's card {tim}")
#print(f"jay's card {jay}")
#print(f"alexandra's card {alexandra}")

# https://www.w3.org/staff/

g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.name, Literal("Citizen Scientist")))
g.add((cit_sci, FOAF.age, Literal(38)))
g.add((cit_sci, FOAF.knows, cit_dev))

g.add((cit_dev, RDF.type, FOAF.Person))
g.add((cit_dev, FOAF.knows, cit_com_fcr))

g.add((cit_com_fcr, RDF.type, FOAF.Person))
g.add((cit_com_fcr, FOAF.knows, tim))

#g.add((tim, FOAF.knows, jay))
#g.add((jay, FOAF.knows, cit_dev))
#g.add((alexandra, FOAF.knows, cit_com_fcr))


print(g[:])
# same as
print(iter(g))

print(g[cit_sci])
# same as
print(g.predicate_objects(cit_sci))

print(g[cit_sci: FOAF.knows])
# same as
print(g.objects(cit_sci, FOAF.knows))

print(g[cit_sci: FOAF.knows: cit_dev]) # true
# same as
print((cit_sci, FOAF.knows, cit_dev) in g) # true

print(g[:FOAF.knows])
# same as
print(g.subject_objects(FOAF.knows))