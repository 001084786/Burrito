package com.example.testlibrary;
/**
 * This class maintains a list of phoneNumbers and one message. It allows phone numbers to be
 * added but only if not already in the list. The maximum number of phone numbers allowed is
 * set when the SMSDateModel object is created
 */
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class SMSDataModelList implements SMSDataModelInterface, Serializable {

  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_MAX_NUM_PHONE_NUMBERS = -1;
  //public static final String FULL = "FULL";
  public static final String DUPLICATE = "DUPLICATE";

  private String message;         //The SMS message
  private List<String> phoneNumbers;  //The collection of phone numbers
  private int numPhoneNumbers;    //How many phone numbers there are
  private int maxPhoneNumbers;    //Max numbers allowed

  public SMSDataModelList(String message) {
    this(message, DEFAULT_MAX_NUM_PHONE_NUMBERS);
  }

  public SMSDataModelList() {
    this("", DEFAULT_MAX_NUM_PHONE_NUMBERS);
  }

  public SMSDataModelList(String initialMessage, int maxNumPhoneNumbers) {
    this.message = initialMessage;
    this.phoneNumbers = new LinkedList();
    this.maxPhoneNumbers = maxNumPhoneNumbers;
  }

  @Override
  public String addPhoneNumber(String newPhoneNumber) throws SMSDataModelFullException
  {
    String result;
    if (this.phoneNumbers.size() == maxPhoneNumbers){
      throw new SMSDataModelFullException(newPhoneNumber);
    }

    boolean exists;
    exists = findPhoneNumberIndex(newPhoneNumber) != -1;
    if (exists) {
      result = DUPLICATE;
    } else {
      phoneNumbers.add(newPhoneNumber);
      result = newPhoneNumber;
    }
    return result;
  }

  public int findPhoneNumberIndex(String targetNumber) {
    return this.phoneNumbers.indexOf(targetNumber);

    /*
    for (int i = 0; i < this.phoneNumbers.size(); i++) {
      if (this.phoneNumbers.get(i).equals(targetNumber)) {
        return i;
      }
    }

    return -1;
    */
  }

  public String updatePhoneNumber(String newPhoneNumber, int i) {

    if (this.phoneNumbers.indexOf(newPhoneNumber) != -1) {
      return DUPLICATE;
    } else {
      if (i < 0 || i >= phoneNumbers.size()) {
        return null;
      } else {
        return phoneNumbers.set(i, newPhoneNumber);
      }
    }
  }

  public String getPhoneNumber(int i) {
    String result;

    if (i < 0 || i >= phoneNumbers.size()) {
      result = null;
    } else {
      result = phoneNumbers.get(i);
    }
    return result;
  }

  public String deleteNumber(int i) {
    String result;
    if (i < 0 || i >= phoneNumbers.size()) {
      return null;
    } else {
      return phoneNumbers.remove(i);
    }
  }

  public int getMaxNumPhoneNumbers() {
    return this.maxPhoneNumbers;
  }

  public boolean isFull() {
    return this.phoneNumbers.size() == this.maxPhoneNumbers;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String mMessage) {
    this.message = mMessage;
  }

  public String[] getPhoneNumbers() {
    String[] phoneNumberArray = phoneNumbers.toArray(new String[this.phoneNumbers.size()]);
    return phoneNumberArray;
  }

  public int getNumPhoneNumbers() {
    return phoneNumbers.size();
  }

  @Override
  public String toString() {
    return "SMSDataModel [message=" + message + ", phoneNumbers="
      + phoneNumbers + ", numPhoneNumbers="
      + phoneNumbers.size() + "]";
  }

  @Override
  public void sortNumbers() {
    //throw new UnsupportedOperationException("Not supported yet.");
    Collections.sort(phoneNumbers);
  }

}
