package com.example.mfood;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class fragment_listView extends Fragment {
    private EditText edtSearch;
    private RelativeLayout mainFragment;
    public static boolean check = false;
    public static boolean check_2 = false;
    private ListView listView;
    private ImageButton imageButtonSearch;
    private ImageButton imgButtonSelect;
    List<Information_Food> listFood;
    MyAdapter myAdapter;
    private connect connect;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        connect = (com.example.mfood.connect) getActivity();
        View view = inflater.inflate(R.layout.activity_fragment_listfood, container, false);

        setID(view);
        imgButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=!check;
                connect.CHECK_CLICK(check);
                }
        });
        mainFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!check)
                {

                }
                else ;check=false;
                connect.CHECK_CLICK(check);

            }
        });
        return view;
    }

    private void setID(View view)
    {
        listView = view.findViewById(R.id.listView);
        edtSearch = view.findViewById(R.id.edtSearch);
        imgButtonSelect = view.findViewById(R.id.imgbtSelect);
        imageButtonSearch = view.findViewById(R.id.imgbtSearch);
        mainFragment = view.findViewById(R.id.mainFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        myAdapter = new MyAdapter(getContext(), Home_Screen.listFood);
        listView.setAdapter(myAdapter);
        Log.d("compile", "ON resume");
    }
}
