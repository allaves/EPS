
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="seriesCatalogType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element name="series" minOccurs="0" maxOccurs="unbounded">
 *       &lt;!-- Reference to inner class Series -->
 *     &lt;/xs:element>
 *     &lt;xs:element type="ns:PropertyType" name="seriesCatalogProperty" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element ref="ns:extension" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" name="menuGroupName"/>
 *   &lt;xs:attribute type="xs:string" name="serviceWsdl"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SeriesCatalogType
{
    private List<NoteType> noteList = new ArrayList<NoteType>();
    private List<Series> seryList = new ArrayList<Series>();
    private List<PropertyType> seriesCatalogPropertyList = new ArrayList<PropertyType>();
    private Extension extension;
    private String menuGroupName;
    private String serviceWsdl;

    /** 
     * Get the list of 'note' element items.
     * 
     * @return list
     */
    public List<NoteType> getNoteList() {
        return noteList;
    }

    /** 
     * Set the list of 'note' element items.
     * 
     * @param list
     */
    public void setNoteList(List<NoteType> list) {
        noteList = list;
    }

    /** 
     * Get the list of 'series' element items.
     * 
     * @return list
     */
    public List<Series> getSeryList() {
        return seryList;
    }

    /** 
     * Set the list of 'series' element items.
     * 
     * @param list
     */
    public void setSeryList(List<Series> list) {
        seryList = list;
    }

    /** 
     * Get the list of 'seriesCatalogProperty' element items.
     * 
     * @return list
     */
    public List<PropertyType> getSeriesCatalogPropertyList() {
        return seriesCatalogPropertyList;
    }

    /** 
     * Set the list of 'seriesCatalogProperty' element items.
     * 
     * @param list
     */
    public void setSeriesCatalogPropertyList(List<PropertyType> list) {
        seriesCatalogPropertyList = list;
    }

    /** 
     * Get the 'extension' element value.
     * 
     * @return value
     */
    public Extension getExtension() {
        return extension;
    }

    /** 
     * Set the 'extension' element value.
     * 
     * @param extension
     */
    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    /** 
     * Get the 'menuGroupName' attribute value.
     * 
     * @return value
     */
    public String getMenuGroupName() {
        return menuGroupName;
    }

    /** 
     * Set the 'menuGroupName' attribute value.
     * 
     * @param menuGroupName
     */
    public void setMenuGroupName(String menuGroupName) {
        this.menuGroupName = menuGroupName;
    }

    /** 
     * Get the 'serviceWsdl' attribute value.
     * 
     * @return value
     */
    public String getServiceWsdl() {
        return serviceWsdl;
    }

    /** 
     * Set the 'serviceWsdl' attribute value.
     * 
     * @param serviceWsdl
     */
    public void setServiceWsdl(String serviceWsdl) {
        this.serviceWsdl = serviceWsdl;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="series" minOccurs="0" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="xs:string" name="dataType" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:VariableInfoType" name="variable"/>
     *       &lt;xs:element name="valueCount" minOccurs="0">
     *         &lt;!-- Reference to inner class ValueCount -->
     *       &lt;/xs:element>
     *       &lt;xs:element type="ns:TimePeriodType" name="variableTimeInterval"/>
     *       &lt;xs:element type="xs:string" name="valueType" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="xs:string" name="generalCategory" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="xs:string" name="sampleMedium" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:MethodType" name="method" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:SourceType" name="source" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:QualityControlLevelType" name="qualityControlLevel" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="ns:PropertyType" name="seriesProperty" minOccurs="0" maxOccurs="unbounded"/>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Series
    {
        private String dataType;
        private VariableInfoType variable;
        private ValueCount valueCount;
        private TimePeriodType variableTimeInterval;
        private String valueType;
        private String generalCategory;
        private String sampleMedium;
        private MethodType method;
        private SourceType source;
        private QualityControlLevelType qualityControlLevel;
        private List<PropertyType> seriesPropertyList = new ArrayList<PropertyType>();

        /** 
         * Get the 'dataType' element value.
         * 
         * @return value
         */
        public String getDataType() {
            return dataType;
        }

        /** 
         * Set the 'dataType' element value.
         * 
         * @param dataType
         */
        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        /** 
         * Get the 'variable' element value.
         * 
         * @return value
         */
        public VariableInfoType getVariable() {
            return variable;
        }

        /** 
         * Set the 'variable' element value.
         * 
         * @param variable
         */
        public void setVariable(VariableInfoType variable) {
            this.variable = variable;
        }

        /** 
         * Get the 'valueCount' element value.
         * 
         * @return value
         */
        public ValueCount getValueCount() {
            return valueCount;
        }

        /** 
         * Set the 'valueCount' element value.
         * 
         * @param valueCount
         */
        public void setValueCount(ValueCount valueCount) {
            this.valueCount = valueCount;
        }

        /** 
         * Get the 'variableTimeInterval' element value.
         * 
         * @return value
         */
        public TimePeriodType getVariableTimeInterval() {
            return variableTimeInterval;
        }

        /** 
         * Set the 'variableTimeInterval' element value.
         * 
         * @param variableTimeInterval
         */
        public void setVariableTimeInterval(TimePeriodType variableTimeInterval) {
            this.variableTimeInterval = variableTimeInterval;
        }

        /** 
         * Get the 'valueType' element value.
         * 
         * @return value
         */
        public String getValueType() {
            return valueType;
        }

        /** 
         * Set the 'valueType' element value.
         * 
         * @param valueType
         */
        public void setValueType(String valueType) {
            this.valueType = valueType;
        }

        /** 
         * Get the 'generalCategory' element value.
         * 
         * @return value
         */
        public String getGeneralCategory() {
            return generalCategory;
        }

        /** 
         * Set the 'generalCategory' element value.
         * 
         * @param generalCategory
         */
        public void setGeneralCategory(String generalCategory) {
            this.generalCategory = generalCategory;
        }

        /** 
         * Get the 'sampleMedium' element value.
         * 
         * @return value
         */
        public String getSampleMedium() {
            return sampleMedium;
        }

        /** 
         * Set the 'sampleMedium' element value.
         * 
         * @param sampleMedium
         */
        public void setSampleMedium(String sampleMedium) {
            this.sampleMedium = sampleMedium;
        }

        /** 
         * Get the 'method' element value.
         * 
         * @return value
         */
        public MethodType getMethod() {
            return method;
        }

        /** 
         * Set the 'method' element value.
         * 
         * @param method
         */
        public void setMethod(MethodType method) {
            this.method = method;
        }

        /** 
         * Get the 'source' element value.
         * 
         * @return value
         */
        public SourceType getSource() {
            return source;
        }

        /** 
         * Set the 'source' element value.
         * 
         * @param source
         */
        public void setSource(SourceType source) {
            this.source = source;
        }

        /** 
         * Get the 'qualityControlLevel' element value.
         * 
         * @return value
         */
        public QualityControlLevelType getQualityControlLevel() {
            return qualityControlLevel;
        }

        /** 
         * Set the 'qualityControlLevel' element value.
         * 
         * @param qualityControlLevel
         */
        public void setQualityControlLevel(
                QualityControlLevelType qualityControlLevel) {
            this.qualityControlLevel = qualityControlLevel;
        }

        /** 
         * Get the list of 'seriesProperty' element items.
         * 
         * @return list
         */
        public List<PropertyType> getSeriesPropertyList() {
            return seriesPropertyList;
        }

        /** 
         * Set the list of 'seriesProperty' element items.
         * 
         * @param list
         */
        public void setSeriesPropertyList(List<PropertyType> list) {
            seriesPropertyList = list;
        }
        /** 
         * Schema fragment(s) for this class:
         * <pre>
         * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="valueCount" minOccurs="0">
         *   &lt;xs:complexType>
         *     &lt;xs:simpleContent>
         *       &lt;xs:extension base="xs:int">
         *         &lt;xs:attribute type="xs:boolean" name="countIsEstimated"/>
         *       &lt;/xs:extension>
         *     &lt;/xs:simpleContent>
         *   &lt;/xs:complexType>
         * &lt;/xs:element>
         * </pre>
         */
        public static class ValueCount
        {
            private int _int;
            private Boolean countIsEstimated;

            /** 
             * Get the extension value.
             * 
             * @return value
             */
            public int getInt() {
                return _int;
            }

            /** 
             * Set the extension value.
             * 
             * @param _int
             */
            public void setInt(int _int) {
                this._int = _int;
            }

            /** 
             * Get the 'countIsEstimated' attribute value.
             * 
             * @return value
             */
            public Boolean getCountIsEstimated() {
                return countIsEstimated;
            }

            /** 
             * Set the 'countIsEstimated' attribute value.
             * 
             * @param countIsEstimated
             */
            public void setCountIsEstimated(Boolean countIsEstimated) {
                this.countIsEstimated = countIsEstimated;
            }
        }
    }
}
