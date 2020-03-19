package com.example.mfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class profile extends AppCompatActivity {
    Spinner spinner;
    EditText edtNameProfile , edtAgeProfile, edtHometownProfile;
    Button submitProfile;
    SharedPreferences saveData;
    int pos;
    String textName , textHomeTown , age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setID();

        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home_Screen.class));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custome_spinner,getResources().getStringArray(R.array.list_gender));
        adapter.setDropDownViewResource(R.layout.custome_spinner_dropdown);
        spinner.setAdapter(adapter);
        loadData();
        updateViews();
        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = spinner.getSelectedItemPosition();
                uploadData();
                Intent intent = new Intent(profile.this,Home_Screen.class);


                startActivity(intent);
            }
        });




    }

    private void setID()
    {
        spinner = findViewById(R.id.spinner);
        edtAgeProfile = findViewById(R.id.tv_proFileAge);
        edtNameProfile= findViewById(R.id.tv_proFileName);
        edtHometownProfile = findViewById(R.id.tv_proFileHomeTown);
        submitProfile = findViewById(R.id.btSubmitProfile);
    }

    private void uploadData()
    {
        String nameDataProfile = login.getUserName();
        saveData = getSharedPreferences(nameDataProfile,MODE_PRIVATE);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString("NAME_PROFILE", edtNameProfile.getText().toString().trim());
        editor.putString("HOMETOWN_PROFILE", edtHometownProfile.getText().toString().trim());
        editor.putString("AGE_PROFILE", edtAgeProfile.getText().toString().trim());
        editor.putInt("GENDER_PROFILE", pos);

        editor.apply();
    }

    private void loadData()
    {
        String nameDataProfile = login.getUserName();
        SharedPreferences load = getSharedPreferences(nameDataProfile,MODE_PRIVATE);
        textName = load.getString("NAME_PROFILE", "");
        textHomeTown = load.getString("HOMETOWN_PROFILE", "");
        age = load.getString("AGE_PROFILE", "");
        pos = load.getInt("GENDER_PROFILE", 0);
    }

    private void updateViews()
    {
        edtNameProfile.setText(textName );
        edtHometownProfile.setText(textHomeTown);
        edtAgeProfile.setText(age);
        spinner.setSelection(pos);
    }
}
