package com.example.mygpschatapp;

import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextViewActivity extends FragmentActivity {

    //initialise variables
    LinearLayout layout;
    ScrollView scrollView;
    Button back;
    List<Location> locations = new ArrayList<>();
    List<TextView> textBoxes = new ArrayList<>();
    Boolean newLocationAdded;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("locations");

    //runs when the text view button of the main activity is selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the layout and adds the background and buttons
        setContentView(R.layout.activity_textview);
        back = findViewById(R.id.backButton);
        scrollView = findViewById(R.id.scrollView);
        layout = findViewById(R.id.layout1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LocationAndMessage data = dataSnapshot.getValue(LocationAndMessage.class);
                Location newLocation = new Location("");
                newLocation.setLongitude(data.longitude);
                newLocation.setLatitude((data.latitude));
                newLocationAdded = true;
                //if in an existing location append message to already existing text box
                for (int i = 0; i < locations.size(); i++) {
                    if (newLocation.distanceTo(locations.get(i)) <= 10) {
                        newLocationAdded = false;
                        String oldMessage = textBoxes.get(i).getText().toString();
                        String date = data.date;
                        String message = data.message;
                        textBoxes.get(i).setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + date + "\n" + message + " \n\n" + oldMessage);
                        break;
                    }
                }
                //if in new location add new text box with new message
                if (newLocationAdded == true) {
                    locations.add(newLocation);

                    String lng = (String.valueOf(data.longitude).substring(0, 7));
                    String lat = (String.valueOf(data.latitude).substring(0, 7));
                    String date = data.date;
                    String message = data.message;
                    String location = "\nLocation: " + lat + " " + lng + " \n______________________________________________________________";
                    addMessageBox(message, location, date);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    //method to create a new text box
    public void addMessageBox(String message, String location, String date) {
        TextView textView = new TextView(TextViewActivity.this);
        textView.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + date + "\n" + message + " \n" + location);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 7.0f;
        lp2.gravity = Gravity.LEFT;

        textView.setLayoutParams(lp2);
        textBoxes.add(textView);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
