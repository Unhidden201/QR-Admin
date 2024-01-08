package com.example.quickscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class qrgenerator extends AppCompatActivity {
    private EditText edit_code;
    private Button save,back;
    private ImageView barCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        init();
    }
    private void init(){
        edit_code = findViewById(R.id.edit_code);
        barCode = findViewById(R.id.bar_code);
        back = findViewById(R.id.back);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCode();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), dashboardAdmin.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void getCode(){
        try {
            barcode();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    private void barcode() throws WriterException{
        BitMatrix bitMatrix = multiFormatWriter.encode(edit_code.getText().toString(),BarcodeFormat.CODE_128,400,170,null);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        barCode.setImageBitmap(bitmap);

    }
}