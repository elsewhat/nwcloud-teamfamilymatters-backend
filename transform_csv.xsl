<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" encoding="iso-8859-1"/>

<xsl:template match="//adgangsadresse ">
<xsl:value-of select="vejnavn/navn"/><xsl:text> </xsl:text> <xsl:value-of select="husnr"/>|<xsl:value-of select="ejerlav/navn"/>|<xsl:value-of select="wgs84koordinat/bredde"/>|<xsl:value-of select="wgs84koordinat/længde"/>
<xsl:text>
</xsl:text>
</xsl:template>

</xsl:stylesheet>