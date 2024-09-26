from rdflib import Graph
from rdflib.namespace import FOAF

g = Graph()

# get the data
g.parse("http://danbri.livejournal.com/data/foaf") # LiveJournal data not fully FOAF confromant

# for every foaf:member_name, add foaf:name and remove foaf:member_name
for s, p, o in g.triples((None, FOAF['member_name'], None)):
    g.add((s, FOAF['name'], o))
    g.remove((s, FOAF['member_name'], o))

# Error is produced on Run and Debug in Visual Studio Code
# The editor could not be opened because the file was not found.
# C: > A > 31 > s > Modules > C pyexpat.c

# print(g.serialize())