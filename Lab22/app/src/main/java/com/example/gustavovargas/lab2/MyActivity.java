package com.example.gustavovargas.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.Facebook;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

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

    public void ingresar(View view){
        ParseFacebookUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Usuario.getElement().nombre = user.getUsername();
                    Usuario.getElement().id = user.getSessionToken();
                    Intent intent = new Intent(getBaseContext(), VerProductos.class);
                    startActivity(intent);
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                } else {
                    Intent intent = new Intent(getBaseContext(), VerProductos.class);
                    startActivity(intent);
                    Usuario.getElement().nombre = user.getUsername();
                    Usuario.getElement().id = user.getSessionToken();
                    Log.d("MyApp", "User logged in through Facebook!");
                }
            }
        });

    }

    public void ingresar2(View view){
        ParseTwitterUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.");
                } else if (user.isNew()) {
                    Usuario.getElement().nombre = user.getUsername();
                    Usuario.getElement().id = user.getSessionToken();
                    Intent intent = new Intent(getBaseContext(), VerProductos.class);
                    startActivity(intent);
                    Log.d("MyApp", "User signed up and logged in through Twitter!");
                } else {
                    Usuario.getElement().nombre = user.getUsername();
                    Usuario.getElement().id = user.getSessionToken();
                    Intent intent = new Intent(getBaseContext(), VerProductos.class);
                    startActivity(intent);
                    Log.d("MyApp", "User logged in through Twitter!");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }


}
