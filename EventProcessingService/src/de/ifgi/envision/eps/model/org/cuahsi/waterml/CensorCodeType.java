
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="CensorCodeType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="censorCode"/>
 *     &lt;xs:element type="xs:string" name="censorCodeDescription"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:int" name="censorCodeID"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class CensorCodeType
{
    private String censorCode;
    private String censorCodeDescription;
    private Integer censorCodeID;

    /** 
     * Get the 'censorCode' element value.
     * 
     * @return value
     */
    public String getCensorCode() {
        return censorCode;
    }

    /** 
     * Set the 'censorCode' element value.
     * 
     * @param censorCode
     */
    public void setCensorCode(String censorCode) {
        this.censorCode = censorCode;
    }

    /** 
     * Get the 'censorCodeDescription' element value.
     * 
     * @return value
     */
    public String getCensorCodeDescription() {
        return censorCodeDescription;
    }

    /** 
     * Set the 'censorCodeDescription' element value.
     * 
     * @param censorCodeDescription
     */
    public void setCensorCodeDescription(String censorCodeDescription) {
        this.censorCodeDescription = censorCodeDescription;
    }

    /** 
     * Get the 'censorCodeID' attribute value.
     * 
     * @return value
     */
    public Integer getCensorCodeID() {
        return censorCodeID;
    }

    /** 
     * Set the 'censorCodeID' attribute value.
     * 
     * @param censorCodeID
     */
    public void setCensorCodeID(Integer censorCodeID) {
        this.censorCodeID = censorCodeID;
    }
}
