from rdflib import Graph

g = Graph()
g.parse("example.nt")

print(len(g))
# prints: 3

import pprint

for stmt in g:
    pprint.pprint(stmt)