
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SampleMediumEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Surface Water"/>
 *     &lt;xs:enumeration value="Ground Water"/>
 *     &lt;xs:enumeration value="Sediment"/>
 *     &lt;xs:enumeration value="Soil"/>
 *     &lt;xs:enumeration value="Air"/>
 *     &lt;xs:enumeration value="Tissue"/>
 *     &lt;xs:enumeration value="Precipitation"/>
 *     &lt;xs:enumeration value="Unknown"/>
 *     &lt;xs:enumeration value="Other"/>
 *     &lt;xs:enumeration value="Snow"/>
 *     &lt;xs:enumeration value="Not Relevant"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum SampleMediumEnum {
    SURFACE_WATER("Surface Water"), GROUND_WATER("Ground Water"), SEDIMENT(
            "Sediment"), SOIL("Soil"), AIR("Air"), TISSUE("Tissue"), PRECIPITATION(
            "Precipitation"), UNKNOWN("Unknown"), OTHER("Other"), SNOW("Snow"), NOT_RELEVANT(
            "Not Relevant");
    private final String value;

    private SampleMediumEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static SampleMediumEnum convert(String value) {
        for (SampleMediumEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
