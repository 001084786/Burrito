package com.example.groupsmsversion1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditSendTo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_send_to);

        Intent editIntent;
        EditText etPhone;
        editIntent = this.getIntent();
        String thePhone;
        thePhone = editIntent.getStringExtra(GroupSMS.CURRENT_PHONE_DATA);
        etPhone = this.findViewById(R.id.etPhone);
        etPhone.setText(thePhone);

        Button btnDoneP = this.findViewById(R.id.btnDoneP);
        btnDoneP.setOnClickListener(new BtnDonePOnClickHandler());
    }

    private class BtnDonePOnClickHandler implements View.OnClickListener {

        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra(GroupSMS.CURRENT_PHONE_DATA,
                    ((EditText) findViewById(R.id.etPhone)).getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}






























