
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="UnitsTypeEnum">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="Angle"/>
 *     &lt;xs:enumeration value="Area"/>
 *     &lt;xs:enumeration value="Dimensionless"/>
 *     &lt;xs:enumeration value="Energy"/>
 *     &lt;xs:enumeration value="Energy Flux"/>
 *     &lt;xs:enumeration value="Flow"/>
 *     &lt;xs:enumeration value="Force"/>
 *     &lt;xs:enumeration value="Frequency"/>
 *     &lt;xs:enumeration value="Length"/>
 *     &lt;xs:enumeration value="Light"/>
 *     &lt;xs:enumeration value="Mass"/>
 *     &lt;xs:enumeration value="Permeability"/>
 *     &lt;xs:enumeration value="Power"/>
 *     &lt;xs:enumeration value="Pressure/Stress"/>
 *     &lt;xs:enumeration value="Resolution"/>
 *     &lt;xs:enumeration value="Scale"/>
 *     &lt;xs:enumeration value="Temperature"/>
 *     &lt;xs:enumeration value="Time"/>
 *     &lt;xs:enumeration value="Velocity"/>
 *     &lt;xs:enumeration value="Volume"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum UnitsTypeEnum {
    ANGLE("Angle"), AREA("Area"), DIMENSIONLESS("Dimensionless"), ENERGY(
            "Energy"), ENERGY_FLUX("Energy Flux"), FLOW("Flow"), FORCE("Force"), FREQUENCY(
            "Frequency"), LENGTH("Length"), LIGHT("Light"), MASS("Mass"), PERMEABILITY(
            "Permeability"), POWER("Power"), PRESSURE_STRESS("Pressure/Stress"), RESOLUTION(
            "Resolution"), SCALE("Scale"), TEMPERATURE("Temperature"), TIME(
            "Time"), VELOCITY("Velocity"), VOLUME("Volume");
    private final String value;

    private UnitsTypeEnum(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static UnitsTypeEnum convert(String value) {
        for (UnitsTypeEnum inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}
