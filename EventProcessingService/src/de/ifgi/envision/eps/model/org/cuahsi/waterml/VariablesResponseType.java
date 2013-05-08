
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="VariablesResponseType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:QueryInfoType" name="queryInfo" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element name="variables" minOccurs="1" maxOccurs="1">
 *       &lt;xs:complexType>
 *         &lt;xs:sequence>
 *           &lt;xs:element type="ns:VariableInfoType" name="variable" minOccurs="0" maxOccurs="unbounded"/>
 *         &lt;/xs:sequence>
 *       &lt;/xs:complexType>
 *     &lt;/xs:element>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class VariablesResponseType
{
    private QueryInfoType queryInfo;
    private List<VariableInfoType> variableList = new ArrayList<VariableInfoType>();

    /** 
     * Get the 'queryInfo' element value.
     * 
     * @return value
     */
    public QueryInfoType getQueryInfo() {
        return queryInfo;
    }

    /** 
     * Set the 'queryInfo' element value.
     * 
     * @param queryInfo
     */
    public void setQueryInfo(QueryInfoType queryInfo) {
        this.queryInfo = queryInfo;
    }

    /** 
     * Get the list of 'variable' element items.
     * 
     * @return list
     */
    public List<VariableInfoType> getVariableList() {
        return variableList;
    }

    /** 
     * Set the list of 'variable' element items.
     * 
     * @param list
     */
    public void setVariableList(List<VariableInfoType> list) {
        variableList = list;
    }
}
