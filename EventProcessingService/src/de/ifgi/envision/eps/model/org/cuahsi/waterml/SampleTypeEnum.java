
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="sampleTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="FD"/>
 *     &lt;xs:enumeration value="FF"/>
 *     &lt;xs:enumeration value="FL"/>
 *     &lt;xs:enumeration value="LF"/>
 *     &lt;xs:enumeration value="GW"/>
 *     &lt;xs:enumeration value="PB"/>
 *     &lt;xs:enumeration value="PD"/>
 *     &lt;xs:enumeration value="PE"/>
 *     &lt;xs:enumeration value="PI"/>
 *     &lt;xs:enumeration value="PW"/>
 *     &lt;xs:enumeration value="RE"/>
 *     &lt;xs:enumeration value="SE"/>
 *     &lt;xs:enumeration value="SR"/>
 *     &lt;xs:enumeration value="SS"/>
 *     &lt;xs:enumeration value="SW"/>
 *     &lt;xs:enumeration value="TE"/>
 *     &lt;xs:enumeration value="TI"/>
 *     &lt;xs:enumeration value="TW"/>
 *     &lt;xs:enumeration value="VE"/>
 *     &lt;xs:enumeration value="VI"/>
 *     &lt;xs:enumeration value="VW"/>
 *     &lt;xs:enumeration value="Grab"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *     &lt;xs:enumeration value="No Sample"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum SampleTypeEnum {
    FD("FD"), FF("FF"), FL("FL"), LF("LF"), GW("GW"), PB("PB"), PD("PD"), PE(
            "PE"), PI("PI"), PW("PW"), RE("RE"), SE("SE"), SR("SR"), SS("SS"), SW(
            "SW"), TE("TE"), TI("TI"), TW("TW"), VE("VE"), VI("VI"), VW("VW"), GRAB(
            "Grab"), UNKNOWN("Unknown"), NO_SAMPLE("No Sample");
    private final String value;

    private SampleTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static SampleTypeEnum convert(String value) {
        for (SampleTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
