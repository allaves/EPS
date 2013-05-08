
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="QualityControlLevelEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Raw data"/>
 *     &lt;xs:enumeration value="Quality controlled data"/>
 *     &lt;xs:enumeration value="Derived products"/>
 *     &lt;xs:enumeration value="Interpreted products"/>
 *     &lt;xs:enumeration value="Knowledge products"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum QualityControlLevelEnum {
    RAWDATA("Raw data"), QUALITYCONTROLLEDDATA("Quality controlled data"), DERIVEDPRODUCTS(
            "Derived products"), INTERPRETEDPRODUCTS("Interpreted products"), KNOWLEDGEPRODUCTS(
            "Knowledge products"), UNKNOWN("Unknown");
    private final String value;

    private QualityControlLevelEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static QualityControlLevelEnum convert(String value) {
        for (QualityControlLevelEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
