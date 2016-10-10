package com.goblog.go_login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.goblog.go_login.Fragment.FragmentDrawer;
import com.goblog.go_login.Fragment.HomeFragment;
import com.goblog.go_login.Fragment.JurusanFragment;
import com.goblog.go_login.Fragment.ProfileFragment;
import com.goblog.go_login.R;

public class HomeApp extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment =
                (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = "Home";/*getString(R.string.nav_item_Home);*/
                break;
            case 1:
                fragment = new ProfileFragment();
                title = getString(R.string.nav_item_Categoty);
                break;
            case 2:
                fragment = new JurusanFragment();
                title = getString(R.string.nav_item_Rate_App);
                break;
            case 3:
                fragment = new JurusanFragment();
                title = getString(R.string.nav_item_Feed_Back);
                break;
            case 4:
                AlertDialogBox();
                title = getString(R.string.nav_item_About_app);
                break;
            case 5:
                super.onBackPressed();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    public void AlertDialogBox(){
        new AlertDialog.Builder(HomeApp.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("About Application")
                .setMessage("Lets Cooking V.0.0.1 \n\nUntuk anda yang suka berExperimen memasak & menikmatinya :) \n \n   Supporrt by haikal@ekel")
                .setPositiveButton("OK",null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            /*finish();*/
            /*super.onBackPressed();
            return;*/
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_search :

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
