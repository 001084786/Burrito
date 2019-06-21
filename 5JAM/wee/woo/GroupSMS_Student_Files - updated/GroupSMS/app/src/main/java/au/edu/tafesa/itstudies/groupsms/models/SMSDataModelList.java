package au.edu.tafesa.itstudies.groupsms.models;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * This implementation of the SMSDataModelInterface works using both
 * a LinkedList and an ArrayList. There is no limit to how many numbers
 * can be added.
 *
 * @author aleferro
 */
public class SMSDataModelList implements SMSDataModelInterface, Serializable {

    // Alternative 1 to allow the list to have no limit unless the
    // appropriate constructor is called (Alterantive 2 in the addPhoneNumber
    // method):
    private static final int DEFAULT_MAX_NUM_PHONE_NUMBERS = -1;

    public static final String FULL = "FULL";
    public static final String DUPLICATE = "DUPLICATE";

    private String message;             // The SMS message
    private List<String> phoneNumbers;  // The collection of phone numbers
    private int maxPhoneNumbers;        // New instance variable  to record the maximum number of phone numbers.

    // Message only constructor
    public SMSDataModelList(String message){
        this(message, DEFAULT_MAX_NUM_PHONE_NUMBERS);
    }

    // No args constructor
    public SMSDataModelList(){
        this("", DEFAULT_MAX_NUM_PHONE_NUMBERS);
    }


    // All args constructor
    public SMSDataModelList(String initialMessage, int maxNumPhoneNumbers){
        this.message = initialMessage;
        this.phoneNumbers = new LinkedList<String>();    // Implement the List as a Linked List
        //this.phoneNumbers = new ArrayList<String>();   // Implement the List as an ArrayList
        this.maxPhoneNumbers = maxNumPhoneNumbers;
    }

    // Check if the list is full
    // Check if the number already exists in the list
    // If the list is not full and the number is not duplicate
    // add the number to the list.
    @Override
    public String addPhoneNumber(String newPhoneNumber) throws SMSDataModelFullException{
        String result;

        // Alternative 2 to allow the list to have no limit unless the
        // appropriate constructor is called:
        //if(phoneNumbers.size() == maxPhoneNumbers  && maxPhoneNumbers != DEFAULT_MAX_NUM_PHONE_NUMBERS){
        if(phoneNumbers.size() == maxPhoneNumbers){

            throw new SMSDataModelFullException(newPhoneNumber);

        } else {
            boolean exists;
            exists = findPhoneNumberIndex(newPhoneNumber) != -1;
            if(exists){
                result = DUPLICATE;
            } else {
                phoneNumbers.add(newPhoneNumber);
                result = newPhoneNumber;
            }
        }
        return result;
    }

    // If the number exist in the list the method return the index of that number
    // If the number does not exist in the list, it return -1.
    @Override
    public int findPhoneNumberIndex(String targetNumber) {

        return phoneNumbers.indexOf(targetNumber);
    }

    // Check if the requested index sits in the list.
    // If the index is out of range it return null.
    // If the index is in range, it replace the old number with the new.
    @Override
    public String updatePhoneNumber(String newPhoneNumber, int i) {

        boolean exists;
        exists = findPhoneNumberIndex(newPhoneNumber) != -1;

        if(i < 0 || i >= phoneNumbers.size()){
            return null;
        }else if(exists) {
            return DUPLICATE;
        } else {
            return phoneNumbers.set(i, newPhoneNumber);
        }
    }

    // Check if the index sits in the List.
    // If it doesn't it returns null.
    // If it does it return the number.
    @Override
    public String getPhoneNumber(int i) {
        String result;

        if (i < 0 || i >= phoneNumbers.size()) {
            return null;
        } else {
            return phoneNumbers.get(i);
        }
    }

    // Check if the index sits in the list.
    // If it doesn't it returns null.
    // If it does it delete the number from the list.
    @Override
    public String deleteNumber(int i) {

        if (i < 0 || i >= phoneNumbers.size()) {
            return null;
        } else {
            return phoneNumbers.remove(i);
        }
    }

    // Return the max number if phone numbers allowed in the list
    @Override
    public int getMaxNumPhoneNumbers() {
        return maxPhoneNumbers;
    }

    // Return true if the size of the list is same as maxPhoneNumbers,
    // otherwise it returns false.
    @Override
    public boolean isFull() {
        return this.phoneNumbers.size() == maxPhoneNumbers;
    }

    // It returns the list as an array. That's because the interface expects an
    // array.
    @Override
    public String[] getPhoneNumbers() {
        String[] phoneNums = phoneNumbers.toArray(new String[phoneNumbers.size()]);
        return phoneNums;
    }

    // It return the size of the list.
    @Override
    public int getNumPhoneNumbers() {
        return this.phoneNumbers.size();
    }

    // It return the message.
    @Override
    public String getMessage() {
        return this.message;
    }

    // It set the message.
    @Override
    public void setMessage(String mMessage) {
        this.message = mMessage;
    }

    @Override
    public String toString() {
        return "SMSDataModel LIST: [message=" + message + ", phoneNumbers="
                + phoneNumbers.toString() + ", numPhoneNumbers="
                + phoneNumbers.size() + "]";
    }


    public void sortNumbers() {

        // A couple of alternatives here as well.
        // The following one uses the sort method of the List class.
        // It requires to import the Comparator library
        //phoneNumbers.sort( Comparator.comparing( String::toString ) );

        // The second way uses the sort method of the Collections class.
        // It requires to import the Collections library.
        Collections.sort(phoneNumbers);
    }


}
