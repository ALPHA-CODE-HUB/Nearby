package com.example.nearby.ServiceProvider;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nearby.R;

public class ReviewFragment extends Fragment {

private LinearLayout rate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=inflater.inflate(R.layout.fragment_review, container, false);
      rate=v.findViewById(R.id.rll1);
      for(int x=0;x<rate.getChildCount();x++)
      {
        final int star=x;
        rate.getChildAt(x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRating(star);
            }
        });

      }
      return v;
    }

    public void setRating(int star) {


        for(int x=0;x<rate.getChildCount();x++)
        {
            ImageView starbtn=(ImageView)rate.getChildAt(x);
            starbtn.setImageResource(R.drawable.ic_baseline_star_24grey);

            if(x <= star)
            {
                starbtn.setImageResource(R.drawable.ic_baseline_star_24yellow);
            }
        }



    }

}