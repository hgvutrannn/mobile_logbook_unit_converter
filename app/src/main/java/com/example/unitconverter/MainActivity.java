// Dán toàn bộ code của MainActivity.java đã cải tiến ở trên vào đây
package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Define UI elements
    private EditText etValueToConvert;
    private EditText etConvertedValue;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;

    /**
     * Enum to manage length units and their conversion factors relative to a base unit (Meter).
     * This makes the code clean, type-safe, and easy to extend.
     */
    public enum LengthUnit {
        METER(1.0),
        MILLIMETER(0.001),
        MILE(1609.34),
        FOOT(0.3048);

        private final double toMetersFactor;

        LengthUnit(double toMetersFactor) {
            this.toMetersFactor = toMetersFactor;
        }

        public double getToMetersFactor() {
            return toMetersFactor;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();
    }

    /**
     * Finds and assigns all UI views to their corresponding variables.
     */
    private void initializeViews() {
        etValueToConvert = findViewById(R.id.etValueToConvert);
        etConvertedValue = findViewById(R.id.etConvertedValue);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
    }

    /**
     * Handles the click event of the "CONVERT" button.
     * @param v The view that was clicked (the button).
     */
    public void onConvertClick(View v) {
        // 1. Get user input string
        String inputStr = etValueToConvert.getText().toString();

        // 2. Validate input
        if (inputStr.isEmpty()) {
            Toast.makeText(this, R.string.error_please_enter_value, Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue;
        try {
            inputValue = Double.parseDouble(inputStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.error_invalid_number, Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Get selected units from spinners
        String fromUnitStr = spinnerFromUnit.getSelectedItem().toString().toUpperCase(Locale.ROOT);
        String toUnitStr = spinnerToUnit.getSelectedItem().toString().toUpperCase(Locale.ROOT);

        LengthUnit fromUnit = LengthUnit.valueOf(fromUnitStr);
        LengthUnit toUnit = LengthUnit.valueOf(toUnitStr);

        // 4. Perform conversion
        double result = convert(inputValue, fromUnit, toUnit);

        // 5. Display the result
        etConvertedValue.setText(String.valueOf(result));
    }

    /**
     * Converts a value from a source unit to a target unit using a base unit (Meter).
     * @param value The numerical value to convert.
     * @param fromUnit The source LengthUnit.
     * @param toUnit The target LengthUnit.
     * @return The converted value.
     */
    private double convert(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        // Step 1: Convert the input value to the base unit (Meters).
        double valueInMeters = value * fromUnit.getToMetersFactor();

        // Step 2: Convert the value from the base unit to the target unit.
        double result = valueInMeters / toUnit.getToMetersFactor();
        return Math.round(result * 10000.0) / 10000.0;
    }
}