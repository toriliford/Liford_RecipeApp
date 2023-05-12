package com.example.liford_recipeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BreakfastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakfastFragment extends Fragment {



//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;



    ListView breakfastLV;

    public BreakfastFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment BreakfastFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static BreakfastFragment newInstance(String param1, String param2) {
//        BreakfastFragment fragment = new BreakfastFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        breakfastLV = (ListView)getView().findViewById(R.id.breakfastLV);

        //create data
        ArrayList<Recipe> bfastRecipes = new ArrayList<>();

        bfastRecipes.add(new Recipe("Pancakes", "Breakfast", new String[] {""}, new String[] {""}, R.drawable.pancakes));
        bfastRecipes.add(new Recipe("Hashbrown Casserole", "Breakfast", new String[] {""}, new String[] {""}, R.drawable.hashbrown_casserole));
        bfastRecipes.add(new Recipe("French Toast", "Breakfast", new String[] {""}, new String[] {""}, R.drawable.french_toast));
        bfastRecipes.add(new Recipe("Overnight Oats", "Breakfast", new String[] {""}, new String[] {""}, R.drawable.overnight_oats));
        bfastRecipes.add(new Recipe("Stuffed French Toast", "Breakfast", new String[] {""}, new String[] {""}, R.drawable.stuffed_toast));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_breakfast, container, false);
        //get reference of listView

        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);

    }
}