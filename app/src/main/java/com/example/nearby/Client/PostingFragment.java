package com.example.nearby.Client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nearby.Authentication.LogIn;
import com.example.nearby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PostingFragment extends Fragment {

    int pin;

    public PostingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posting, container, false);
        ProgressBar progressBar = v.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        Spinner mySpinner = v.findViewById(R.id.spinner1);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(getActivity().getApplication(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Levels));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myadapter);

        Button button = v.findViewById(R.id.btn);
        EditText serviceRequest=v.findViewById(R.id.serviceRequest);
        EditText description=v.findViewById(R.id.desc);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Bundle bundle=this.getArguments();
        pin=bundle.getInt("pin");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String difficulty=mySpinner.getSelectedItem().toString();
                String desc=description.getText().toString();
                String sr=serviceRequest.getText().toString();
                long timestamp=System.currentTimeMillis();
                Date date=new Date();
                SimpleDateFormat format=new SimpleDateFormat("dd/MM/yy");
                String date1=format.format(date);

                if(desc==null || difficulty==null || sr==null){
                    Toast.makeText(getActivity(),"Fill all fields!",Toast.LENGTH_LONG).show();
                }else{
                    RequestBean rb=new RequestBean(sr,desc,difficulty,date1,timestamp,pin);
                    progressBar.setVisibility(View.VISIBLE);
                    FirebaseDatabase.getInstance().getReference("Request").child(String.valueOf(pin)).child(String.valueOf(System.currentTimeMillis())).setValue(rb);
                    FirebaseDatabase.getInstance().getReference("Requests").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(timestamp)).setValue(rb).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"Request posted!",Toast.LENGTH_LONG).show();

                            }else{

                                Toast.makeText(getActivity(),"Something went wrong!",Toast.LENGTH_LONG).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }


            }
        });

        return v;
    }

}