
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="generalCategoryEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Water Quality"/>
 *     &lt;xs:enumeration value="Climate"/>
 *     &lt;xs:enumeration value="Hydrology"/>
 *     &lt;xs:enumeration value="Geology"/>
 *     &lt;xs:enumeration value="Biota"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *     &lt;xs:enumeration value="Instrumentation"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum GeneralCategoryEnum {
    WATER_QUALITY("Water Quality"), CLIMATE("Climate"), HYDROLOGY("Hydrology"), GEOLOGY(
            "Geology"), BIOTA("Biota"), UNKNOWN("Unknown"), INSTRUMENTATION(
            "Instrumentation");
    private final String value;

    private GeneralCategoryEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static GeneralCategoryEnum convert(String value) {
        for (GeneralCategoryEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
