package com.example.carewise.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carewise.R;
import com.example.carewise.ReachEmergencyActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link emergency_bottomsheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class emergency_bottomsheet extends BottomSheetDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText name,number;
    private Button btnadd;
    View view;

    public emergency_bottomsheet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment emergency_bottomsheet.
     */
    // TODO: Rename and change types and number of parameters
    public static emergency_bottomsheet newInstance(String param1, String param2) {
        emergency_bottomsheet fragment = new emergency_bottomsheet();
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_emergency_bottomsheet, container, false);

        btnadd = view.findViewById(R.id.btnadd);
        name = view.findViewById(R.id.name);
        number = view.findViewById(R.id.number);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the entered name and number
                String str_name = name.getText().toString();
                String str_number = number.getText().toString();

                if (str_name.isEmpty() || str_number.isEmpty()) {
                    // Show an error message if either field is empty
                    Toast.makeText(getContext(), "Empty Credentinals", Toast.LENGTH_SHORT).show();
                } else {
                    // Add the contact to the activity
                    ReachEmergencyActivity activity = (ReachEmergencyActivity) getActivity();
                    if (activity != null) {
                        activity.addEmergencyContact(str_name, str_number);
                        // Show a success message
                        Toast.makeText(getContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
                    }

                    // Dismiss the bottom sheet
                    dismiss();
                }
            }
        });
        return view;
    }
}