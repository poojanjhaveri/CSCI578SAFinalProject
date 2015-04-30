package edu.usc.poojan.activities;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends ListFragment {


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryForThoughts();
    }


    @Override
    public void onResume() {

        queryForThoughts();
        super.onResume();
    }



    public void queryForThoughts()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Thought");
        query.orderByDescending("updatedAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> thoughtList, ParseException e) {
                if (e == null) {


                    CustomRowAdapter adapter = new CustomRowAdapter(getActivity(),thoughtList);
                    setListAdapter(adapter);
                } else {

                    // Error
                    Log.d("Thought", "Error: " + e.getMessage());
                }
            }
        });
    }

}

