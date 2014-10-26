package com.example.gustavovargas.lab2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;

/**
 * Created by gustavovargas on 26/10/14.
 */
public class AndroidFood extends Application {

    public void onCreate() {
        Parse.initialize(this, "li2vRGdt06sDicvZBY4CoKN8AHX2zy4EWD3WX71b", "LobXBC0YkZo2HTsQ1PmOHoAblXEtpbHk5IJYhceD");
        ParseFacebookUtils.initialize("287643098113118");
        ParseTwitterUtils.initialize("nXjRXSBkeEmoGzSiFbT8G26EA", "NMzRPyypmmxKXl8nwwBlJq6SzcX4rJiRVqMHr7bYuvDFaO9x6V");
    }
}
