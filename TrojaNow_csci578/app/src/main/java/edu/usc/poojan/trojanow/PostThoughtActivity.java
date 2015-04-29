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

import edu.usc.poojan.PhotoComponent.PhotoActivity;


public class PostThoughtActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_thought);

        Button PhotoButton = (Button) findViewById(R.id.cameraButton);
        PhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),PhotoActivity.class);
                startActivity(i);
            }
        });

        Button sensorButton = (Button) findViewById(R.id.temperature);
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),SensorActivity.class);
                startActivity(i);
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


}
