package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Register_Screen extends AppCompatActivity {

    String Page;
    ImageView backButton;
    EditText name, email, contact, card, cnic, address, password;
    RelativeLayout signupButton;

    // Define DatabaseHelper object
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        Page = getIntent().getStringExtra("Page");
        backButton = findViewById(R.id.back_button);
        name = findViewById(R.id.Name_text);
        email = findViewById(R.id.Email_text);
        contact = findViewById(R.id.Contact_text);
        cnic = findViewById(R.id.CNIC_text);
        address = findViewById(R.id.Address_text);
        password = findViewById(R.id.Password_text);
        card = findViewById(R.id.Card_text);
        signupButton = findViewById(R.id.sign_up_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Cnic = cnic.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Contact = contact.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Card = card.getText().toString().trim();

                if (Name.isEmpty() || Cnic.isEmpty() || Email.isEmpty() || Contact.isEmpty() || Address.isEmpty() || Password.isEmpty() || Card.isEmpty()) {
                    Toast.makeText(Register_Screen.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                } else if (!isValidEmail(Email)) {
                    Toast.makeText(Register_Screen.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                } else if (!isValidPassword(Password)) {
                    Toast.makeText(Register_Screen.this, "Password must be at least 6 characters long", Toast.LENGTH_LONG).show();
                } else if (!isValidCNIC(Cnic)) {
                    Toast.makeText(Register_Screen.this, "CNIC must be 13 digits long", Toast.LENGTH_LONG).show();
                } else if (!isValidContact(Contact)) {
                    Toast.makeText(Register_Screen.this, "Contact number must be 11 digits long", Toast.LENGTH_LONG).show();
                } else {
                    // All fields are valid, proceed with registration
                    // Add user to database
                    long userId = dbHelper.addUser(Name, Email, Contact, Cnic, Address, Password, Card);
                    if (userId != -1) {
                        Toast.makeText(Register_Screen.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        // You can add additional logic here, like navigating to another activity
                    } else {
                        Toast.makeText(Register_Screen.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Helper method to validate email
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Helper method to validate password
    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    // Helper method to validate CNIC
    private boolean isValidCNIC(String cnic) {
        return cnic.length() == 13;
    }

    // Helper method to validate contact number
    private boolean isValidContact(String contact) {
        return contact.length() == 11;
    }
}
