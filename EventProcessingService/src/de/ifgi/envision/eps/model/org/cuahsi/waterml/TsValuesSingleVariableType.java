
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="TsValuesSingleVariableType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:ValueSingleVariable" name="value" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:UnitsType" name="units" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:QualifierType" name="qualifier" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:QualityControlLevelType" name="qualityControlLevel" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:MethodType" name="method" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:SourceType" name="source" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:OffsetType" name="offset" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:SampleType" name="sample" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:CensorCodeType" name="censorCode" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TsValuesSingleVariableType
{
    private List<ValueSingleVariable> valueList = new ArrayList<ValueSingleVariable>();
    private UnitsType units;
    private List<QualifierType> qualifierList = new ArrayList<QualifierType>();
    private List<QualityControlLevelType> qualityControlLevelList = new ArrayList<QualityControlLevelType>();
    private List<MethodType> methodList = new ArrayList<MethodType>();
    private List<SourceType> sourceList = new ArrayList<SourceType>();
    private List<OffsetType> offsetList = new ArrayList<OffsetType>();
    private List<SampleType> sampleList = new ArrayList<SampleType>();
    private List<CensorCodeType> censorCodeList = new ArrayList<CensorCodeType>();

    /** 
     * Get the list of 'value' element items. text
     * 
     * @return list
     */
    public List<ValueSingleVariable> getValueList() {
        return valueList;
    }

    /** 
     * Set the list of 'value' element items. text
     * 
     * @param list
     */
    public void setValueList(List<ValueSingleVariable> list) {
        valueList = list;
    }

    /** 
     * Get the 'units' element value.
     * 
     * @return value
     */
    public UnitsType getUnits() {
        return units;
    }

    /** 
     * Set the 'units' element value.
     * 
     * @param units
     */
    public void setUnits(UnitsType units) {
        this.units = units;
    }

    /** 
     * Get the list of 'qualifier' element items.
     * 
     * @return list
     */
    public List<QualifierType> getQualifierList() {
        return qualifierList;
    }

    /** 
     * Set the list of 'qualifier' element items.
     * 
     * @param list
     */
    public void setQualifierList(List<QualifierType> list) {
        qualifierList = list;
    }

    /** 
     * Get the list of 'qualityControlLevel' element items.
     * 
     * @return list
     */
    public List<QualityControlLevelType> getQualityControlLevelList() {
        return qualityControlLevelList;
    }

    /** 
     * Set the list of 'qualityControlLevel' element items.
     * 
     * @param list
     */
    public void setQualityControlLevelList(List<QualityControlLevelType> list) {
        qualityControlLevelList = list;
    }

    /** 
     * Get the list of 'method' element items. Multiple &amp;lt;method&amp;gt;s  lists the methods used to collect the data and any additional information about the method.

     Different instruments should be represented as different methods, according to ODM best practices.
     * 
     * @return list
     */
    public List<MethodType> getMethodList() {
        return methodList;
    }

    /** 
     * Set the list of 'method' element items. Multiple &amp;lt;method&amp;gt;s  lists the methods used to collect the data and any additional information about the method.

     Different instruments should be represented as different methods, according to ODM best practices.
     * 
     * @param list
     */
    public void setMethodList(List<MethodType> list) {
        methodList = list;
    }

    /** 
     * Get the list of 'source' element items.
     * 
     * @return list
     */
    public List<SourceType> getSourceList() {
        return sourceList;
    }

    /** 
     * Set the list of 'source' element items.
     * 
     * @param list
     */
    public void setSourceList(List<SourceType> list) {
        sourceList = list;
    }

    /** 
     * Get the list of 'offset' element items.
     * 
     * @return list
     */
    public List<OffsetType> getOffsetList() {
        return offsetList;
    }

    /** 
     * Set the list of 'offset' element items.
     * 
     * @param list
     */
    public void setOffsetList(List<OffsetType> list) {
        offsetList = list;
    }

    /** 
     * Get the list of 'sample' element items.
     * 
     * @return list
     */
    public List<SampleType> getSampleList() {
        return sampleList;
    }

    /** 
     * Set the list of 'sample' element items.
     * 
     * @param list
     */
    public void setSampleList(List<SampleType> list) {
        sampleList = list;
    }

    /** 
     * Get the list of 'censorCode' element items.
     * 
     * @return list
     */
    public List<CensorCodeType> getCensorCodeList() {
        return censorCodeList;
    }

    /** 
     * Set the list of 'censorCode' element items.
     * 
     * @param list
     */
    public void setCensorCodeList(List<CensorCodeType> list) {
        censorCodeList = list;
    }
}
