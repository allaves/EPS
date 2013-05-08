
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="valueTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Field Observation"/>
 *     &lt;xs:enumeration value="Sample"/>
 *     &lt;xs:enumeration value="Model Simulation Result"/>
 *     &lt;xs:enumeration value="Derived Value"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum ValueTypeEnum {
    FIELD_OBSERVATION("Field Observation"), SAMPLE("Sample"), MODEL_SIMULATION_RESULT(
            "Model Simulation Result"), DERIVED_VALUE("Derived Value"), UNKNOWN(
            "Unknown");
    private final String value;

    private ValueTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static ValueTypeEnum convert(String value) {
        for (ValueTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
