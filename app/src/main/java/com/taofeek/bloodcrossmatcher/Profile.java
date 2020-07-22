package com.taofeek.bloodcrossmatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Profile extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_donate :
                        Snackbar.make(findViewById(R.id.nav_view),"Donate", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.nav_edit_profile:
                        Intent editIntent = new Intent(Profile.this,EditProfile.class);
                        startActivity(editIntent);

                        break;
                    case R.id.nav_match_viewer:
                        Snackbar.make(findViewById(R.id.nav_view),"Blood match viewer", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.nav_receiver:
                        Snackbar.make(findViewById(R.id.nav_view),"Receiver", Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
          }
    public void bloodmatcher (String bloodgroup) {
        ProfileOpenHelper mdb = new ProfileOpenHelper(this);
        switch (bloodgroup){
            case "A+" :
                String selectionAP = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?,?,?)" ;
                String[] selectionArgsAP= {"A+", "A-" , "O+" , "O-"};
                mdb.donorGetter(selectionAP,selectionArgsAP);
                break;
            case "O+" :
                String selectionOP = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?)" ;
                String[] selectionArgsOP = {"O+","O-"};
                mdb.donorGetter(selectionOP,selectionArgsOP);
                break;

            case "B+" :
                String selectionBP = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?,?,?)" ;
                String[] selectionArgsBP = {"O+","O-" , "B+", "B-"};
                mdb.donorGetter(selectionBP,selectionArgsBP);
                break;

            case "AB+" :
                String selectionABP = null ;
                String[] selectionArgsABP = null;
                mdb.donorGetter(selectionABP,selectionArgsABP);
                break;
            case "A-" :
                String selectionAM = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?)" ;
                String[] selectionArgsAM= { "A-" , "O-"};
                mdb.donorGetter(selectionAM,selectionArgsAM);
                break;
            case "O-" :
                String selectionOM = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " =?" ;
                String[] selectionArgsOM = {"O-"};
                mdb.donorGetter(selectionOM,selectionArgsOM);
                break;

            case "B-" :
                String selectionBM = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?)" ;
                String[] selectionArgsBM = {"O-" , "B-"};
                mdb.donorGetter(selectionBM,selectionArgsBM);
                break;

            case "AB-" :
                String selectionABM = DatabaseContract.Profile.COLUMN_BLOOD_TYPE + " IN(?,?,?,?)" ;
                String[] selectionArgsABM = {"AB-","A-" , "B-", "O-"};
                mdb.donorGetter(selectionABM,selectionArgsABM);
                break;



        }



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }



}
