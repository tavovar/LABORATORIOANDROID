package com.example.gustavovargas.lab2;

import android.content.Context;
import com.parse.*;
import android.view.*;
import android.widget.TextView;

/**
 * Created by gustavovargas on 06/10/14.
 */

public class FoodAdapter extends ParseQueryAdapter<ParseObject> {

    public FoodAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Food");
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.fooditem, null);
        }
        super.getItemView(object, v, parent);
        ParseImageView foodImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("Image");
        if (imageFile != null) {
            foodImage.setParseFile(imageFile);
            foodImage.loadInBackground();
        }
        TextView nameTextView = (TextView) v.findViewById(R.id.name);
        nameTextView.setText(object.getString("Name"));
        TextView typeView = (TextView) v.findViewById(R.id.type);
        typeView.setText(object.getString("Type"));
        return v;
    }

}
