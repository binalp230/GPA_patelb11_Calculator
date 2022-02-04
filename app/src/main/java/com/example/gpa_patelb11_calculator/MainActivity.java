package com.example.gpa_patelb11_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillSpinner(R.id.spinner3, R.array.Courses);

        fillSpinner(R.id.spinner4, R.array.Courses);

        fillSpinner(R.id.spinner5, R.array.Courses);

        fillSpinner(R.id.spinner6, R.array.Courses);

        fillSpinner(R.id.spinner7, R.array.Courses);

        fillSpinner(R.id.spinner8, R.array.grades);

        fillSpinner(R.id.spinner9, R.array.grades);

        fillSpinner(R.id.spinner10, R.array.grades);

        fillSpinner(R.id.spinner11, R.array.grades);

        fillSpinner(R.id.spinner12, R.array.grades);
    }

    private void fillSpinner(int p, int gradesOrCourses) {
        Spinner spinnerCourse3 = findViewById(p);          //for courses spinner
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, gradesOrCourses, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse3.setAdapter(adapter3);
        spinnerCourse3.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text =  parent.getItemAtPosition(position).toString();
        //Toast toast = Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
        //toast.show();
    }

    public void computeGpa(View view) {
        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView4);

        Spinner spinnerCourse3 = findViewById(R.id.spinner3);
        Spinner spinnerCourse4 = findViewById(R.id.spinner4);
        Spinner spinnerCourse5 = findViewById(R.id.spinner5);
        Spinner spinnerCourse6 = findViewById(R.id.spinner6);
        Spinner spinnerCourse7 = findViewById(R.id.spinner7);
        Spinner spinnerCourse8 = findViewById(R.id.spinner8);
        Spinner spinnerCourse9 = findViewById(R.id.spinner9);
        Spinner spinnerCourse10 = findViewById(R.id.spinner10);
        Spinner spinnerCourse11 = findViewById(R.id.spinner11);
        Spinner spinnerCourse12 = findViewById(R.id.spinner12);

        if (button.getText().toString().equals("Compute GPA")) {
            Set<String> courses = new HashSet<>();

            courses.add((String) spinnerCourse3.getSelectedItem());

            courses.add((String) spinnerCourse4.getSelectedItem());

            courses.add((String) spinnerCourse5.getSelectedItem());

            courses.add((String) spinnerCourse6.getSelectedItem());

            courses.add((String) spinnerCourse7.getSelectedItem());

            List<String> grades = new ArrayList<>();

            grades.add((String) spinnerCourse8.getSelectedItem());

            grades.add((String) spinnerCourse9.getSelectedItem());

            grades.add((String) spinnerCourse10.getSelectedItem());

            grades.add((String) spinnerCourse11.getSelectedItem());

            grades.add((String) spinnerCourse12.getSelectedItem());

            if (courses.size() != 5) {
                Toast.makeText(this, "You have selected duplicate subject.", Toast.LENGTH_LONG).show();
            } else if (courses.contains("Select Course")) {
                Toast.makeText(this, " You have not selected course.", Toast.LENGTH_LONG).show();
            } else if (grades.contains("Grades")) {
                Toast.makeText(this, " Please select grade for all courses.", Toast.LENGTH_LONG).show();
            } else {
                double totalGpa = getTotalGpa(grades);
                //Toast.makeText(this, " Your GPA score is : " + totalGpa , Toast.LENGTH_LONG).show();

                textView.setText(new StringBuilder().append(getString(R.string.gpa_display)).append(totalGpa).toString());

                changeBackgroundColor(totalGpa);

                button.setText(R.string.clear_button);
            }
        } else if (button.getText().toString().equals("Clear")) {
            button.setText(R.string.compute_gpa);
            spinnerCourse3.setSelection(0);
            spinnerCourse4.setSelection(0);
            spinnerCourse5.setSelection(0);
            spinnerCourse6.setSelection(0);
            spinnerCourse7.setSelection(0);
            spinnerCourse8.setSelection(0);
            spinnerCourse9.setSelection(0);
            spinnerCourse10.setSelection(0);
            spinnerCourse11.setSelection(0);
            spinnerCourse12.setSelection(0);
            textView.setText("");
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }

    }

    private void changeBackgroundColor(double totalGpa) {
        if (totalGpa <= 60) {
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        } else if (totalGpa > 60 && totalGpa <= 79) {
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }
    }

    private double getTotalGpa(List<String> grades) {
        float total = 0;
        for (int i = 0; i < grades.size(); i++) {
            switch (grades.get(i)) {
                case "A":
                    total += 97;
                    break;
                case "A-":
                    total += 92;
                    break;
                case "B+":
                    total += 87;
                    break;
                case "B":
                    total += 82;
                    break;
                case "B-":
                    total += 77;
                    break;
                case "C+":
                    total += 72;
                    break;
                case "C":
                    total += 67;
                    break;
                case "C-":
                    total += 62;
                    break;
                case "D+":
                    total += 57;
                    break;
                case "D":
                    total += 52;
                    break;
                case "D-":
                    total += 47;
                    break;
                case "F":
                    total += 44;
                    break;
            }
        }
        double totalGpa = total / 5.0;
        return totalGpa;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}