package com.example.groupsmsversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

public class EditMsg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_msg);
        // Get the intent for this activity. Every activity has an intent and
        // set the EditText contents to the string in the extra info that comes with
        // the intent
        Intent editIntent;
        EditText etMessage;
        editIntent = this.getIntent();
        String theMessage;
        theMessage = editIntent.getStringExtra(GroupSMS.CURRENT_MESSAGE_DATA);
        etMessage = this.findViewById(R.id.etMessage);
        etMessage.setText(theMessage);

        // Get an event handler going for the Done button so that we can return the
        // new message
        Button btnDoneM = this.findViewById(R.id.btnDoneM);
        btnDoneM.setOnClickListener(new BtnDoneMOnClickHandler());
    }

    private class BtnDoneMOnClickHandler implements View.OnClickListener {

        public void onClick(View v) {

            Intent intent = new Intent();
            intent.putExtra(GroupSMS.CURRENT_MESSAGE_DATA,
                    ((EditText) findViewById(R.id.etMessage)).getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}














