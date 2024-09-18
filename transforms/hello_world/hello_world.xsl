<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="html"/>
	
	<xsl:template match="/">
		<xsl:apply-templates select="hello-world"/>
	</xsl:template>
	
	<xsl:template match="hello-world">
		<!-- doctype not pemitted as it causes Transform to fail with error -->
		<!--!DOCTYPE html-->
		<html>
			<head>
				<title>
					<xsl:value-of select="."/>
				</title>
			</head>
			<body>
				<center>
					<xsl:value-of select="."/>
				</center>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>