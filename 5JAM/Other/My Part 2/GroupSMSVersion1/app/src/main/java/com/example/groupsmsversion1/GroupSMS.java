package com.example.groupsmsversion1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.telephony.SmsManager;


public class GroupSMS extends AppCompatActivity {

    /**
    * i am christian hear me roar
    **/
    private String message = "";
    private String phone = "";
    public static final String CURRENT_MESSAGE_DATA = "CURRENT_MESSAGE_DATA";
    public static final int NEW_MESSAGE_REQUEST = 1;
    public static final String CURRENT_PHONE_DATA = "CURRENT_PHONE_DATA";
    public static final int NEW_PHONE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sms);

        // Getting to the views defined in the XmL files.
        TextView tvMessageDetails = findViewById(R.id.tvMessageDetails);
        tvMessageDetails.setBackgroundColor(Color.GREEN);
        tvMessageDetails.setMovementMethod(new ScrollingMovementMethod());
        message = "Is it St. Patricks Day?";
        phone = "0483759934";
        setSummary();

        //Responding to an event - the onClick for the Edit Message Button
        //Using a named inner class
        Button btnEditMsg;
        btnEditMsg = this.findViewById(R.id.btnEditMsg);
        HandleBtnEditMsgOnC btnEditMsgOnC;
        btnEditMsgOnC = new HandleBtnEditMsgOnC();
        btnEditMsg.setOnClickListener(btnEditMsgOnC);

        //EditSendTo Button
        Button btnEditSendTo;
        btnEditSendTo = this.findViewById(R.id.btnEditSendTo);
        HandleBtnEditSendToOnC btnEditSendToOnC;
        btnEditSendToOnC  = new HandleBtnEditSendToOnC();
        btnEditSendTo.setOnClickListener(btnEditSendToOnC);

        //Send Button
        Button btnSend;
        btnSend = this.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    /*
                    Intent sendSMSIntent;
                    Uri uri = Uri.parse("smsto:" + phone);
                    sendSMSIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    sendSMSIntent.putExtra("sms_body", message);
                    startActivity(sendSMSIntent);
                    */
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, null, null);
                Toast toast = Toast.makeText(getApplicationContext(), "SMS sent to: " + phone, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 600);
                toast.show();
            }
        });

    }

    //Put together a summary of the phone and message and display it.
    private void setSummary() {
        StringBuilder summary;

        summary = new StringBuilder("Sending To:\n");
        summary.append(phone);
        summary.append("\n\nMessage: \n");
        summary.append(message);
        TextView tvMessageDetails = findViewById(R.id.tvMessageDetails);
        tvMessageDetails.setText(summary);
    }

    /*
    Handle Edit Button OnClick by starting the activity.
    Example of starting another activity using explicit intent.
     */

    @SuppressWarnings("rawtypes")
    public class HandleBtnEditMsgOnC implements View.OnClickListener {

        public static final String CLASS_TAG = "HandleBtnEditMsgOnC";

        public void onClick(View v) {
            Log.i(CLASS_TAG, "onClick started...");

            //Example of an EXPLICIT intent, as we are naming the java class to use
            // (EditMsg.class)
            Intent editIntent;
            Activity sourceActivity;
            Class destinationClass;

            sourceActivity = GroupSMS.this;
            destinationClass = EditMsg.class;
            editIntent = new Intent(sourceActivity, destinationClass);

            // Sending information to the intent receiver through the Intent object
            editIntent.putExtra(CURRENT_MESSAGE_DATA, GroupSMS.this.message);

            // startActivity(editIntent);
            startActivityForResult(editIntent, NEW_MESSAGE_REQUEST);
        }
    }

    public class HandleBtnEditSendToOnC implements View.OnClickListener {

        public static final String CLASS_TAG = "HandleBtnEditSendTo";

        public void  onClick(View v) {
            Log.i(CLASS_TAG, "onClick started...");

            // (EditSentTo.class)
            Intent editIntent;
            Activity sourceActivity;
            Class destinationClass;

            sourceActivity = GroupSMS.this;
            destinationClass = EditSendTo.class;
            editIntent = new Intent(sourceActivity, destinationClass);

            editIntent.putExtra(CURRENT_PHONE_DATA, GroupSMS.this.phone);

            startActivityForResult(editIntent, NEW_PHONE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == NEW_MESSAGE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String newMessage = data.getStringExtra(CURRENT_MESSAGE_DATA);
                message = newMessage;
                setSummary();
            }
        }

        // Check for EditSendTo
        if (requestCode == NEW_PHONE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String newPhone = data.getStringExtra(CURRENT_PHONE_DATA);
                phone = newPhone;
                setSummary();
            }
        }
    }
}
