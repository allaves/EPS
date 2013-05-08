
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * A list of options. Option elements are key-value pair elements that control how a variable maght be utilized in a service.
 Examples:
 MODIS web service. Information is aggreated over land or ocean or both. The plotarea option can include: plotarea=land, plotarea=land, plotarea=landocean

 USGS uses a statistic code, 0003, to repesent a  value type of 'Average'. The USGS statistic codes also several options that do not fit the ODM data model. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="options">
 *   &lt;xs:complexType>
 *     &lt;xs:sequence>
 *       &lt;xs:element ref="ns:option" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;/xs:sequence>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class Options
{
    private List<Option> optionList = new ArrayList<Option>();

    /** 
     * Get the list of 'option' element items. Option elements are key-value pair elements that control how a variable maght be utilized in a service.
     Examples:
     MODIS web service. Information is aggreated over land or ocean or both. The plotarea option can include: plotarea=land, plotarea=land, plotarea=landocean

     USGS uses a statistic code, 0003, to repesent a  value type of 'Average'. The USGS statistic codes also several options that do not fit the ODM data model. 
     * 
     * @return list
     */
    public List<Option> getOptionList() {
        return optionList;
    }

    /** 
     * Set the list of 'option' element items. Option elements are key-value pair elements that control how a variable maght be utilized in a service.
     Examples:
     MODIS web service. Information is aggreated over land or ocean or both. The plotarea option can include: plotarea=land, plotarea=land, plotarea=landocean

     USGS uses a statistic code, 0003, to repesent a  value type of 'Average'. The USGS statistic codes also several options that do not fit the ODM data model. 
     * 
     * @param list
     */
    public void setOptionList(List<Option> list) {
        optionList = list;
    }
}
