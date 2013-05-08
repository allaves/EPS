
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="latLonLineString">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="ns:GeogLocationType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:CoordType" name="coordLatLong" minOccurs="2" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *       &lt;xs:attribute type="xs:string" name="zDirection"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class LatLonLineString extends GeogLocationType
{
    private List<CoordType> coordLatLongList = new ArrayList<CoordType>();
    private String ZDirection;

    /** 
     * Get the list of 'coordLatLong' element items.
     * 
     * @return list
     */
    public List<CoordType> getCoordLatLongList() {
        return coordLatLongList;
    }

    /** 
     * Set the list of 'coordLatLong' element items.
     * 
     * @param list
     */
    public void setCoordLatLongList(List<CoordType> list) {
        coordLatLongList = list;
    }

    /** 
     * Get the 'zDirection' attribute value.
     * 
     * @return value
     */
    public String getZDirection() {
        return ZDirection;
    }

    /** 
     * Set the 'zDirection' attribute value.
     * 
     * @param zDirection
     */
    public void setZDirection(String zDirection) {
        ZDirection = zDirection;
    }
}
