package com.example.carewise.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carewise.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText name, email, contact, dateOfBirth;
    Button editProfile,submit;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Calendar calendar;
    RadioButton male,female;
    RadioGroup gender;
    String sGender,sCity;
    boolean isSelect = false;

    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        contact = view.findViewById(R.id.profile_contact);
        dateOfBirth = view.findViewById(R.id.profile_date_of_birth);
        male = view.findViewById(R.id.profile_male);
        female = view.findViewById(R.id.profile_female);
        gender = view.findViewById(R.id.profile_gender);
        submit = view.findViewById(R.id.profile_submit_button);

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = view.findViewById(i);
                sGender = radioButton.getText().toString();
            }
        });

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateClick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                dateOfBirth.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelect) {
                    //new DatePickerDialog(ProfileActivity.this,dateClick,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

                    DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), dateClick, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                    //pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    pickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    pickerDialog.show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(getContext(), "Submitted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void saveData() {
        String nameValue = name.getText().toString().trim();
//        String emailValue = email.getText().toString().trim();
        String contactValue = contact.getText().toString().trim();
        String dobValue = dateOfBirth.getText().toString().trim();

        // Get an instance of SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Profile", MODE_PRIVATE);

        // Get an instance of SharedPreferences.Editor to edit the data
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the data with corresponding keys
        editor.putString("name", nameValue);
//        editor.putString("email", emailValue);
        editor.putString("contact", contactValue);
        editor.putString("dob", dobValue);
        editor.putString("gender", sGender);

        // Commit the changes
        editor.apply();
    }

    private void loadData() {
        // Get an instance of SharedPreferences
        //SharedPreferences sharedPreferences = getContext().get(MODE_PRIVATE);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Profile", MODE_PRIVATE);

        // Get the current user from Firebase Authentication
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Get the current user's email
            String userEmail = currentUser.getEmail();
            if (userEmail != null && !userEmail.isEmpty()) {
                email.setText(userEmail);
            }
        }

        // Retrieve the data using the keys and set them back to the UI elements
        name.setText(sharedPreferences.getString("name", ""));
//        email.setText(sharedPreferences.getString("email", ""));
        contact.setText(sharedPreferences.getString("contact", ""));
        dateOfBirth.setText(sharedPreferences.getString("dob", ""));
        // Set the radio button selection based on the saved gender value
        String gender = sharedPreferences.getString("gender", "");
        if (gender.equals("Male")) {
            male.setChecked(true);
        } else if (gender.equals("Female")) {
            female.setChecked(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Load the saved data when the activity resumes
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Save the data when the activity is paused (i.e., when the user switches to another activity or leaves the app)
    }

}