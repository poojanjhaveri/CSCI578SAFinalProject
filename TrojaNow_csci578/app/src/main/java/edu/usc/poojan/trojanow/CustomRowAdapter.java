package edu.usc.poojan.trojanow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by poojan on 4/28/15.
 */
public class CustomRowAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List values;

    private TextView tUserName;
    private TextView tContent;

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
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //Parse Object
        ParseObject pobject = (ParseObject)values.get(position);
     //   tUserName.setText(pobject.get("username").toString());
        tContent.setText(pobject.get("content").toString());
        return rowView;
    }


}
