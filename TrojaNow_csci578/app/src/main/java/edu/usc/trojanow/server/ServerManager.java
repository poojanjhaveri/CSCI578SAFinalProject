package edu.usc.trojanow.server;

import android.app.Activity;
import com.parse.Parse;
import com.parse.ParseUser;

/**
 * This is used to communicate with the back-end server.
 * We are using Parse (http://www.parse.com)
 * @author Trina Gregory, Poojan Jhaveri
 * Created for CSCI-578, Spring 2015
 */
public class ServerManager {
    // ID and KEY for Parse given to us when we created an app in Parse
    static String PARSE_APPLICATION_ID = "fI9EePHkFfD9GHUmNkoQtF9fgSqTFin3wyeupWuF";
    static String PARSE_CLIENT_KEY = "aRm6wWylGELtNY6vmzk5sviXRw31axHoZJFTH2JL";

    public ServerManager () {
        // default constructor
    }

    public static void initialize (Activity app) {

        Parse.initialize(app, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        // Testing Parse - it works
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */
    }

    public static void logOut () {
        ParseUser.logOut();
    }
}
