# https://rdflib.readthedocs.io/en/latest/gettingstarted.html
from rdflib import Graph

# Create a Graph
g = Graph()

# Parse in an RDF file hosted on the Internet
g.parse("http://www.w3.org/People/Berners-Lee/card")

# Loop through each triple in the graph (subj, pred, obj)
for a_subject, a_predicate, an_object in g:
    # Check if there is at least one triple in the Graph
    if (a_subject, a_predicate, an_object) not in g:
        raise Exception("Strange that it is not! Someone should inform TBL pronto.")
        
# Print the number of "triples" in the Graph
print(f"Graph g has {len(g)} statements.")

# Print out the entire Graph in the RDF Turtle format
print(g.serialize(format="turtle"))