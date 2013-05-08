
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * A sampling station is any place where data are collected.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SiteInfoType">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="SourceInfoType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="siteName"/>
 *         &lt;xs:element name="siteCode" maxOccurs="unbounded">
 *           &lt;!-- Reference to inner class SiteCode -->
 *         &lt;/xs:element>
 *         &lt;xs:element name="timeZoneInfo" minOccurs="0" maxOccurs="1">
 *           &lt;!-- Reference to inner class TimeZoneInfo -->
 *         &lt;/xs:element>
 *         &lt;xs:element name="geoLocation">
 *           &lt;xs:complexType>
 *             &lt;xs:sequence>
 *               &lt;xs:element type="GeogLocationType" name="geogLocation"/>
 *               &lt;xs:element name="localSiteXY" minOccurs="0" maxOccurs="unbounded">
 *                 &lt;!-- Reference to inner class LocalSiteXY -->
 *               &lt;/xs:element>
 *             &lt;/xs:sequence>
 *           &lt;/xs:complexType>
 *         &lt;/xs:element>
 *         &lt;xs:element type="xs:double" name="elevation_m" minOccurs="0" maxOccurs="1"/>
 *         &lt;xs:element type="xs:string" name="verticalDatum" minOccurs="0" maxOccurs="1"/>
 *         &lt;xs:element type="NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
 *         &lt;xs:element ref="extension" minOccurs="0"/>
 *         &lt;xs:element type="xs:string" name="altname" minOccurs="0"/>
 *         &lt;xs:element type="xs:string" name="siteType" minOccurs="0" maxOccurs="unbounded"/>
 *         &lt;xs:element type="PropertyType" name="siteProperty" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *       &lt;xs:attribute ref="oid">
 *         &lt;!-- Reference to inner class Oid -->
 *       &lt;/xs:attribute>
 *       &lt;xs:attribute ref="metadataTime">
 *         &lt;!-- Reference to inner class MetadataTime -->
 *       &lt;/xs:attribute>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SiteInfoType extends SourceInfoType
{
    private String siteName;
    private List<SiteCode> siteCodeList = new ArrayList<SiteCode>();
    private TimeZoneInfo timeZoneInfo;
    private GeogLocationType geoLocationGeogLocation;
    private List<LocalSiteXY> localSiteXYList = new ArrayList<LocalSiteXY>();
    private Double elevationM;
    private String verticalDatum;
    private List<NoteType> noteList = new ArrayList<NoteType>();
    private Extension extension;
    private String altname;
    private List<String> siteTypeList = new ArrayList<String>();
    private List<PropertyType> sitePropertyList = new ArrayList<PropertyType>();
    private Oid oid;
    private MetadataTime metadataTime;

    /** 
     * Get the 'siteName' element value.
     * 
     * @return value
     */
    public String getSiteName() {
        return siteName;
    }

    /** 
     * Set the 'siteName' element value.
     * 
     * @param siteName
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /** 
     * Get the list of 'siteCode' element items.
     * 
     * @return list
     */
    public List<SiteCode> getSiteCodeList() {
        return siteCodeList;
    }

    /** 
     * Set the list of 'siteCode' element items.
     * 
     * @param list
     */
    public void setSiteCodeList(List<SiteCode> list) {
        siteCodeList = list;
    }

    /** 
     * Get the 'timeZoneInfo' element value.
     * 
     * @return value
     */
    public TimeZoneInfo getTimeZoneInfo() {
        return timeZoneInfo;
    }

    /** 
     * Set the 'timeZoneInfo' element value.
     * 
     * @param timeZoneInfo
     */
    public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo) {
        this.timeZoneInfo = timeZoneInfo;
    }

    /** 
     * Get the 'geogLocation' element value.
     * 
     * @return value
     */
    public GeogLocationType getGeoLocationGeogLocation() {
        return geoLocationGeogLocation;
    }

    /** 
     * Set the 'geogLocation' element value.
     * 
     * @param geoLocationGeogLocation
     */
    public void setGeoLocationGeogLocation(
            GeogLocationType geoLocationGeogLocation) {
        this.geoLocationGeogLocation = geoLocationGeogLocation;
    }

    /** 
     * Get the list of 'localSiteXY' element items.
     * 
     * @return list
     */
    public List<LocalSiteXY> getLocalSiteXYList() {
        return localSiteXYList;
    }

    /** 
     * Set the list of 'localSiteXY' element items.
     * 
     * @param list
     */
    public void setLocalSiteXYList(List<LocalSiteXY> list) {
        localSiteXYList = list;
    }

    /** 
     * Get the 'elevation_m' element value.
     * 
     * @return value
     */
    public Double getElevationM() {
        return elevationM;
    }

    /** 
     * Set the 'elevation_m' element value.
     * 
     * @param elevationM
     */
    public void setElevationM(Double elevationM) {
        this.elevationM = elevationM;
    }

    /** 
     * Get the 'verticalDatum' element value.
     * 
     * @return value
     */
    public String getVerticalDatum() {
        return verticalDatum;
    }

    /** 
     * Set the 'verticalDatum' element value.
     * 
     * @param verticalDatum
     */
    public void setVerticalDatum(String verticalDatum) {
        this.verticalDatum = verticalDatum;
    }

    /** 
     * Get the list of 'note' element items.
     * 
     * @return list
     */
    public List<NoteType> getNoteList() {
        return noteList;
    }

    /** 
     * Set the list of 'note' element items.
     * 
     * @param list
     */
    public void setNoteList(List<NoteType> list) {
        noteList = list;
    }

    /** 
     * Get the 'extension' element value.
     * 
     * @return value
     */
    public Extension getExtension() {
        return extension;
    }

    /** 
     * Set the 'extension' element value.
     * 
     * @param extension
     */
    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    /** 
     * Get the 'altname' element value.
     * 
     * @return value
     */
    public String getAltname() {
        return altname;
    }

    /** 
     * Set the 'altname' element value.
     * 
     * @param altname
     */
    public void setAltname(String altname) {
        this.altname = altname;
    }

    /** 
     * Get the list of 'siteType' element items.
     * 
     * @return list
     */
    public List<String> getSiteTypeList() {
        return siteTypeList;
    }

    /** 
     * Set the list of 'siteType' element items.
     * 
     * @param list
     */
    public void setSiteTypeList(List<String> list) {
        siteTypeList = list;
    }

    /** 
     * Get the list of 'siteProperty' element items.
     * 
     * @return list
     */
    public List<PropertyType> getSitePropertyList() {
        return sitePropertyList;
    }

    /** 
     * Set the list of 'siteProperty' element items.
     * 
     * @param list
     */
    public void setSitePropertyList(List<PropertyType> list) {
        sitePropertyList = list;
    }

    /** 
     * Get the 'oid' attribute value.
     * 
     * @return value
     */
    public Oid getOid() {
        return oid;
    }

    /** 
     * Set the 'oid' attribute value.
     * 
     * @param oid
     */
    public void setOid(Oid oid) {
        this.oid = oid;
    }

    /** 
     * Get the 'metadataTime' attribute value.
     * 
     * @return value
     */
    public MetadataTime getMetadataTime() {
        return metadataTime;
    }

    /** 
     * Set the 'metadataTime' attribute value.
     * 
     * @param metadataTime
     */
    public void setMetadataTime(MetadataTime metadataTime) {
        this.metadataTime = metadataTime;
    }
    /** 
     * A siteID is an identifier that this site is referred to as.
     There may be multiple siteID elements. Only one should be labeled as the defaultID (set attribute defaultID=true)
     Multiple siteID elements are allowed because site identifiers may change, 
     and different observation networks may refer to the same site with different identifiers.
     * 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="siteCode" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:simpleContent>
     *       &lt;xs:extension base="xs:string">
     *         &lt;xs:attribute type="xs:boolean" name="default"/>
     *         &lt;xs:attribute type="xs:string" use="required" name="network"/>
     *         &lt;xs:attribute type="xs:int" name="siteID"/>
     *         &lt;xs:attribute type="xs:string" name="agencyCode"/>
     *         &lt;xs:attribute type="xs:string" name="agencyName"/>
     *       &lt;/xs:extension>
     *     &lt;/xs:simpleContent>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class SiteCode
    {
        private String string;
        private Boolean _default;
        private String network;
        private Integer siteID;
        private String agencyCode;
        private String agencyName;

        /** 
         * Get the extension value.
         * 
         * @return value
         */
        public String getString() {
            return string;
        }

        /** 
         * Set the extension value.
         * 
         * @param string
         */
        public void setString(String string) {
            this.string = string;
        }

        /** 
         * Get the 'default' attribute value. 
         True if this is the main identifier that this service uses to access this site.
         default value is false.
         
         * 
         * @return value
         */
        public Boolean getDefault() {
            return _default;
        }

        /** 
         * Set the 'default' attribute value. 
         True if this is the main identifier that this service uses to access this site.
         default value is false.
         
         * 
         * @param _default
         */
        public void setDefault(Boolean _default) {
            this._default = _default;
        }

        /** 
         * Get the 'network' attribute value. The name of the observation network that this site identification is use for.
         * 
         * @return value
         */
        public String getNetwork() {
            return network;
        }

        /** 
         * Set the 'network' attribute value. The name of the observation network that this site identification is use for.
         * 
         * @param network
         */
        public void setNetwork(String network) {
            this.network = network;
        }

        /** 
         * Get the 'siteID' attribute value. 
         An identifier of the site, the type of identifier must be specified.
         
         * 
         * @return value
         */
        public Integer getSiteID() {
            return siteID;
        }

        /** 
         * Set the 'siteID' attribute value. 
         An identifier of the site, the type of identifier must be specified.
         
         * 
         * @param siteID
         */
        public void setSiteID(Integer siteID) {
            this.siteID = siteID;
        }

        /** 
         * Get the 'agencyCode' attribute value.
         * 
         * @return value
         */
        public String getAgencyCode() {
            return agencyCode;
        }

        /** 
         * Set the 'agencyCode' attribute value.
         * 
         * @param agencyCode
         */
        public void setAgencyCode(String agencyCode) {
            this.agencyCode = agencyCode;
        }

        /** 
         * Get the 'agencyName' attribute value.
         * 
         * @return value
         */
        public String getAgencyName() {
            return agencyName;
        }

        /** 
         * Set the 'agencyName' attribute value.
         * 
         * @param agencyName
         */
        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }
    }
    /** 
     * the default time zone for this site (+00:00) and if this site shifts to daylight savings time (attribute: usesDaylightSavingsTime)
     * 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="timeZoneInfo" minOccurs="0" maxOccurs="1">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:TimeZoneType" name="defaultTimeZone" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:TimeZoneType" name="daylightSavingsTimeZone" minOccurs="0" maxOccurs="1"/>
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:boolean" name="siteUsesDaylightSavingsTime"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class TimeZoneInfo
    {
        private TimeZoneType defaultTimeZone;
        private TimeZoneType daylightSavingsTimeZone;
        private Boolean siteUsesDaylightSavingsTime;

        /** 
         * Get the 'defaultTimeZone' element value.
         * 
         * @return value
         */
        public TimeZoneType getDefaultTimeZone() {
            return defaultTimeZone;
        }

        /** 
         * Set the 'defaultTimeZone' element value.
         * 
         * @param defaultTimeZone
         */
        public void setDefaultTimeZone(TimeZoneType defaultTimeZone) {
            this.defaultTimeZone = defaultTimeZone;
        }

        /** 
         * Get the 'daylightSavingsTimeZone' element value.
         * 
         * @return value
         */
        public TimeZoneType getDaylightSavingsTimeZone() {
            return daylightSavingsTimeZone;
        }

        /** 
         * Set the 'daylightSavingsTimeZone' element value.
         * 
         * @param daylightSavingsTimeZone
         */
        public void setDaylightSavingsTimeZone(
                TimeZoneType daylightSavingsTimeZone) {
            this.daylightSavingsTimeZone = daylightSavingsTimeZone;
        }

        /** 
         * Get the 'siteUsesDaylightSavingsTime' attribute value.
         * 
         * @return value
         */
        public Boolean getSiteUsesDaylightSavingsTime() {
            return siteUsesDaylightSavingsTime;
        }

        /** 
         * Set the 'siteUsesDaylightSavingsTime' attribute value.
         * 
         * @param siteUsesDaylightSavingsTime
         */
        public void setSiteUsesDaylightSavingsTime(
                Boolean siteUsesDaylightSavingsTime) {
            this.siteUsesDaylightSavingsTime = siteUsesDaylightSavingsTime;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="localSiteXY" minOccurs="0" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:double" name="X"/>
     *       &lt;xs:element type="xs:double" name="Y"/>
     *       &lt;xs:element type="xs:double" name="Z" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:string" name="projectionInformation"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class LocalSiteXY
    {
        private Double X;
        private Double Y;
        private Double Z;
        private List<NoteType> noteList = new ArrayList<NoteType>();
        private String projectionInformation;

        /** 
         * Get the 'X' element value.
         * 
         * @return value
         */
        public Double getX() {
            return X;
        }

        /** 
         * Set the 'X' element value.
         * 
         * @param x
         */
        public void setX(Double x) {
            X = x;
        }

        /** 
         * Get the 'Y' element value.
         * 
         * @return value
         */
        public Double getY() {
            return Y;
        }

        /** 
         * Set the 'Y' element value.
         * 
         * @param y
         */
        public void setY(Double y) {
            Y = y;
        }

        /** 
         * Get the 'Z' element value.
         * 
         * @return value
         */
        public Double getZ() {
            return Z;
        }

        /** 
         * Set the 'Z' element value.
         * 
         * @param z
         */
        public void setZ(Double z) {
            Z = z;
        }

        /** 
         * Get the list of 'note' element items.
         * 
         * @return list
         */
        public List<NoteType> getNoteList() {
            return noteList;
        }

        /** 
         * Set the list of 'note' element items.
         * 
         * @param list
         */
        public void setNoteList(List<NoteType> list) {
            noteList = list;
        }

        /** 
         * Get the 'projectionInformation' attribute value.
         * 
         * @return value
         */
        public String getProjectionInformation() {
            return projectionInformation;
        }

        /** 
         * Set the 'projectionInformation' attribute value.
         * 
         * @param projectionInformation
         */
        public void setProjectionInformation(String projectionInformation) {
            this.projectionInformation = projectionInformation;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" ref="ns:oid"/>
     * 
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" type="xs:string" name="oid"/>
     * </pre>
     */
    public static class Oid
    {
        private String oid;

        /** 
         * Get the 'oid' attribute value.
         * 
         * @return value
         */
        public String getOid() {
            return oid;
        }

        /** 
         * Set the 'oid' attribute value.
         * 
         * @param oid
         */
        public void setOid(String oid) {
            this.oid = oid;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" ref="ns:metadataTime"/>
     * 
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" type="xs:dateTime" name="metadataTime"/>
     * </pre>
     */
    public static class MetadataTime
    {
        private Date metadataTime;

        /** 
         * Get the 'metadataTime' attribute value.
         * 
         * @return value
         */
        public Date getMetadataTime() {
            return metadataTime;
        }

        /** 
         * Set the 'metadataTime' attribute value.
         * 
         * @param metadataTime
         */
        public void setMetadataTime(Date metadataTime) {
            this.metadataTime = metadataTime;
        }
    }
}
