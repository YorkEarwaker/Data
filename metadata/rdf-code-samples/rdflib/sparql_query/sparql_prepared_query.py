from rdflib import Graph, URIRef, Literal
from rdflib.namespace import RDF, FOAF
from rdflib.plugins.sparql import prepareQuery

# The query statement
person_knows_query = """
SELECT ?s
WHERE {
    ?person foaf:knows ?s .
}
"""

# the prapared query
q = prepareQuery(
    person_knows_query,
    initNs = { "foaf": FOAF }
)


g = Graph()
#g.parse("foaf.rdf") # get some foaf data, from example on web
g.bind("foaf", FOAF)

# set up some foaf knows rdf things
cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")
cit_com_fcr = URIRef("http://anthropogenicglobalwarming.org/people/citizen-communications-officer")
cit_dev = URIRef("http://anthropogenicglobalwarming.org/people/citizen-developer")
cit_arc = URIRef("http://anthropogenicglobalwarming.org/people/citizen-architect")

name = Literal("Citizen Scientist")
age = Literal(24)

g.add((cit_sci, RDF.type, FOAF.Person))
g.add((cit_sci, FOAF.name, name))
g.add((cit_sci, FOAF.age, age))
g.add((cit_sci, FOAF.knows, cit_com_fcr))
g.add((cit_com_fcr, RDF.type, FOAF.Person))
g.add((cit_com_fcr, FOAF.name, Literal("Citizen Communications Officer")))
g.add((cit_dev, RDF.type, FOAF.Person))
g.add((cit_dev, FOAF.name, Literal("Citizen Developer")))
g.add((cit_dev, FOAF.knows, cit_sci))
g.add((cit_arc, FOAF.name, Literal("Citizen Architect")))
g.add((cit_arc, FOAF.knows, cit_dev))

tim = URIRef("http://www.w3.org/People/Berners-Lee/card#i")

g.add((cit_sci, FOAF.knows, tim))
g.add((tim, FOAF.knows, cit_com_fcr))
g.add((cit_arc, FOAF.knows, tim))
g.add((tim, FOAF.knows, cit_dev))

the_knower = tim

for row in g.query(q, initBindings={'person': the_knower}):
    print(f"{the_knower} knows {row}")