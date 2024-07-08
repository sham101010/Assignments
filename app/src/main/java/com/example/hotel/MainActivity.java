package com.example.hotel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {
    RelativeLayout customer;
    RelativeLayout vendor;
    String Page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customer = findViewById(R.id.rl_customer_button);
        vendor = findViewById(R.id.rl_vendor_button);

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page= "Customer";
                Intent intent=new Intent(MainActivity.this,Login_Screen.class);
                intent.putExtra("Page",Page);
                startActivity(intent);
            }
        });
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page= "Vendor";
                Intent intent=new Intent(MainActivity.this,Login_Screen.class);
                intent.putExtra("Page",Page);
                startActivity(intent);
            }
        });

    }
}