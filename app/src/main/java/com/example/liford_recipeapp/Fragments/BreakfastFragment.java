package com.example.liford_recipeapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.liford_recipeapp.Adapters.RandomRecipeAdapter;
import com.example.liford_recipeapp.CategoryChoice;
import com.example.liford_recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.liford_recipeapp.MainActivity;
import com.example.liford_recipeapp.Models.RandomRecipeApiGenerator;
import com.example.liford_recipeapp.R;
import com.example.liford_recipeapp.RequestManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BreakfastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreakfastFragment extends Fragment {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    public BreakfastFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BreakfastFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BreakfastFragment newInstance(String param1, String param2) {
        BreakfastFragment fragment = new BreakfastFragment();
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
        return inflater.inflate(R.layout.fragment_breakfast, container, false);
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiGenerator response, String message) {
            //dismiss dialog when fetched
            dialog.dismiss();
            recyclerView = (RecyclerView) recyclerView.findViewById(R.id.);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(CategoryChoice.class, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(CategoryChoice.class, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        }
    };
}