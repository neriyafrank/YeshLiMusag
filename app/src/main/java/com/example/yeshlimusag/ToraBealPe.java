package com.example.yeshlimusag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ToraBealPe extends AppCompatActivity implements View.OnClickListener {
    Button btnHarishonim, btnSaveToHarishonim, btnSederHadorot, btnSaveToSederHadorot;
    Button close;
    EditText etHarishonim, etSederHadorot;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    final String tora_beal_pe_harishonim = "תקופת הראשונים היא התקופה של פוסקי ההלכה לאחר תקופת הגאונים, הראשונים פירשו והסבירו בעיקר את הגמרא",
            tora_beal_pe_sederHadorot = "סדר הדורות הוא סדר השתלשלות התורה שבעל פה עד ימינו בפסיקת ההלכה ובאגדה";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tora_beal_pe);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnHarishonim = findViewById(R.id.btnHarishonim);
        btnSaveToHarishonim = findViewById(R.id.btnSaveToHarishonim);
        etHarishonim = findViewById(R.id.etHarishonim);
        btnSederHadorot = findViewById(R.id.btnSederHadorot);
        btnSaveToSederHadorot = findViewById(R.id.btnSaveSederHadorot);
        etSederHadorot = findViewById(R.id.etSederHadorot);
        btnSederHadorot.setOnClickListener(this);
        btnSaveToSederHadorot.setOnClickListener(this);
        btnHarishonim.setOnClickListener(this);
        btnSaveToHarishonim.setOnClickListener(this);
        settingData();
    }
    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("tora_beal_pe_harishonim", "")=="") {
            editor.putString("tora_beal_pe_harishonim", tora_beal_pe_harishonim);
        }
        if(folderData.getString("tora_beal_pe_sederHadorot", "")=="") {
            editor.putString("tora_beal_pe_sederHadorot", tora_beal_pe_sederHadorot);
        }
        editor.commit();
    }

    public void createDialogHesber(String musagName, String title) {
        dialogHesber = new Dialog(this);
        dialogHesber.setContentView(R.layout.layout_hesber);
        dialogHesber.setCancelable(true);
//        dialogHesber.getWindow().getDecorView().setBackground(drawable.shabat.getDrawable());
        textTitle = (TextView) dialogHesber.findViewById(R.id.textTitle);
        textTitle.setText(title);
        musagText = (TextView) dialogHesber.findViewById(R.id.textMusagHesber);
        addText(musagName);
        close = (Button) dialogHesber.findViewById(R.id.close);
        close.setOnClickListener(this);
        dialogHesber.show();
    }

    private void addText(String musagName) {
        musagText.setText(folderData.getString("tora_beal_pe_" + musagName, ""));
    }
    @Override
    public void onClick(View view) {
        if (view == btnHarishonim) {
            createDialogHesber("harishonim", "תקופת הראשונים");
        }
        else if(view == btnSaveToHarishonim){
            if(etHarishonim.getText()!=null){
                addData("tora_beal_pe_harishonim", tora_beal_pe_harishonim, etHarishonim.getText().toString());
                etHarishonim.setText("");
                etHarishonim.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btnSederHadorot) {
            createDialogHesber("sederHadorot", "סדר הדורות");
        }
        else if(view == btnSaveToSederHadorot){
            if(etSederHadorot.getText()!=null){
                addData("tora_beal_pe_sederHadorot", tora_beal_pe_sederHadorot, etSederHadorot.getText().toString());
                etSederHadorot.setText("");
                etSederHadorot.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view == close){
            dialogHesber.dismiss();
        }
    }
    public void addData(String key, String text, String valueSp){
        SharedPreferences.Editor editor = folderData.edit();
        editor.putString(key,text + "\nהוספה שלך: " + valueSp);
        editor.commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}