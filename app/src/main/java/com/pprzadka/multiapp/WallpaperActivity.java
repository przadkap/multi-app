package com.pprzadka.multiapp;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class WallpaperActivity extends AppCompatActivity {

    ImageView bigPicture;

    public int toPhone;
    Double result;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        bigPicture = (ImageView) findViewById(R.id.bigImage);
        Button setWallpaper = (Button) findViewById(R.id.btn_change_wallpaper);
        setupView();
        setupNewEquation();

        setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isResultCorrect()) {
                    InputStream is = getResources().openRawResource(toPhone);
                    Bitmap picture = BitmapFactory.decodeStream(is);
                    WallpaperManager mywall = WallpaperManager.getInstance(getApplicationContext());
                    try{
                        mywall.setBitmap(picture);
                        showToast("Wallpaper changed!");
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //show toast
                    showToast("Incorrect result");

                }
                setupNewEquation();

            }
        });
    }

    private void showToast(CharSequence text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void setupView() {
        for(int i = 1; i < 13; i++) {
            final ImageView ig = new ImageView(this);
            String a = "smpic" + Integer.toString(i);
            int id = getResources().getIdentifier(a, "drawable", getPackageName());
            if(i == 1) {
                toPhone = getResources().getIdentifier("pic1", "drawable", getPackageName());
            }
            ig.setImageResource(id);
            ig.setId(i);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(15, 30, 25, 30);
            ig.setLayoutParams(lp);
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.li_layout_pics);
            linearLayout.addView(ig);
            ig.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int ide = ig.getId();
                    String b = "pic" + Integer.toString(ide);
                    int id = getResources().getIdentifier(b, "drawable", getPackageName());
                    bigPicture.setImageResource(id);
                    toPhone = id;
                    setupNewEquation();
                }
            });
        }
    }

    private void setupNewEquation() {
        Random rand = new Random();
        Integer firstNumber = rand.nextInt(20) + 1;
        Integer secondNumber = rand.nextInt(20) + 1;
        Integer operator = rand.nextInt(4);

        TextView tv_first_no = (TextView)findViewById(R.id.tv_first_no);
        TextView tv_operator = (TextView)findViewById(R.id.tv_operator);
        TextView tv_second_no = (TextView)findViewById(R.id.tv_second_no);
        EditText et_result = (EditText)findViewById(R.id.et_result);

        switch(operator) {
            case 0:
                tv_operator.setText("+");
                result = (double) firstNumber + secondNumber;
                break;
            case 1:
                tv_operator.setText("-");
                result = (double) firstNumber - secondNumber;
                break;
            case 2:
                tv_operator.setText("*");
                result = (double) firstNumber * secondNumber;
                break;
            case 3:
                tv_operator.setText("/");
                result = (double) firstNumber / secondNumber;
                break;
            default:
                result = 0.0;
        }

        result = round(result, 2);

        et_result.setHint(result.toString());
        tv_first_no.setText(firstNumber.toString());
        tv_second_no.setText(secondNumber.toString());

    }

    public boolean isResultCorrect() {
        EditText et_result = (EditText)findViewById(R.id.et_result);

//        String stringResult = et_result.getText().toString();
//
//        if(stringResult.isEmpty()) {
//            return false;
//        }
//        else
        try {
            double inputResult = Double.parseDouble(et_result.getText().toString());
            return (inputResult == result);
        }
        catch (NumberFormatException e) {
            return false;
        }


    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}