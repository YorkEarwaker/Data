import rdflib

g = rdflib.Graph()

# select from a remote service end point
# limit the response to thirteen (13) things
# DBPedias SPARQL end point
remote_service_end_point_query = """
SELECT ?s ?o
WHERE {
    SERVICE <https://dbpedia.org/sparql> {
        ?s a ?o
    }
}
LIMIT 13
"""

# do remote service end point query
qres = g.query(remote_service_end_point_query)

# print response
for row in qres:
    print(f"{row.s} a {row.o}")