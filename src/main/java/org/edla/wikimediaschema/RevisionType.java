//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.02.24 à 10:33:56 PM CET 
//


package org.edla.wikimediaschema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour RevisionType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RevisionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="parentid" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="contributor" type="{http://www.mediawiki.org/xml/export-0.8/}ContributorType"/>
 *         &lt;element name="minor" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://www.mediawiki.org/xml/export-0.8/}CommentType" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.mediawiki.org/xml/export-0.8/}TextType"/>
 *         &lt;element name="sha1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="model" type="{http://www.mediawiki.org/xml/export-0.8/}ContentModelType"/>
 *         &lt;element name="format" type="{http://www.mediawiki.org/xml/export-0.8/}ContentFormatType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RevisionType", propOrder = {
    "id",
    "parentid",
    "timestamp",
    "contributor",
    "minor",
    "comment",
    "text",
    "sha1",
    "model",
    "format"
})
public class RevisionType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger parentid;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(required = true)
    protected ContributorType contributor;
    protected Object minor;
    protected CommentType comment;
    @XmlElement(required = true)
    protected TextType text;
    @XmlElement(required = true)
    protected String sha1;
    @XmlElement(required = true)
    protected String model;
    @XmlElement(required = true)
    protected String format;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété parentid.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getParentid() {
        return parentid;
    }

    /**
     * Définit la valeur de la propriété parentid.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setParentid(BigInteger value) {
        this.parentid = value;
    }

    /**
     * Obtient la valeur de la propriété timestamp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Définit la valeur de la propriété timestamp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Obtient la valeur de la propriété contributor.
     * 
     * @return
     *     possible object is
     *     {@link ContributorType }
     *     
     */
    public ContributorType getContributor() {
        return contributor;
    }

    /**
     * Définit la valeur de la propriété contributor.
     * 
     * @param value
     *     allowed object is
     *     {@link ContributorType }
     *     
     */
    public void setContributor(ContributorType value) {
        this.contributor = value;
    }

    /**
     * Obtient la valeur de la propriété minor.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMinor() {
        return minor;
    }

    /**
     * Définit la valeur de la propriété minor.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMinor(Object value) {
        this.minor = value;
    }

    /**
     * Obtient la valeur de la propriété comment.
     * 
     * @return
     *     possible object is
     *     {@link CommentType }
     *     
     */
    public CommentType getComment() {
        return comment;
    }

    /**
     * Définit la valeur de la propriété comment.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentType }
     *     
     */
    public void setComment(CommentType value) {
        this.comment = value;
    }

    /**
     * Obtient la valeur de la propriété text.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getText() {
        return text;
    }

    /**
     * Définit la valeur de la propriété text.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setText(TextType value) {
        this.text = value;
    }

    /**
     * Obtient la valeur de la propriété sha1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * Définit la valeur de la propriété sha1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSha1(String value) {
        this.sha1 = value;
    }

    /**
     * Obtient la valeur de la propriété model.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Définit la valeur de la propriété model.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Obtient la valeur de la propriété format.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Définit la valeur de la propriété format.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

}
