/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Handles a full SMSDataModel exception
 *
 * @author sruiz
 */
class SMSDataModelFullException extends Exception {

  private String phoneNumber;

  public SMSDataModelFullException(String thePhoneNumber) {
    super("SMSDataModel is full.");
    this.phoneNumber = thePhoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

}
