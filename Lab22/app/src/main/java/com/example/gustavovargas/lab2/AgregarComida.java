package com.example.gustavovargas.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class AgregarComida extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.agregar_comida, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private static int RESULT_LOAD_IMAGE = 1;
    private static String picturePath = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_comida);
        cargarListaPaises();
    }


     public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgProducto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

    public void cargarListaPaises() {
        final ArrayList lista = new ArrayList<String>();

        Spinner spinner1 = (Spinner) this.findViewById(R.id.spinner_pais);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pais");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < scoreList.size(); i++) {
                        ParseObject pais = scoreList.get(i);
                        //lista.add(pais.getString("nombre"));
                    }
                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
        lista.add("Costa Rica");
        lista.add("Nicaragua");
        lista.add("Honduras");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);
    }

    public void agregarComidaParse(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Desea agregar producto");
        EditText nombre = (EditText) findViewById(R.id.inpNombre);
        EditText descripcion = (EditText) findViewById(R.id.inpDescripcion);
        Spinner pais = (Spinner) findViewById(R.id.spinner_pais);
        if (nombre.length() > 0 && descripcion.length() > 0 && pais.getSelectedItem() != null) {
            FoodAdapter foodAdapter = new FoodAdapter(this,"");
            String message = nombre.getText().toString() + " - " + descripcion.getText().toString()+ " - " + pais.getSelectedItem().toString();
            alertDialog.setMessage(message);
            if(picturePath == "") {
                alertDialog.setButton("Comida sin imagen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                foodAdapter.agregarItem(nombre.getText().toString(), descripcion.getText().toString(), pais.getSelectedItem().toString());
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
            }else {
                foodAdapter.agregarItem(nombre.getText().toString(), descripcion.getText().toString(), pais.getSelectedItem().toString(), picturePath);
                alertDialog.setButton("Comida agregada", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
            }
            ((EditText)findViewById(R.id.inpNombre)).setText("");
            ((EditText) findViewById(R.id.inpDescripcion)).setText("");
        }else{
            alertDialog.setMessage("Datos incompletos");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((EditText)findViewById(R.id.inpNombre)).setText("");
                        ((EditText) findViewById(R.id.inpDescripcion)).setText("");
                    }
                });
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.show();
        }
    }

    public void cancelarComida(View view){
        finish();
    }





}
