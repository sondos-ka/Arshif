package com.example.arshif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Edit_Types extends Fragment {


    Spinner choose_type_spinner;
 Fragment_Edit_Main_Types edit_main_types;
 Fragment_Edit_Detais edit_detais;
    private SpinnerAdapter spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_edit_types_details,container,false);

       choose_type_spinner=view.findViewById(R.id.sp_Choose_Edit);
        edit_main_types =new Fragment_Edit_Main_Types();
        edit_detais=new Fragment_Edit_Detais();


        List<String> options = new ArrayList<String>();

       options.add(getResources().getString(R.string.Type));
        options.add(getResources().getString(R.string.Detail));

        spinnerAdapter =new SpinnerAdapter(getActivity(),options);
        choose_type_spinner.setAdapter(spinnerAdapter);


       /* ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Edit_fields, android.R.layout.simple_list_item_1);
    choose_type_spinner.setAdapter(adapter);*/



    choose_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        Fragment selectedFragment =null;
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            switch (position){
                case 0:
                    selectedFragment =edit_main_types;
                    break;
                case 1:
                    selectedFragment=edit_detais;
                    break;


            }
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container_Edit, selectedFragment, "NewFragmentTag");
            ft.commit();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
        return view;

    }


}
