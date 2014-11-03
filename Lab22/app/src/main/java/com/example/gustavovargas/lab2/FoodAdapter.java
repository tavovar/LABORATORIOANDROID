package com.example.gustavovargas.lab2;

import android.content.Context;
import com.parse.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.parse.ParseFile;
import com.parse.ParseObject;
import android.view.View;

/**
 * Created by gustavovargas on 06/10/14.
 */

public class FoodAdapter extends ParseQueryAdapter<ParseObject>{

    public FoodAdapter(Context context, final String busqueda) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Food");
                if(busqueda!="") {
                    query.whereEqualTo("Name", busqueda);
                }
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object,View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.fooditem, null);
        }
        super.getItemView(object, v, parent);
        final View v2 = v;
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pais");
        query.whereEqualTo("nombre", object.getString("pais"));
        query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject parseObject, ParseException e) {
                    ParseImageView foodImage2 = (ParseImageView) v2.findViewById(R.id.img_pais);
                    ParseFile imageFile2 = parseObject.getParseFile("imagen");
                    foodImage2.setParseFile(imageFile2);
                    foodImage2.loadInBackground();
                }
            });
        return v;
    }



    public void agregarItem(String nombre, String descripcion, String pais, String img) {
        ParseObject foot = new ParseObject("Food");
        foot.put("Name", nombre);
        foot.put("Type", descripcion);
        foot.put("pais", pais);
        Bitmap bitmap = BitmapFactory.decodeFile(img);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("img.png", image);
        // Upload the image into Parse Cloud
        try {
            file.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        foot.put("Image", file);
        foot.saveInBackground();
    }

    public void agregarItem(String nombre, String descripcion, String pais) {
        ParseObject foot = new ParseObject("Food");
        foot.put("Name", nombre);
        foot.put("Type", descripcion);
        foot.put("pais", pais);
        foot.saveInBackground();
    }

}
