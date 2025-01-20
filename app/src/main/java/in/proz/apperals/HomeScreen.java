package in.proz.apperals;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import in.proz.apperals.BottomNavigation.BottomNavigationHandler;


public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize the handler and set up the navigation
        BottomNavigationHandler navigationHandler = new BottomNavigationHandler(this);
        navigationHandler.setupNavigation(bottomNavigationView);
    }
}
