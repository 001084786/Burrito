

/**
 * A Test class to test the implementations of the SMSDataModelInterface.
 * 
 * @author aleferro
 */
public class SMSDataModelTest {
    
    /**
     * The main method of the test class provide instances of both the array
     * and the list implementations of the interface.
     * @param args
     * @throws SMSDataModelFullException
     */
    public static void main(String[] args) throws SMSDataModelFullException {
        
        SMSDataModelInterface sms;
        String[] phoneNums = {"1111", "2222", "3333", "4444"};
        
        //********************************************
        // TEST NO ARGS CONSTRUCTOR
        System.out.println("NO ARGS CONSTRUCTOR WITH NO LIMIT TO PHONE NUMBERS");
        //sms = new SMSDataModelArray();    // Implement the interface as an Array.
        sms = new SMSDataModelList();     // Implement the interface as a List.
        
        // Populate the List with 50 strings to show the list has no limit.
        // Uncomment just when the List is used.
        for(int i=0; i<50; i++){
            sms.addPhoneNumber("ale"+i);
        }
        System.out.println(sms);
        
        //********************************************
        // TEST MESSAGE ONLY CONSTRUCTOR
        System.out.println("\nMESSAGE ONLY CONSTRUCTOR");
        String message = "Hello!!!";
        //sms = new SMSDataModelArray(message);   // Implement the Interface as an Array.
        sms = new SMSDataModelList(message);    // Implement the Interface as a List.
        System.out.println(sms);
        
        //*******************************************
        // TEST ALL ARGS CONSTRUCTOR
        System.out.println("\nALL ARGS CONSTRUCTOR");
        String initMessage = "Good Morning!";
        int maxPhoneNo = 5;
        //sms = new SMSDataModelArray(initMessage, maxPhoneNo);  // Implement the Interface as an Array.
        sms = new SMSDataModelList(initMessage, maxPhoneNo);   // Implement the Interface as a List.
        System.out.println(sms);
        
        //*******************************************
        // TEST METHOD addPhoneNumber
        System.out.println("\nTEST METHOD addPhoneNumber: ADD 4 NUMBERS -- ADD A DUPLICATE / ADD FIFTH NUMBER / ARRAY IS FULL");
        for (String phoneNum : phoneNums) {
            sms.addPhoneNumber(phoneNum);
        }
        System.out.println(sms);
        // Trying adding a duplicate
        System.out.println("Add 1111: " + sms.addPhoneNumber("1111"));
        System.out.println(sms);
        // Adding another valid number to fill up the array/list
        System.out.println("Add 5555: " + sms.addPhoneNumber("5555"));
        System.out.println(sms);
        // Trying adding one more number but the array/list is full
        String numToAdd = "0000";
        try{
            sms.addPhoneNumber(numToAdd);
        }catch(SMSDataModelFullException e){
            System.out.println("Add " + numToAdd + ": FULL");
        }
//        System.out.println("Add extra: " + sms.addPhoneNumber("0000"));
        System.out.println(sms);
        
        //********************************************
        // TEST METHOD findPhoneNumberIndex
        System.out.println("\nTEST METHOD findPhoneNumberIndex -- NUMBER IN ARRAY or LIST/ NUMBER NOT IN ARRAY or LIST");
        // numToFind is part of the array/list
        String numToFind = "5555";
        int numAtIndex;
        numAtIndex = sms.findPhoneNumberIndex(numToFind);
        System.out.println("The number " + numToFind + " is at index " + numAtIndex);
        // numToFind is not in the array/list
        numToFind = "0000";
        numAtIndex = sms.findPhoneNumberIndex(numToFind);
        System.out.println("The number " + numToFind + " is at index " + numAtIndex);
        
        //********************************************
        // TEST METHOD updatePhoneNumber
        System.out.println("\nTEST METHOD updatePhoneNumber -- INDEX < 0 / INDEX > ARRAY or LIST LENGTH / INDEX = 0 / DUPLICATE");
        String result;
        String newNum = "0000";
        // The position required is below 0
        int i = -1;
        result = "Update: " + sms.updatePhoneNumber(newNum, i);
        System.out.println(result);
        // The position required is above the array/list length
        i = 5;
        result = "Update: " + sms.updatePhoneNumber(newNum, i);
        System.out.println(result);
        // The position required is at index 0
        i = 0;
        result = "Update: " + sms.updatePhoneNumber(newNum, i);
        System.out.println(result);
        // The update would result in a duplicate
        newNum = "2222";
        result = "Update: " + sms.updatePhoneNumber(newNum, i);
        System.out.println(result);
        System.out.println(sms);
        
        //*********************************************
        // TEST METHOD getPhoneNumber
        System.out.println("\nTEST METHOD getPhoneNumber -- INDEX < 0 / INDEX > ARRAY or LIST LENGTH / INDEX = 0");
        // Index is negative
        i = -1;
        result = sms.getPhoneNumber(i);
        System.out.println("The number at position " + i + " is " + result);
        // Index is bigger than array/lisst length
        i = 10;
        result = sms.getPhoneNumber(i);
        System.out.println("The number at position " + i + " is " + result);
        // Index = 0
        i = 0;
        result = sms.getPhoneNumber(i);
        System.out.println("The number at position " + i + " is " + result);
        
        //********************************************
        // TEST METHOD deleteNumber
        System.out.println("\nTEST METHOD deleteNumber -- INDEX < 0 / INDEX > ARRAY or LIST LENGTH / INDEX = 0");
        // Index is negative
        i = -1;
        result = sms.deleteNumber(i);
        System.out.println("Delete " + result);
        // Index is bigger than array/list length
        i = 10;
        result = sms.deleteNumber(i);
        System.out.println("Delete " + result);
        // Index = 0
        i = 0;
        result = sms.deleteNumber(i);
        System.out.println("Delete " + result);
        // new array/list
        System.out.println(sms);
        
        //******************************************
        // TEST METHOD getMaxPhoneNumber
        System.out.println("\nTEST METHOD getMaxPhoneNumber");
        int maxNums = sms.getMaxNumPhoneNumbers();
        System.out.println("Max Phone Number: " + maxNums);
        
        //******************************************
        // TEST METHOD isFull
        System.out.println("\nTEST METHOD isFull");
        // Array/List not full
        System.out.println("Array/List at this stage: " + sms);
        boolean boolFull = sms.isFull();
        System.out.println("Array/List full: " + boolFull);
        // Array/Listfull
        sms.addPhoneNumber("1111");
        System.out.println("Array/List after adding one more phone number: " + sms);
        boolFull = sms.isFull();
        System.out.println("Array/Listfull: " + boolFull);
        
        //******************************************
        // TEST METHOD getMessage
        System.out.println("\nTEST METHOD getMessage");
        String daMessage;
        daMessage = sms.getMessage();
        System.out.println("Message: " + daMessage);
        
        //******************************************
        // TEST METHOD setMessage
        System.out.println("\nTEST METHOD setMessage WITH MESSAGE 'GOOD AFTERNOON!'");
        sms.setMessage("Good Afternoon!");
        System.out.println("Message: " + sms.getMessage());
        
        //******************************************
        // TEST METHOD getPhoneNumbers
        System.out.println("\nTEST METHOD getPhoneNumbers");
        String[] currentNumbers = sms.getPhoneNumbers();
        for(String curNum : currentNumbers){
            System.out.println(curNum);
        }
        
        //*****************************************
        // TEST METHOD getNumPhoneNumbers
        System.out.println("\nTEST METHOD getNumPhoneNumbers");
        int numPhoneNumbers = sms.getNumPhoneNumbers();
        System.out.println("The number of phone numbers is: " + numPhoneNumbers);
        
        //*****************************************
        // TEST METHOD sortNumbers. Exception expected
        System.out.println("\nTEST METHOD sortNumbers -- TWO VERSIONS -- check code");
        System.out.println("BEFORE: " + sms);
        sms.sortNumbers();
        System.out.println("AFTER: " + sms);
        
        
    }
}
