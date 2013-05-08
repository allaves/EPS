
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="VariableInfoType">
 *   &lt;xs:sequence>
 *     &lt;xs:sequence>
 *       &lt;xs:element name="variableCode" minOccurs="1" maxOccurs="unbounded">
 *         &lt;!-- Reference to inner class VariableCode -->
 *       &lt;/xs:element>
 *       &lt;xs:element type="xs:string" name="variableName" minOccurs="0" maxOccurs="1"/>
 *       &lt;xs:element type="xs:string" name="variableDescription" minOccurs="0"/>
 *       &lt;xs:element type="xs:string" name="valueType" minOccurs="0" maxOccurs="1"/>
 *       &lt;xs:element type="xs:string" name="dataType" minOccurs="0"/>
 *       &lt;xs:element type="xs:string" name="generalCategory" minOccurs="0"/>
 *       &lt;xs:element type="xs:string" name="sampleMedium" minOccurs="0" maxOccurs="1"/>
 *       &lt;xs:element type="UnitsType" name="unit" minOccurs="0"/>
 *       &lt;xs:element ref="options" minOccurs="0" maxOccurs="1"/>
 *       &lt;xs:element type="NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;xs:element name="related" minOccurs="0">
 *         &lt;!-- Reference to inner class Related -->
 *       &lt;/xs:element>
 *       &lt;xs:element ref="extension" minOccurs="0"/>
 *       &lt;xs:element type="xs:double" name="noDataValue" minOccurs="0"/>
 *       &lt;xs:element nillable="true" name="timeScale" minOccurs="0">
 *         &lt;!-- Reference to inner class TimeScale -->
 *       &lt;/xs:element>
 *       &lt;xs:element type="xs:string" name="speciation" minOccurs="0"/>
 *       &lt;xs:element name="categories" minOccurs="0" maxOccurs="1">
 *         &lt;!-- Reference to inner class Categories -->
 *       &lt;/xs:element>
 *       &lt;xs:element type="PropertyType" name="variableProperty" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;/xs:sequence>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute ref="oid">
 *     &lt;!-- Reference to inner class Oid -->
 *   &lt;/xs:attribute>
 *   &lt;xs:attribute ref="metadataTime">
 *     &lt;!-- Reference to inner class MetadataTime -->
 *   &lt;/xs:attribute>
 * &lt;/xs:complexType>
 * </pre>
 */
public class VariableInfoType
{
    private List<VariableCode> variableCodeList = new ArrayList<VariableCode>();
    private String variableName;
    private String variableDescription;
    private String valueType;
    private String dataType;
    private String generalCategory;
    private String sampleMedium;
    private UnitsType unit;
    private Options options;
    private List<NoteType> noteList = new ArrayList<NoteType>();
    private Related related;
    private Extension extension;
    private Double noDataValue;
    private TimeScale timeScale;
    private String speciation;
    private Categories categories;
    private List<PropertyType> variablePropertyList = new ArrayList<PropertyType>();
    private Oid oid;
    private MetadataTime metadataTime;

    /** 
     * Get the list of 'variableCode' element items.
     * 
     * @return list
     */
    public List<VariableCode> getVariableCodeList() {
        return variableCodeList;
    }

    /** 
     * Set the list of 'variableCode' element items.
     * 
     * @param list
     */
    public void setVariableCodeList(List<VariableCode> list) {
        variableCodeList = list;
    }

    /** 
     * Get the 'variableName' element value. A brief name of the variable that could be shown in a menu
     * 
     * @return value
     */
    public String getVariableName() {
        return variableName;
    }

    /** 
     * Set the 'variableName' element value. A brief name of the variable that could be shown in a menu
     * 
     * @param variableName
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /** 
     * Get the 'variableDescription' element value. A detailed descriptn of the variable. May include processing information and other details.
     * 
     * @return value
     */
    public String getVariableDescription() {
        return variableDescription;
    }

