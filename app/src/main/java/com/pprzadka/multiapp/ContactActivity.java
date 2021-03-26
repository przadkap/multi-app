package com.pprzadka.multiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ContactActivity extends Activity {

    String name, starPattern;
    Boolean isNameRevealed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        setupStringData();
        Button toggleRevealButton = (Button) findViewById(R.id.btn_toggle_reveal);
        toggleRevealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleReveal();
            }
        });

    }

    private void toggleReveal() {
        TextView lblName = findViewById(R.id.tv_name);
        if(isNameRevealed) {
            lblName.setText(starPattern);
        } else {
            lblName.setText(name);
        }
        isNameRevealed = !isNameRevealed;
    }

    private void setupStringData() {
        Intent in = getIntent();

        name = in.getStringExtra(GV.TAG_NAME);
        String email = in.getStringExtra(GV.TAG_EMAIL);
        String address = in.getStringExtra(GV.TAG_ADDRESS);
        String gender = in.getStringExtra(GV.TAG_GENDER);
        String mobile = in.getStringExtra(GV.TAG_MOBILE);
        String home = in.getStringExtra(GV.TAG_HOME);
        String office = in.getStringExtra(GV.TAG_OFFICE);


        TextView lblName = findViewById(R.id.tv_name);
        TextView lblEmail = findViewById(R.id.tv_email);
        TextView lblAddress = findViewById(R.id.tv_address);
        TextView lblGender = findViewById(R.id.tv_gender);
        TextView lblMobile = findViewById(R.id.tv_mobile);
        TextView lblHome = findViewById(R.id.tv_home);
        TextView lblOffice = findViewById(R.id.tv_office);

        TextView lblPhone = findViewById(R.id.tv_hobbies);

        lblPhone.setText(mobile);

        lblEmail.setText(email);
        lblAddress.setText(address);
        lblGender.setText(gender);
        lblMobile.setText(mobile);
        lblHome.setText(home);
        lblOffice.setText(office);

        starPattern = generateStarPattern(name);
        lblName.setText(starPattern);
    }

    private String generateStarPattern(String name) {
        StringBuilder starPattern = new StringBuilder();
        for(char c : name.toCharArray()) {
            if(Character.isWhitespace(c)) {
                starPattern.append(" ");
            } else {
                starPattern.append("*");
            }
        }
        return starPattern.toString();
    }
}
