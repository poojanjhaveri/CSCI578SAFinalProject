package edu.usc.poojan.trojanow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterActivity extends Activity {

    //UI References

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
               // Check if any of the textfield is empty
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
                          } else {

                              // Error Registering

                              AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                              builder.setTitle("Error")
                                      .setMessage(e.getMessage().toString())
                                      .setCancelable(false)
                                      .setNegativeButton("Okay",new DialogInterface.OnClickListener() {
                                          public void onClick(DialogInterface dialog, int id) {
                                              dialog.cancel();
                                          }
                                      });
                              AlertDialog alert = builder.create();
                              alert.show();


                          }
                      }
                  });
              }
              else
              {

                  // Alert message for missing value in textfield
                  AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                  builder.setTitle("Invalid Input")
                          .setMessage("Please enter all values")
                          .setCancelable(false)
                          .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int id) {
                                  dialog.cancel();
                              }
                          });
                  AlertDialog alert = builder.create();
                  alert.show();
              }
        }
    });


}

}
