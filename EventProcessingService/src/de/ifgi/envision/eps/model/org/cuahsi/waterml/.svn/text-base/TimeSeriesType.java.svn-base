
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="TimeSeriesType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:SourceInfoType" name="sourceInfo"/>
 *     &lt;xs:element type="ns:VariableInfoType" name="variable"/>
 *     &lt;xs:element type="ns:TsValuesSingleVariableType" name="values" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="optional" name="name"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TimeSeriesType
{
    private SourceInfoType sourceInfo;
    private VariableInfoType variable;
    private List<TsValuesSingleVariableType> valueList = new ArrayList<TsValuesSingleVariableType>();
    private String name;

    /** 
     * Get the 'sourceInfo' element value.
     * 
     * @return value
     */
    public SourceInfoType getSourceInfo() {
        return sourceInfo;
    }

    /** 
     * Set the 'sourceInfo' element value.
     * 
     * @param sourceInfo
     */
    public void setSourceInfo(SourceInfoType sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    /** 
     * Get the 'variable' element value.
     * 
     * @return value
     */
    public VariableInfoType getVariable() {
        return variable;
    }

    /** 
     * Set the 'variable' element value.
     * 
     * @param variable
     */
    public void setVariable(VariableInfoType variable) {
        this.variable = variable;
    }

    /** 
     * Get the list of 'values' element items.
     * 
     * @return list
     */
    public List<TsValuesSingleVariableType> getValueList() {
        return valueList;
    }

    /** 
     * Set the list of 'values' element items.
     * 
     * @param list
     */
    public void setValueList(List<TsValuesSingleVariableType> list) {
        valueList = list;
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
}
