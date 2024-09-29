# IRI Internatonal Resource Identifier
# rdflib Python; class rdflib.term.URIRef(value: str, base: str | None = None )
# w3     RDF 1.1; IRI Section https://www.w3.org/TR/rdf11-concepts#section-IRIs

from rdflib import URIRef

# will fail on run
# uri = URIRef() # requires and argument, str

# Traceback (most recent call last): File "<stdin>", line 1, in <module>

# TypeError: __new__() missing 1 required positional argument: 'value'

uri = URIRef('')

print(uri) # rdflib.term.URIRef(''), empty string is printed

uri = URIRef('http://anthropogenicglobalwarming.org')

print(uri) # rdflib.term.URIRef('http://anthropogenicglobalwarming.org')

print(uri.n3()) # '<http://anthropogenicglobalwarming.org>'