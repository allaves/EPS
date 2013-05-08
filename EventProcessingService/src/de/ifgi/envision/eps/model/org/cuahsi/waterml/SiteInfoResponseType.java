
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SiteInfoResponseType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:QueryInfoType" name="queryInfo" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element name="site" minOccurs="0" maxOccurs="unbounded">
 *       &lt;!-- Reference to inner class Site -->
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SiteInfoResponseType
{
    private QueryInfoType queryInfo;
    private List<Site> siteList = new ArrayList<Site>();

    /** 
     * Get the 'queryInfo' element value.
     * 
     * @return value
     */
    public QueryInfoType getQueryInfo() {
        return queryInfo;
    }

    /** 
     * Set the 'queryInfo' element value.
     * 
     * @param queryInfo
     */
    public void setQueryInfo(QueryInfoType queryInfo) {
        this.queryInfo = queryInfo;
    }

    /** 
     * Get the list of 'site' element items.
     * 
     * @return list
     */
    public List<Site> getSiteList() {
        return siteList;
    }

    /** 
     * Set the list of 'site' element items.
     * 
     * @param list
     */
    public void setSiteList(List<Site> list) {
        siteList = list;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="site" minOccurs="0" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:SiteInfoType" name="siteInfo" minOccurs="1" maxOccurs="1"/>
     *       &lt;xs:element type="ns:seriesCatalogType" name="seriesCatalog" minOccurs="0" maxOccurs="unbounded"/>
     *       &lt;xs:element ref="ns:extension" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Site
    {
        private SiteInfoType siteInfo;
        private List<SeriesCatalogType> seriesCatalogList = new ArrayList<SeriesCatalogType>();
        private List<Extension> extensionList = new ArrayList<Extension>();

        /** 
         * Get the 'siteInfo' element value.
         * 
         * @return value
         */
        public SiteInfoType getSiteInfo() {
            return siteInfo;
        }

        /** 
         * Set the 'siteInfo' element value.
         * 
         * @param siteInfo
         */
        public void setSiteInfo(SiteInfoType siteInfo) {
            this.siteInfo = siteInfo;
        }

        /** 
         * Get the list of 'seriesCatalog' element items.
         * 
         * @return list
         */
        public List<SeriesCatalogType> getSeriesCatalogList() {
            return seriesCatalogList;
        }

        /** 
         * Set the list of 'seriesCatalog' element items.
         * 
         * @param list
         */
        public void setSeriesCatalogList(List<SeriesCatalogType> list) {
            seriesCatalogList = list;
        }

        /** 
         * Get the list of 'extension' element items.
         * 
         * @return list
         */
        public List<Extension> getExtensionList() {
            return extensionList;
        }

        /** 
         * Set the list of 'extension' element items.
         * 
         * @param list
         */
        public void setExtensionList(List<Extension> list) {
            extensionList = list;
        }
    }
}
