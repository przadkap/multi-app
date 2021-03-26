package com.pprzadka.multiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


//TODO make 2 new button types DONE
//TODO make hangman unable to take repeating misses (e.g. 2 times 'M') DONE
//TODO maybe look into too consistent randomness in hangman

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    Button wallpaperButton, contactButton, textGenButton, dateCalcButton, myActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkExistingObjects();
        setListeners();
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.isChecked()) {
                    return false;
                }
                switch(item.getItemId()) {
                    case R.id.nav_wallpaper : {
                        startWallpaperActivity();
                        return true;
                    }
                    case R.id.nav_contacts : {
                        startContactActivity();
                        return true;
                    }
                    case R.id.nav_date_calculator : {
                        startDateCalculatorActivity();
                        return true;
                    }
                    case R.id.nav_text_generator : {
                        startTextGeneratorActivity();
                        return true;
                    }
                    case R.id.nav_game : {
                        startHangmanActivity();
                        return true;
                    }
                }

                return false;
            }
        });
    }



    private void linkExistingObjects() {
        wallpaperButton = (Button) findViewById(R.id.btn_wallpaper_changer);
        contactButton = (Button) findViewById(R.id.btn_contact_list);
        textGenButton = (Button) findViewById(R.id.btn_text_generator);
        dateCalcButton = (Button) findViewById(R.id.btn_date_calculator);
        myActivityButton = (Button) findViewById(R.id.btn_sth_useful);
    }

    private void setListeners() {
        wallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WallpaperActivity.class);
                startActivity(intent);
            }
        });
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivity(intent);
            }
        });
        textGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TextGeneratorActivity.class);
                startActivity(intent);
            }
        });
        dateCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DateCalculatorActivity.class);
                startActivity(intent);
            }
        });
        myActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HangmanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startDateCalculatorActivity() {
        Intent intent = new Intent(getApplicationContext(), DateCalculatorActivity.class);
        startActivity(intent);
    }

    private void startContactActivity() {
        Log.d("CLICKED", "NAV_SETTINGS");
        Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
        startActivity(intent);
    }

    private void startWallpaperActivity() {
        Log.d("CLICKED", "NAV_SEARCH");
        Intent intent = new Intent(getApplicationContext(), WallpaperActivity.class);
        startActivity(intent);
    }

    private void startHangmanActivity() {
        Log.d("IT WORKED", "congrats");
        Intent intent = new Intent(getApplicationContext(), HangmanActivity.class);
        startActivity(intent);
    }

    private void startTextGeneratorActivity() {
        Intent intent = new Intent(getApplicationContext(), TextGeneratorActivity.class);
        startActivity(intent);
    }
}