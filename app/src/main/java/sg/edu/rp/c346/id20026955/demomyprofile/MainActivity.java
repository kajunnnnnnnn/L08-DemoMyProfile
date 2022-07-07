package sg.edu.rp.c346.id20026955.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    Button btnSave;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        btnSave = findViewById(R.id.buttonSave);
        rgGender = findViewById(R.id.rgGender);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedData = etName.getText().toString();
                String savedGPA  = etGPA.getText().toString();

                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name", savedData);
                prefEdit.putString("GPA", savedGPA);
                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        String gender;
        if (rgGender.getCheckedRadioButtonId() == R.id.rgMale){
            gender = "Male";
        } else {
            gender = "Female";
        }
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putString("gender", gender);



        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String loadedData = prefs.getString("name", "John");
        float loadedGPA = prefs.getFloat("gpa", 0);
        String gender = prefs.getString("gender", "Male");

        etName.setText(loadedData);
        etGPA.setText(loadedGPA + "");

        if (gender.equals("Male") ){
            rgGender.check(R.id.rgMale);
        } else if ( gender.equals("Female") ){
            rgGender.check(R.id.rgFemale);
        }

    }
}