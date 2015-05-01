package edu.usc.trojanow.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * This activity allows a new user to register. All text fields are required.
 * @author Trina Gregory, Poojan Jhaveri
 * Created for CSCI-578, Spring 2015
 */
public class RegisterActivity extends Activity {

    // UI References
    private EditText nameTextField;
    private EditText emailTextField;
    private EditText passwordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameTextField = (EditText) findViewById(R.id.nameTextField);
        emailTextField = (EditText) findViewById(R.id.emailTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);

        Button RegisterButton = (Button) findViewById(R.id.registerUserButton);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Register Button Clicked
               // Check if any of the text field is empty
              if(nameTextField.getText().length()>0 && emailTextField.getText().length()>0 && passwordTextField.getText().length()>0)
              {
                  // Registering User
                  ParseUser user = new ParseUser();
                  user.setUsername(emailTextField.getText().toString());
                  user.setPassword(passwordTextField.getText().toString());
                  user.setEmail(emailTextField.getText().toString());
                  user.put("Name", nameTextField.getText().toString());

                  // If we don't want to use sign up in background, then normal sign up is also available.
                  user.signUpInBackground(new SignUpCallback() {
                      public void done(ParseException e) {
                          if (e == null) {
                              // User registered
                              // Hooray! Let them use the app now.
                              Toast toast = Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT);
                              toast.show();
                              finish();
                          } else {
                              // Error Registering
                              Toast toast = Toast.makeText(getApplicationContext(),
                                      "Unable to create user. USC email address required.", Toast.LENGTH_LONG);
                              toast.show();
                          }
                      }
                  });
              }
              else
              {
                  Toast toast = Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT);
                  toast.show();
              }
        }
    });

    }

}
