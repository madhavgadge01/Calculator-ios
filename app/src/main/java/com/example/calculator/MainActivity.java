package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText, outputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        inputEditText = findViewById(R.id.inputText);
        outputEditText = findViewById(R.id.outputText);
        Toast.makeText(this, "Created By Madhav", Toast.LENGTH_SHORT).show();
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void buttonClicked(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString().trim();
        String oldInput = inputEditText.getText().toString();

        switch (buttonText) {
            case "AC":
                inputEditText.setText("");
                outputEditText.setText("");
                break;

            case "D":
                if (!oldInput.isEmpty()) {
                    inputEditText.setText(oldInput.substring(0, oldInput.length() - 1));
                }
                break;

            case "=":
                evaluateExpression(oldInput);
                break;

            default:
                inputEditText.setText(oldInput + buttonText);
                break;
        }
    }

    private void evaluateExpression(String expression) {
        try {
            // Replace X and ÷ with standard operators
            expression = expression.replace("X", "*").replace("÷", "/");

            Expression exp = new ExpressionBuilder(expression).build();
            double result = exp.evaluate();

            outputEditText.setText(String.valueOf(result));

        } catch (Exception e) {
            outputEditText.setText("Error");
        }
    }
}