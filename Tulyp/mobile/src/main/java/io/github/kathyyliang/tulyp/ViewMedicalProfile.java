package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewMedicalProfile extends AppCompatActivity {
    ArrayList<User> patients = TulypApplication.patients;
    protected String[] headings = new String[6];
    protected String patientID;
    protected String contact;
    protected String gender;
    protected String weight;
    protected ArrayList<String> height;
    protected String medication;
    protected String age;
    protected User patient;
    ListView patientList;
    MedicalProfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_profile);
        headings[0] = "Gender";
        headings[1] = "Date of Birth";
        headings[2] = "Height";
        headings[3] = "Weight";
        headings[4] = "Contact";
        headings[5] = "Medication";
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medical Profile");
        setSupportActionBar(toolbar);

        TextView nameView = (TextView) findViewById(R.id.name_profile);
        nameView.setText(intent.getStringExtra("name"));
        weight = intent.getStringExtra("weight");
        contact = intent.getStringExtra("contact");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");
        height = intent.getStringArrayListExtra("height");
        patient = (User) intent.getSerializableExtra("patient");

        patientList = (android.widget.ListView) findViewById(R.id.medicalprofile);
        adapter = new MedicalProfileAdapter(this, headings, height, weight, age, gender, contact, medication, patientID);
        patientList.setAdapter(adapter);

        ListView listView = (ListView) findViewById(R.id.medicalprofile);
        listView.setOnItemClickListener(new MyOnItemClickListener(getBaseContext(), patient));
    }

}
