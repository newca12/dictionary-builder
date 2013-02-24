//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.02.24 à 10:33:56 PM CET 
//


package org.edla.wikimediaschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Classe Java pour CommentType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CommentType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="deleted" type="{http://www.mediawiki.org/xml/export-0.8/}DeletedFlagType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommentType", propOrder = {
    "value"
})
public class CommentType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "deleted")
    protected DeletedFlagType deleted;

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété deleted.
     * 
     * @return
     *     possible object is
     *     {@link DeletedFlagType }
     *     
     */
    public DeletedFlagType getDeleted() {
        return deleted;
    }

    /**
     * Définit la valeur de la propriété deleted.
     * 
     * @param value
     *     allowed object is
     *     {@link DeletedFlagType }
     *     
     */
    public void setDeleted(DeletedFlagType value) {
        this.deleted = value;
    }

}
