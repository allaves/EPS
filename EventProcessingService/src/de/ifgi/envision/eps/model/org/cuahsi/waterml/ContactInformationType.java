
package de.ifgi.envision.eps.model.org.cuahsi.waterml;

import java.util.ArrayList;
import java.util.List;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://www.cuahsi.org/waterML/1.1/" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ContactInformationType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="contactName" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="typeOfContact" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="email" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:string" name="phone" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:anyType" name="address" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ContactInformationType
{
    private String contactName;
    private String typeOfContact;
    private List<String> emailList = new ArrayList<String>();
    private List<String> phoneList = new ArrayList<String>();
    private List<String> addresses = new ArrayList<String>();

    /** 
     * Get the 'contactName' element value.
     * 
     * @return value
     */
    public String getContactName() {
        return contactName;
    }

    /** 
     * Set the 'contactName' element value.
     * 
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /** 
     * Get the 'typeOfContact' element value.
     * 
     * @return value
     */
    public String getTypeOfContact() {
        return typeOfContact;
    }

    /** 
     * Set the 'typeOfContact' element value.
     * 
     * @param typeOfContact
     */
    public void setTypeOfContact(String typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    /** 
     * Get the list of 'email' element items.
     * 
     * @return list
     */
    public List<String> getEmailList() {
        return emailList;
    }

    /** 
     * Set the list of 'email' element items.
     * 
     * @param list
     */
    public void setEmailList(List<String> list) {
        emailList = list;
    }

    /** 
     * Get the list of 'phone' element items.
     * 
     * @return list
     */
    public List<String> getPhoneList() {
        return phoneList;
    }

    /** 
     * Set the list of 'phone' element items.
     * 
     * @param list
     */
    public void setPhoneList(List<String> list) {
        phoneList = list;
    }

    /** 
     * Get the list of 'address' element items.
     * 
     * @return list
     */
    public List<String> getAddresses() {
        return addresses;
    }

    /** 
     * Set the list of 'address' element items.
     * 
     * @param list
     */
    public void setAddresses(List<String> list) {
        addresses = list;
    }
}
