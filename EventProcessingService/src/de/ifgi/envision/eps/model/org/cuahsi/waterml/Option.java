
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.math.BigInteger;

/** 
 * Option elements are key-value pair elements that control how a variable maght be utilized in a service.
 Examples:
 MODIS web service. Information is aggreated over land or ocean or both. The plotarea option can include: plotarea=land, plotarea=land, plotarea=landocean

 USGS uses a statistic code, 0003, to repesent a  value type of 'Average'. The USGS statistic codes also several options that do not fit the ODM data model. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="option">
 *   &lt;xs:complexType>
 *     &lt;xs:simpleContent>
 *       &lt;xs:extension base="xs:string">
 *         &lt;xs:attribute type="xs:string" name="name"/>
 *         &lt;xs:attribute type="xs:integer" name="optionID"/>
 *         &lt;xs:attribute type="xs:string" name="optionCode"/>
 *       &lt;/xs:extension>
 *     &lt;/xs:simpleContent>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class Option
{
    private String string;
    private String name;
    private BigInteger optionID;
    private String optionCode;

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
     * Get the 'name' attribute value.
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' attribute value.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Get the 'optionID' attribute value.
     * 
     * @return value
     */
    public BigInteger getOptionID() {
        return optionID;
    }

    /** 
     * Set the 'optionID' attribute value.
     * 
     * @param optionID
     */
    public void setOptionID(BigInteger optionID) {
        this.optionID = optionID;
    }

    /** 
     * Get the 'optionCode' attribute value.
     * 
     * @return value
     */
    public String getOptionCode() {
        return optionCode;
    }

    /** 
     * Set the 'optionCode' attribute value.
     * 
     * @param optionCode
     */
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }
}
