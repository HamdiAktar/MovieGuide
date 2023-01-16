package com.android.epicboost.guide.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.epicboost.guide.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.epicboost.guide.databinding.ActivityMainBinding;
import com.android.epicboost.guide.utils.ResourceUtil;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    public static String API_Key = "API_KEY";
    public static ResourceUtil resourceInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resourceInstance= new ResourceUtil(this);
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_notifications).build();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

}