
/**
 * This class maintains a list of phoneNumbers and one message. It allows phone numbers to be
 * added but only if not already in the list. The maximum number of phone numbers allowed is
 * set when the SMSDateModel object is created
 */
import java.io.Serializable;
import java.util.Arrays;

public class SMSDataModelArray implements SMSDataModelInterface, Serializable {

  private static final long serialVersionUID = 1L;
  private static final int DEFAULT_MAX_NUM_PHONE_NUMBERS = 20;
  public static final String FULL = "FULL";
  public static final String DUPLICATE = "DUPLICATE";

  private String message;         //The SMS message
  private String[] phoneNumbers;  //The collection of phone numbers
  private int numPhoneNumbers;    //How many phone numbers there are

  public SMSDataModelArray(String message) {
    this(message, DEFAULT_MAX_NUM_PHONE_NUMBERS);
  }

  public SMSDataModelArray() {
    this("", DEFAULT_MAX_NUM_PHONE_NUMBERS);
  }

  public SMSDataModelArray(String initialMessage, int maxNumPhoneNumbers) {
    this.message = initialMessage;
    this.phoneNumbers = new String[maxNumPhoneNumbers];
    this.numPhoneNumbers = 0;
  }

  @Override
  public String addPhoneNumber(String newPhoneNumber) throws
    SMSDataModelFullException {
    String result;

    if (numPhoneNumbers == phoneNumbers.length) {
      //result = FULL;
      throw new SMSDataModelFullException(newPhoneNumber);
    } else {
      boolean exists;
      exists = findPhoneNumberIndex(newPhoneNumber) != -1;
      if (exists) {
        result = DUPLICATE;
      } else {
        phoneNumbers[numPhoneNumbers] = newPhoneNumber;
        numPhoneNumbers++;
        result = newPhoneNumber;
      }
    }
    return result;
  }

  public int findPhoneNumberIndex(String targetNumber) {
    int i = 0;
    for (i = 0; i < this.numPhoneNumbers; i++) {
      if (this.phoneNumbers[i].equals(targetNumber)) {
        return i;
      }
    }
    return -1;
  }

  public String updatePhoneNumber(String newPhoneNumber, int i) {
    String result;
    boolean exists;
    exists = findPhoneNumberIndex(newPhoneNumber) != -1;

    if (i < 0 || i >= numPhoneNumbers) {
      result = null;
    } else if(exists) {
        result = DUPLICATE;
    } else {
      result = phoneNumbers[i];
      phoneNumbers[i] = newPhoneNumber;
    }
    return result;
  }

  public String getPhoneNumber(int i) {
    String result;

    if (i < 0 || i >= numPhoneNumbers) {
      result = null;
    } else {
      result = phoneNumbers[i];
    }
    return result;
  }

  public String deleteNumber(int i) {
    String result;
    if (i < 0 || i >= numPhoneNumbers) {
      result = null;
    } else {
      result = phoneNumbers[i];
      if (i != this.numPhoneNumbers - 1) {
        System.arraycopy(phoneNumbers, i + 1, phoneNumbers, i, this.numPhoneNumbers - i - 1);
      }
      this.numPhoneNumbers--;
    }
    return result;
  }

  public int getMaxNumPhoneNumbers() {
    return this.phoneNumbers.length;
  }

  public boolean isFull() {
    return this.numPhoneNumbers == this.phoneNumbers.length;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String mMessage) {
    this.message = mMessage;
  }

  public String[] getPhoneNumbers() {
    return phoneNumbers;
  }

  public int getNumPhoneNumbers() {
    return numPhoneNumbers;
  }

  @Override
  public String toString() {
    return "SMSDataModel [message=" + message + ", phoneNumbers="
      + Arrays.toString(phoneNumbers) + ", numPhoneNumbers="
      + numPhoneNumbers + "]";
  }

  @Override
  public void sortNumbers() {
    
    // Version 1
    // Use the sort(array a) method of the Arrays class
    Arrays.sort(phoneNumbers);

    // Version 2
    // Use a bubble sort algoritm

//    int[] tempNums = new int[numPhoneNumbers];
//    
//    for (int i=0; i<numPhoneNumbers; i++){
//        tempNums[i] = Integer.parseInt((phoneNumbers[i]));
//    }
//    
//    for(int i=0;i<numPhoneNumbers-1;i++)
//    {
//        for(int j=i+1;j<numPhoneNumbers;j++)
//        {
//            if(tempNums[i]>tempNums[j])
//            {
//                int temp = tempNums[i];
//                tempNums[i] = tempNums[j];
//                tempNums[j] = temp;
//            }   
//        }
//    }
//    
//    for (int i=0; i<numPhoneNumbers; i++){
//        phoneNumbers[i] = Integer.toString(tempNums[i]);
//    }
    
  }

}
