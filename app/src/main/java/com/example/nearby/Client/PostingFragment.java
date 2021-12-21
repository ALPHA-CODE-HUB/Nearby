package com.example.nearby.Client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.nearby.R;


public class PostingFragment extends Fragment {


    public PostingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posting, container, false);
        Spinner mySpinner = v.findViewById(R.id.spinner1);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(getActivity().getApplication(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Levels));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myadapter);
        Button button;
        button = v.findViewById(R.id.btn);
        return v;
    }
}