@echo off

:: Set your cmd line java environment

:: From Jena README
::
::   Windows:
::    set JENA_HOME=\path\to\apache-jena-x.y.z
::    bat\sparql.bat --version 
::
:: Successfully called after adding unicode hex in place of spaces in file paths
:: without the \u00A0 in place of space Jena returns an error

:: Set your cmd jena home
set JENA_HOME=C:\path\to\your\Jena\apache-jena-5.1.0

:: Set your java home
:: set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.4.7-hotspot
set JAVA_HOME=C:\Program\u00A0Files\Eclipse\u00A0Adoptium\jdk-21.0.4.7-hotspot

:: unicode hex character for space in paths and file names for java programes
:: You may also enter with a U+ prefix, or in Java or C styles: U+00A0, \u00A0, 0x00A0.
:: https://www.unicode.org/charts/PDF/U0080.pdf
:: https://en.wikipedia.org/wiki/Space_(punctuation)
:: \u00A0

:: set JAVA_HOME=C:\Program Files\Java\jdk-17.0.3.1
:: The standard Java JDK install
:: This file demonstrates use of the hotspot instance, as above, 
:: The hotspot instance is the OpenJDK instance from Eclipse Adoptium
set JRE=%JAVA_HOME%\bin
set JLIB=%JAVA_HOME%\lib\*

:: example, add other jav file dependencies as needed
::set CLASSPATH=%CLASSPATH%;C:\path\to\your\jar\file.jar

set CLASSPATH=%JLIB%;%CLASSPATH%

set PATH=%JRE%;%PATH%

