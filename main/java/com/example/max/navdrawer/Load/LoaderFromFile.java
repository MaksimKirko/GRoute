package com.example.max.navdrawer.Load;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.example.max.navdrawer.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LoaderFromFile implements IRead {
	private Activity activity;
    private int id;

	public LoaderFromFile(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
	}

    public ArrayList<String> textToList(String text) {
        ArrayList<String> textList = new ArrayList<String>();
        String[] arr = text.split("\n");
        for(String line : arr) {
            textList.add(line);
        }
        return  textList;
    }

    public String  getStringFromRawFile(Activity activity, int id) {
        Resources r = activity.getResources();
        InputStream is = r.openRawResource(id);
        String myText = null;
        try {
            myText = convertStreamToString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  myText;
    }

    public String  convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while( i != -1)
        {
            baos.write(i);
            i = is.read();
        }
        return  baos.toString();
    }
}
