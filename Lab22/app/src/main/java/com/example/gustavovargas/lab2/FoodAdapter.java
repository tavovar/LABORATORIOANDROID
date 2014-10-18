package com.example.gustavovargas.lab2;

import android.content.Context;
import com.parse.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import com.parse.ParseFile;
import com.parse.ParseObject;
import android.view.View;

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

    /**
    @Override
    public View getItemViewForItem(ParseObject object, View v, ViewGroup parent) {
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
    **/


    public void agregarItem(String nombre, String descripcion, String pais, String img) {
        ParseObject foot = new ParseObject("Food");
        foot.put("Name", nombre);
        foot.put("Type", descripcion);
        Bitmap bitmap = BitmapFactory.decodeFile(img);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("img.png", image);
        // Upload the image into Parse Cloud
        file.saveInBackground();
        foot.put("Image", file);
        foot.saveInBackground();
    }

    public void agregarItem(String nombre, String descripcion, String pais) {
        ParseObject foot = new ParseObject("Food");
        foot.put("Name", nombre);
        foot.put("Type", descripcion);
        foot.saveInBackground();
    }

}
