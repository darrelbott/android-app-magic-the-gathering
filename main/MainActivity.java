package com.darrelbott.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.darrelbott.finalproject.Adapter.SearchAdapter;
import com.darrelbott.finalproject.Database.Database;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * This portion was made but never implemented because of the thought of using fragments for "pages".
 * Essentially this could more than likely be implemented as is, but only if I was to create my own
 * custom AppCompatActivity.  Otherwise, if it calls activities they will be stacked on one another.
 * This is essentially the menu for navigation, but never gets called in any other activity as this
 * was suppose to be the page that is called after the Splash activity.  I am sure it will show up
 * if: Intent i = new Intent(Splash.this, LoginActivity.class);
 * is changed to: Intent i = new Intent(Splash.this, MainActivity.class);
 * If given maybe 2 more months I believe everything could have been finished.  Took me more time to
 * learn than anticipated.
 */


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database database;

    //used for inserting card into collection database
    DB_Controller controller;
    TextView card_name, type, rarity, artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menu
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState(); // rotating the hamburger icon together with the drawer

        // start page
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DeckFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_decks);
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_collection:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CollectionFragment()).commit();
                break;
            case R.id.nav_decks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DeckFragment()).commit();
                break;
            case R.id.nav_search:
                //startActivity(new Intent(getApplicationContext(),SearchFragment.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        //new SearchFragment()).commit();
                break;
            case R.id.nav_account:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return true; // when action is triggered, so something
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}
