
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="TimeSeriesResponseType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:QueryInfoType" name="queryInfo" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:TimeSeriesType" name="timeSeries" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TimeSeriesResponseType
{
    private QueryInfoType queryInfo;
    private List<TimeSeriesType> timeSeryList = new ArrayList<TimeSeriesType>();

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
     * Get the list of 'timeSeries' element items.
     * 
     * @return list
     */
    public List<TimeSeriesType> getTimeSeryList() {
        return timeSeryList;
    }

    /** 
     * Set the list of 'timeSeries' element items.
     * 
     * @param list
     */
    public void setTimeSeryList(List<TimeSeriesType> list) {
        timeSeryList = list;
    }
}
