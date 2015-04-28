package edu.usc.poojan.server;

import com.parse.Parse;

/**
 * Created by Trina Gregory on 4/28/15.
 */
public class ServerManager {
    static String PARSE_APPLICATION_ID = "fI9EePHkFfD9GHUmNkoQtF9fgSqTFin3wyeupWuF";
    static String PARSE_CLIENT_KEY = "aRm6wWylGELtNY6vmzk5sviXRw31axHoZJFTH2JL";

    public ServerManager (Object context) {
     //   Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        // Testing Parse - it works
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */
    }
}