    /** 
     * Set the 'variableDescription' element value. A detailed descriptn of the variable. May include processing information and other details.
     * 
     * @param variableDescription
     */
    public void setVariableDescription(String variableDescription) {
        this.variableDescription = variableDescription;
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
     * Get the 'unit' element value. The units of the measurement.
     * 
     * @return value
     */
    public UnitsType getUnit() {
        return unit;
    }

    /** 
     * Set the 'unit' element value. The units of the measurement.
     * 
     * @param unit
     */
    public void setUnit(UnitsType unit) {
        this.unit = unit;
    }

    /** 
     * Get the 'options' element value. See option element for details. This is a list of options. Options are key-value pair elements that control how a variable might be utilized in a service.     
     
     * 
     * @return value
     */
    public Options getOptions() {
        return options;
    }

    /** 
     * Set the 'options' element value. See option element for details. This is a list of options. Options are key-value pair elements that control how a variable might be utilized in a service.     
     
     * 
     * @param options
     */
    public void setOptions(Options options) {
        this.options = options;
    }

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
     * Get the 'related' element value.
     * 
     * @return value
     */
    public Related getRelated() {
        return related;
    }

    /** 
     * Set the 'related' element value.
     * 
     * @param related
     */
    public void setRelated(Related related) {
        this.related = related;
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
     * Get the 'noDataValue' element value.
     * 
     * @return value
     */
    public Double getNoDataValue() {
        return noDataValue;
    }

    /** 
     * Set the 'noDataValue' element value.
     * 
     * @param noDataValue
     */
    public void setNoDataValue(Double noDataValue) {
        this.noDataValue = noDataValue;
    }

    /** 
     * Get the 'timeScale' element value.
     * 
     * @return value
     */
    public TimeScale getTimeScale() {
        return timeScale;
    }

    /** 
     * Set the 'timeScale' element value.
     * 
     * @param timeScale
     */
    public void setTimeScale(TimeScale timeScale) {
        this.timeScale = timeScale;
    }

    /** 
     * Get the 'speciation' element value.
     * 
     * @return value
     */
    public String getSpeciation() {
        return speciation;
    }

    /** 
     * Set the 'speciation' element value.
     * 
     * @param speciation
     */
    public void setSpeciation(String speciation) {
        this.speciation = speciation;
    }

    /** 
     * Get the 'categories' element value.
     * 
     * @return value
     */
    public Categories getCategories() {
        return categories;
    }

    /** 
     * Set the 'categories' element value.
     * 
     * @param categories
     */
    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    /** 
     * Get the list of 'variableProperty' element items.
     * 
     * @return list
     */
    public List<PropertyType> getVariablePropertyList() {
        return variablePropertyList;
    }

    /** 
     * Set the list of 'variableProperty' element items.
     * 
     * @param list
     */
    public void setVariablePropertyList(List<PropertyType> list) {
        variablePropertyList = list;
    }

    /** 
     * Get the 'oid' attribute value.
     * 
     * @return value
     */
    public Oid getOid() {
        return oid;
    }

    /** 
     * Set the 'oid' attribute value.
     * 
     * @param oid
     */
    public void setOid(Oid oid) {
        this.oid = oid;
    }

    /** 
     * Get the 'metadataTime' attribute value.
     * 
     * @return value
     */
    public MetadataTime getMetadataTime() {
        return metadataTime;
    }

    /** 
     * Set the 'metadataTime' attribute value.
     * 
     * @param metadataTime
     */
    public void setMetadataTime(MetadataTime metadataTime) {
        this.metadataTime = metadataTime;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="variableCode" minOccurs="1" maxOccurs="unbounded">
     *   &lt;xs:complexType>
     *     &lt;xs:simpleContent>
     *       &lt;xs:extension base="xs:string">
     *         &lt;xs:attribute type="xs:string" name="network"/>
     *         &lt;xs:attribute type="xs:string" name="vocabulary"/>
     *         &lt;xs:attribute type="xs:boolean" name="default"/>
     *         &lt;xs:attribute type="xs:int" name="variableID"/>
     *       &lt;/xs:extension>
     *     &lt;/xs:simpleContent>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class VariableCode
    {
        private String string;
        private String network;
        private String vocabulary;
        private Boolean _default;
        private Integer variableID;

        /** 
         * Get the extension value.
         * 
         * @return value
         */
        public String getString() {
            return string;
        }

        /** 
         * Set the extension value.
         * 
         * @param string
         */
        public void setString(String string) {
            this.string = string;
        }

        /** 
         * Get the 'network' attribute value.
         * 
         * @return value
         */
        public String getNetwork() {
            return network;
        }

        /** 
         * Set the 'network' attribute value.
         * 
         * @param network
         */
        public void setNetwork(String network) {
            this.network = network;
        }

        /** 
         * Get the 'vocabulary' attribute value.
         * 
         * @return value
         */
        public String getVocabulary() {
            return vocabulary;
        }

        /** 
         * Set the 'vocabulary' attribute value.
         * 
         * @param vocabulary
         */
        public void setVocabulary(String vocabulary) {
            this.vocabulary = vocabulary;
        }

        /** 
         * Get the 'default' attribute value.
         * 
         * @return value
         */
        public Boolean getDefault() {
            return _default;
        }

        /** 
         * Set the 'default' attribute value.
         * 
         * @param _default
         */
        public void setDefault(Boolean _default) {
            this._default = _default;
        }

        /** 
         * Get the 'variableID' attribute value.
         * 
         * @return value
         */
        public Integer getVariableID() {
            return variableID;
        }

        /** 
         * Set the 'variableID' attribute value.
         * 
         * @param variableID
         */
        public void setVariableID(Integer variableID) {
            this.variableID = variableID;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="related" minOccurs="0">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence maxOccurs="unbounded">
     *       &lt;!-- Reference to inner class RelatedInner -->
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Related
    {
        private List<RelatedInner> relatedList = new ArrayList<RelatedInner>();

        /** 
         * Get the list of 'related' element items.
         * 
         * @return list
         */
        public List<RelatedInner> getRelatedList() {
            return relatedList;
        }

        /** 
         * Set the list of 'related' element items.
         * 
         * @param list
         */
        public void setRelatedList(List<RelatedInner> list) {
            relatedList = list;
        }
        /** 
         * Schema fragment(s) for this class:
         * <pre>
         * &lt;xs:sequence xmlns:xs="http://www.w3.org/2001/XMLSchema" maxOccurs="unbounded">
         *   &lt;xs:element name="parentCode">
         *     &lt;xs:complexType>
         *       &lt;xs:simpleContent>
         *         &lt;xs:extension base="xs:string">
         *           &lt;xs:attribute type="xs:string" name="network"/>
         *           &lt;xs:attribute type="xs:string" name="vocabulary"/>
         *           &lt;xs:attribute type="xs:boolean" name="default"/>
         *         &lt;/xs:extension>
         *       &lt;/xs:simpleContent>
         *     &lt;/xs:complexType>
         *   &lt;/xs:element>
         *   &lt;xs:element name="relatedCode">
         *     &lt;xs:complexType>
         *       &lt;xs:simpleContent>
         *         &lt;xs:extension base="xs:string">
         *           &lt;xs:attribute type="xs:string" name="network"/>
         *           &lt;xs:attribute type="xs:string" name="vocabulary"/>
         *           &lt;xs:attribute type="xs:boolean" name="default"/>
         *         &lt;/xs:extension>
         *       &lt;/xs:simpleContent>
         *     &lt;/xs:complexType>
         *   &lt;/xs:element>
         * &lt;/xs:sequence>
         * </pre>
         */
        public static class RelatedInner
        {
            private String parentCodeString;
            private String parentCodeNetwork;
            private String parentCodeVocabulary;
            private Boolean parentCodeDefault;
            private String relatedCodeString;
            private String relatedCodeNetwork;
            private String relatedCodeVocabulary;
            private Boolean relatedCodeDefault;

            /** 
             * Get the extension value.
             * 
             * @return value
             */
            public String getParentCodeString() {
                return parentCodeString;
            }

            /** 
             * Set the extension value.
             * 
             * @param parentCodeString
             */
            public void setParentCodeString(String parentCodeString) {
                this.parentCodeString = parentCodeString;
            }

            /** 
             * Get the 'network' attribute value.
             * 
             * @return value
             */
            public String getParentCodeNetwork() {
                return parentCodeNetwork;
            }

            /** 
             * Set the 'network' attribute value.
             * 
             * @param parentCodeNetwork
             */
            public void setParentCodeNetwork(String parentCodeNetwork) {
                this.parentCodeNetwork = parentCodeNetwork;
            }

            /** 
             * Get the 'vocabulary' attribute value.
             * 
             * @return value
             */
            public String getParentCodeVocabulary() {
                return parentCodeVocabulary;
            }

            /** 
             * Set the 'vocabulary' attribute value.
             * 
             * @param parentCodeVocabulary
             */
            public void setParentCodeVocabulary(String parentCodeVocabulary) {
                this.parentCodeVocabulary = parentCodeVocabulary;
            }

            /** 
             * Get the 'default' attribute value.
             * 
             * @return value
             */
            public Boolean getParentCodeDefault() {
                return parentCodeDefault;
            }

            /** 
             * Set the 'default' attribute value.
             * 
             * @param parentCodeDefault
             */
            public void setParentCodeDefault(Boolean parentCodeDefault) {
                this.parentCodeDefault = parentCodeDefault;
            }

            /** 
             * Get the extension value.
             * 
             * @return value
             */
            public String getRelatedCodeString() {
                return relatedCodeString;
            }

            /** 
             * Set the extension value.
             * 
             * @param relatedCodeString
             */
            public void setRelatedCodeString(String relatedCodeString) {
                this.relatedCodeString = relatedCodeString;
            }

            /** 
             * Get the 'network' attribute value.
             * 
             * @return value
             */
            public String getRelatedCodeNetwork() {
                return relatedCodeNetwork;
            }

            /** 
             * Set the 'network' attribute value.
             * 
             * @param relatedCodeNetwork
             */
            public void setRelatedCodeNetwork(String relatedCodeNetwork) {
                this.relatedCodeNetwork = relatedCodeNetwork;
            }

            /** 
             * Get the 'vocabulary' attribute value.
             * 
             * @return value
             */
            public String getRelatedCodeVocabulary() {
                return relatedCodeVocabulary;
            }

            /** 
             * Set the 'vocabulary' attribute value.
             * 
             * @param relatedCodeVocabulary
             */
            public void setRelatedCodeVocabulary(String relatedCodeVocabulary) {
                this.relatedCodeVocabulary = relatedCodeVocabulary;
            }

            /** 
             * Get the 'default' attribute value.
             * 
             * @return value
             */
            public Boolean getRelatedCodeDefault() {
                return relatedCodeDefault;
            }

            /** 
             * Set the 'default' attribute value.
             * 
             * @param relatedCodeDefault
             */
            public void setRelatedCodeDefault(Boolean relatedCodeDefault) {
                this.relatedCodeDefault = relatedCodeDefault;
            }
        }
    }
    /** 
     * Element containing the time support (or temporal footprint) of the data values.  

     @isRegular indicates if the spacing is regular.
     the timeInterval is the spacing between the observations, with the units being the units of the spacing.
     This basically communicates the precision of the observation. If timeSupport isReqgular, and has units of 1 day, then clients should reformat output to spreadsheets and applications to day values.

     In waterML 1.0, there is a divergence of mean between ODM, and WaterML.
     WaterML only communcates the regularity, and the spacing of the observations (timeInterval). Whereas timesupport in the ODM is associated with the dataType, and time support. This will be addressed in 1.1

     * 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" nillable="true" name="timeScale" minOccurs="0">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element type="ns:UnitsType" name="unit" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="xs:float" name="timeSpacing" minOccurs="0" maxOccurs="1"/>
     *       &lt;xs:element type="xs:float" name="timeSupport" minOccurs="0" maxOccurs="1"/>
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:boolean" default="false" name="isRegular"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class TimeScale
    {
        private UnitsType unit;
        private Float timeSpacing;
        private Float timeSupport;
        private Boolean isRegular;

        /** 
         * Get the 'unit' element value.
         * 
         * @return value
         */
        public UnitsType getUnit() {
            return unit;
        }

        /** 
         * Set the 'unit' element value.
         * 
         * @param unit
         */
        public void setUnit(UnitsType unit) {
            this.unit = unit;
        }

        /** 
         * Get the 'timeSpacing' element value.
         * 
         * @return value
         */
        public Float getTimeSpacing() {
            return timeSpacing;
        }

        /** 
         * Set the 'timeSpacing' element value.
         * 
         * @param timeSpacing
         */
        public void setTimeSpacing(Float timeSpacing) {
            this.timeSpacing = timeSpacing;
        }

        /** 
         * Get the 'timeSupport' element value.
         * 
         * @return value
         */
        public Float getTimeSupport() {
            return timeSupport;
        }

        /** 
         * Set the 'timeSupport' element value.
         * 
         * @param timeSupport
         */
        public void setTimeSupport(Float timeSupport) {
            this.timeSupport = timeSupport;
        }

        /** 
         * Get the 'isRegular' attribute value.
         * 
         * @return value
         */
        public Boolean getIsRegular() {
            return isRegular;
        }

        /** 
         * Set the 'isRegular' attribute value.
         * 
         * @param isRegular
         */
        public void setIsRegular(Boolean isRegular) {
            this.isRegular = isRegular;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="categories" minOccurs="0" maxOccurs="1">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence>
     *       &lt;xs:element name="category" minOccurs="0" maxOccurs="unbounded">
     *         &lt;!-- Reference to inner class Category -->
     *       &lt;/xs:element>
     *     &lt;/xs:sequence>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Categories
    {
        private List<Category> categoryList = new ArrayList<Category>();

        /** 
         * Get the list of 'category' element items.
         * 
         * @return list
         */
        public List<Category> getCategoryList() {
            return categoryList;
        }

        /** 
         * Set the list of 'category' element items.
         * 
         * @param list
         */
        public void setCategoryList(List<Category> list) {
            categoryList = list;
        }
        /** 
         * Schema fragment(s) for this class:
         * <pre>
         * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="category" minOccurs="0" maxOccurs="unbounded">
         *   &lt;xs:complexType>
         *     &lt;xs:sequence>
         *       &lt;xs:element type="xs:decimal" name="dataValue"/>
         *       &lt;xs:element type="xs:string" name="description" minOccurs="0" maxOccurs="1"/>
         *     &lt;/xs:sequence>
         *     &lt;xs:attribute type="xs:int" name="categoryID"/>
         *   &lt;/xs:complexType>
         * &lt;/xs:element>
         * </pre>
         */
        public static class Category
        {
            private BigDecimal dataValue;
            private String description;
            private Integer categoryID;

            /** 
             * Get the 'dataValue' element value.
             * 
             * @return value
             */
            public BigDecimal getDataValue() {
                return dataValue;
            }

            /** 
             * Set the 'dataValue' element value.
             * 
             * @param dataValue
             */
            public void setDataValue(BigDecimal dataValue) {
                this.dataValue = dataValue;
            }

            /** 
             * Get the 'description' element value.
             * 
             * @return value
             */
            public String getDescription() {
                return description;
            }

            /** 
             * Set the 'description' element value.
             * 
             * @param description
             */
            public void setDescription(String description) {
                this.description = description;
            }

            /** 
             * Get the 'categoryID' attribute value.
             * 
             * @return value
             */
            public Integer getCategoryID() {
                return categoryID;
            }

            /** 
             * Set the 'categoryID' attribute value.
             * 
             * @param categoryID
             */
            public void setCategoryID(Integer categoryID) {
                this.categoryID = categoryID;
            }
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" ref="ns:oid"/>
     * 
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" type="xs:string" name="oid"/>
     * </pre>
     */
    public static class Oid
    {
        private String oid;

        /** 
         * Get the 'oid' attribute value.
         * 
         * @return value
         */
        public String getOid() {
            return oid;
        }

        /** 
         * Set the 'oid' attribute value.
         * 
         * @param oid
         */
        public void setOid(String oid) {
            this.oid = oid;
        }
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" ref="ns:metadataTime"/>
     * 
     * &lt;xs:attribute xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" type="xs:dateTime" name="metadataTime"/>
     * </pre>
     */
    public static class MetadataTime
    {
        private Date metadataTime;

        /** 
         * Get the 'metadataTime' attribute value.
         * 
         * @return value
         */
        public Date getMetadataTime() {
            return metadataTime;
        }

        /** 
         * Set the 'metadataTime' attribute value.
         * 
         * @param metadataTime
         */
        public void setMetadataTime(Date metadataTime) {
            this.metadataTime = metadataTime;
        }
    }
}
