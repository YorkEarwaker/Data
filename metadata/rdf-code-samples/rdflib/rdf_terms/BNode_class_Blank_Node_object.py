# Node with no IRI Internatonal Resource Identifier, unamed node
# rdflib Python; class rdflib.term.Bnode(value: str | _sn_gen: Callable[[], str] | Generator | None = None, _prefix: str = 'N')
# w3     RDF 1.1; Blank Node Section https://www.w3.org/TR/rdf11-concepts#section-blank-nodes

from rdflib import BNode

bn = BNode()

print(bn) # N428da27e1fcf431bb7c8422c759e957d

print(bn.n3()) # _:N428da27e1fcf431bb7c8422c759e957d