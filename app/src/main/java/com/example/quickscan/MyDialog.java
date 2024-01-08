package com.example.quickscan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG = "addClass";
    public static final String STUDENT_ADD_DIALOG = "addStudent";


    private OnclickListener listener;
    public  interface Onclicklistener{
        void onClick(String text1,String text2);
    }
    public void setListener(OnclickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        Dialog dialog = null;
        if(getTag().equals(CLASS_ADD_DIALOG)) dialog=getAddStudentDialog();
        if(getTag().equals(STUDENT_ADD_DIALOG)) dialog = getAddStudentDialog();
        return dialog;
    }

    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Students");

        EditText roll_edt = view.findViewById(R.id.est01  );
        EditText name_edt = view.findViewById(R.id.est02);

        roll_edt.setHint("Roll");
        name_edt.setHint("Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v->{
           String roll = roll_edt.getText().toString();
           String name = name_edt.getText().toString();
           roll_edt.setText(String.valueOf(Integer.parseInt(roll)+1));
           listener.onClick(roll,name);

        });
        return builder.create();


    }
    private Dialog getAddClassDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title = view.findViewById(R.id.titleDialog);
        title.setText("Add New Event");

        EditText class_edt = view.findViewById(R.id.est01);
        EditText subject_edt = view.findViewById(R.id.est02);

        class_edt.setHint("Event Name");
        subject_edt.setHint("Date");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String className = class_edt.getText().toString();
            String subName = subject_edt.getText().toString();
            listener.onClick(className,subName);
            dismiss();
        });

        return  builder.create();


    }


}
