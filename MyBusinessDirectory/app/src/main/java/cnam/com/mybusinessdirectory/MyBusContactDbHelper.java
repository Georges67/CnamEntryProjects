package cnam.com.mybusinessdirectory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class MyBusContactDbHelper extends SQLiteOpenHelper {


    private static final String LOG_TAG = "MyBusContactDbHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BusinessContact";

    // Table name: Contact.
    private static final String TABLE_CONTACT = "Contact";

    private static final String COLUMN_CONTACT_ID = "id";
    private static final String COLUMN_CONTACT_FIRSTNAME = "firstname";
    private static final String COLUMN_CONTACT_LASTNAME = "lastname";
    private static final String COLUMN_CONTACT_ADDRESS = "address";
    private static final String COLUMN_CONTACT_PHONE = "phone";

    private static final int COLUMN_CONTACT_ID_POSITION = 0;
    private static final int COLUMN_CONTACT_FIRSTNAME_POSITION = 1;
    private static final int COLUMN_CONTACT_LASTNAME_POSITION = 2;
    private static final int COLUMN_CONTACT_ADDRESS_POSITION = 3;
    private static final int COLUMN_CONTACT_PHONE_POSITION = 4;

    public MyBusContactDbHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 1 create database here
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 2 manage Upgrade database here
        // Just do DROP TABLE
    }

    public void createDefaultContactsIfNeeded()  {
        int count = this.getContactsCount();
        if(count == 0 ) {
            // TODO 9 create 2 contacts in database
        }
    }


    public void addContact(BusinessContact contact) {
        // TODO 3 create contact in database
    }


    public BusinessContact getContact(int id) {
        // TODO 4 get contact from database
        return null;
    }


    public List<BusinessContact> getAllContacts() {
        Log.i(LOG_TAG, "getAllContacts");

        // TODO 5 get ALL contacts from database
        return null;
    }

    public int getContactsCount() {
        Log.i(LOG_TAG, ">getContactsCount");

        // TODO 6 get contacts number from database
        return 0;
    }


    public int updateContact(BusinessContact contact) {
        Log.i(LOG_TAG, ">updateContact");

        // TODO 7 update contact in database
        return 0;
    }

    public void deleteContact(BusinessContact contact) {
        Log.i(LOG_TAG, ">deleteContact");

        // TODO 8 delete contact from database
    }

}