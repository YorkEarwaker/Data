from rdflib import Graph, URIRef, Literal
from rdflib.namespace import RDF, FOAF # needs FOAF import for initNs to work

#import rdflib
#g = rdflib.Graph()
# g.parse("http://danbri.org/foaf.rdf#") # returned 403 Forbidden, no permission to view , 
# You don't have permission to access this resource.
# Apache/2.4.25 (Debian) Server at danbri.org Port 443

# g.bind("foaf", FOAF) # bing an RDFLib provided namespace to a prefix
# g.parse("http://www.w3.org/people/Berners-Lee/card") # get some FOAF data, appears to have no foaf:knows 

# set up foaf stuff
g = Graph()
g.bind("foaf", FOAF)

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

knows_query_with_prefix = """
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT DISTINCT ?aname ?bname
WHERE {
    ?a foaf:knows ?b .
    ?a foaf:name ?aname .
    ?b foaf:name ?bname .
}"""

print("query has prefix so does not require initNs={'foaf': FOAF}")
print(knows_query_with_prefix)
# query has prefix so does not require initNs={'foaf': FOAF}
qres = g.query(knows_query_with_prefix) # https://rdflib.readthedocs.io/en/stable/namespaces_and_bindings.html
for row in qres:
    print(f"{row.aname} knows {row.bname}")

knows_query_no_prefix = """
SELECT DISTINCT ?aname ?bname
WHERE {
    ?a foaf:knows ?b .
    ?a foaf:name ?aname .
    ?b foaf:name ?bname .
}"""

print("\nquery has no prefix so does require initNs={'foaf': FOAF}")
print(knows_query_no_prefix)
# query has no prefix so does require initNs={'foaf': FOAF} 
qres = g.query(knows_query_no_prefix, initNs={'foaf': FOAF}) # https://rdflib.readthedocs.io/en/stable/namespaces_and_bindings.html
for row in qres:
    print(f"{row.aname} knows {row.bname}")