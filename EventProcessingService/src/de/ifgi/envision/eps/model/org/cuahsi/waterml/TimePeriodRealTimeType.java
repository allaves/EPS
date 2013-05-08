
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="TimePeriodRealTimeType">
 *   &lt;xs:complexContent mixed="false">
 *     &lt;xs:extension base="ns:TimePeriodType">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="realTimeDataPeriod"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class TimePeriodRealTimeType extends TimePeriodType
{
    private String realTimeDataPeriod;

    /** 
     * Get the 'realTimeDataPeriod' element value.
     * 
     * @return value
     */
    public String getRealTimeDataPeriod() {
        return realTimeDataPeriod;
    }

    /** 
     * Set the 'realTimeDataPeriod' element value.
     * 
     * @param realTimeDataPeriod
     */
    public void setRealTimeDataPeriod(String realTimeDataPeriod) {
        this.realTimeDataPeriod = realTimeDataPeriod;
    }
}
