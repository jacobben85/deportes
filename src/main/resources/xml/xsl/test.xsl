<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:sportsml="http://iptc.org/std/SportsML/2008-04-01/" version="2.0">
    <xsl:output method="xml" omit-xml-declaration="yes" />

    <xsl:template match="@* | node()">
        <xsl:choose>
            <xsl:when test="not(data(.) eq 'Unknown')">
                <xsl:copy>
                    <xsl:apply-templates select="@* | node()"/>
                </xsl:copy>
            </xsl:when>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>