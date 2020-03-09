package com.example.softwareengrproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteCursor;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InfoScreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Button backButton, submitButton;
    private EditText fName, lName, passwd;

    private OnFragmentInteractionListener mListener;

    public InfoScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info_screen, container, false);

        submitButton = view.findViewById(R.id.submit_button);
        backButton = view.findViewById(R.id.back_button);

        fName = view.findViewById(R.id.first_name_edit_text);
        lName = view.findViewById(R.id.last_name_edit_text);
        passwd = view.findViewById(R.id.password_edit_text);

        // When submit clicked, check values and insert into acctDB
        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View click_view) {

                }
        });

        // When back button clicked go back to register screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View click_view){

            }
        });
        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
