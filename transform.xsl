<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" encoding="iso-8859-1"/>

<xsl:template match="//adgangsadresse ">
INSERT INTO T_FAMILY (email, adresse,owner, latitude,longitude,fatherName, motherName,nrKids,kid1Age,kid2Age,kid3Age,kid1IsGirl,kid2IsGirl,kid3IsGirl) VALUES('dagfinn.parnas@gmail.com', '<xsl:value-of select="vejnavn/navn"/><xsl:text> </xsl:text> <xsl:value-of select="husnr"/>', '<xsl:value-of select="ejerlav/navn"/>','<xsl:value-of select="wgs84koordinat/bredde"/>','<xsl:value-of select="wgs84koordinat/længde"/>','Phil', 'Martha', 2,12,7,-1,0,1,0);
</xsl:template>

</xsl:stylesheet>