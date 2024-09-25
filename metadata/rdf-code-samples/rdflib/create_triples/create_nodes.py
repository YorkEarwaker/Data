from rdflib import URIRef, BNode, Literal

cit_sci = URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")
cit_com_fcr = BNode() # a GUID is generated

name = Literal("Citizen Scientist") # passing a string
age = Literal(24) # passing a python int
height = Literal(76.5) # passing a python float