package com.pprzadka.multiapp;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;

public class DateCalculatorActivity extends AppCompatActivity {

    Button dateFromButton, dateToButton, calculateButton;
    TextView dateFromTextView, dateToTextView, daysTextView, hoursTextView, minutesTextView;
    int currentDay, currentMonth, currentYear;
    int dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo;
    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_calculator);
        linkExistingObjects();
        setListeners();
        setupDefaultDates();
    }

    private void linkExistingObjects() {
        dateFromButton = (Button) findViewById(R.id.btn_dc_pick_from);
        dateToButton = (Button) findViewById(R.id.btn_dc_pick_to);
        calculateButton = (Button) findViewById(R.id.btn_dc_calculate);

        dateFromTextView = (TextView) findViewById(R.id.tv_dc_date_from);
        dateToTextView = (TextView) findViewById(R.id.tv_dc_date_to);
        daysTextView = (TextView) findViewById(R.id.tv_dc_days);
        hoursTextView = (TextView) findViewById(R.id.tv_dc_hours);
        minutesTextView = (TextView) findViewById(R.id.tv_dc_minutes);
    }

    private void setListeners() {
        dateFromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DateCalculatorActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                yearFrom = year;
                                monthFrom = month + 1;
                                dayFrom = day;
                                dateFromTextView.setText(dayFrom + "/" + monthFrom + "/" + yearFrom);
                            }
                        }, currentYear, currentMonth - 1, currentDay);
                datePickerDialog.show();
            }
        });

        dateToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DateCalculatorActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                yearTo = year;
                                monthTo = month + 1;
                                dayTo = day;
                                dateToTextView.setText(dayTo + "/" + monthTo + "/" + yearTo);
                            }
                        }, currentYear, currentMonth - 1, currentDay);
                datePickerDialog.show();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
                LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);
                long daysDifference = Duration.between(dateFrom.atStartOfDay(), dateTo.atStartOfDay()).toDays();
                long hoursDifference = Duration.between(dateFrom.atStartOfDay(), dateTo.atStartOfDay()).toHours();
                long minutesDifference = Duration.between(dateFrom.atStartOfDay(), dateTo.atStartOfDay()).toMinutes();
//                if(dateFrom.isAfter(dateTo)) {
//                    daysDifference = -daysDifference;
//                    hoursDifference = -hoursDifference;
//                    minutesDifference = -minutesDifference;
//                }
                daysTextView.setText(daysDifference + " days");
                hoursTextView.setText(hoursDifference + " hours");
                minutesTextView.setText(minutesDifference + " minutes");
            }
        });
    }
    private void setupDefaultDates() {
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        yearTo = currentYear;
        yearFrom = currentYear;
        monthFrom = currentMonth;
        monthTo = currentMonth;
        dayFrom = currentDay;
        dayTo = currentDay;
        daysTextView.setText("0 days");
        hoursTextView.setText("0 hours");
        minutesTextView.setText("0 minutes");
        dateFromTextView.setText(currentDay + "/" + currentMonth + "/" + currentYear);
        dateToTextView.setText(currentDay + "/" + currentMonth + "/" + currentYear);
    }
}
