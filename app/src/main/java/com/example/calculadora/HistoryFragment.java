package com.example.calculadora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class HistoryFragment extends Fragment {

       public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_history);
        dbhelper.select(getActivity(), lv);
        return v;
    }
}