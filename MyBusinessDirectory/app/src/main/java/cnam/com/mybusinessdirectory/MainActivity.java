package cnam.com.mybusinessdirectory;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "BUSINESS_CONTACT";

    private MyBusContactDbHelper m_busContactDbHelper;
    private ListView listView;

    private List<BusinessContact> m_contactList = new ArrayList<>();
    private MyCustomListAdapter m_listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        this.listView = findViewById(R.id.listContacts);

        m_busContactDbHelper = new MyBusContactDbHelper(this);
        // TODO 10 use the createDefaultContactsIfNeeded from dbHelper to display default Contacts in main Activity

        this.m_contactList = m_busContactDbHelper.getAllContacts();

        m_listAdapter = new MyCustomListAdapter(this, this.m_contactList);
        this.listView.setAdapter(m_listAdapter);

        // TODO 21 manage List Item Click to openActivity in order to modify or delete contact
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO 20 Refresh m_contactList with ALL contacts available in database
    }

    // TODO 13 Manage a menu in the toolBar on overriding method onCreateOptionsMenu
    // Create an xml file : New -> Android Resource File -> Resource Type : menu
    // Use this file to inflate menu here
    //
    // In the XML menu file : use an SVG icon 'ADD' found in the android SVG library
    // -> menu New -> Vector Asset -> (clip art)

    // TODO 14 Manage the menu toolBar icon click on overriding method onOptionsItemSelected
    // test id is icon Id and open activity : CreateContactActivity
}