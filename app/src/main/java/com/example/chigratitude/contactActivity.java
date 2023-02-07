package com.example.chigratitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class contactActivity extends AppCompatActivity {

    private ListView contactlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contacts);

        contactlist = findViewById(R.id.contactlist);
        ArrayList<String> contacts = new ArrayList<>();
        contacts.add("vamshi");
        contacts.add("tanish");
        ArrayAdapter<String> contactsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, contacts);

        contactlist.setAdapter(contactsAdapter);

    }
}