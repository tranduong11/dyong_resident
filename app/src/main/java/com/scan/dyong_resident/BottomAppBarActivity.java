//import android.os.Bundle;
//import android.view.MenuItem;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.scan.dyong_resident.HomeActivity;
//import com.scan.dyong_resident.R;
//import com.scan.dyong_resident.models.HomeFragment;
//import com.scan.dyong_resident.models.ProfileFragment;
//
//public class BottomAppBarActivity extends AppCompatActivity {
//    private BottomNavigationView bottomNavigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        if (savedInstanceState == null) {
//            loadFragment(new HomeFragment());
//        }
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        Fragment selectedFragment = null;
//                        switch (item.getItemId()) {
//                            case R.id.nav_home:
//                                selectedFragment = new HomeFragment();
//                                break;
//                            case R.id.nav_search:
//                                selectedFragment = new HomeActivity();
//                                break;
//
//                        }
//                        return loadFragment(selectedFragment);
//                    }
//                });
//    }
//
//    // Load the fragment dynamically
//    private boolean loadFragment(Fragment fragment) {
//        // Replace the current fragment with the new one
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .commit();
//            return true;
//        }
//        return false;
//    }
//}
