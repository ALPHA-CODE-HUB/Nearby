package com.example.nearby.ServiceProvider;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class db {
    public static List<String> myratedids = new ArrayList<>();
    public static List<Long> myRating = new ArrayList<>();


    public static void loadRatinglist() {
        myratedids.clear();
        myRating.clear();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("Reviews").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("Reviews")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    for (long x=0; x < (long)task.getResult().get("list_size");x++)
                    {
                        myratedids.add(task.getResult().get("Id"+x).toString());
                        myRating.add((long)task.getResult().get("ratings"+x));

                    }

                } else {

                }
            }
        });

    }
}
