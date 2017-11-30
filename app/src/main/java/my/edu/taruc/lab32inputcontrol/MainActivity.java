package my.edu.taruc.lab32inputcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale,radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linking UI to program
        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);
        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);

        //Create an adaptor for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.age_group,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(
            AdapterView<?> parent,
            View view,
            int position,
            long id) {
        Toast.makeText(getApplicationContext(), "Position:" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        double premium=0;
        int pos;
        int indexGender;
        indexGender = radioGroupGender.getCheckedRadioButtonId();
        pos = spinnerAge.getSelectedItemPosition();
        //todo: determine the basic premium
        if (pos == 0){
            premium += 50;
        }else if (pos == 1){
            premium += 55;
        }else if (pos == 2){
            premium += 60;
            if (indexGender==R.id.radioButtonMale){
                premium += 50;
            }
        }else if (pos == 3){
            premium += 70;
            if (indexGender==R.id.radioButtonMale){
                premium += 100;
            }
            if(checkBoxSmoker.isChecked()){
                premium += 100;
            }
        }else if (pos == 4){
            premium += 120;
            if (indexGender==R.id.radioButtonMale){
                premium += 100;
            }
            if(checkBoxSmoker.isChecked()){
                premium += 150;
            }
        }else if (pos == 5){
            premium += 160;
            if (indexGender==R.id.radioButtonMale){
                premium += 50;
            }
            if(checkBoxSmoker.isChecked()){
                premium += 150;
            }
        }else if (pos == 6){
            premium += 200;
            if(checkBoxSmoker.isChecked()){
                premium += 250;
            }
        }else if (pos == 7){
            premium += 250;
            if(checkBoxSmoker.isChecked()){
                premium += 250;
            }
        }

        textViewPremium.setText(
                getString(R.string.premium) + ":" + premium
        );
    }

    public void resetPremium(View view){
        //todo: clear UI and reset premium value
        spinnerAge.setSelection(0);
        radioButtonMale.setChecked(true);
        checkBoxSmoker.setChecked(false);
        textViewPremium.setText("Premium");
    }
}
