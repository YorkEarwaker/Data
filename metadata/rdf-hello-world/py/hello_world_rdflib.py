import rdflib

g = rdflib.Graph()

# Define URI's for resources
co2 = rdflib.URIRef('http://www.anthropogenicglobalwarming.com/co2')
observation = rdflib.URIRef('http://www.anthropogenicglobalwarming/observation')

# Add triple to the graph
g.add((co2, observation, rdflib.Literal('21 Sep. 2024 422.19 ppm')))

# Serialize the graph to a file (e.g., in RDF/XML format)
g.serialize('hello_world_rdflib.rdf', format='xml')