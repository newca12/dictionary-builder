//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2013.02.24 � 10:33:56 PM CET 
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
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MediaWikiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="siteinfo" type="{http://www.mediawiki.org/xml/export-0.8/}SiteInfoType" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.mediawiki.org/xml/export-0.8/}PageType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="logitem" type="{http://www.mediawiki.org/xml/export-0.8/}LogItemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Obtient la valeur de la propri�t� siteinfo.
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
     * D�finit la valeur de la propri�t� siteinfo.
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
     * Obtient la valeur de la propri�t� version.
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
     * D�finit la valeur de la propri�t� version.
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
     * Obtient la valeur de la propri�t� lang.
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
     * D�finit la valeur de la propri�t� lang.
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
