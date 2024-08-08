package com.example.stringsaver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SIGN = "sign";
    private static final String KEY_COUNTERSIGN = "countersign";
    private static final String DEFAULT_VALUE_STRING = "";

    private TextView textViewSign;
    private TextView textViewCounterSign;
    private EditText editTextSign;
    private Button btnSetAsSign;
    private Button btnSetAsCounterSign;
    private Button btnClearSigns;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        preferences = MainActivity.this.getSharedPreferences("com.example.stringsaver", Context.MODE_PRIVATE);

        populateViews();
        populateTextViewSignOnCreate();
        populateTextViewCounterSignOnCreate();

    }

    private void populateViews() {
        this.textViewSign = findViewById(R.id.textViewSign);
        this.textViewCounterSign = findViewById(R.id.textViewCounterSign);
        this.editTextSign = findViewById(R.id.editTextSign);
        this.btnSetAsSign = findViewById(R.id.btnSetAsSign);
        this.btnSetAsCounterSign = findViewById(R.id.btnSetAsCounterSign);
        this.btnClearSigns = findViewById(R.id.btnClearSigns);

    }



    private void populateTextViewSignOnCreate() {
        String sign = this.preferences.getString(KEY_SIGN, DEFAULT_VALUE_STRING);
        if(sign.equals(DEFAULT_VALUE_STRING)) {
            this.textViewSign.setText("No saved sign found.");
        } else {
            this.textViewSign.setText("Previously saved sign was '" + sign + "'.");
        }
    }

    private void populateTextViewCounterSignOnCreate() {
        String countersign = this.preferences.getString(KEY_COUNTERSIGN, DEFAULT_VALUE_STRING);
        if(countersign.equals(DEFAULT_VALUE_STRING)) {
            this.textViewCounterSign.setText("No saved countersign found.");
        } else {
            this.textViewCounterSign.setText("Previously saved countersign was '" + countersign + "'.");
        }
    }

    public void onBtnSetAsSignClick(View view) {
        Toast.makeText(this, "Saving text as sign..", Toast.LENGTH_SHORT).show();
        String str = editTextSign.getText().toString();
        setStringAsSign(str);
    }

    public void onBtnSetAsCounterSignClick(View view) {
        Toast.makeText(this, "Saving text as countersign...", Toast.LENGTH_SHORT).show();
        String str = editTextSign.getText().toString();
        setStringAsCounterSign(str);
    }

    public void onBtnClearSignsClick(View view) {
        Toast.makeText(this, "Clearing sign and countersign...", Toast.LENGTH_SHORT).show();
        setStringAsSign(DEFAULT_VALUE_STRING);
        setStringAsCounterSign(DEFAULT_VALUE_STRING);
    }

    @SuppressLint("ApplySharedPref")
    private void setStringAsSign(String str) {
        this.preferences.edit().putString(KEY_SIGN, str).commit();
        populateTextViewSignOnCreate();
    }

    @SuppressLint("ApplySharedPref")
    private void setStringAsCounterSign(String str) {
        this.preferences.edit().putString(KEY_COUNTERSIGN, str).commit();
        populateTextViewCounterSignOnCreate();
    }
}
