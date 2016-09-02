package com.example.max.navdrawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max.navdrawer.Elements.*;
import com.example.max.navdrawer.Collections.*;
import com.example.max.navdrawer.Answers.*;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    FloatingActionButton fab;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    EditText etStartPoint;
    EditText etDestPoint;
    ImageButton btGo;
    TextView tvTest;
    ProgressBar progressBar;

    public double latitude;
    public double longitude;

    public String placeName;
    public Coords startCoords;
    public Coords destCoords;
    public String myLocation;

    public boolean startPointEditable = false;

    BusCollection busCollection;
    HaltCollection haltCollection;
    ArrayList<NoTransferAnswerArgs> noTrResults;
    ArrayList<OneTransferAnswerArgs> oneTrResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Geolocation.SetUpLocationListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                hideSoftKeyboard();
            }
        });


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        etStartPoint = (EditText) findViewById(R.id.editTextStartPoint);
        etDestPoint = (EditText) findViewById(R.id.editTextDestPoint);
        btGo = (ImageButton) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        startCoords = new Coords();
        destCoords = new Coords();

        busCollection = new BusCollection(MainActivity.this, R.raw.routes_min);

        busCollection.getBusesFromFile();

        haltCollection = new HaltCollection(MainActivity.this, R.raw.halts_min);
        haltCollection.getHaltsFromFile();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                startActivity(new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
            catch (Exception ex) { }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myplace) {
            progressBar.setVisibility(View.VISIBLE);
            Runnable runnable = new Runnable() {
                public void run() {
                    myLocation = "Не найдено!";
                    try {
                        Coords myCoords = null;
                        do {
                            myCoords = new Coords(Geolocation.imHere.getLatitude(), Geolocation.imHere.getLongitude());
                            myLocation = NetworkInfo.getPlaceName(myCoords.toString());
                        }
                        while(myCoords.X() == 0.0 || myCoords.Y() == 0.0);
                    }
                    catch (Exception ex) {
                    }
                    handlerMyLoc.sendEmptyMessage(0);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

        else if (id == R.id.nav_get_distance_point_route) {
            if(startPointEditable) {
                item.setTitle("Задать пункт отправления");
                startPointEditable = false;
            }
            else {
                item.setTitle("Задать пункт отправления    ✔");
                startPointEditable = true;
            }
            if(startPointEditable) {
                etStartPoint.setVisibility(View.VISIBLE);
            }
            else {
                etStartPoint.setVisibility(View.INVISIBLE);
            }
        } else if (id == R.id.nav_buses) {
            try {
                Intent intent = new Intent(MainActivity.this, BusesActivity.class);
                intent.putExtra("buses", busCollection.getNames());
                startActivity(intent);
            }
            catch (Exception ex) { }

        }else if (id == R.id.nav_halts) {
            try {
                Intent intent = new Intent(MainActivity.this, HaltsActivity.class);
                intent.putExtra("halts", haltCollection.getNames());
                startActivity(intent);
            }
            catch (Exception ex) { }
        } else if (id == R.id.nav_tools) {
            try {
                startActivity(new Intent(
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
            catch (Exception ex) { }
        } else if (id == R.id.nav_about) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("О приложении")
                        .setMessage("Версия - 0.3a")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            catch (Exception ex) { }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void getCustomRoutes() {
        String position = etStartPoint.getText().toString();
        String destination = etDestPoint.getText().toString();
        if(position.trim().isEmpty()) {
            handlerUnknownStartPlace.sendEmptyMessage(0);
            return;
        }
        if(destination.trim().isEmpty()) {
            handlerUnknownDestPlace.sendEmptyMessage(0);
            return;
        }
        try {
            ArrayList<Halt> startHalts = null;

            Halt startHalt = null;
            int counter1 = 0;
            do {
                startHalt = haltCollection.findByName(position);
                Log.i("TRY_LOCAL_FIND", "---------------------------------------------");
                counter1++;
            }
            while(startHalt == null && counter1 < 1000);

            Halt destHalt = null;
            counter1 = 0;
            do {
                destHalt = haltCollection.findByName(destination);
                Log.i("TRY_LOCAL_FIND", "---------------------------------------------");
                counter1++;
            }
            while(destHalt == null && counter1 < 1000);

            if(startHalt == null && destHalt == null) {
                int counter2 = 0;
                do {
                    startCoords = NetworkInfo.getPlaceCoords(position);
                    Log.i("TRY_CONNECT", "---------------------------------------------");
                    counter2++;
                }
                while(startCoords == null && counter2 < 5);

                counter2 = 0;
                do {
                    destCoords = NetworkInfo.getPlaceCoords(destination);
                    Log.i("TRY_CONNECT", "---------------------------------------------");
                    counter2++;
                }
                while(destCoords == null && counter2 < 5);

                startHalts = haltCollection.getNearests(startCoords);
                destHalt = haltCollection.getNearest(destCoords);
            }
            else if(startHalt != null && destHalt == null) {
                startCoords = startHalt.Coords();
                int counter2 = 0;
                do {
                    destCoords = NetworkInfo.getPlaceCoords(destination);
                    Log.i("TRY_CONNECT", "---------------------------------------------");
                    counter2++;
                }
                while(destCoords == null && counter2 < 5);

                startHalts = new ArrayList<Halt>();
                startHalts.add(startHalt);
                startHalts.addAll(haltCollection.getNearests(startCoords));
                destHalt = haltCollection.getNearest(destCoords);
            }
            else if(startHalt == null && destHalt != null) {
                int counter2 = 0;
                do {
                    startCoords = NetworkInfo.getPlaceCoords(position);
                    Log.i("TRY_CONNECT", "---------------------------------------------");
                    counter2++;
                }
                while(startCoords == null && counter2 < 5);
                destCoords = destHalt.Coords();

                startHalts = haltCollection.getNearests(startCoords);
                destHalt = haltCollection.getNearest(destCoords);
            }
            else if(startHalt != null && destHalt != null) {
                startCoords = startHalt.Coords();
                destCoords = destHalt.Coords();

                startHalts = new ArrayList<Halt>();
                startHalts.add(startHalt);
                startHalts.addAll(haltCollection.getNearests(startCoords));
                destHalt = haltCollection.getNearest(destCoords);
            }

            if (startCoords == null || position.equals("") || position == null) {
                handlerUnknownStartPlace.sendEmptyMessage(0);
                return;
            }

            if (destCoords == null || destination.equals("") || destination == null) {
                handlerUnknownDestPlace.sendEmptyMessage(0);
                return;
            }

            NoTransferAnswer answer1 = new NoTransferAnswer();
            noTrResults = new ArrayList<NoTransferAnswerArgs>();
            for(Halt h : startHalts) {
                try {
                    noTrResults.addAll(answer1.getAnswer(new AnswerParams(h,
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll(answer1.getAnswer(new AnswerParams(h,
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll((answer1.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            destHalt, busCollection, haltCollection))));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll((answer1.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection))));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
            }
            answer1.deleteExtras(noTrResults);
            Collections.sort(noTrResults);

            OneTransferAnswer answer2 = new OneTransferAnswer();
            oneTrResults = new ArrayList<OneTransferAnswerArgs>();
            for(Halt h : startHalts) {
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(h,
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(h,
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
            }
            answer2.deleteExtras(oneTrResults, noTrResults);
            Collections.sort(oneTrResults);
        } catch (Exception ex) {
        }
        Log.i("ThreadGetCoords", destCoords.toString());
        handlerGetCoords.sendEmptyMessage(0);
    }

    public void getRoutes() {
        String destination = etDestPoint.getText().toString();
        if(destination.trim().isEmpty()) {
            handlerUnknownDestPlace.sendEmptyMessage(0);
            return;
        }
        Coords startCoords = new Coords(0, 0);

        int k = 0;
        do {
            k++;
            try {
                startCoords = new Coords(Geolocation.imHere.getLatitude(),
                        Geolocation.imHere.getLongitude());
            }
            catch (NullPointerException ex) {
                Log.i("NULL_POINTER_EXCEPTION", "Geolocation");
            }
        }
        while(k < 10000);

        if(startCoords == null || startCoords.X() == 0.0 || startCoords.Y() == 0.0) {
            handlerNullResults.sendEmptyMessage(0);
            return;
        }

        try {
            ArrayList<Halt> startHalts = haltCollection.getNearests(startCoords);
            Halt destHalt = null;
            int counter1 = 0;
            do {
                destHalt = haltCollection.findByName(destination);
                Log.i("TRY_LOCAL_FIND", "---------------------------------------------");
                counter1++;
            }
            while(destHalt == null && counter1 < 1000);

            if(destHalt == null) {
                int counter2 = 0;
                do {
                    destCoords = NetworkInfo.getPlaceCoords(destination);
                    Log.i("TRY_CONNECT", "---------------------------------------------");
                    counter2++;
                }
                while(destCoords == null && counter2 < 5);
                if (destCoords == null || destination.equals("") || destination == null) {
                    handlerUnknownDestPlace.sendEmptyMessage(0);
                    return;
                }

                destHalt = haltCollection.getNearest(destCoords);
            }
            else {
                destCoords = destHalt.Coords();
            }

            NoTransferAnswer answer1 = new NoTransferAnswer();
            noTrResults = new ArrayList<NoTransferAnswerArgs>();
            for(Halt h : startHalts) {
                try {
                    noTrResults.addAll(answer1.getAnswer(new AnswerParams(h,
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll(answer1.getAnswer(new AnswerParams(h,
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll((answer1.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            destHalt, busCollection, haltCollection))));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
                try {
                    noTrResults.addAll((answer1.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection))));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "NoTransferAnswer");
                }
            }
            answer1.deleteExtras(noTrResults);
            Collections.sort(noTrResults);

            OneTransferAnswer answer2 = new OneTransferAnswer();
            oneTrResults = new ArrayList<OneTransferAnswerArgs>();
            for(Halt h : startHalts) {
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(h,
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(h,
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            destHalt, busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
                try {
                    oneTrResults.addAll(answer2.getAnswer(new AnswerParams(haltCollection.getNeighbour(h),
                            haltCollection.getNeighbour(destHalt), busCollection, haltCollection)));
                }
                catch (NullPointerException ex) {
                    Log.i("NULL_POINTER_EXCEPTION", "OneTransferAnswer");
                }
            }
            answer2.deleteExtras(oneTrResults, noTrResults);
            Collections.sort(oneTrResults);
        } catch (Exception ex) {
        }
        Log.i("ThreadGetCoords", destCoords.toString());
        handlerGetCoords.sendEmptyMessage(0);
    }

    public void onClickGo(View view) {
        hideSoftKeyboard();
        try {
            progressBar.setVisibility(View.VISIBLE);
            if (startPointEditable) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        getCustomRoutes();
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            } else {
                Runnable runnable = new Runnable() {
                    public void run() {
                        getRoutes();
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }
        catch (Exception ex) {
            Log.i("EXCEPTION", "OnClickGo");
        }
    }

    Handler handlerGetCoords = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.INVISIBLE);
            boolean flag1 = false;
            boolean flag2 = false;
            if(noTrResults != null) {
                if(noTrResults.size() > 0) {
                    flag1 = true;
                }
            }
            if(oneTrResults != null) {
                if(oneTrResults.size() > 0) {
                    flag2 = true;
                }
            }
            if (flag1 || flag2) {
                try {
                    Intent intent = new Intent(MainActivity.this, TabbedActivity.class);
                    intent.putParcelableArrayListExtra("noTransferResults", noTrResults);
                    intent.putParcelableArrayListExtra("oneTransferResults", oneTrResults);
                    startActivity(intent);
                } catch (Exception ex) {
                }
            } else {
                handlerNullResults.sendEmptyMessage(0);
                return;
            }
        }
    };

    Handler handlerNullResults = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Нет подходящих маршрутов!")
                        //.setMessage("Не найдено")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            catch (Exception ex) { }
        }
    };

    Handler handlerUnknownStartPlace = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Пункт отправления не найден!")
                        //.setMessage("Не найдено")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            catch (Exception ex) { }
        }
    };

    Handler handlerUnknownDestPlace = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Пункт назначения не найден!")
                        //.setMessage("Не найдено")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            catch (Exception ex) { }
        }
    };

    Handler handlerMyLoc = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                Halt nearestHalt = haltCollection.getNearest(new Coords(Geolocation.imHere.getLatitude(),
                                Geolocation.imHere.getLongitude()));
                String line = "";
                if(nearestHalt.Direction().equals("Unknown") || nearestHalt.Direction().equals("") || nearestHalt.Direction() == null) {
                    line = myLocation + "\nБлижайшая остановка: " + nearestHalt.Name();
                }
                else {
                    line = myLocation + "\nБлижайшая остановка: " + nearestHalt.Name() + "(" + nearestHalt.Direction() + ")";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Местоположение")
                        .setMessage(line)
//                                + "\n" + haltCollection.getNeighbor(nearestHalt).Name() + "("
//                                + haltCollection.getNeighbor(nearestHalt).Direction() + ")")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            catch (Exception ex) {
            }
        }
    };
}
