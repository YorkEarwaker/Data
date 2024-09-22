@echo off

::Currently only works for Saxon
::set SAXON_HOME=C:\path\to\your\saxon\SaxonHE12-5J

:: Set your cmd line java environment

set JAVA_HOME=C:\Program Files\Java\jdk-17.0.3.1
set JRE=%JAVA_HOME%\bin
set JLIB=%JAVA_HOME%\lib\*

:: example
::set CLASSPATH=%CLASSPATH%;C:\path\to\your\jar\file.jar

set CLASSPATH=%JLIB%;%CLASSPATH%

set PATH=%JRE%;%PATH%
