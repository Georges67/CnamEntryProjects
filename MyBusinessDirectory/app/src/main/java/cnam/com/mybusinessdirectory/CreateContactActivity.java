package cnam.com.mybusinessdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateContactActivity extends AppCompatActivity {

    private MyBusContactDbHelper m_busContactDbHelper;

    private EditText m_firstName;
    private EditText m_lastname;
    private EditText m_address;
    private EditText m_phone;

    private BusinessContact m_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        m_firstName = findViewById(R.id.firstName);
        // TODO 16 Get controlers for lastname, address and phone



        m_busContactDbHelper = new MyBusContactDbHelper(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            m_contact = (BusinessContact) bundle.getSerializable(MainActivity.EXTRA_CONTACT);

            // TODO 23 fill firstName lastName address and phone using Contact Getter
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        // TODO 17 set Toolbar Title with "Create New Contact"
        // TODO 22 set Toolbar Title with "Create New Contact" or "Update Contact"
        setSupportActionBar(toolbar);

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // TODO 18 Manage a menu in the toolBar on overriding method onCreateOptionsMenu
    // If we are on mode update contact : hide menu item create
    // and display update and delete


    // TODO 19 Manage the menu toolBar icon click on overriding method onOptionsItemSelected
    // TODO 24 Manage update and delete buttons


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}