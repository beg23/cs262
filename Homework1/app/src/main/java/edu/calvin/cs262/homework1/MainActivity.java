package edu.calvin.cs262.homework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CharSequence operator = "+";

    /**
     * Creates the activity and sets up the Spinner with math operations as options.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner operation = findViewById(R.id.select_operator);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operation.setAdapter(adapter);
        operation.setOnItemSelectedListener(this);
    }

    /**
     * Stores the operator type (+,-,*,÷) when selected on the Spinner.
     *
     * @param parent The AdapterView which stores the array of operations.
     * @param view The View which was clicked.
     * @param pos The int index of the item which the Spinner currently has selected.
     * @param id The long row id of the selected item.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        operator = (CharSequence) parent.getItemAtPosition(pos);
    }

    /**
     * If the selection is lost from the Spinner, default to addition.
     *
     * @param parent The AdapterView which lost its selection.
     */
    public void onNothingSelected(AdapterView<?> parent) {
        operator = "+";
    }

    /**
     * When the Calculate button is pressed, display the result of <inputOne> <operator> <inputTwo>.
     * <p>
     * Uses integer division (ex: 6 / 5 = 1).
     * Will display nothing if division by zero or either input is not provided.
     * </p>
     *
     * @param view The Calculate button.
     */
    public void calculateAnswer(View view) {

        // Clear any text in output field
        TextView output = findViewById(R.id.output_value);
        output.setText("");

        // Find the input text fields
        TextView inputTextOne = findViewById(R.id.input_value_one);
        TextView inputTextTwo = findViewById(R.id.input_value_two);

        // Only display an answer if both inputs are provided
        if (!inputTextOne.getText().toString().equals("") &&
                !inputTextTwo.getText().toString().equals("")) {

            // Help from https://stackoverflow.com/questions/5858307/charsequence-to-int
            int inputOne = Integer.parseInt(inputTextOne.getText().toString());
            int inputTwo = Integer.parseInt(inputTextTwo.getText().toString());
            int result;

            // Determine result depending on operator
            if (operator.equals("-")) {
                result = inputOne - inputTwo;
            } else if (operator.equals("*")) {
                result = inputOne * inputTwo;
            } else if (operator.equals("÷")) {
                // Prevent divide by zero
                if (inputTwo == 0) {
                    return;
                }
                result = inputOne / inputTwo;
            } else {
                result = inputOne + inputTwo;
            }
            output.setText(String.format(Locale.ENGLISH, "%d", result));
        }
    }
}
