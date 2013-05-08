
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="LatLonPointType">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="ns:GeogLocationType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:double" name="latitude"/>
 *         &lt;xs:element type="xs:double" name="longitude"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class LatLonPointType extends GeogLocationType
{
    private Double latitude;
    private Double longitude;

    /** 
     * Get the 'latitude' element value. 
     The latitude of the site in a decimal degrees as calculated in terms of the given datum.
     
     * 
     * @return value
     */
    public Double getLatitude() {
        return latitude;
    }

    /** 
     * Set the 'latitude' element value. 
     The latitude of the site in a decimal degrees as calculated in terms of the given datum.
     
     * 
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /** 
     * Get the 'longitude' element value. 
     The longitude of the site in a decimal degrees as calculated in terms of the given datum.
     
     * 
     * @return value
     */
    public Double getLongitude() {
        return longitude;
    }

    /** 
     * Set the 'longitude' element value. 
     The longitude of the site in a decimal degrees as calculated in terms of the given datum.
     
     * 
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
