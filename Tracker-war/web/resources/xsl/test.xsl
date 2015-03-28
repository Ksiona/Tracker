<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://xmlns.jcp.org/jsf/html"
                xmlns:f="http://xmlns.jcp.org/jsf/core"
                xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
                xmlns:c="http://xmlns.jcp.org/jsp/jstl/core"
                xmlns:p="http://primefaces.org/ui">
    <h:form>  
  <xsl:for-each select="xmlResult/employee">
                    <table border="0">
                        <tr>
                            <th style="text-align:left">Name</th>
                            <td>
                                    <xsl:value-of select="lastName"/>
                                    <xsl:value-of select="firstName"/>
                                    <xsl:value-of select="surName"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:left">Position</th>
                            <td>
                                <xsl:value-of select="department/deptName"/> - 
                                <xsl:value-of select="jobTitle"/>
                            </td>

                        </tr>
                    </table>

                    <table border="1">
                        <tr bgcolor="#9acd32">
                            <th style="text-align:left">Title</th>
                            <th style="text-align:left">Description</th>
                            <th style="text-align:left">Start date</th>
                            <th style="text-align:left">End date</th>
                        </tr>
                        <xsl:for-each select="productionUnits">
                            <tr>
                                <td>
                                    <xsl:value-of select="unitTitle"/>
                                </td>
                                <td>
                                    <xsl:value-of select="unitDesc"/>
                                </td>
                                <td>
                                    <xsl:value-of select="conclusionDate"/>
                                </td>
                                <td>
                                    <xsl:value-of select="expireDate"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </xsl:for-each>

 </h:form>
</ui:composition>

</xsl:template>

</xsl:stylesheet>