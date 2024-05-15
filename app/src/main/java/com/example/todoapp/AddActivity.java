package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText task_input;
    TextView date_input, time_input;
    Button add_button;
    ImageView imageButtonDate, imageButtonTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        task_input = findViewById (R.id.task_input);
        date_input = findViewById (R.id.date_input);
        time_input = findViewById (R.id.time_input);
        add_button = findViewById(R.id.add_button);
        imageButtonDate = findViewById (R.id.imageButtonDate);
        imageButtonTime = findViewById (R.id.imageButtonTime);

        imageButtonDate.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openDatePicker(); // Open date picker dialog
            }
        });

        imageButtonTime.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openTimePicker(); //Open time picker dialog
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addTask(task_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        time_input.getText().toString().trim());
            }
        });


    }

    private void openDatePicker() {
        // Get current date to pre-fill the date picker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        // Display the selected date in the text view
                        date_input.setText(selectedYear + "." + (selectedMonth + 1) + "." + selectedDayOfMonth);
                    }
                }, year, month, dayOfMonth);

        // Show the date picker dialog
        datePickerDialog.show();
    }


    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {


                //Showing the picked value in the textView
                time_input.setText(hour + ":"+ minute);

            }
        }, 15, 30, false);

        timePickerDialog.show();
    }

}