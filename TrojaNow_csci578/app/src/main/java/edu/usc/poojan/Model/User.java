package edu.usc.poojan.Model;

import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseException;
import com.parse.LogInCallback;

/**
 * Created by Trina Gregory on 4/27/15.
 */
public class User extends ParseUser {

    public void register() {

    this.signUpInBackground(new SignUpCallback() {
        public void done(ParseException e) {
            if (e == null) {
                System.out.println("register successful");
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                System.out.println("unable to register at this time");
            }
        }
    });

    }

    public static void signIn (String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }


}
