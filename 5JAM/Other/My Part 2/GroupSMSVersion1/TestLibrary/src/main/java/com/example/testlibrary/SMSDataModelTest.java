package com.example.testlibrary;

public class SMSDataModelTest {

    public static void main(String args[]) {
        System.out.println("Console app to test the SMSDataModel");
        // etc etc

        //no arg constructor
        SMSDataModelList test = new SMSDataModelList("", 5);
        //SMSDataModelArray test = new SMSDataModelArray();

        //Add 5 Phone Numbers
        try {
            test.addPhoneNumber("000");
            test.addPhoneNumber("111");
            test.addPhoneNumber("222");
            test.addPhoneNumber("222"); //Duplicate
            test.addPhoneNumber("333");
            test.addPhoneNumber("444");
            test.addPhoneNumber("555"); //6th Phone number (exceeds max numbers)
        }
        catch (SMSDataModelFullException e){
            System.out.println("Error: " + e);
        }
        System.out.println("\nAdd Numbers");
        System.out.println(test);

        //Find Phone Number
        System.out.println("\nFind Number 333");
        System.out.println(test);
        System.out.println("Result: " + test.findPhoneNumberIndex("333"));

        //Update Phone Number
        test.updatePhoneNumber("999", 0);
        System.out.println("\nUpdate Number (000) to (999)");
        System.out.println(test);

        //Update Phone Number again
        test.updatePhoneNumber("999", 1);
        System.out.println("\nUpdate Number (111) to (999)");
        System.out.println(test);

        //Delete Phone Number
        test.deleteNumber(3);
        System.out.println("\nDelete Number (333)");
        System.out.println(test);

        //Get Max Phone Numbers
        System.out.println("\nGet Max Phone Numbers");
        System.out.println(test);
        System.out.println("Max Amount: " + test.getMaxNumPhoneNumbers());

        //Check IsFull
        System.out.println("\nCheck IsFull");
        System.out.println(test);
        System.out.println("IsFull: " + test.isFull());

        //Get Phone Number
        System.out.println("\nGet Phone Number (444)");
        System.out.println(test);
        System.out.println("Get phone number: " + test.getPhoneNumber(3));

        //Get Num Phone Number
        System.out.println("\nGet Num of Phone Number");
        System.out.println(test);
        System.out.println("Get num phone number: " + test.getNumPhoneNumbers());

        //Set Message
        System.out.println("\nSet message");
        test.setMessage("yeah");
        System.out.println(test);
        System.out.println("Set message: yeah");

        //Get Message
        System.out.println("\nGet message");
        System.out.println(test);
        System.out.println("Get message: " + test.getMessage());

        //Sort Numbers
        System.out.println("\nSort numbers");
        test.sortNumbers();
        System.out.println(test);


    }
}
