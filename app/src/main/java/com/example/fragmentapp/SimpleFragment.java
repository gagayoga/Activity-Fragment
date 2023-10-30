package com.example.fragmentapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment extends Fragment {

    private static final String CHOICE = "choice";

    private OnFragmentListener mlistener;
    private int mchoice;

    public interface OnFragmentListener{
        void onRadioButtonChoice(int choice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener){
            mlistener=(OnFragmentListener) context;
        }
    }

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE, choice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final TextView textView = rootView.findViewById(R.id.fragment_header);
        if (getArguments().containsKey(CHOICE)){
            mchoice=getArguments().getInt(CHOICE);
            if(mchoice!=2){
                radioGroup.check(radioGroup.getChildAt(mchoice).getId());
            }
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index){
                    case 0:
                        mchoice=0;
                        textView.setText("Article : Like!");
                        break;
                    case 1:
                        mchoice=1;
                        textView.setText("Article : Thanks!");
                        break;
                    default:
                        mchoice=2;
                        break;
                }
                mlistener.onRadioButtonChoice(mchoice);
            }
        });
        return rootView;
    }
}