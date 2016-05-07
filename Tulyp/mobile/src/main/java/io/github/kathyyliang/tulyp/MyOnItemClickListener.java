package io.github.kathyyliang.tulyp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by kathyyliang on 5/6/16.
 */
public class MyOnItemClickListener implements AdapterView.OnItemClickListener {

    Context context;
    User patient;

    public MyOnItemClickListener(Context context, User patient) {
        this.context = context;
        this.patient = patient;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = (String) ((TextView) view).getText();
        if (text.equalsIgnoreCase("Medication")) {
            Intent intent = new Intent(context, DoctorMedication.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("patient", patient);
            context.startActivity(intent);
        }
    }

}
