package com.example.user.fuelngo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addlocation extends AppCompatActivity {

    ImageButton imgbtnpic;
    Button btncapture, btnadd;
    TextView tvname, tvaddress;
    CheckBox cbatm, cbtoilet, cbprayer, cbstore;
    int PLACE_PICKER_REQUEST = 1;
    Intent intent;
    DatabaseReference rootRef, demoRef, place;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlocation);

        ImageButton btnback;

        imgbtnpic = (ImageButton) findViewById(R.id.imgbtnpic);
        btncapture = (Button) findViewById(R.id.btncapture);
        btnadd = (Button) findViewById(R.id.btnadd);
        tvname = (TextView) findViewById(R.id.tvname);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        cbatm = (CheckBox) findViewById(R.id.cbatm);
        cbstore = (CheckBox) findViewById(R.id.cbstore);
        cbtoilet = (CheckBox) findViewById(R.id.cbtoilet);
        cbprayer = (CheckBox) findViewById(R.id.cbprayer);


        EnableRuntimePermission();

        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database references pointing to root of database
                rootRef = FirebaseDatabase.getInstance().getReference();

                //database references demo to node
                demoRef = rootRef.child("location");

                String locationName = tvname.getText().toString();
                place = demoRef.child(locationName);
                String locationAddress = tvaddress.getText().toString();
                place.child("Address").setValue(locationAddress);

                if (cbatm.isChecked()) {
                    String locationAtm = cbatm.getText().toString();
                    place.child("ATM").setValue(locationAtm);
                }
                if (cbstore.isChecked()) {
                    String locationStore = cbstore.getText().toString();
                    place.child("Store").setValue(locationStore);
                }
                if (cbprayer.isChecked()) {
                    String locationPrayerroom = cbprayer.getText().toString();
                    place.child("prayerroom").setValue(locationPrayerroom);
                }
                if (cbtoilet.isChecked()) {
                    String locationToilet = cbtoilet.getText().toString();
                    place.child("Toilet").setValue(locationToilet);
                }


                Toast.makeText(addlocation.this, "Location added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(addlocation.this, MainActivity.class));
                finish();

            }
        });

    }

    private void EnableRuntimePermission() {
    }

    public void goPlacePicker(View view)
    {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try{

            startActivityForResult(builder.build(addlocation.this),PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode==PLACE_PICKER_REQUEST){
            if(resultCode==RESULT_OK){
                final Place place = PlacePicker.getPlace(addlocation.this,data);
                final CharSequence name = place.getName();
                final CharSequence address = place.getAddress();

                tvname.setText(name);
                tvaddress.setText(address);

            }
        }
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            imgbtnpic.setImageBitmap(bitmap);
        }
    }





}
