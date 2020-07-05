package com.kithnkin.yummytreat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import fragment.FragmentHome;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String fragmentTitle = "";
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHomeFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setHomeFragment() {
        fragmentTitle = "Home";
        FragmentManager manager = getSupportFragmentManager();
        FragmentHome fragmentHome = new FragmentHome();
        //FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.layoutFragment, fragmentHome,
                fragmentHome.getTag()).commit();
        setTitle(fragmentTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        /*new AlertDialog.Builder(this)
                .setMessage("Exit the application?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);

                            startActivity(intent);
                            System.exit(0);
                        }
    }).

    setNavigationButton("No",null).
    show();
}*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (id) {
            case R.id.nav_home:
                fragmentTitle = "Yummy Treat";
                FragmentHome fragmentHome = new FragmentHome();
                fragmentManager.beginTransaction().replace
                        (R.id.layoutFragment,
                                fragmentHome,
                                fragmentHome.getTag()
                        ).commit();
                drawer.closeDrawers();
        }
        setTitle(fragmentTitle);
        return true;
        /*if (id == R.id.nav_all) {

        } else if (id == R.id.nav_cake) {

        } else if (id == R.id.nav_cup_cake) {

        } else if (id == R.id.nav_cookies) {

        } else if (id == R.id.nav_ice_cream) {

        } else if (id == R.id.nav_indian_dessert) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;*/
    }

    /*public void setTitleFragment(String title) {
        setTitle(title);
    }*/

    public void setTitle(String string) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(string);
        }
    }
}
