package com.example.arshif;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.room.TypeConverters;

import com.example.arshif.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.getSystemService;

@TypeConverters({Convertes.class})

public class Fragment_Add_Lines extends Fragment {


    View view;
    MyAppDatabase myAppDatabase=TestActivity.myAppDatabase;
    EditText ed_Date,ed_Cost,ed_Note;
    Button btn_AddLine;
    ListView lv_Lines;
    DatePickerDialog Date_Picker,Date_Picker_edit;
    Spinner sp_Details;
    String ChosenDetail="";
    List<String> ResultDetails = new ArrayList<String>();
    List<LinesEntity> ArrayLines;
    Adapter_Lines adapter_lines;
   int CurDetailId ;
  String CurLineDate;
   int Lineid;
    private SpinnerAdapter spinnerAdapter;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.main_type_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       CurDetailId =adapter_lines.getItem(info.position).getDetailId();
       CurLineDate=adapter_lines.getItem(info.position).getLineDate();
       Lineid=myAppDatabase.linesDao().GetLineId(CurDetailId,CurLineDate);

        switch (item.getItemId()){
            //delete case
            case R.id.deleteMainType:


                LinesEntity linesEntity=new LinesEntity();
                linesEntity.setLineId(Lineid);


                try {
                    myAppDatabase.linesDao().DeleteLine(linesEntity);

                    Toast.makeText(getActivity(),R.string.DeleteDone,Toast.LENGTH_SHORT).show();
                    if (ArrayLines.size()!=0) FillListViewLines();
                    else{ lv_Lines.setVisibility(view.INVISIBLE);}

                                  }
                catch (Exception e) {
                    e.printStackTrace();
                }

                return true;

            default: return super.onContextItemSelected(item);   }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_lines_add,container,false);
      /////////////////////////////////////////////////////////////////
        init();
        int hasLines=0;
        Date mydate = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yestr = dateFormat.format(mydate);
        hasLines=myAppDatabase.linesDao().GetDateLinesCount(yestr);
        if ( hasLines<=0
        ){
            createNotificationChannel();
        RunNotification();
        }

