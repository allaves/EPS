
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="DataSetInfoType">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="ns:SourceInfoType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="dataSetIdentifier"/>
 *         &lt;xs:element name="timeZoneInfo" minOccurs="0" maxOccurs="1">
 *           &lt;!-- Reference to inner class TimeZoneInfo -->
 *         &lt;/xs:element>
 *         &lt;xs:element type="xs:string" name="dataSetDescription"/>
 *         &lt;xs:element type="ns:NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
 *         &lt;xs:element type="ns:GeogLocationType" name="dataSetLocation" minOccurs="0" maxOccurs="1"/>
 *         &lt;xs:element ref="ns:extension" minOccurs="0"/>
 *         &lt;xs:element type="ns:PropertyType" name="dataSetProperty" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class DataSetInfoType extends SourceInfoType
{
    private String dataSetIdentifier;
    private TimeZoneInfo timeZoneInfo;
    private String dataSetDescription;
    private List<NoteType> noteList = new ArrayList<NoteType>();
    private GeogLocationType dataSetLocation;
    private Extension extension;
    private List<PropertyType> dataSetPropertyList = new ArrayList<PropertyType>();

    /** 
     * Get the 'dataSetIdentifier' element value.
     * 
     * @return value
     */
    public String getDataSetIdentifier() {
        return dataSetIdentifier;
    }

    /** 
     * Set the 'dataSetIdentifier' element value.
     * 
     * @param dataSetIdentifier
     */
    public void setDataSetIdentifier(String dataSetIdentifier) {
        this.dataSetIdentifier = dataSetIdentifier;
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
     * Get the 'dataSetDescription' element value.
     * 
     * @return value
     */
    public String getDataSetDescription() {
        return dataSetDescription;
    }

    /** 
     * Set the 'dataSetDescription' element value.
     * 
     * @param dataSetDescription
     */
    public void setDataSetDescription(String dataSetDescription) {
        this.dataSetDescription = dataSetDescription;
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
     * Get the 'dataSetLocation' element value.
     * 
     * @return value
     */
    public GeogLocationType getDataSetLocation() {
        return dataSetLocation;
    }

    /** 
     * Set the 'dataSetLocation' element value.
     * 
     * @param dataSetLocation
     */
    public void setDataSetLocation(GeogLocationType dataSetLocation) {
        this.dataSetLocation = dataSetLocation;
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
     * Get the list of 'dataSetProperty' element items.
     * 
     * @return list
     */
    public List<PropertyType> getDataSetPropertyList() {
        return dataSetPropertyList;
    }

    /** 
     * Set the list of 'dataSetProperty' element items.
     * 
     * @param list
     */
    public void setDataSetPropertyList(List<PropertyType> list) {
        dataSetPropertyList = list;
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
}
