package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etConvertedValue; // This seems unused, but keeping it as is

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //     Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //     return insets;
        // });
    }

    public void onConvertClick(View v) {
        Spinner sp1 = (Spinner) findViewById(R.id.spinner1);
        String choice1 = sp1.getSelectedItem().toString(); // Source unit
        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        String choice2 = sp2.getSelectedItem().toString(); // Target unit
        EditText ed1 = (EditText) findViewById(R.id.etValue1);
        EditText ed2 = (EditText) findViewById(R.id.etValue2);

        // Validate input: Check if empty or not a valid number
        String inputStr = ed1.getText().toString();
        if (inputStr.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double value1;
        try {
            value1 = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input: Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        double value2 = 0;

        // Conversion logic using switch for source and target units
        switch (choice1) {
            case "Meter":
                switch (choice2) {
                    case "Meter":
                        value2 = value1;
                        break;
                    case "Millimeter":
                        value2 = value1 * 1000;
                        break;
                    case "Mile":
                        value2 = value1 * 0.000621371;
                        break;
                    case "Foot":
                        value2 = value1 * 3.28084;
                        break;
                }
                break;
            case "Millimeter":  // Fixed typo from "Milllimeter"
                switch (choice2) {
                    case "Meter":
                        value2 = value1 / 1000;
                        break;
                    case "Millimeter":
                        value2 = value1;
                        break;
                    case "Mile":
                        value2 = value1 * 0.000000621371;
                        break;
                    case "Foot":
                        value2 = value1 * 0.00328084;
                        break;
                }
                break;
            case "Mile":
                switch (choice2) {
                    case "Meter":
                        value2 = value1 * 1609.34;
                        break;
                    case "Millimeter":
                        value2 = value1 * 1609340;
                        break;
                    case "Mile":
                        value2 = value1;
                        break;
                    case "Foot":
                        value2 = value1 * 5280;
                        break;
                }
                break;
            case "Foot":
                switch (choice2) {
                    case "Meter":
                        value2 = value1 * 0.3048;
                        break;
                    case "Millimeter":
                        value2 = value1 * 304.8;
                        break;
                    case "Mile":
                        value2 = value1 * 0.000189394;
                        break;
                    case "Foot":
                        value2 = value1;
                        break;
                }
                break;
        }

        ed2.setText(String.valueOf(value2));
    }
}