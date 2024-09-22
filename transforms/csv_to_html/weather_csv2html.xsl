<xsl:stylesheet 
				version="2.0" 
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:xs="http://www.w3.org/2001/XMLSchema"
				exclude-result-prefixes="xs">
	<xsl:output indent="yes"/>
	<xsl:strip-space elements="*"/> 
	<!-- code modified from Daniel Haley example code, 
	Mar 27, 2015 at 8:00, 
	https://stackoverflow.com/questions/29295377/xslt-2-0-to-convert-csv-to-xml-format -->
	
	<!-- todo: edit this file path to match path to your csv file instance
	note the number of forward slashes / 
	is equal to the number of folder (directory) names -->
	<xsl:variable name="csv-file-path" as="xs:string">
		<xsl:text>file://///C:/path/to/your/transforms/csv_to_html/weather.csv</xsl:text>
	</xsl:variable>

	<xsl:param name="csv-encoding" as="xs:string" select="'iso-8859-1'"/>
	<!--xsl:param name="csv-encoding" as="xs:string" select="'UTF-8'"/--><!-- not working -->
	<xsl:param name="csv-uri" as="xs:string" select="$csv-file-path"/>
	
	<xsl:template match="/" name="csv2html">
	
		<!-- DOCTYPE html-->
<html>
	<head>
		<meta charset="UTF8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<title>Simple web site</title>
		<link rel="stylesheet" href="./assets/common/css/styles.css"/>
		<link rel="stylesheet" href="./assets/common/css/grid.css"/>
		<!--base href="/" /-->
	</head>
	<body>
		<header>
			<h1>A Simple Web Site Example</h1>
			<nav>
				<li><a href="weather.html">Weather</a></li>
			</nav>
		</header>
	
		<main><!-- html output element -->
			<xsl:choose>
				<xsl:when test="unparsed-text-available($csv-uri, $csv-encoding)">
					<xsl:variable name="csv" select="unparsed-text($csv-uri, $csv-encoding)"/>
					<!-- Get header from csv, 
					the first row of column headings to turn into xml elements -->
					<xsl:variable name="header-tokens" as="xs:string*">
						<xsl:analyze-string select="$csv" regex="\r\n?|\n">
							<xsl:non-matching-substring>
								<xsl:if test="position()=1">
									<xsl:copy-of select="tokenize(.,',')"/>
								</xsl:if>
							</xsl:non-matching-substring>
						</xsl:analyze-string>
					</xsl:variable>
					<xsl:analyze-string select="$csv" regex="\r\n?|\n">
						<xsl:non-matching-substring>
							<xsl:if test="not(position()=1)">
								<section><!-- html output element -->
								<div class="grid-container"><!-- html output element -->
									<xsl:for-each select="tokenize(.,',')">
										<xsl:variable name="pos" select="position()"/>
										<!-- for every column in the csv row create a div html tag -->
										<xsl:element name="div"><!-- html output element -->				
											<xsl:attribute name="id">
												<xsl:value-of select="$header-tokens[$pos]"/>
											</xsl:attribute>
											<xsl:attribute name="class">grid-item</xsl:attribute>
											<xsl:value-of select="$header-tokens[$pos],':',."/>
										</xsl:element>
									</xsl:for-each>
								</div><!-- html output element -->
								</section><!-- html output element -->
							</xsl:if>
						</xsl:non-matching-substring>
					</xsl:analyze-string>
				</xsl:when><!-- If there is a problem reading the csv file output the following message as content to the xml file -->
				<xsl:otherwise>
					<xsl:variable name="error">
						<xsl:text>Error reading</xsl:text>
						<xsl:value-of select="$csv-encoding"/>
						<xsl:text>" (encoding "</xsl:text>
						<xsl:value-of select="$csv-encoding"/>
						<xsl:text>").</xsl:text>
					</xsl:variable>
					<xsl:message><xsl:value-of select="$error"/></xsl:message>
					<xsl:value-of select="$error"/>
				</xsl:otherwise>
			</xsl:choose>
		</main><!-- html output element -->
		
				<footer>
			<!--p>&copy; 2024 A Simple Web Site Example </p-->
			<!-- use unicode entity &#169; in place of &copy; to avoid xsl parser erorrs -->
			<p>&#169; 2024 A Simple Web Site Example </p>
		</footer>
	</body>
</html>
		
		
	</xsl:template>
</xsl:stylesheet>