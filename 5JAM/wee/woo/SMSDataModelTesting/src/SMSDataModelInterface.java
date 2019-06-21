
/**
 * This interface defines a model that maintains a list of phoneNumbers and
 * one message.
 * It allows phone numbers to be added but only if not already in the list.
 * The maximum number of phone numbers allowed can be set when the SMSDateModel
 * object is created. If none is set then there is no maximum.
 *
 * @author S. Ruiz
 */
public interface SMSDataModelInterface {

  /**
   * Adds a new phone number.
   *
   * @param newPhoneNumber - the number to be added
   * @return "DUPLICATE" if the number is already in the list otherwise returns
   * the number that was added
   * @throws SMSDataModelFullException
   */
  public String addPhoneNumber(String newPhoneNumber) throws
    SMSDataModelFullException;

  /**
   * Finds the position in the array of the target phone number
   *
   * @param targetNumber the target phone number
   * @return returns the index of the number in the array if found otherwise
   * returns -1
   */
  public int findPhoneNumberIndex(String targetNumber);

  /**
   * Updates a phone number at a particular position in the list
   *
   * @param newPhoneNumber - the number to be use
   * @param i - the position to update
   * @return null if the number cannot be updated because the index i is out of
   * range "DUPLICATE" if the number being provided, newPhoneNumber, is actually
   * already in the model otherwise returns the number just replaced
   */
  public String updatePhoneNumber(String newPhoneNumber, int i);

  /**
   * Get a phone number at a particular position in the list
   *
   * @param i - the position to retrieve
   * @return null if the number cannot be retrieved because the index i is out
   * of range otherwise returns the number at position i
   */
  public String getPhoneNumber(int i);

  /**
   * Deletes a phone number at a particular position in the list
   *
   * @param i - the position to delete
   * @return null if the number cannot be deleted because the index i is out of
   * range otherwise returns the number that is deleted
   */
  public String deleteNumber(int i);

  /**
   * @return the max number of phone numbers that this model can hold, -1 if
   * there is no Maximum
   */
  public int getMaxNumPhoneNumbers();

  /**
   * @return true if the Model has reached the maximum number of phone numbers
   * it can hold, false otherwise
   */
  public boolean isFull();

  /**
   * @return an Array of the phone numbers in the model.
   */
  public String[] getPhoneNumbers();

  /**
   * @return the number of phone numbers in the model
   */
  public int getNumPhoneNumbers();

  /**
   * @return the message in the model
   */
  public String getMessage();

  /**
   * Sets the message in the model to the one provided, mMessage
   *
   * @param mMessage The new text message for this model
   */
  public void setMessage(String mMessage);

  /**
   * Sorts the phone numbers in the model so that they are in phone number order
   */
  public void sortNumbers();

}
