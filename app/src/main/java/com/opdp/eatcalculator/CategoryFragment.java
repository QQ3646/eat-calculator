package com.opdp.eatcalculator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

//    public static final String RECIPES = "RECIPES";
//    public static final String PRODUCTS = "PRODUCTS";
//    public static final String FAVORITE = "FAVORITE";

    public static final int CATEGORY_OF_PRODUCTS = 0;
    public static final int CATEGORY_OF_RECIPES = 1;
    public static final int FAVORITE = 2;
    public static final int HISTORY = 3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TYPE = "-1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(int param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(TYPE);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            MaterialCardView anotherCard = (MaterialCardView) LayoutInflater.from(getContext()).inflate(R.layout.sample_blank_card, view.findViewById(R.id.cont), false);
            ((TextView) anotherCard.findViewById(R.id.card_text)).setText(Integer.toString(i));
            anotherCard.setOnClickListener(this::onClick);
            anotherCard.findViewById(R.id.pic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ImageView) anotherCard.findViewById(R.id.pic)).setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            });
            ((LinearLayout) view.findViewById(R.id.cont)).addView(anotherCard);
        }

        super.onViewCreated(view, savedInstanceState);
    }

    public void onClick(View view) {
        System.out.println("[");
    }
}