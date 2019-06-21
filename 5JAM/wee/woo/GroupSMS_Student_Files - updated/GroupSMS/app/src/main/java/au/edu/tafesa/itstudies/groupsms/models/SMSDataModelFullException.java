package au.edu.tafesa.itstudies.groupsms.models;

public class SMSDataModelFullException extends Exception {

    private String phoneNumber;

    public SMSDataModelFullException(String thePhoneNumber) {
        super("SMSDataModel is full.");
        this.phoneNumber = thePhoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
