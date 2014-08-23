//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.08.23 à 05:44:25 PM CEST 
//


package org.edla.wikimediaschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SiteInfoType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SiteInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sitename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="base" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="generator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="case" type="{http://www.mediawiki.org/xml/export-0.9/}CaseType" minOccurs="0"/>
 *         &lt;element name="namespaces" type="{http://www.mediawiki.org/xml/export-0.9/}NamespacesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SiteInfoType", propOrder = {
    "sitename",
    "dbname",
    "base",
    "generator",
    "_case",
    "namespaces"
})
public class SiteInfoType {

    protected String sitename;
    protected String dbname;
    @XmlSchemaType(name = "anyURI")
    protected String base;
    protected String generator;
    @XmlElement(name = "case")
    protected CaseType _case;
    protected NamespacesType namespaces;

    /**
     * Obtient la valeur de la propriété sitename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * Définit la valeur de la propriété sitename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSitename(String value) {
        this.sitename = value;
    }

    /**
     * Obtient la valeur de la propriété dbname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * Définit la valeur de la propriété dbname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbname(String value) {
        this.dbname = value;
    }

    /**
     * Obtient la valeur de la propriété base.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        return base;
    }

    /**
     * Définit la valeur de la propriété base.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Obtient la valeur de la propriété generator.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * Définit la valeur de la propriété generator.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenerator(String value) {
        this.generator = value;
    }

    /**
     * Obtient la valeur de la propriété case.
     * 
     * @return
     *     possible object is
     *     {@link CaseType }
     *     
     */
    public CaseType getCase() {
        return _case;
    }

    /**
     * Définit la valeur de la propriété case.
     * 
     * @param value
     *     allowed object is
     *     {@link CaseType }
     *     
     */
    public void setCase(CaseType value) {
        this._case = value;
    }

    /**
     * Obtient la valeur de la propriété namespaces.
     * 
     * @return
     *     possible object is
     *     {@link NamespacesType }
     *     
     */
    public NamespacesType getNamespaces() {
        return namespaces;
    }

    /**
     * Définit la valeur de la propriété namespaces.
     * 
     * @param value
     *     allowed object is
     *     {@link NamespacesType }
     *     
     */
    public void setNamespaces(NamespacesType value) {
        this.namespaces = value;
    }

}
