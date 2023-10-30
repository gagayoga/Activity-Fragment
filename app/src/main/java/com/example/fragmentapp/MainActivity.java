package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentListener{

    private boolean isFragmentDisplay=false;
    private int mchoice=2;

    private Button btnopen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnopen=findViewById(R.id.buttonopen);

    }

    public void ButtonOpen(View view) {
        if (!isFragmentDisplay){
            open();
        }else {
            close();
        }
    }

    public void open(){
        SimpleFragment simpleFragment=SimpleFragment.newInstance(mchoice);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,simpleFragment).addToBackStack(null).commit();

        isFragmentDisplay=true;
        btnopen.setText("Close");

    }

    public void close(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        SimpleFragment simpleFragment=(SimpleFragment) fragmentManager.findFragmentById(R.id.fragment);

        if(simpleFragment!=null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        isFragmentDisplay=false;
        btnopen.setText("Open");
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mchoice=choice;
    }
}