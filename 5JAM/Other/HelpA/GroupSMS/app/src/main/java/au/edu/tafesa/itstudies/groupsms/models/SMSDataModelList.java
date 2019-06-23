package au.edu.tafesa.itstudies.groupsms.models;
/**
 * This class maintains a list of phoneNumbers and one message. It allows phone numbers to be
 * added but only if not already in the list. The maximum number of phone numbers allowed is
 * set when the SMSDateModel object is created
 */
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SMSDataModelList implements SMSDataModelInterface, Serializable {

  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_PHONE_NUMBERS = -1;
  public static final String FULL = "FULL";
  public static final String DUPLICATE = "DUPLICATE";

  private String message;         //The SMS message
  private List<String> phoneNumbers;  //The collection of phone numbers
  private int maxPhoneNumbers;    //How many phone numbers there are

  public SMSDataModelList(String message) {
    this(message, DEFAULT_PHONE_NUMBERS);
  }

  public SMSDataModelList() {
    this("", DEFAULT_PHONE_NUMBERS);
  }

  public SMSDataModelList(String initialMessage, int maxNumPhoneNumbers) {
    this.phoneNumbers = new LinkedList();
    this.message = initialMessage;
    this.maxPhoneNumbers = maxNumPhoneNumbers;
  }
  /**
   * Adds a new phone number.
   *
   * @param newPhoneNumber - the number to be added
   * @return "DUPLICATE" if the number is already in the list otherwise returns
   * the number that was added
   * @throws SMSDataModelFullException
   */
  @Override
  public String addPhoneNumber(String newPhoneNumber) throws
    SMSDataModelFullException {
    String result;

    if (phoneNumbers.size() == maxPhoneNumbers) {
      throw new SMSDataModelFullException(newPhoneNumber);
      //result = FULL;
    } else {
      boolean exists;
      exists = findPhoneNumberIndex(newPhoneNumber) != -1;
      if (exists) {
        result = DUPLICATE;
      } else {
        phoneNumbers.add (newPhoneNumber);
        result = newPhoneNumber;
      }
    }
    return result;
  }

  /**
   * Finds the position in the array of the target phone number
   *
   * @param targetNumber the target phone number
   * @return returns the index of the number in the array if found otherwise
   * returns -1
   */
  public int findPhoneNumberIndex(String targetNumber) {
      return phoneNumbers.indexOf(targetNumber);
  }

  /**
   * Updates a phone number at a particular position in the list
   *
   * @param newPhoneNumber - the number to be use
   * @param i - the position to update
   * @return null if the number cannot be updated because the index i is out of
   * range "DUPLICATE" if the number being provided, newPhoneNumber, is actually
   * already in the model otherwise returns the number just replaced
   */
  public String updatePhoneNumber(String newPhoneNumber, int i) {
    String result;
{
      result = phoneNumbers.set(i, newPhoneNumber);
    }
    return result;
  }

  /**
   * Get a phone number at a particular position in the list
   *
   * @param i - the position to retrieve
   * @return null if the number cannot be retrieved because the index i is out
   * of range otherwise returns the number at position i
   */
  public String getPhoneNumber(int i) {
    String result;

    if (i < 0 || i >= phoneNumbers.size()) {
      result = null;
    } else {
      result = phoneNumbers.get(i);
    }
    return result;
  }

  /**
   * Deletes a phone number at a particular position in the list
   *
   * @param i - the position to delete
   * @return null if the number cannot be deleted because the index i is out of
   * range otherwise returns the number that is deleted
   */
  public String deleteNumber(int i) {
    String result;
    if (i < 0 || i >= phoneNumbers.size()) {
      result = null;
    } else {
       result = phoneNumbers.remove(i);
    }
    return result;
  }

  /**
   * @return the max number of phone numbers that this model can hold, -1 if
   * there is no Maximum
   */
  public int getMaxNumPhoneNumbers() {
    return this.phoneNumbers.size();
  }

  /**
   * @return true if the Model has reached the maximum number of phone numbers
   * it can hold, false otherwise
   */
  public boolean isFull() {
    return this.maxPhoneNumbers == this.phoneNumbers.size();
  }

  /**
   * @return an Array of the phone numbers in the model.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message in the model to the one provided, mMessage
   *
   * @param mMessage The new text message for this model
   */
  public void setMessage(String mMessage) {
    this.message = mMessage;
  }

  /**
   * @return the number of phone numbers in the model
   */
  public String[] getPhoneNumbers() {
    String[] numberArray = this.phoneNumbers.toArray(new String[this.phoneNumbers.size()]);
    return numberArray;
  }

  public int getNumPhoneNumbers() { return maxPhoneNumbers; }

  @Override
  public String toString() {
    return "SMSDataModel [message=" + message + ", phoneNumbers="
      + phoneNumbers + ", numPhoneNumbers="
      + maxPhoneNumbers + "]";
  }

  /**
   * Sorts the phone numbers in the model so that they are in phone number order
   */
  //@Override
  public void sortNumbers() {
    //throw new UnsupportedOperationException("Not supported yet.");
    Collections.sort(phoneNumbers);
  }
}
