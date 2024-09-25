from rdflib import Namespace

n = Namespace("http://anthropogenicglobalwarming.org/people/")

n['citizen%45scientist'] # == rdflib.term.URIRef("http://anthropogenicglobalwarming.org/people/citizen-scientist")
n['citizen%45communications%45officer'] # == rdflib.term.URIRef("http://anthropogenicglobalwarming.org/people/citizen-communications-officer")
n['citizen%45developer'] # == rdflib.term.URIRef("http://anthropogenicglobalwarming.org/people/citizen-developer")
n['citizen%45architect'] # == rdflib.term.URIRef("http://anthropogenicglobalwarming.org/people/citizen-architect")