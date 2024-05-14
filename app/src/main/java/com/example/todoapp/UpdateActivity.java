package com.example.todoapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText task_input;
    TextView date_input, time_input;
    Button update_button, delete_button;
    String id, task, date, time;
    ImageView imageButtonDate, imageButtonTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        task_input = findViewById(R.id.task_input2);
        date_input = findViewById(R.id.date_input2);
        time_input = findViewById(R.id.time_input2);
        imageButtonDate = findViewById (R.id.imageButtonDate2);
        imageButtonTime = findViewById (R.id.imageButtonTime2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar task after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(task);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                task = task_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                time = time_input.getText().toString().trim();
                myDB.updateData(id, task, date, time);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

            }
        });

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

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("task") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("time")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            task = getIntent().getStringExtra("task");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");

            //Setting Intent Data
            task_input.setText(task);
            date_input.setText(date);
            time_input.setText(time);
            Log.d("step", task+" "+date+" "+time);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + task + " ?");
        builder.setMessage("Are you sure you want to delete " + task + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void openDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                date_input.setText(year + "."+ month + "."+ day);

            }
        }, 2024, 5, 15);

        datePickerDialog.show();
    }

    private void openTimePicker(){

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {


                //Showing the picked value in the textView
                time_input.setText(hour + ":"+ minute);

            }
        }, 15, 30, false);

        timePickerDialog.show();
    }
}