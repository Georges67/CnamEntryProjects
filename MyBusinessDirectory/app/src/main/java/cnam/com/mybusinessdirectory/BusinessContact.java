package cnam.com.mybusinessdirectory;

import java.io.Serializable;

public class BusinessContact implements Serializable {

    private int contactId;
    private String firstName;
    private String lastName;

    private String address;
    private String phoneNumber;

    public BusinessContact()  {
    }

    public BusinessContact(int contactId) {
        this.contactId = contactId;
    }

    public BusinessContact(int contactId, String firstName, String lastName,
                           String address, String phoneNumber) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // TODO 4 generate getter/setter using menu -> generate

}