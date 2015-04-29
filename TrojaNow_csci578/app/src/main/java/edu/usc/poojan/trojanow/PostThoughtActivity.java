package edu.usc.poojan.trojanow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseException;

import edu.usc.poojan.Location.LocationActivity;
import edu.usc.poojan.PhotoComponent.PhotoActivity;


public class PostThoughtActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_thought);

//        Button PhotoButton = (Button) findViewById(R.id.cameraButton);
//        PhotoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(),PhotoActivity.class);
//                startActivity(i);
//            }
//        });

        Button sensorButton = (Button) findViewById(R.id.temperature);
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),SensorActivity.class);
                startActivityForResult(i, 1);
            }
        });


        Button locationButton = (Button) findViewById(R.id.location);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),LocationActivity.class);
                startActivityForResult(i, 1);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelbutton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button postButton = (Button) findViewById(R.id.postthoughtbutton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText content = (EditText) findViewById(R.id.editText);
                Switch anonymous = (Switch) findViewById(R.id.anoymousSwitch);
                if(content.getText().length()>0) {

                    String anonymousString = "Anonymous";
                    ParseUser currentUser = ParseUser.getCurrentUser();

                    if(!anonymous.isChecked())
                    {
                        anonymousString = currentUser.get("Name").toString();
                    }

                    ParseObject thought = new ParseObject("Thought");
                    thought.put("content", content.getText().toString());
                    thought.put("byUser", currentUser);
                    thought.put("username", anonymousString);
                    try {
                        thought.save();
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    // Invalid thought content. Display error or do not do anything
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            String sensorInfo = data.getStringExtra("SensorInfo");
            if(sensorInfo!=null) {
                System.out.println("sensorInfo = " + sensorInfo);
                if (sensorInfo.length() > 0) {
                    EditText content = (EditText) findViewById(R.id.editText);
                    String text = String.valueOf(content.getText());

                    if (text.length() > 0) {
                        text = text.concat(sensorInfo);
                        content.setText(text);
                    } else {
                        content.setText(sensorInfo);
                    }
                }
            }

            String locationInfo = data.getStringExtra("LocationInfo");
            if(locationInfo!=null) {
                System.out.println("locationInfo = " + locationInfo);
                if (locationInfo.length() > 0) {
                    EditText content = (EditText) findViewById(R.id.editText);
                    String text = String.valueOf(content.getText());
                    if (text.length() > 0) {
                        text = text.concat(locationInfo);
                        content.setText(text);
                    } else {
                        content.setText(locationInfo);
                    }
                }
            }



        }
    }

}
