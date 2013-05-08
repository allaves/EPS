
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SourceType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="sourceCode" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="organization" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="sourceDescription" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:MetaDataType" name="metadata" minOccurs="0"/>
 *     &lt;xs:element type="ns:ContactInformationType" name="contactInformation" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:string" name="sourceLink" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:string" name="citation" minOccurs="0"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:int" name="sourceID"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SourceType
{
    private String sourceCode;
    private String organization;
    private String sourceDescription;
    private MetaDataType metadata;
    private List<ContactInformationType> contactInformationList = new ArrayList<ContactInformationType>();
    private List<String> sourceLinkList = new ArrayList<String>();
    private String citation;
    private Integer sourceID;

    /** 
     * Get the 'sourceCode' element value. The abbreviation for the source. e.g. USGS for US Geological Survey.
     * 
     * @return value
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /** 
     * Set the 'sourceCode' element value. The abbreviation for the source. e.g. USGS for US Geological Survey.
     * 
     * @param sourceCode
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /** 
     * Get the 'organization' element value.
     * 
     * @return value
     */
    public String getOrganization() {
        return organization;
    }

    /** 
     * Set the 'organization' element value.
     * 
     * @param organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /** 
     * Get the 'sourceDescription' element value.
     * 
     * @return value
     */
    public String getSourceDescription() {
        return sourceDescription;
    }

    /** 
     * Set the 'sourceDescription' element value.
     * 
     * @param sourceDescription
     */
    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    /** 
     * Get the 'metadata' element value.
     * 
     * @return value
     */
    public MetaDataType getMetadata() {
        return metadata;
    }

    /** 
     * Set the 'metadata' element value.
     * 
     * @param metadata
     */
    public void setMetadata(MetaDataType metadata) {
        this.metadata = metadata;
    }

    /** 
     * Get the list of 'contactInformation' element items.
     * 
     * @return list
     */
    public List<ContactInformationType> getContactInformationList() {
        return contactInformationList;
    }

    /** 
     * Set the list of 'contactInformation' element items.
     * 
     * @param list
     */
    public void setContactInformationList(List<ContactInformationType> list) {
        contactInformationList = list;
    }

    /** 
     * Get the list of 'sourceLink' element items.
     * 
     * @return list
     */
    public List<String> getSourceLinkList() {
        return sourceLinkList;
    }

    /** 
     * Set the list of 'sourceLink' element items.
     * 
     * @param list
     */
    public void setSourceLinkList(List<String> list) {
        sourceLinkList = list;
    }

    /** 
     * Get the 'citation' element value.
     * 
     * @return value
     */
    public String getCitation() {
        return citation;
    }

    /** 
     * Set the 'citation' element value.
     * 
     * @param citation
     */
    public void setCitation(String citation) {
        this.citation = citation;
    }

    /** 
     * Get the 'sourceID' attribute value.
     * 
     * @return value
     */
    public Integer getSourceID() {
        return sourceID;
    }

    /** 
     * Set the 'sourceID' attribute value.
     * 
     * @param sourceID
     */
    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }
}
