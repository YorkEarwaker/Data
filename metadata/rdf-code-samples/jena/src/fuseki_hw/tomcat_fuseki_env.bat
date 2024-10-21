@echo off

:: Variable suggestions from tomcat RUNNING.txt file, says to call this file setenv.bat and place it in tomcat bin (not so far necessary.)
::
:: FUSEKI_BASE :: todo point to a folder /etc that the war file can write to. also db can write to? write datasets too?
:: CATALINA_HOME :: done below
:: CATALINA_BASE ::
:: CATALINA_PID  :: process forks for tomcat
:: CATALINA_OPTS :: command options
:: CATALINA_TMPDIR :: 
:: JRE_HOME :: 
:: JAVA_OPTS :: command options

:: Set your cmd line java environment

:: From Tomcat README
::
::   Windows:
::    set CATALINA_HOME=\path\to\apache-tomcat-[version]
::
:: Successfully called with this env file

:: Set your cmd jena home
set CATALINA_HOME=C:\path\to\your\tomcat\apache-tomcat-10.1.31

:: Set your java home
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.4.7-hotspot

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


:: %CATALINA_HOME%\bin\startup.bat
:: %CATALINA_HOME%\bin\catalina.bat start
:: http://localhost:8080/fuseki/
:: %CATALINA_HOME%\bin\shutdown.bat
:: %CATALINA_HOME%\bin\catalina.bat stop


::