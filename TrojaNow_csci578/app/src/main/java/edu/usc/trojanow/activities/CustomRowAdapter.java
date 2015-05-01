package edu.usc.trojanow.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Custom Row Adapter used to display posts in the feed or user's feed.
 */
public class CustomRowAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List values;

    private TextView tUserName;
    private TextView tContent;
    private TextView tDate;

    public CustomRowAdapter(Context context, List values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        tUserName = (TextView) rowView.findViewById(R.id.userName);
        tContent = (TextView) rowView.findViewById(R.id.content);
        tDate = (TextView) rowView.findViewById(R.id.date);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //Parse Object
        ParseObject pobject = (ParseObject)values.get(position);
        tUserName.setText(pobject.get("username").toString());
        tContent.setText(pobject.get("content").toString());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = pobject.getCreatedAt();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        tDate.setText(df.format(date));

        return rowView;
    }

}
