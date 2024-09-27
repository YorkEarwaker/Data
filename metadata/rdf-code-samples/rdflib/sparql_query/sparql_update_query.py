from rdflib import Graph

# Create a Graph, add in some test data
g = Graph()

# instantiate graph with data
g.parse(
    data = """
        <x:> a <c:> .
        <y:> a <c:> .
    """,
    format = "turtle"
)

# Select all the things (s) that are of type (rdf:type) c:
type_things_query = """
SELECT ?s
WHERE {
    ?s a <c:>
}
"""

# so select query
qres = g.query(type_things_query)

# output graph change
for row in qres:
    print(f"{row.s}")
    
# Add in a new triple using SPARQl UPDATE
type_thing_update = """
INSERT DATA {
    <z:> a <c:>
}
"""

# do update
g.update(type_thing_update)

# do select query
qres = g.query(type_things_query)

# output graph change
print("After update:")
for row in qres:
    print(f"{row.s}")

# Change type of <y:> from <c:> to <d:>
type_thing_U_D = """
DELETE { <y:> a <c:> }
INSERT { <y:> a <d:> }
WHERE  { <y:> a <c:> }
"""

# do update
g.update(type_thing_U_D)

# Select subjects and objects
type_thing_sub_obj = """
SELECT ?s ?o
WHERE {
?s a ?o
}
"""

# do select query
qres = g.query(type_thing_sub_obj)

# output graph change
print("After second update:")
for row in qres:
    print(f"{row.s} a {row.o}")
