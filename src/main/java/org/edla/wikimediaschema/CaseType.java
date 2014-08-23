//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.08.23 à 05:44:25 PM CEST 
//


package org.edla.wikimediaschema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CaseType.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CaseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="first-letter"/>
 *     &lt;enumeration value="case-sensitive"/>
 *     &lt;enumeration value="case-insensitive"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CaseType")
@XmlEnum
public enum CaseType {

    @XmlEnumValue("first-letter")
    FIRST_LETTER("first-letter"),
    @XmlEnumValue("case-sensitive")
    CASE_SENSITIVE("case-sensitive"),
    @XmlEnumValue("case-insensitive")
    CASE_INSENSITIVE("case-insensitive");
    private final String value;

    CaseType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CaseType fromValue(String v) {
        for (CaseType c: CaseType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
