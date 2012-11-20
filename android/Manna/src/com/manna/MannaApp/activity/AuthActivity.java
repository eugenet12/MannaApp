package com.manna.MannaApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.manna.MannaApp.R;
import com.manna.MannaApp.helper.SharedPrefsHelper;

public class AuthActivity extends Activity {

    private EditText nameEntry;
    private EditText yearEntry;
    private EditText emailEntry;
    private Button goButton;
    private Button anonButton;

    SharedPrefsHelper prefsHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());
        if (prefsHelper.getFirst())
            startHomeActivity();
        else {
            setContentView(R.layout.activity_auth);

            nameEntry = (EditText) findViewById(R.id.auth_name_entry);
            yearEntry = (EditText) findViewById(R.id.auth_name_entry);
            emailEntry = (EditText) findViewById(R.id.auth_name_entry);
            goButton = (Button) findViewById(R.id.auth_btn_go);
            anonButton = (Button) findViewById(R.id.auth_btn_anon);

            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefsHelper.saveName(nameEntry.getText().toString());
                    prefsHelper.saveYear(yearEntry.getText().toString());
                    prefsHelper.saveEmail(emailEntry.getText().toString());
                    prefsHelper.setFirst();
                    startHomeActivity();
                }
            });

            anonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefsHelper.setFirst();
                    startHomeActivity();
                }
            });
        }
    }

    private void startHomeActivity() {
        Log.d("MANNA", "start home");
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}
