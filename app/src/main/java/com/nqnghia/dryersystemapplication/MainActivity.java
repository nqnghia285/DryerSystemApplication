package com.nqnghia.dryersystemapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private volatile int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_about:
                    navController.navigate(R.id.nav_about);
                    break;
                case R.id.nav_account:
                    navController.navigate(R.id.nav_account);
                    break;
                case R.id.nav_device_sensor:
                    navController.navigate(R.id.nav_device_sensor);
                    break;
                case R.id.nav_machine_system:
                    navController.navigate(R.id.nav_machine_system);
                    break;
                case R.id.nav_notifications:
                    navController.navigate(R.id.nav_notifications);
                    break;
                case R.id.nav_recipes:
                    navController.navigate(R.id.nav_recipes);
                    break;
                case R.id.nav_settings:
                    navController.navigate(R.id.nav_settings);
                    break;
                case R.id.nav_statistics:
                    navController.navigate(R.id.nav_statistics);
                    break;
                case R.id.nav_tasks:
                    navController.navigate(R.id.nav_tasks);
                    break;
                case R.id.nav_logout:
                    closeApp();
                    break;
                default:
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            Menu menu = navigationView.getMenu();
            for (int h = 0, size = menu.size(); h < size; h++) {
                MenuItem item = menu.getItem(h);
                NavDestination currentDestination = destination;
                while (currentDestination.getId() != item.getItemId() && currentDestination.getParent() != null) {
                    currentDestination = currentDestination.getParent();
                }
                item.setChecked(currentDestination.getId() == item.getItemId());
            }
            drawer.closeDrawer(GravityCompat.START);

            switch (destination.getId()) {
                case R.id.nav_about:
                case R.id.nav_account:
                case R.id.nav_settings:
                case R.id.nav_statistics:
                    getFab().hide();
                    break;
                case R.id.nav_machine_system:
                case R.id.nav_device_sensor:
                case R.id.nav_notifications:
                case R.id.nav_recipes:
                case R.id.nav_tasks:
                    getFab().show();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void onBackPressed() {
        k++;

        if (k < 2) {
            Toast.makeText(this, "Nhấn Back lần nữa để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> k = 0, 2000);
        } else {

            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    public void closeApp() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    public FloatingActionButton getFab() {
        return findViewById(R.id.fab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
