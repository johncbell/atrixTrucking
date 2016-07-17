package com.johncbell.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;

import com.johncbell.atrixtrucking.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class setDate extends Activity implements OnClickListener {

    //UI References
    private EditText enteredDate;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();
    }

    private void findViewsById() {
        enteredDate = (EditText) findViewById(R.id.enteredDate);
        enteredDate.setInputType(InputType.TYPE_NULL);
        enteredDate.requestFocus();
    }

    private void setDateTimeField() {
        enteredDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                enteredDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View view) {
        if(view == enteredDate) {
            fromDatePickerDialog.show();
        }
    }
}