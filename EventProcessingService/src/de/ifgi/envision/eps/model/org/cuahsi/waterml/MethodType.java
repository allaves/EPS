
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="MethodType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="methodCode" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="methodDescription" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="methodLink" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:int" name="methodID"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class MethodType
{
    private String methodCode;
    private String methodDescription;
    private String methodLink;
    private Integer methodID;

    /** 
     * Get the 'methodCode' element value.
     * 
     * @return value
     */
    public String getMethodCode() {
        return methodCode;
    }

    /** 
     * Set the 'methodCode' element value.
     * 
     * @param methodCode
     */
    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    /** 
     * Get the 'methodDescription' element value.
     * 
     * @return value
     */
    public String getMethodDescription() {
        return methodDescription;
    }

    /** 
     * Set the 'methodDescription' element value.
     * 
     * @param methodDescription
     */
    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    /** 
     * Get the 'methodLink' element value.
     * 
     * @return value
     */
    public String getMethodLink() {
        return methodLink;
    }

    /** 
     * Set the 'methodLink' element value.
     * 
     * @param methodLink
     */
    public void setMethodLink(String methodLink) {
        this.methodLink = methodLink;
    }

    /** 
     * Get the 'methodID' attribute value.
     * 
     * @return value
     */
    public Integer getMethodID() {
        return methodID;
    }

    /** 
     * Set the 'methodID' attribute value.
     * 
     * @param methodID
     */
    public void setMethodID(Integer methodID) {
        this.methodID = methodID;
    }
}
