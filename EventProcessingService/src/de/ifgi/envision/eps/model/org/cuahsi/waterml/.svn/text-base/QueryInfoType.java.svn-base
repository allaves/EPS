
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="QueryInfoType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:dateTime" name="creationTime" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="queryURL" minOccurs="0"/>
 *     &lt;xs:element name="criteria" minOccurs="0">
 *       &lt;!-- Reference to inner class Criteria -->
 *     &lt;/xs:element>
 *     &lt;xs:element type="ns:NoteType" name="note" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element ref="ns:extension" minOccurs="0"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class QueryInfoType
{
    private Date creationTime;
    private String queryURL;
    private Criteria criteria;
    private List<NoteType> noteList = new ArrayList<NoteType>();
    private Extension extension;

    /** 
     * Get the 'creationTime' element value.
     * 
     * @return value
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /** 
     * Set the 'creationTime' element value.
     * 
     * @param creationTime
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /** 
     * Get the 'queryURL' element value.
     * 
     * @return value
     */
    public String getQueryURL() {
        return queryURL;
    }

    /** 
     * Set the 'queryURL' element value.
     * 
     * @param queryURL
     */
    public void setQueryURL(String queryURL) {
        this.queryURL = queryURL;
    }

    /** 
     * Get the 'criteria' element value.
     * 
     * @return value
     */
    public Criteria getCriteria() {
        return criteria;
    }

    /** 
     * Set the 'criteria' element value.
     * 
     * @param criteria
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
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
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="criteria" minOccurs="0">
     *   &lt;xs:complexType>
     *     &lt;xs:sequence minOccurs="0">
     *       &lt;!-- Reference to inner class Sequence -->
     *     &lt;/xs:sequence>
     *     &lt;xs:attribute type="xs:string" name="MethodCalled"/>
     *   &lt;/xs:complexType>
     * &lt;/xs:element>
     * </pre>
     */
    public static class Criteria
    {
        private Sequence locationParam;
        private String methodCalled;

        /** 
         * Get the sequence value.
         * 
         * @return value
         */
        public Sequence getLocationParam() {
            return locationParam;
        }

        /** 
         * Set the sequence value.
         * 
         * @param locationParam
         */
        public void setLocationParam(Sequence locationParam) {
            this.locationParam = locationParam;
        }

        /** 
         * Get the 'MethodCalled' attribute value.
         * 
         * @return value
         */
        public String getMethodCalled() {
            return methodCalled;
        }

        /** 
         * Set the 'MethodCalled' attribute value.
         * 
         * @param methodCalled
         */
        public void setMethodCalled(String methodCalled) {
            this.methodCalled = methodCalled;
        }
        /** 
         * Schema fragment(s) for this class:
         * <pre>
         * &lt;xs:sequence xmlns:xs="http://www.w3.org/2001/XMLSchema" minOccurs="0">
         *   &lt;xs:element type="xs:string" name="locationParam" minOccurs="0"/>
         *   &lt;xs:element type="xs:string" name="variableParam" minOccurs="0"/>
         *   &lt;xs:element name="timeParam" minOccurs="0">
         *     &lt;!-- Reference to inner class TimeParam -->
         *   &lt;/xs:element>
         *   &lt;xs:element name="parameter" minOccurs="0" maxOccurs="unbounded">
         *     &lt;!-- Reference to inner class Parameter -->
         *   &lt;/xs:element>
         * &lt;/xs:sequence>
         * </pre>
         */
        public static class Sequence
        {
            private String locationParam;
            private String variableParam;
            private TimeParam timeParam;
            private List<Parameter> parameterList = new ArrayList<Parameter>();

            /** 
             * Get the 'locationParam' element value.
             * 
             * @return value
             */
            public String getLocationParam() {
                return locationParam;
            }

            /** 
             * Set the 'locationParam' element value.
             * 
             * @param locationParam
             */
            public void setLocationParam(String locationParam) {
                this.locationParam = locationParam;
            }

            /** 
             * Get the 'variableParam' element value.
             * 
             * @return value
             */
            public String getVariableParam() {
                return variableParam;
            }

            /** 
             * Set the 'variableParam' element value.
             * 
             * @param variableParam
             */
            public void setVariableParam(String variableParam) {
                this.variableParam = variableParam;
            }

            /** 
             * Get the 'timeParam' element value.
             * 
             * @return value
             */
            public TimeParam getTimeParam() {
                return timeParam;
            }

            /** 
             * Set the 'timeParam' element value.
             * 
             * @param timeParam
             */
            public void setTimeParam(TimeParam timeParam) {
                this.timeParam = timeParam;
            }

            /** 
             * Get the list of 'parameter' element items.
             * 
             * @return list
             */
            public List<Parameter> getParameterList() {
                return parameterList;
            }

            /** 
             * Set the list of 'parameter' element items.
             * 
             * @param list
             */
            public void setParameterList(List<Parameter> list) {
                parameterList = list;
            }
            /** 
             * Schema fragment(s) for this class:
             * <pre>
             * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="timeParam" minOccurs="0">
             *   &lt;xs:complexType>
             *     &lt;xs:sequence>
             *       &lt;xs:element type="xs:string" name="beginDateTime" minOccurs="0" maxOccurs="1"/>
             *       &lt;xs:element type="xs:string" name="endDateTime" minOccurs="0" maxOccurs="1"/>
             *     &lt;/xs:sequence>
             *   &lt;/xs:complexType>
             * &lt;/xs:element>
             * </pre>
             */
            public static class TimeParam
            {
                private String beginDateTime;
                private String endDateTime;

                /** 
                 * Get the 'beginDateTime' element value.
                 * 
                 * @return value
                 */
                public String getBeginDateTime() {
                    return beginDateTime;
                }

                /** 
                 * Set the 'beginDateTime' element value.
                 * 
                 * @param beginDateTime
                 */
                public void setBeginDateTime(String beginDateTime) {
                    this.beginDateTime = beginDateTime;
                }

                /** 
                 * Get the 'endDateTime' element value.
                 * 
                 * @return value
                 */
                public String getEndDateTime() {
                    return endDateTime;
                }

                /** 
                 * Set the 'endDateTime' element value.
                 * 
                 * @param endDateTime
                 */
                public void setEndDateTime(String endDateTime) {
                    this.endDateTime = endDateTime;
                }
            }
            /** 
             * Schema fragment(s) for this class:
             * <pre>
             * &lt;xs:element xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="parameter" minOccurs="0" maxOccurs="unbounded">
             *   &lt;xs:complexType>
             *     &lt;xs:attribute type="xs:string" name="name"/>
             *     &lt;xs:attribute type="xs:string" name="value"/>
             *   &lt;/xs:complexType>
             * &lt;/xs:element>
             * </pre>
             */
            public static class Parameter
            {
                private String name;
                private String value;

                /** 
                 * Get the 'name' attribute value.
                 * 
                 * @return value
                 */
                public String getName() {
                    return name;
                }

                /** 
                 * Set the 'name' attribute value.
                 * 
                 * @param name
                 */
                public void setName(String name) {
                    this.name = name;
                }

                /** 
                 * Get the 'value' attribute value.
                 * 
                 * @return value
                 */
                public String getValue() {
                    return value;
                }

                /** 
                 * Set the 'value' attribute value.
                 * 
                 * @param value
                 */
                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
