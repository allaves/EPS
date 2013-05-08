
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.math.BigDecimal;

/** 
 * 
 Represents a coordinate tuple in one, two, or three dimensions.
 
 ==============================================================
 There are two ways to represent coordinates: (1) as a sequence 
 of &amp;lt;coord&gt; elements that encapsulate tuples, or (2) using a 
 single &amp;lt;coordinates&gt; string.
 =================================================================== 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="CoordType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:double" name="latitude"/>
 *     &lt;xs:element type="xs:double" name="longitude" minOccurs="1"/>
 *     &lt;xs:element type="xs:decimal" name="Z" minOccurs="0"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class CoordType
{
    private Double latitude;
    private Double longitude;
    private BigDecimal Z;

    /** 
     * Get the 'latitude' element value.
     * 
     * @return value
     */
    public Double getLatitude() {
        return latitude;
    }

    /** 
     * Set the 'latitude' element value.
     * 
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /** 
     * Get the 'longitude' element value.
     * 
     * @return value
     */
    public Double getLongitude() {
        return longitude;
    }

    /** 
     * Set the 'longitude' element value.
     * 
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /** 
     * Get the 'Z' element value.
     * 
     * @return value
     */
    public BigDecimal getZ() {
        return Z;
    }

    /** 
     * Set the 'Z' element value.
     * 
     * @param z
     */
    public void setZ(BigDecimal z) {
        Z = z;
    }
}
