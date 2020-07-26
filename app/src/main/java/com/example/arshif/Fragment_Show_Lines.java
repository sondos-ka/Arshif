package com.example.arshif;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment_Show_Lines extends Fragment {


    View view;
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    EditText ed_Date;
    RadioGroup ShowType;
    RadioButton selected_radio;
    RecyclerView rv_lines;
    RecyclerView rv_Account;
    Line_Show_Adpater lineAdapter;
    Accounting_Line_Show_Adapter AccountAdapter;
    List<LinesEntity> ArrayLines;
    List<AccountObject> ArrayAccount;
    DatePickerDialog Date_Picker;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {



       view=inflater.inflate(R.layout.fragment_show_lines,container,false);
        init();
        ed_Date.setText( new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date()));

        ed_Date.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           final Calendar cldr = Calendar.getInstance();


                                           int day = cldr.get(Calendar.DAY_OF_MONTH);
                                           int month = cldr.get(Calendar.MONTH);
                                           int year = cldr.get(Calendar.YEAR);


                                           Date_Picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                               @Override
                                               public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                   String m="";
                                                   if(monthOfYear<9) m="0";
                                                   else m="";
                                                   String d="";
                                                   if(dayOfMonth<10) d="0";
                                                   else d="";

                                                   ed_Date.setText(year+ "-" +m+ (monthOfYear + 1) + "-"+d+ dayOfMonth );

                                                   UpdateRecycler();

                                               }
                                           }, year, month, day);
                                           Date_Picker.show();

                                       }
                                   }


        );

        ArrayLines = new ArrayList<LinesEntity>();
        ArrayAccount=new ArrayList<AccountObject>();

        ArrayLines= myAppDatabase.linesDao().getLinesDay(ed_Date.getText().toString());
        ArrayAccount=myAppDatabase.linesDao().getLinesDayAccount(ed_Date.getText().toString());

        FillRecyclerView();

 ShowType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ArrayLines.clear();
                ArrayAccount.clear();
                switch (checkedId){


                    case R.id.radioDay :
                        ArrayLines= myAppDatabase.linesDao().getLinesDay(ed_Date.getText().toString());
                        ArrayAccount=myAppDatabase.linesDao().getLinesDayAccount(ed_Date.getText().toString());

                        FillRecyclerView();
                        break;
                    case R.id.radioMonth:

                        ArrayLines= myAppDatabase.linesDao().getLinesMonth(ed_Date.getText().toString());
                        ArrayAccount=myAppDatabase.linesDao().getLinesMonthAccount(ed_Date.getText().toString());

                        FillRecyclerView();
                        break;
                    case R.id.radioYear:
                        //
                        ArrayLines= myAppDatabase.linesDao().getLinesYear(ed_Date.getText().toString());
                        ArrayAccount=myAppDatabase.linesDao().getLinesYearAccount(ed_Date.getText().toString());

                        FillRecyclerView();
                        break;
                    default:
                        break;
                }

            }
        });

        // find the radiobutton by returned id



        return  view;
    }




    public void init(){
      ed_Date=view.findViewById(R.id.ed_Date_cln_show);
      ShowType=view.findViewById(R.id.radioGroup);
      rv_lines=view.findViewById(R.id.rv_lines_show);
      rv_Account=view.findViewById(R.id.rv_Accounting);

    }
    public void FillRecyclerView(){


            rv_lines.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            lineAdapter = new Line_Show_Adpater(ArrayLines);
            rv_lines.setAdapter(lineAdapter);

            rv_Account.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
            AccountAdapter=new Accounting_Line_Show_Adapter(ArrayAccount);
            rv_Account.setAdapter( AccountAdapter);
        }
    public void UpdateRecycler(){

        ArrayLines.clear();
        ArrayAccount.clear();

        if (ShowType.getCheckedRadioButtonId()==R.id.radioDay){
            ArrayLines= myAppDatabase.linesDao().getLinesDay(ed_Date.getText().toString());
            ArrayAccount=myAppDatabase.linesDao().getLinesDayAccount(ed_Date.getText().toString());
            FillRecyclerView();
        }
        else if (ShowType.getCheckedRadioButtonId()==R.id.radioMonth){
            ArrayLines= myAppDatabase.linesDao().getLinesMonth(ed_Date.getText().toString());
            ArrayAccount=myAppDatabase.linesDao().getLinesMonthAccount(ed_Date.getText().toString());
            FillRecyclerView();
        }
        else if (ShowType.getCheckedRadioButtonId()==R.id.radioYear){
            ArrayLines= myAppDatabase.linesDao().getLinesYear(ed_Date.getText().toString());
            ArrayAccount=myAppDatabase.linesDao().getLinesYearAccount(ed_Date.getText().toString());
            FillRecyclerView();
        }



    }
    }


