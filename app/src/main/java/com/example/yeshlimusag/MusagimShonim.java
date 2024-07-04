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

public class MusagimShonim extends AppCompatActivity implements View.OnClickListener {
    Button btnPeterHamor, btnSaveToPeterHamor, btnKibudHorim, btnSaveToKibudHorim;
    Button close;
    EditText etPeterHamor, etKibudHorim;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    final String musagimShonim_peterHamor = "מצווה מהתורה שמחייבת את הזכר הראשון שממליטה האתון, לפדות ולתת את הפדיון לכהן. לאחר הפדיון, אין קדושה לא בחמור ולא בפדיונו, אם אין בעל החמור רוצה לפדותו הוא צריך לערוף את ראשו ולקבור אותו.",
            musagimShonim_kibudHorim = "כיבוד הורים זו מצווה מהתורה שמחייבת כל איש ואישה מעם ישראל לכבד את הוריו";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musagim_shonim);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnPeterHamor = findViewById(R.id.btnPeterHamor);
        btnSaveToPeterHamor = findViewById(R.id.btnSaveToPeterHamor);
        etPeterHamor = findViewById(R.id.etPeterHamor);
        btnKibudHorim = findViewById(R.id.btnKibudHorim);
        btnSaveToKibudHorim = findViewById(R.id.btnSaveKibudHorim);
        etKibudHorim = findViewById(R.id.etKibudHorim);
        btnKibudHorim.setOnClickListener(this);
        btnSaveToKibudHorim.setOnClickListener(this);
        btnPeterHamor.setOnClickListener(this);
        btnSaveToPeterHamor.setOnClickListener(this);
        settingData();
    }

    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("musagimShonim_peterHamor", "")=="") {
            editor.putString("musagimShonim_peterHamor", musagimShonim_peterHamor);
        }
        if(folderData.getString("musagimShonim_kibudHorim", "")=="") {
            editor.putString("musagimShonim_kibudHorim", musagimShonim_kibudHorim);
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
        musagText.setText(folderData.getString("musagimShonim_" + musagName, ""));
    }
    @Override
    public void onClick(View view) {
        if (view == btnPeterHamor) {
            createDialogHesber("peterHamor", "פטר חמור");
        }
        else if(view == btnSaveToPeterHamor){
            if(etPeterHamor.getText()!=null){
                addData("musagimShonim_peterHamor", musagimShonim_peterHamor, etPeterHamor.getText().toString());
                etPeterHamor.setText("");
                etPeterHamor.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btnKibudHorim) {
            createDialogHesber("kibudHorim", "כיבוד הורים");
        }
        else if(view == btnSaveToKibudHorim){
            if(etKibudHorim.getText()!=null){
                addData("musagimShonim_kibudHorim", musagimShonim_kibudHorim, etKibudHorim.getText().toString());
                etKibudHorim.setText("");
                etKibudHorim.setHint("הוסף את ידיעותיך במושג");
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