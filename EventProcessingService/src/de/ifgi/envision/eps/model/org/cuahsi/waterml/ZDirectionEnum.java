
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="zDirectionEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="PositiveUp"/>
 *     &lt;xs:enumeration value="PositiveDown"/>
 *     &lt;xs:enumeration value="Depth"/>
 *     &lt;xs:enumeration value="Altitude"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum ZDirectionEnum {
    POSITIVE_UP("PositiveUp"), POSITIVE_DOWN("PositiveDown"), DEPTH("Depth"), ALTITUDE(
            "Altitude");
    private final String value;

    private ZDirectionEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static ZDirectionEnum convert(String value) {
        for (ZDirectionEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
