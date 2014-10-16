package com.example.gustavovargas.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.Parse;


public class VerProductos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);
        Parse.initialize(this, "li2vRGdt06sDicvZBY4CoKN8AHX2zy4EWD3WX71b", "LobXBC0YkZo2HTsQ1PmOHoAblXEtpbHk5IJYhceD");
        FoodAdapter foodAdapter = new FoodAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(foodAdapter);
        foodAdapter.loadObjects();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ver_productos, menu);
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


    public void agregarComida(View view){
        Intent intent = new Intent(this, AgregarComida.class);
        startActivity(intent);
    }
}
