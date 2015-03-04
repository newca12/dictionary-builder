//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.04 à 11:33:32 PM CET 
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
 * &lt;simpleType name="CaseType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="first-letter"/&gt;
 *     &lt;enumeration value="case-sensitive"/&gt;
 *     &lt;enumeration value="case-insensitive"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
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
