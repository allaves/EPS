
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="QualityControlLevelType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="qualityControlLevelCode" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="definition" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="explanation" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:int" name="qualityControlLevelID"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class QualityControlLevelType
{
    private String qualityControlLevelCode;
    private String definition;
    private String explanation;
    private Integer qualityControlLevelID;

    /** 
     * Get the 'qualityControlLevelCode' element value. Code used to identify the level of quality control to which data values have been subjected.

     This code is used to link a quality element (qualityControlLevelType) to a dataValue/@qualityControlLevel

     “1”, “1.1”, “Raw”, “QCChecked”

     A quality control level code of -9999 is suggested for data whose quality control level is unknown
     * 
     * @return value
     */
    public String getQualityControlLevelCode() {
        return qualityControlLevelCode;
    }

    /** 
     * Set the 'qualityControlLevelCode' element value. Code used to identify the level of quality control to which data values have been subjected.

     This code is used to link a quality element (qualityControlLevelType) to a dataValue/@qualityControlLevel

     “1”, “1.1”, “Raw”, “QCChecked”

     A quality control level code of -9999 is suggested for data whose quality control level is unknown
     * 
     * @param qualityControlLevelCode
     */
    public void setQualityControlLevelCode(String qualityControlLevelCode) {
        this.qualityControlLevelCode = qualityControlLevelCode;
    }

    /** 
     * Get the 'definition' element value. Definition of Quality Control Level.
     “Raw Data”, “Quality Controlled Data”
     * 
     * @return value
     */
    public String getDefinition() {
        return definition;
    }

    /** 
     * Set the 'definition' element value. Definition of Quality Control Level.
     “Raw Data”, “Quality Controlled Data”
     * 
     * @param definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /** 
     * Get the 'explanation' element value. Explanation of Quality Control Level

     eg
     “Raw data is defined as unprocessed data and data products that have not undergone quality control.” 
     * 
     * @return value
     */
    public String getExplanation() {
        return explanation;
    }

    /** 
     * Set the 'explanation' element value. Explanation of Quality Control Level

     eg
     “Raw data is defined as unprocessed data and data products that have not undergone quality control.” 
     * 
     * @param explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /** 
     * Get the 'qualityControlLevelID' attribute value.
     * 
     * @return value
     */
    public Integer getQualityControlLevelID() {
        return qualityControlLevelID;
    }

    /** 
     * Set the 'qualityControlLevelID' attribute value.
     * 
     * @param qualityControlLevelID
     */
    public void setQualityControlLevelID(Integer qualityControlLevelID) {
        this.qualityControlLevelID = qualityControlLevelID;
    }
}
