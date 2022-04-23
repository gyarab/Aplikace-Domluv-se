package com.example.rocnikovaprace;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NastaveniFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NastaveniFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NastaveniFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NastaveniFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NastaveniFragment newInstance(String param1, String param2) {
        NastaveniFragment fragment = new NastaveniFragment();
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






        File file = new File(getContext().getFilesDir(), "cislo.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            bw.write("ano");
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            System.out.println("Do souboru se nepovedlo zapsat.");
        }



        return inflater.inflate(R.layout.fragment_nastaveni, container, false);
    }







}