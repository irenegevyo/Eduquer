package com.gevyo.android.eduquer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.gevyo.android.Fragment.AccountFragment;
import com.gevyo.android.Fragment.HomeFragment;
import com.gevyo.android.Fragment.OtherFragment;
import com.gevyo.android.Fragment.ShopFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    String TAG = "firebaseApp";
    FirebaseApp myApp;
    FirebaseDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("First", "Sudah masuk");
        initFirebase();
        readDBFirebase();
        writeDBFirebase();

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    private void writeDBFirebase() {

    }

    //to show the database at the logcat
    private void readDBFirebase() {
        DatabaseReference dbref = myDB.getReference("Eduquer");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Snapshot received : " + dataSnapshot.getChildrenCount() +
                        ", key" + dataSnapshot.getKey() +
                        ", value" + dataSnapshot.getValue());
                Log.d(TAG, "Snapshot for article01: " + dataSnapshot
                        .child("Author")
                        .child("Auth01")
                        .child("Article")
                        .child("Art01")
                        .getKey() + ", value: " + dataSnapshot
                        .child("Author")
                        .child("Auth01")
                        .child("Article")
                        .child("Art01")
                        .getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initFirebase() {
        //FirebaseApp.initializeApp(this);
        myApp = FirebaseApp.getInstance();

        myDB = FirebaseDatabase.getInstance(myApp);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_shop:
                fragment = new ShopFragment();
                break;
            case R.id.nav_account:
                fragment = new AccountFragment();
                break;
            case R.id.nav_more:
                fragment = new OtherFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}