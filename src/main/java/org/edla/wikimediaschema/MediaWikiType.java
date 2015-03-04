//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.03.05 à 12:17:00 AM CET 
//


package org.edla.wikimediaschema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MediaWikiType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MediaWikiType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="siteinfo" type="{http://www.mediawiki.org/xml/export-0.10/}SiteInfoType" minOccurs="0"/&gt;
 *         &lt;element name="page" type="{http://www.mediawiki.org/xml/export-0.10/}PageType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="logitem" type="{http://www.mediawiki.org/xml/export-0.10/}LogItemType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaWikiType", propOrder = {
    "siteinfo",
    "page",
    "logitem"
})
public class MediaWikiType {

    protected SiteInfoType siteinfo;
    protected List<PageType> page;
    protected List<LogItemType> logitem;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    protected String lang;

    /**
     * Obtient la valeur de la propriété siteinfo.
     * 
     * @return
     *     possible object is
     *     {@link SiteInfoType }
     *     
     */
    public SiteInfoType getSiteinfo() {
        return siteinfo;
    }

    /**
     * Définit la valeur de la propriété siteinfo.
     * 
     * @param value
     *     allowed object is
     *     {@link SiteInfoType }
     *     
     */
    public void setSiteinfo(SiteInfoType value) {
        this.siteinfo = value;
    }

    /**
     * Gets the value of the page property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the page property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PageType }
     * 
     * 
     */
    public List<PageType> getPage() {
        if (page == null) {
            page = new ArrayList<PageType>();
        }
        return this.page;
    }

    /**
     * Gets the value of the logitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogitem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LogItemType }
     * 
     * 
     */
    public List<LogItemType> getLogitem() {
        if (logitem == null) {
            logitem = new ArrayList<LogItemType>();
        }
        return this.logitem;
    }

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété lang.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Définit la valeur de la propriété lang.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

	/**
	 * Install a listener for page on this object. If l is null, the listener is
	 * removed again.
	 */
	public void setPageListener(final Listener l) {
		page = (l == null) ? null : new ArrayList<PageType>() {
			public boolean add(PageType o) {
				l.handlePage(MediaWikiType.this, o);
				return false;
			}
		};
	}

	/**
	 * This listener is invoked every time a new page is unmarshalled.
	 */
	public static interface Listener {
		void handlePage(MediaWikiType mediaWikiType, PageType page);
	}

}
