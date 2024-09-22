# ##
# Convert csv file content to html file content
# the first row of the csv file to contain column headings that are xml compliant
# for csv to html the column headings will be used as id's of xml elements in the html output
# <todo: improve page design with css grid and styles>
# see also csv to xml example

# ##
# the path to your saxon instance
C:\path\to\your\saxon\SaxonHE12-5J

# ##
# shell, csv to html
java -cp C:\path\to\your\saxon\SaxonHE12-5J\saxon-he-12.5.jar net.sf.saxon.Transform -t -s:dummy.xml -xsl:weather_csv2xml.xsl -o:c:weather_saxon.html

# ##
# xslt code
# this is code modified from 
# Daniel Haley example code, 
# Mar 27, 2015 at 8:00, 
# https://stackoverflow.com/questions/29295377/xslt-2-0-to-convert-csv-to-xml-format
#
# open the xsl file 'weather_csv2html.xsl'
# edit the text contents of the variable 'csv-file-path' to path to your csv file instance of 'weather.csv' 
# 
# <todo: defect 01. fix issue to with non 'iso-8859-1' chars. for example; csv file input = 22Â°C (73Â°F), xml file output = 22Ã&#x82;Â°C (73Ã&#x82;Â°F) . try utf-8 file encoding or similar. try select="document('weather.csv')". issue with xslt line 27, unparsed-text($csv-uri, $csv-encoding) ?>
# <todo: issue 01. consider using select="document('.properties')" to hold csv file path in a properties file external to the xsl code.>

# ##
# data
# from the bbc weather rss feed
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/
#
# data munging. All commas were removed from rss content returned
# if a substitute data set is used errors in processing are likely due to commas in the csv column data.
# <todo: for this data set consider another delimiter for csv file like semicolon ';' >
# 
# the data used has been modified from the data returned from bbc rss feeds below
# retreived 20/09/2024, see rawdata.txt file
#
# London 2643743
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2643743
# St Helier 3042091
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/3042091
# Cardiff 2653822
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2653822
# Hull 2645425
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2645425
# Manchester 2643123
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2643123
# Belfast 2655984
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2655984
# Edinburgh 2650225
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2650225
# Inverness 2646088
# https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2646088



