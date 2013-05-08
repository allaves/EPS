
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="dataTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Continuous"/>
 *     &lt;xs:enumeration value="Instantaneous"/>
 *     &lt;xs:enumeration value="Cumulative"/>
 *     &lt;xs:enumeration value="Incremental"/>
 *     &lt;xs:enumeration value="Average"/>
 *     &lt;xs:enumeration value="Maximum"/>
 *     &lt;xs:enumeration value="Minimum"/>
 *     &lt;xs:enumeration value="Constant Over Interval"/>
 *     &lt;xs:enumeration value="Categorical"/>
 *     &lt;xs:enumeration value="Best Easy Systematic Estimator "/>
 *     &lt;xs:enumeration value="Unknown"/>
 *     &lt;xs:enumeration value="Variance"/>
 *     &lt;xs:enumeration value="Median"/>
 *     &lt;xs:enumeration value="Mode"/>
 *     &lt;xs:enumeration value="Best Easy Systematic Estimator"/>
 *     &lt;xs:enumeration value="Standard Deviation"/>
 *     &lt;xs:enumeration value="Skewness"/>
 *     &lt;xs:enumeration value="Equivalent Mean"/>
 *     &lt;xs:enumeration value="Sporadic"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum DataTypeEnum {
    CONTINUOUS("Continuous"), INSTANTANEOUS("Instantaneous"), CUMULATIVE(
            "Cumulative"), INCREMENTAL("Incremental"), AVERAGE("Average"), MAXIMUM(
            "Maximum"), MINIMUM("Minimum"), CONSTANT_OVER_INTERVAL(
            "Constant Over Interval"), CATEGORICAL("Categorical"), BEST_EASY_SYSTEMATIC_ESTIMATOR(
            "Best Easy Systematic Estimator "), UNKNOWN("Unknown"), VARIANCE(
            "Variance"), MEDIAN("Median"), MODE("Mode"), BEST_EASY_SYSTEMATIC_ESTIMATOR1(
            "Best Easy Systematic Estimator"), STANDARD_DEVIATION(
            "Standard Deviation"), SKEWNESS("Skewness"), EQUIVALENT_MEAN(
            "Equivalent Mean"), SPORADIC("Sporadic"), UNKNOWN1("Unknown");
    private final String value;

    private DataTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static DataTypeEnum convert(String value) {
        for (DataTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
