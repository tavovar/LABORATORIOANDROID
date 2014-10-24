package com.example.gustavovargas.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ListView;

import com.parse.Parse;


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

    public void agregarAux(String nombre, String descripcion, String pais){


    }

    public void agregarComidaParse(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Desea agregar producto");
        EditText nombre = (EditText) findViewById(R.id.inpNombre);
        EditText descripcion = (EditText) findViewById(R.id.inpDescripcion);
        ListView pais = (ListView) findViewById(R.id.listPais);
        if (nombre.length() > 0 && descripcion.length() > 0 && pais.getItemsCanFocus()) {
            FoodAdapter foodAdapter = new FoodAdapter(this);
            String message = nombre.getText().toString() + " - " + descripcion.getText().toString();
            alertDialog.setMessage(message);
            if(picturePath == "") {
                alertDialog.setButton("Comida sin imagen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                foodAdapter.agregarItem(nombre.getText().toString(), descripcion.getText().toString(), "pppp");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
            }else {
                foodAdapter.agregarItem(nombre.getText().toString(), descripcion.getText().toString(), "pppp", picturePath);
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
        Intent intent = new Intent(this, VerProductos.class);
        startActivity(intent);
    }



}
