package com.example.calendarsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.example.calendarsample.adapter.AdapterCalendar;
import com.example.calendarsample.provider.CalendarProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerAccount;
    private Spinner spinnerType;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private AdapterCalendar adapterCalendar;
    private ArrayList<String> emails;
    private CalendarProvider calendarProvider;
    private String accountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerAccount = findViewById(R.id.spinnerAccount);

        btnSearch = findViewById(R.id.btnSearch);
        adapterCalendar = new AdapterCalendar(this);
        recyclerView = findViewById(R.id.recyclerViewCalendar);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterCalendar);

        calendarProvider = new CalendarProvider(this);


        accountName = "";
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.GET_ACCOUNTS)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.GET_ACCOUNTS}, 111);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.GET_ACCOUNTS}, 111);
            }
        } else {
            emails = new ArrayList<>();

            Pattern gmailPattern = Patterns.EMAIL_ADDRESS;
            Account[] accounts = AccountManager.get(this).getAccounts();
            for (Account account : accounts) {
                if (gmailPattern.matcher(account.name).matches()) {
                    emails.add(account.name);
                }
            }
            emails.add(CalendarProvider.LOCAL);
            emails.add(CalendarProvider.ALL);
            setupSpinnerAccount();

        }

        spinnerAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accountName = emails.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                accountName = "";
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CalendarObject> list = calendarProvider.readCalendar(accountName);
                adapterCalendar.setList(list);
            }
        });


    }

    private void setupSpinnerAccount() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, emails);
        spinnerAccount.setAdapter(arrayAdapter);
    }


}