////////////////////////////////
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
                       FillListViewLines();
                   }
                   }, year, month, day);
               Date_Picker.show();

           }


       }

      );
       //

        ed_Date.setText( new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date()));
        // ed_Date.setText( new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        ArrayLines = new ArrayList<LinesEntity>();
        ArrayLines= myAppDatabase.linesDao().getLinesDay(ed_Date.getText().toString());

        if (ArrayLines.size()!=0) FillListViewLines();
        else{ lv_Lines.setVisibility(view.INVISIBLE);}
        FillSpinner();



        btn_AddLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_Cost.getText().length()!=0 && ed_Date.getText().length()!=0){

                    LinesEntity linesEntity=new LinesEntity();
                    linesEntity.setDetailId(myAppDatabase.detailDao().GetDetailId(ChosenDetail));
                    linesEntity.setLineCost(Float.valueOf(ed_Cost.getText().toString()));

                    linesEntity.setLineDate(ed_Date.getText().toString());
                    linesEntity.setLineNote(ed_Note.getText().toString());


                    try {
                        myAppDatabase.linesDao().AddLine(linesEntity);
                        Toast.makeText(getActivity(),R.string.InsertDone,Toast.LENGTH_SHORT).show();
                        ed_Cost.setText("");
                        ed_Note.setText("");
                        FillListViewLines();
                    }
                    catch (Exception e) {
                        ShowMassege(R.string.ErrorCantAddUpdateLine);
                        e.printStackTrace();
                    }
                }
                else
                    ShowMassege(R.string.ErrorLineNull);


}
        });
        //for update
       lv_Lines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lineid =ArrayLines.get(position).getLineId();
                CurDetailId=ArrayLines.get(position).getDetailId();
                ShowUpdateBox(ArrayLines.get(position).getLineCost(),ArrayLines.get(position).getLineDate(),ArrayLines.get(position).getLineNote(),position);

            }
        });

        return view;
    }

    public  void ShowMassege(int message){

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.show();
    }

    //find views by id
    public void init(){
        ed_Date=view.findViewById(R.id.ed_Date_cln);
        sp_Details=view.findViewById(R.id.sp_details);
        ed_Cost=view.findViewById(R.id.ed_Cost);
        ed_Note=view.findViewById(R.id.ed_Note);
        btn_AddLine=view.findViewById(R.id.btn_AddLines);
        lv_Lines=view.findViewById(R.id.lv_lines);


    }

    public void FillListViewLines(){
        lv_Lines.setVisibility(view.VISIBLE);
        ArrayLines.clear();
        ArrayLines= myAppDatabase.linesDao().getLinesDay(ed_Date.getText().toString());

        //don't exception when array list empty only invisible list view
      if (ArrayLines.size()!=0)  {adapter_lines=new Adapter_Lines(getActivity(), ArrayLines);
           lv_Lines.setAdapter(adapter_lines);

        registerForContextMenu( lv_Lines);}
      else    lv_Lines.setVisibility(view.INVISIBLE);
    }

    //Box for Update
    public  void ShowUpdateBox(Float OldCost,String OldDate,String OldNote,final int index){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setTitle(R.string.UpdateDetailType);
        dialog.setContentView(R.layout.input_lines_for_update);

        final EditText edNewCost=dialog.findViewById(R.id.ed_update_cost_line);

        final EditText edNewDate=dialog.findViewById(R.id.ed_update_Date_line);

        final EditText edNewNote=dialog.findViewById(R.id.ed_update_Note_line);

        Button btnSaveNewLine=dialog.findViewById(R.id.btn_Update_Lines);

        edNewCost.setText(OldCost.toString());
        edNewDate.setText(OldDate);
        edNewNote.setText(OldNote);

        edNewDate.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           final Calendar cldr = Calendar.getInstance();
                                           int day = cldr.get(Calendar.DAY_OF_MONTH);
                                           int month = cldr.get(Calendar.MONTH);
                                           int year = cldr.get(Calendar.YEAR);
                                           Date_Picker_edit = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                               @Override
                                               public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                   String m="";
                                                   if(monthOfYear<9) m="0";
                                                   else m="";
                                                   String d="";
                                                   if(dayOfMonth<10) d="0";
                                                   else d="";
                                                   edNewDate.setText(year+ "-" +m+ (monthOfYear + 1) + "-"+d+ dayOfMonth );
                                                   FillListViewLines();
                                               }
                                           }, year, month, day);
                                           Date_Picker_edit.show();

                                       }


                                     }

        );
        edNewDate.setText( new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));






        btnSaveNewLine.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {



                //I neeeeeeeed do check not repait same id in same date
             //   if(myAppDatabase.detailDao().CheckDetailExist(DetailType.getDetailName())<1) {


                if(edNewCost.getText().length()!=0 && edNewDate.getText().length()!=0)

                {
                    LinesEntity line=new LinesEntity();
                    line.setLineId(Lineid);
                    line.setLineCost(Float.valueOf(edNewCost.getText().toString()));
                    line.setLineDate(edNewDate.getText().toString());
                    line.setLineNote(edNewNote.getText().toString());
                    line.setDetailId(CurDetailId);
                    try {
                        myAppDatabase.linesDao().UpdateLine(line);
                        //Cant edit type here
                        Toast.makeText(getActivity(), R.string.UpdateDone, Toast.LENGTH_SHORT).show();
                        adapter_lines.notifyDataSetChanged();
                        FillListViewLines();
                    }
                  catch (Exception e){
                      ShowMassege(R.string.ErrorCantAddUpdateLine);
                  };
                }


              else
                   ShowMassege(R.string.ErrorLineNull);

                dialog.dismiss();

            }
        });
        dialog.show();

    }

    public  void FillSpinner(){
        ResultDetails.clear();
        ResultDetails=myAppDatabase.detailDao().getDetailsName();
        spinnerAdapter =new SpinnerAdapter(getActivity(),ResultDetails);
        sp_Details.setAdapter(spinnerAdapter);

        sp_Details.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChosenDetail = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE){
           CharSequence name="LemubitReminderChannel" ;
           String description ="Channel for Lemubit Reminder";
           int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("notifyLemubit",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);



        }
    }

    private void RunNotification(){
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY,00);
    calendar.set(Calendar.MINUTE, 05);

   Intent intent=new Intent(getActivity(),ReminderBroadCast.class);
    PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),0,intent,0);
    AlarmManager alarmManager=(AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

    if (alarmManager != null) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    }
}
