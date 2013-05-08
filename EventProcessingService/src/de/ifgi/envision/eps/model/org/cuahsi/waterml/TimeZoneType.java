
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="TimeZoneType">
 *   &lt;xs:attribute type="xs:string" use="required" name="zoneOffset"/>
 *   &lt;xs:attribute type="xs:string" use="optional" name="zoneAbbreviation"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TimeZoneType
{
    private String zoneOffset;
    private String zoneAbbreviation;

    /** 
     * Get the 'zoneOffset' attribute value.
     * 
     * @return value
     */
    public String getZoneOffset() {
        return zoneOffset;
    }

    /** 
     * Set the 'zoneOffset' attribute value.
     * 
     * @param zoneOffset
     */
    public void setZoneOffset(String zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    /** 
     * Get the 'zoneAbbreviation' attribute value.
     * 
     * @return value
     */
    public String getZoneAbbreviation() {
        return zoneAbbreviation;
    }

    /** 
     * Set the 'zoneAbbreviation' attribute value.
     * 
     * @param zoneAbbreviation
     */
    public void setZoneAbbreviation(String zoneAbbreviation) {
        this.zoneAbbreviation = zoneAbbreviation;
    }
}
