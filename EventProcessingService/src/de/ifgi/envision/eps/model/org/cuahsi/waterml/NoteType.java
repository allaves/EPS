
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NoteType">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:attribute type="xs:string" name="type"/>
 *       &lt;xs:attribute type="xs:string" name="href"/>
 *       &lt;xs:attribute type="xs:string" name="title"/>
 *       &lt;xs:attribute type="xs:string" name="show"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class NoteType
{
    private String string;
    private String type;
    private String href;
    private String title;
    private String show;

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
     * Get the 'type' attribute value.
     * 
     * @return value
     */
    public String getType() {
        return type;
    }

    /** 
     * Set the 'type' attribute value.
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** 
     * Get the 'href' attribute value.
     * 
     * @return value
     */
    public String getHref() {
        return href;
    }

    /** 
     * Set the 'href' attribute value.
     * 
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /** 
     * Get the 'title' attribute value.
     * 
     * @return value
     */
    public String getTitle() {
        return title;
    }

    /** 
     * Set the 'title' attribute value.
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     * Get the 'show' attribute value.
     * 
     * @return value
     */
    public String getShow() {
        return show;
    }

    /** 
     * Set the 'show' attribute value.
     * 
     * @param show
     */
    public void setShow(String show) {
        this.show = show;
    }
}
