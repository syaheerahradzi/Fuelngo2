package com.example.user.fuelngo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class addevent extends AppCompatActivity {


    ImageButton btnstartdate, btnenddate, btnstarttime, btnendtime;
    Button btnsave;
    String format;
    DatabaseReference rootRef, demoRef, event;
    int PLACE_PICKER_REQUEST = 1;
    TextView tvaddress, tvname;
    Context context = this;
    EditText editDate;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener startdate,enddate;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.GERMAN);
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        final EditText etname = (EditText) findViewById(R.id.etname);
        final EditText etstartdate = (EditText) findViewById(R.id.etstartdate);
        final EditText etenddate = (EditText) findViewById(R.id.etenddate);
        final EditText etstarttime = (EditText) findViewById(R.id.etstarttime);
        final EditText etendtime = (EditText) findViewById(R.id.etendtime);
        final EditText etdescription = (EditText) findViewById(R.id.etdescription);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnstartdate = (ImageButton) findViewById(R.id.btnstartdate);
        btnenddate = (ImageButton) findViewById(R.id.btnenddate);
        btnstarttime = (ImageButton) findViewById(R.id.btnstarttime);
        btnendtime = (ImageButton) findViewById(R.id.btnendtime);
        tvname = (TextView) findViewById(R.id.tvname);
        tvaddress = (TextView) findViewById(R.id.tvaddress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        startdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                etstartdate.setText(sdf.format(myCalendar.getTime()));

            }
        };
        enddate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                etenddate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        etstartdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, startdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        etenddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, enddate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etstarttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addevent.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        if (selectedHour == 0) {

                            selectedHour += 12;

                            format = "AM";
                        } else if (selectedHour == 12) {

                            format = "PM";

                        } else if (selectedHour > 12) {

                            selectedHour -= 12;

                            format = "PM";

                        } else {

                            format = "AM";
                        }

                        etstarttime.setText(selectedHour + ":" + selectedMinute + format);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });


        etendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addevent.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        if (selectedHour == 0) {

                            selectedHour += 12;

                            format = "AM";
                        } else if (selectedHour == 12) {

                            format = "PM";

                        } else if (selectedHour > 12) {

                            selectedHour -= 12;

                            format = "PM";

                        } else {

                            format = "AM";
                        }

                        etendtime.setText(selectedHour + ":" + selectedMinute + format);
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database references pointing to root of database
                rootRef = FirebaseDatabase.getInstance().getReference();

                //database references demo to node
                    demoRef = rootRef.child("Events");

                String key = demoRef.push().getKey();
                event = demoRef.child(key);
                String eventname = etname.getText().toString();
                event.child("title").setValue(eventname);
                String locationName = tvname.getText().toString();
                event.child("location").setValue(locationName);
                String locationAddress = tvaddress.getText().toString();
                event.child("address").setValue(locationAddress);
                String startdate = etstartdate.getText().toString();
                event.child("startdate").setValue(startdate);
                String enddate = etenddate.getText().toString();
                event.child("enddate").setValue(enddate);
                String starttime = etstarttime.getText().toString();
                event.child("starttime").setValue(starttime);
                String endtime = etendtime.getText().toString();
                event.child("endtime").setValue(endtime);
                String desc = etdescription.getText().toString();
                event.child("description").setValue(desc);

                Toast.makeText(addevent.this, "Event added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addevent.this, event.class));
                finish();

            }
        });
    }

    public void goPlacePicker(View view) {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {

            startActivityForResult(builder.build(addevent.this), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                final Place place = PlacePicker.getPlace(addevent.this, data);
                final CharSequence name = place.getName();
                final CharSequence address = place.getAddress();

                tvname.setText(name);
                tvaddress.setText(address);

            }
        }

    }
}


