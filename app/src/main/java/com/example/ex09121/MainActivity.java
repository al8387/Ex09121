package com.example.ex09121;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * A simple calculator activity that performs basic arithmetic operations.
 * @author Adam Lifshiz
 * @version 1.0
 * @since 04/01/2026
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The input field for the first number.
     */
    private EditText eT1;
    /**
     * The input field for the second number.
     */
    private EditText eT2;
    /**
     * The text view to display the result and trigger the context menu.
     */
    private TextView tV;

    /**
     * Called when the activity is first created. Initializes the UI components and registers the context menu.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eT1 = findViewById(R.id.eT1);
        eT2 = findViewById(R.id.eT2);
        tV = findViewById(R.id.tV);
        tV.setText("long press for operations: ");
        tV.setTextColor(Color.BLUE);
        registerForContextMenu(tV);
    }

    /**
     * Called when the context menu for the view is being built. Populates the menu with arithmetic operations.
     * @param menu The context menu that is being built.
     * @param v The view for which the context menu is being built.
     * @param menuInfo Extra information about the item for which the context menu should be shown.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose an operation");
        menu.add(0, v.getId(), 0, "+");
        menu.add(0, v.getId(), 1, "-");
        menu.add(0, v.getId(), 2, "*");
        menu.add(0, v.getId(), 3, "/");
    }

    /**
     * Checks if a given string represents a valid number (integer or double).
     * @param text The string to be checked.
     * @return true if the string is a valid number, false otherwise.
     */
    private boolean isNum(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        return text.matches("[-+]?([0-9]+\\.?[0-9]*|\\.[0-9]+)");
    }

    /**
     * This hook is called whenever an item in a context menu is selected. It performs the calculation based on the selected operation.
     * @param item The context menu item that was selected.
     * @return boolean Return false to allow normal context menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        String num1 = eT1.getText().toString();
        String num2 = eT2.getText().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            Toast.makeText(this, "please enter both numbers", Toast.LENGTH_SHORT).show();
            return super.onContextItemSelected(item);
        }
        if (!isNum(num1) || !isNum(num2)) {
            Toast.makeText(this, "invalid number", Toast.LENGTH_SHORT).show();
            return super.onContextItemSelected(item);
        }

        double numA = Double.parseDouble(num1);
        double numB = Double.parseDouble(num2);
        double answer;

        if (item.getTitle().equals("+")) {
            answer = numA + numB;
        } else if (item.getTitle().equals("-")) {
            answer = numA - numB;
        } else if (item.getTitle().equals("*")) {
            answer = numA * numB;
        } else if (item.getTitle().equals("/")) {
            if (numB == 0) {
                Toast.makeText(this, "cannot divide by zero", Toast.LENGTH_SHORT).show();
                return super.onContextItemSelected(item);
            }
            answer = numA / numB;
        } else {
            return super.onContextItemSelected(item);
        }

        tV.setText(String.valueOf(answer));
        return super.onContextItemSelected(item);
    }
}
