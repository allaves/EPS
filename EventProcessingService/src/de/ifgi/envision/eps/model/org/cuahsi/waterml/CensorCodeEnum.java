
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="CensorCodeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="lt"/>
 *     &lt;xs:enumeration value="gt"/>
 *     &lt;xs:enumeration value="nc"/>
 *     &lt;xs:enumeration value="nd"/>
 *     &lt;xs:enumeration value="pnq"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum CensorCodeEnum {
    LT("lt"), GT("gt"), NC("nc"), ND("nd"), PNQ("pnq");
    private final String value;

    private CensorCodeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static CensorCodeEnum convert(String value) {
        for (CensorCodeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
