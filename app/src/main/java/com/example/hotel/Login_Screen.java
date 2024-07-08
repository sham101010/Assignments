package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Screen extends AppCompatActivity {
    ImageView backButton;
    EditText email, password;
    RelativeLayout loginButton;
    TextView page_user, sign_up;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        backButton = findViewById(R.id.back_button);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.log_in_button);
        page_user = findViewById(R.id.tv_page);
        sign_up = findViewById(R.id.tv_sign_up);
        dbHelper = new DatabaseHelper(this);

        String page = getIntent().getStringExtra("Page");
        page_user.setText(page);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_string = email.getText().toString();
                String password_string = password.getText().toString();

                if (email_string.isEmpty() || password_string.isEmpty()) {
                    Toast.makeText(Login_Screen.this, "Kindly fill all the input fields", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {
                    Toast.makeText(Login_Screen.this, "Invalid email format", Toast.LENGTH_LONG).show();
                } else if (password_string.length() < 6) {
                    Toast.makeText(Login_Screen.this, "Password must be at least 6 characters long", Toast.LENGTH_LONG).show();
                } else {
                    // Query the database to check if the user exists with the provided credentials
                    if (isValidUser(email_string, password_string)) {
                        // Authentication successful
                        Toast.makeText(Login_Screen.this, "Login Successful", Toast.LENGTH_LONG).show();
                        // Proceed to the next activity or do whatever you need

                            // If the user is a vendor, open the VendorActivity
                            Intent intent = new Intent(Login_Screen.this,Homescreen_Activity.class);
                            startActivity(intent);

                            // Handle other user types or scenarios

                    } else {
                        // Authentication failed
                        Toast.makeText(Login_Screen.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Screen.this, Register_Screen.class);
                intent.putExtra("Page", page);
                startActivity(intent);
            }
        });
    }

    // Method to check if the user exists with the provided email and password
    private boolean isValidUser(String email, String password) {
        return dbHelper.isValidUser(email, password);
    }
}
