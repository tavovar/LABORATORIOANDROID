package com.example.gustavovargas.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;


import com.parse.Parse;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.v("Lab2", "estoyenelonCreate()");
        setContentView(R.layout.activity_my);
        Parse.initialize(this, "li2vRGdt06sDicvZBY4CoKN8AHX2zy4EWD3WX71b", "LobXBC0YkZo2HTsQ1PmOHoAblXEtpbHk5IJYhceD");
        FoodAdapter foodAdapter = new FoodAdapter(this);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(foodAdapter);
        foodAdapter.loadObjects();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart","-------->>>>>>");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.v("Lab2", "estoyenelonCreate()");
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.v("Lab2", "estoyenelonCreate()");
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
