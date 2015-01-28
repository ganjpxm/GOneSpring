<?xml version="1.0" encoding="UTF-8"?>
<!--
This file was generated by Altova MapForce 2008sp1

YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.

Refer to the Altova MapForce Documentation for further details.
http://www.altova.com/mapforce
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs xsi xsl">
	<xsl:output method="xml" encoding="UTF-8" indent="yes" doctype-public="-//Hibernate/Hibernate Mapping DTD 3.0//EN" doctype-system="http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd"/>
	<xsl:template match="/hibernate-mapping">
		<hibernate-mapping>
			<xsl:for-each select="class">
				<class>
					<xsl:for-each select="@name">
						<xsl:attribute name="name">
							<xsl:value-of select="."/>
						</xsl:attribute>
					</xsl:for-each>
					<xsl:for-each select="@table">
						<xsl:attribute name="table">
							<xsl:value-of select="."/>
						</xsl:attribute>
					</xsl:for-each>
					<xsl:for-each select="cache">
						<cache>
							<xsl:for-each select="@usage">
								<xsl:attribute name="usage">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@region">
								<xsl:attribute name="region">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
						</cache>
					</xsl:for-each>
					<xsl:for-each select="id">
						<id>
							<xsl:for-each select="@name">
								<xsl:attribute name="name">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@column">
								<xsl:attribute name="column">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@type">
								<xsl:attribute name="type">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:attribute name="unsaved-value">
								<xsl:value-of select="''"/>
							</xsl:attribute>
							<xsl:for-each select="generator">
								<generator>
									<xsl:attribute name="class">
										<xsl:value-of select="'uuid'"/>
									</xsl:attribute>
								</generator>
							</xsl:for-each>
						</id>
					</xsl:for-each>
					<xsl:for-each select="composite-id">
						<composite-id>
							<xsl:for-each select="@class">
								<xsl:attribute name="class">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@name">
								<xsl:attribute name="name">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="key-property">
								<key-property>
									<xsl:for-each select="@name">
										<xsl:attribute name="name">
											<xsl:value-of select="."/>
										</xsl:attribute>
									</xsl:for-each>
									<xsl:for-each select="@type">
										<xsl:attribute name="type">
											<xsl:value-of select="."/>
										</xsl:attribute>
									</xsl:for-each>
									<xsl:for-each select="@column">
										<xsl:attribute name="column">
											<xsl:value-of select="."/>
										</xsl:attribute>
									</xsl:for-each>
								</key-property>
							</xsl:for-each>
						</composite-id>
					</xsl:for-each>
					<xsl:for-each select="property">
						<property>
							<xsl:for-each select="@name">
								<xsl:attribute name="name">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@type">
								<xsl:attribute name="type">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@column">
								<xsl:attribute name="column">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
						</property>
					</xsl:for-each>
					<xsl:for-each select="many-to-one">
						<many-to-one>
							<xsl:for-each select="@name">
								<xsl:attribute name="name">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@class">
								<xsl:attribute name="class">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@column">
								<xsl:attribute name="column">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@update">
								<xsl:attribute name="update">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@insert">
								<xsl:attribute name="insert">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="@not-found">
								<xsl:attribute name="not-found">
									<xsl:value-of select="."/>
								</xsl:attribute>
							</xsl:for-each>
							<xsl:for-each select="column">
								<column>
									<xsl:for-each select="@name">
										<xsl:attribute name="name">
											<xsl:value-of select="."/>
										</xsl:attribute>
									</xsl:for-each>
								</column>
							</xsl:for-each>
						</many-to-one>
					</xsl:for-each>
				</class>
			</xsl:for-each>
			<xsl:for-each select="query">
				<query>
					<xsl:for-each select="@name">
						<xsl:attribute name="name">
							<xsl:value-of select="."/>
						</xsl:attribute>
					</xsl:for-each>
					<xsl:value-of select="."/>
				</query>
			</xsl:for-each>
		</hibernate-mapping>
	</xsl:template>
</xsl:stylesheet>
