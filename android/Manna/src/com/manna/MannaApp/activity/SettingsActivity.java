package com.manna.MannaApp.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.manna.MannaApp.R;
import com.manna.MannaApp.helper.SharedPrefsHelper;

public class SettingsActivity extends Activity {

    private TextView name;
    private TextView year;
    private TextView email;
    private Dialog nameDialog;
    private Dialog yearDialog;
    private Dialog emailDialog;

    SharedPrefsHelper prefsHelper;

    private enum DIALOGSWITCH {
        NAME,
        YEAR,
        EMAIL
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsHelper = new SharedPrefsHelper(getApplicationContext());

        setContentView(R.layout.activity_settings);

        name = (TextView) findViewById(R.id.settings_name);
        year = (TextView) findViewById(R.id.settings_year);
        email = (TextView) findViewById(R.id.settings_email);

        refresh();

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameDialog.show();
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearDialog.show();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailDialog.show();
            }
        });
    }

    private Dialog createDialog(String titleString, String textString, String editHintString, String yesText, String noText, final DIALOGSWITCH dialogSwitch) {
        final Dialog dialog = new Dialog(SettingsActivity.this, R.style.dialog_no_title);
        dialog.setContentView(R.layout.settings_dialog);

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
        final EditText edit = (EditText) dialog.findViewById(R.id.dialog_edit);
        final Button yes = (Button) dialog.findViewById(R.id.dialog_yes);
        final Button no = (Button) dialog.findViewById(R.id.dialog_no);

        title.setText(titleString);
        text.setText(textString);
        edit.setHint(editHintString);
        yes.setText(yesText);
        no.setText(noText);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogSwitch == DIALOGSWITCH.NAME)
                    prefsHelper.saveName(edit.getText().toString());
                else if (dialogSwitch == DIALOGSWITCH.YEAR)
                    prefsHelper.saveYear(edit.getText().toString());
                else if (dialogSwitch == DIALOGSWITCH.EMAIL)
                    prefsHelper.saveEmail(edit.getText().toString());

                dialog.dismiss();
                refresh();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.setCancelable(true);
        return dialog;
    }

    private void refresh() {
        name.setText(prefsHelper.getName());
        year.setText(prefsHelper.getYear());
        email.setText(prefsHelper.getEmail());

        nameDialog = createDialog("Change your name?", prefsHelper.getName(), "Enter new name here", "Save", "Cancel", DIALOGSWITCH.NAME);
        yearDialog = createDialog("Change your year?", prefsHelper.getYear(), "Enter new year here", "Save", "Cancel", DIALOGSWITCH.YEAR);
        emailDialog = createDialog("Change your email?", prefsHelper.getEmail(), "Enter new email here", "Save", "Cancel", DIALOGSWITCH.EMAIL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if ((nameDialog != null) && (nameDialog.isShowing()))
            nameDialog.dismiss();
        if ((yearDialog != null) && (yearDialog.isShowing()))
            yearDialog.dismiss();
        if ((emailDialog != null) && (emailDialog.isShowing()))
            emailDialog.dismiss();
    }
}
