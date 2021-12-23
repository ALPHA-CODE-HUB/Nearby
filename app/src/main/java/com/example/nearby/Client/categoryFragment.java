package com.example.nearby.Client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nearby.R;

import java.util.ArrayList;


public class categoryFragment extends Fragment {
    private EditText pin;

    public categoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_category, container, false);
        GridView simpleList;
        pin = v.findViewById(R.id.pin);

        ArrayList list = new ArrayList<>();

        simpleList = v.findViewById(R.id.simpleGridView);
        ArrayList<coursemodel> courseModelArrayList = new ArrayList<coursemodel>();
        courseModelArrayList.add(new coursemodel("ELECTRICIAN", R.drawable.elec));
        courseModelArrayList.add(new coursemodel("PLUMBING", R.drawable.plumberr));
        courseModelArrayList.add(new coursemodel("AC SERVICES", R.drawable.ac));
        courseModelArrayList.add(new coursemodel("PAINTER", R.drawable.paint));
        courseModelArrayList.add(new coursemodel("CARPENTER", R.drawable.carpntr));
        courseModelArrayList.add(new coursemodel("HOME CLEANING", R.drawable.house));
        courseModelArrayList.add(new coursemodel("KITCHEN CLEANING", R.drawable.kitchen));
        courseModelArrayList.add(new coursemodel("TILE WORK", R.drawable.tilework));
        courseModelArrayList.add(new coursemodel("STEAM IRON", R.drawable.steam));
        courseModelArrayList.add(new coursemodel("LAUNDRY", R.drawable.laundy));
        courseModelArrayList.add(new coursemodel("EVENT PHOTO SHOOT", R.drawable.photoshoot));
        courseModelArrayList.add(new coursemodel("MORE", R.drawable.more));

        pin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        if (pin.getText().toString().length() < 6) {

                            Toast.makeText(getActivity().getApplication(), "Invalid Pin, Minimum 6 digit", Toast.LENGTH_SHORT).show();

                        } else {
                            if (Integer.parseInt(pin.getText().toString()) > 670001 && Integer.parseInt(pin.getText().toString()) < 695615) {

                                InputMethodManager imm = (InputMethodManager) getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(pin.getWindowToken(), 0);

                            } else {
                                Toast.makeText(getActivity().getApplication(), "This pin code is out of kerala", Toast.LENGTH_SHORT).show();

                            }
                        }

                        return true;
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplication(), "Invalid Pin", Toast.LENGTH_SHORT).show();

                    }
                }
                return false;
            }
        });

        servicesAdapter adapter = new servicesAdapter(getActivity().getApplication(), courseModelArrayList);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (pin.getText().toString().length() < 6) {

                        Toast.makeText(getActivity().getApplication(), "Invalid Pin, Minimum 6 digit", Toast.LENGTH_SHORT).show();

                    } else {
                        if (Integer.parseInt(pin.getText().toString()) > 670001 && Integer.parseInt(pin.getText().toString()) < 695615) {
                            Bundle bundle=new Bundle();
                            if(pin.getText().toString().length()==0){
                                Toast.makeText(getActivity().getApplication(),"Enter pincode",Toast.LENGTH_LONG).show();
                            }else {

                                bundle.putInt("pin", Integer.parseInt(pin.getText().toString()));
                                PostingFragment fragment = new PostingFragment();
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                            }
                            InputMethodManager imm = (InputMethodManager) getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(pin.getWindowToken(), 0);

                        } else {
                            Toast.makeText(getActivity().getApplication(), "This pin code is out of kerala", Toast.LENGTH_SHORT).show();

                        }
                    }


                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplication(), "Invalid Pin", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }
}