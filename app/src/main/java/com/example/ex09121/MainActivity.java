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

public class MainActivity extends AppCompatActivity {

    private EditText eT1;
    private EditText eT2;
    private TextView tV;
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
    }@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose an operation");
        menu.add(0, v.getId(), 0, "+");
        menu.add(0, v.getId(), 1, "-");
        menu.add(0, v.getId(), 2, "*");
        menu.add(0, v.getId(), 3, "/");
    }
    private boolean isNum(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        return text.matches("[-+]?([0-9]+\\.?[0-9]*|\\.[0-9]+)");
    }
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