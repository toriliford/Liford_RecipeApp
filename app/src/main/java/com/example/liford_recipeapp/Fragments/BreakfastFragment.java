package com.example.liford_recipeapp.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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


public class BreakfastFragment extends Fragment {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    public BreakfastFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        dialog = new ProgressDialog(this.getActivity());
        dialog.setTitle("Loading...");

        manager = new RequestManager(this.getActivity());
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        randomRecipeAdapter = new RandomRecipeAdapter(this.getContext(), );


        //inflate layout for fragment
        View view = inflater.inflate(R.layout.fragment_breakfast, container, false);
        //create recycler view
        recyclerView = view.findViewById(R.id.breakfastRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(randomRecipeAdapter);

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
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//            dialog = new ProgressDialog(this);
//            dialog.setTitle("Loading...");
//
//            manager = new RequestManager(this);
//            manager.getRandomRecipes(randomRecipeResponseListener);
//            dialog.show();
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_breakfast, container, false);
//    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiGenerator response, String message) {

            //dismiss dialog when fetched
            dialog.dismiss();
            recyclerView = (RecyclerView) recyclerView.findViewById(R.id.breakfastRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(new LinearLayoutManager(BreakfastFragment.this));
            randomRecipeAdapter = new RandomRecipeAdapter(BreakfastFragment.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(BreakfastFragment.this, message, Toast.LENGTH_SHORT);
        }
    };
}