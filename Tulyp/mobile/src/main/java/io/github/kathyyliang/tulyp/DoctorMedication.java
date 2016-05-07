package io.github.kathyyliang.tulyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

// todo this needs lots of fixing
public class DoctorMedication extends AppCompatActivity {
    // need to pass patient id
    User user = TulypApplication.mUser;
    MyFirebase mfirebase = TulypApplication.mFirebase;
    private User patient;
    private HashMap<String, Drug> meds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_medication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Medication");
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        patient = (User) bundle.getSerializable("patient");

        EditText medication = (EditText) findViewById(R.id.editMedication);
        EditText instructions = (EditText) findViewById(R.id.editDosage);
        EditText warnings = (EditText) findViewById(R.id.editWarnings);
        EditText start_date = (EditText) findViewById(R.id.editFirstDay);
        EditText end_date = (EditText) findViewById(R.id.editLastDay);

        // need to show medication info if it currently exists
        if (patient != null) {
            meds = patient.getMedications();
            if (meds != null || !meds.isEmpty()) {
                String medStr = (String) meds.keySet().toArray()[0];
                Drug medData = meds.get(medStr);
                medication.setText(medStr);
                instructions.setText(medData.getInstructions());
                warnings.setText(medData.getWarnings());
                start_date.setText(medData.getStart_date());
                end_date.setText(medData.getEnd_date());
            }
        }

        Button save = (Button) findViewById(R.id.saveMedication);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMedicationView(v);
            }
        });
    }

    public void updateMedicationView(View view) {
        EditText medication = (EditText) findViewById(R.id.editMedication);
        EditText instructions = (EditText) findViewById(R.id.editDosage);
        EditText warnings = (EditText) findViewById(R.id.editWarnings);
        EditText start_date = (EditText) findViewById(R.id.editFirstDay);
        EditText end_date = (EditText) findViewById(R.id.editLastDay);

        String medName = medication.getText().toString();
        String instr = instructions.getText().toString();
        String warns = warnings.getText().toString();
        String start = start_date.getText().toString();
        String end = end_date.getText().toString();

        Drug d = new Drug(instr, warns, start, end);

        HashMap<String, Drug> drugs = new HashMap<>();
        drugs.put(medName, d);

        if (patient != null) {
            patient.setMedications(drugs);
            mfirebase.setNewUserInfo(patient);
        }

        finish();
    }
}
