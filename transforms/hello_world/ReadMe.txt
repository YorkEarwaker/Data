
# ##
# CMD line
# Delete the example output files, or move them to another folder, 
# hello_world_saxon.html and hello_world_xalan.html respectively
# 
# Linux
# <todo: linux batch file and similar>
# 
# Windows
# open the hw_xslt_env.bat in notepad and set paths to your environment
# open a command prompt
# change directory to go to the folder with hello_world.xsl and hello_world.xml
# C:\path\to\your\data\transforms\hello_world
# call hw_xslt_env.bat
# make changes to paths in Xalan and Saxon lines to your environment
# copy and paste the xslt processor call lines to the cmd prompt

# ##
# Xalan
# for XSLT (all on one line):
java -cp C:\path\to\your\xalan\xalan-j_2_7_3\xalan.jar org.apache.xalan.xslt.Process -in hello_world.xml -xsl hello_world.xsl -out hello_world_xalan.html

# ##
# Saxon
# for XSLT (all on one line):
java -cp C:\path\to\your\saxon\SaxonHE12-5J\saxon-he-12.5.jar  net.sf.saxon.Transform -t -s:hello_world.xml -xsl:hello_world.xsl -o:c:hello_world_saxon.html

# for XQuery (all on one line):
<todo: xquery>