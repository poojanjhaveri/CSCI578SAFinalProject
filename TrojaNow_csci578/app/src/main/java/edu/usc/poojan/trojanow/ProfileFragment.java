package edu.usc.poojan.trojanow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends ListFragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryForThoughts();

    }



    @Override
    public void onResume() {


        //TODO : ProfileName set Text is not working. IT crashes
//        TextView profileName = (TextView)getView().findViewById(R.id.profileName);
//        profileName.setText(ParseUser.getCurrentUser().get("Name").toString());
        queryForThoughts();
        super.onResume();
    }






    public void queryForThoughts()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Thought");
        query.orderByDescending("updatedAt");
        query.whereEqualTo("byUser",ParseUser.getCurrentUser());
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
