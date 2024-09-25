from rdflib import Graph

g = Graph()
g.parse("http://www.w3.org/People/Berners-Lee/card")
g.serialize(destination="tbl/timothy-berers-lee.ttl") # Turtle, turtle, ttl or turtle2, default for rdflib
# save the xml to the variable v, and save the file to disk
v = g.serialize(format="xml", destination="tbl/timothy-berers-lee.xml") # RDF?XML, xml or pretty-xml
g.serialize(format="json-ld", destination="tbl/timothy-berers-lee.json-ld") # JSON-LD, json-ld
g.serialize(format="nt", destination="tbl/timothy-berers-lee.nt") # N-Triples, ntriples, nt or nt11, 
g.serialize(format="n3", destination="tbl/timothy-berers-lee.n3") # Notation-3, n3
g.serialize(format="trig", destination="tbl/timothy-berers-lee.trig") # Trig, trig
# Studio Visuall Code; Exception: TriX serialization only makes sense for context-aware stores
#g.serialize(format="trix", destination="tbl/timothy-berers-lee.trix") # Trix, trix
# Studio Visuall Code; Exception: NQuads serialization only makes sense for context-aware stores!
#g.serialize(format="nquads", destination="tbl/timothy-berers-lee.nquads") # N-Quads, nquads

# But work as quads in Dataset this class is 'context-aware' i.e. has lables
# Therefore quad not triple, quad is triple + graph ID's
from rdflib import Dataset
d = Dataset()
d.parse("http://www.w3.org/People/Berners-Lee/card")
d.serialize(format="trix", destination="tbl/timothy-berers-lee.trix") # Trix, trix
d.serialize(format="nquads", destination="tbl/timothy-berers-lee.nquads") # N-Quads, nquads