# strings, double, dateTime, 
# rdflib Python; class rdflib.term.Literal(Lexical_or_value: Any, Lang: str | None = None, datatype: str | None = None, normalize: bool | None = None )
# w3     RDF 1.1; Literals Section https://www.w3.org/TR/rdf11-concepts#section-Graph-Literal
from rdflib import Literal, Namespace, URIRef
from rdflib.namespace import XSD

from datetime import datetime, timezone, date

name1_bear1 = Literal("Winnie-the-Pooh") # the name 'Winnie-the-Pooh', as a string
name2_bear1 = Literal("Pooh Bear") # the name 'Pooh Bear', as a string
name3_bear1 = Literal("Pooh") # the name 'Pooh', as a string

name1_bear2 = Literal("Paddington Bear") # the name 'Paddington Bear', as a string
name2_bear2 = Literal("Paddington Brown") # the name 'Paddington Brown', as a string
name3_bear2 = Literal("Paddington") # the name 'Paddington', as a string

age = Literal(9, datatype=XSD.integer) # the number 9, as an integer

isim = Literal("Jurji", lang="ar") # the Arabic verson of the name 'George'
onoma = Literal("Georgios", lang="el") # the Greek version of the name 'George'
name = Literal("George", lang="en") # the name 'George, as an English string
nombre = Literal("Jorge", lang="es") # the Spanish version of the name 'George'
Nām =  Literal("Jarj", lang="hi") # the Hindi version of the name 'George'
Nâm = Literal("Jūrjis", lang="fa") # the Farsi version of the name 'George'
nom = Literal("Géorge", lang="fr") # the French version of the name 'George'
namae = Literal("Jōji", lang="ja") # the Japanese version of the name 'George'
nomen = Literal("Georgius", lang="la") # the Latin version of the name 'George'
imie = Literal("Grzegorz", lang="pl") # the Polish version of the name 'George'
nome = Literal("Jorge", lang="pt") # the Portuguese version of the name'George'
imya = Literal("Dzhordzh", lang="ru") # the Russian version of the name 'George'
ad = Literal("Yörji)", lang="tr") # the Turkish version of the name 'George'
# im'ya also in Ukrainian
nazva = Literal("Heorhiy", lang="uk") # the Ukrainian version of the name 'George'

GEO = Namespace("http://www.opengis.net/ont/geosparql#")

geojson_geometry = Literal(
    '''{"type": "Point", "coordinates": [-83.38,33.95]}''',
    datatype = GEO.geoJSONLiteral
)

# ##
# hashing of Literals are done based on the lexical form

# clear - strings differ,
print(Literal('01') != Literal('1'))  # prints True

# but with data-type they get normalized
print(Literal('01', datatype=XSD.integer) != Literal('1', datatype=XSD.integer)) # prints False

# unless disabled
print(Literal('01', datatype=XSD.integer, normalize=False) != Literal('1', datatype=XSD.integer)) # prints True

# value based on comparision is possible
print(Literal('01', datatype=XSD.integer).eq(Literal('1', datatype=XSD.float))) # prints True

# ##
# eq also provides support for basic Python types

# fine - int compatible with xsd:integer
print(Literal(1).eq(1)) # prints True

# fine - str compatible with plain-literals
print(Literal('a').eq('b')) # prints False

# fine - str cmopatible with xsd:string
print(Literal('a', datatype=XSD.string).eq('a')) # prints True

# not fine, int incompatible with plain-literal, 
print(Literal('a').eq(1)) # prints NotImplemented

# ##
# Greater-than/less-than ordering comparisons are also done in value space, when compatible datatypes are used.
# Incompatible datatypes are ordered by DT, or by lang-tag.
# For other nodes the ordering is None < BNode < URIRef < Literal
# Any comparison with non-rdblib Node are "NotImplemented" In PY3 this is an error.

lit2006 = Literal('2006-01-01', datatype=XSD.date)

print(lit2006) # prints, 2006-01-01

lit2006.toPython()

print(lit2006) # prints, 2006-01-01

print(datetime.date) # prints, <method 'date' of 'datetime.datetime' objects>

print(date.fromisoformat('2006-01-01')) # prints, 2006-01-01

print(lit2006 < Literal('2007-01-01, datatype=XSD.date')) # prints True

# utcnow() and utcfromtimestamp() now depricated? 
# https://blog.miguelgrinberg.com/post/it-s-time-for-a-change-datetime-utcnow-is-now-deprecated
print(Literal(datetime.now(tz=None)).datatype) # prints, http://www.w3.org/2001/XMLSchema#dateTime

# by value
print(Literal(1) > Literal(2)) # prints, False

# by value
print(Literal(1) > Literal(2.0)) # prints, False

# by DT
print(Literal('1') > Literal(1)) # prints, True

# by lexical form
print(Literal('1') < Literal('1')) # prints, False

# by lang tag
print(Literal('a', lang="en") > Literal('a', lang='fr')) # prints, False

# by node-type
print(Literal(1) > URIRef('foo')) # prints, True

# invalid syntax, so commented out
# print(Literal(1)__gt__(2.0)) # prints NotImplemented, eats NotImplemented and throws TypeError py3k

# other language things from literals.
language_code_names_standard = Literal("ISO 639-1:2002, Codes for the representation of names of languages—Part 1: Alpha-2 code")
lexilogos = URIRef("https://www.lexilogos.com/english/")


