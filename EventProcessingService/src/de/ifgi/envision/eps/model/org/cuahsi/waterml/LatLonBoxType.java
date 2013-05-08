
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="LatLonBoxType">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="ns:GeogLocationType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:double" name="south"/>
 *         &lt;xs:element type="xs:double" name="west"/>
 *         &lt;xs:element type="xs:double" name="north"/>
 *         &lt;xs:element type="xs:double" name="east"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class LatLonBoxType extends GeogLocationType
{
    private Double south;
    private Double west;
    private Double north;
    private Double east;

    /** 
     * Get the 'south' element value.
     * 
     * @return value
     */
    public Double getSouth() {
        return south;
    }

    /** 
     * Set the 'south' element value.
     * 
     * @param south
     */
    public void setSouth(Double south) {
        this.south = south;
    }

    /** 
     * Get the 'west' element value.
     * 
     * @return value
     */
    public Double getWest() {
        return west;
    }

    /** 
     * Set the 'west' element value.
     * 
     * @param west
     */
    public void setWest(Double west) {
        this.west = west;
    }

    /** 
     * Get the 'north' element value.
     * 
     * @return value
     */
    public Double getNorth() {
        return north;
    }

    /** 
     * Set the 'north' element value.
     * 
     * @param north
     */
    public void setNorth(Double north) {
        this.north = north;
    }

    /** 
     * Get the 'east' element value.
     * 
     * @return value
     */
    public Double getEast() {
        return east;
    }

    /** 
     * Set the 'east' element value.
     * 
     * @param east
     */
    public void setEast(Double east) {
        this.east = east;
    }
}
