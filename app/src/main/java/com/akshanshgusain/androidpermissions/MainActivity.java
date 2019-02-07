package com.akshanshgusain.androidpermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private final int PERMISSION_READ_CONTACT = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.permission_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   //1. First check whether or not the permissions are granted.
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)==
                        PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this, "Permission is already granted.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                      //2. If it is not the first time the user is launching the app, then,  show the rationale
                      if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_CONTACTS))
                      {
                          //Showing the rationale
                          Snackbar.make(findViewById(android.R.id.content), "Need Permission to load the data", BaseTransientBottomBar.LENGTH_INDEFINITE).setAction("ENABLE",
                                  new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},PERMISSION_READ_CONTACT);
                                      }
                                  }).show();


                      }//3. If the user is launching the app for the first time, then, show the permission pop up.
                      else
                      {
                               ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_CONTACTS},
                                       PERMISSION_READ_CONTACT);
                      }
                }
            }
        });
    }
}
