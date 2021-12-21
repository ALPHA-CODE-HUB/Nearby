package com.example.nearby.Client;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nearby.R;

import java.util.ArrayList;


public class categoryFragment extends Fragment {

    public categoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_category, container, false);
        GridView simpleList;
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


        servicesAdapter adapter = new servicesAdapter(getActivity().getApplication(), courseModelArrayList);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              /* switch (i) {

                    case 11:
                        Intent intent = new Intent(getActivity().getApplication(), gridview.class);
                        startActivity(intent);
                        return;
                }*/
                //more option intent




                Bundle bundle=new Bundle();
                getFragmentManager().beginTransaction().replace(R.id.framelayout,new PostingFragment()).commit();
            }
        });




        return v;
    }
}