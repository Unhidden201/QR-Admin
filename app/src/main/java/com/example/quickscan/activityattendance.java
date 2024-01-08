package com.example.quickscan;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class activityattendance extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;

    ClassAdapter classAdapter;
    ArrayList<ClassItem> classItems = new ArrayList<>();
    Toolbar toolbar;

    EditText class_edt;
    EditText subject_edt;


    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityattendance);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v-> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        classAdapter = new ClassAdapter(this,classItems);
        recyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClicklistener(position -> gotoItemActivity(position));

        setToolbar();

    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = findViewById(R.id.title_toolbar);
        TextView subtitle = findViewById(R.id.subtitle_toolbar);
        ImageButton back = findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("Attendance App");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this,StudentActivity.class);

        intent.putExtra("className",classItems.get(position).getClassName());
        intent.putExtra("subjectName",classItems.get(position).getSubjectName());
        intent.putExtra("position",position);
        startActivity(intent);

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.class_dialog,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        class_edt = view.findViewById(R.id.class_edt);
        subject_edt = view.findViewById(R.id.subject_edt);

        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add = view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dialog.dismiss());

        add.setOnClickListener(v -> {
            addClass();
            dialog.dismiss();
        });


    }

    private void addClass() {
        String className = class_edt.getText().toString();
        String subjectName = subject_edt.getText().toString();
        classItems.add(new ClassItem(className,subjectName));
        classAdapter.notifyDataSetChanged();
    }
}