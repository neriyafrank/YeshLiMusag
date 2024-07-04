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

public class MusagimBsisiim extends AppCompatActivity implements View.OnClickListener {
    Button btnShabbat, btnSaveToShabat, btn5tora, btnSaveTo5tora;
    Button close;
    EditText etShabat, et5tora;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    final String musagimBsisiim_shabat = "השבת היא יום של קדוּשה, שביתה ממלאכה ומנוחה, המועד הראשון במועדים הקבועים מהתורה. השבת חלה באופן קבוע ביום השביעי שבשבוע. תחילת השבת היא בערבו של יום שישי לאחר שקיעת החמה זמן הקרוי \"ליל שבת\", וסיומה הוא בערב למחרת, עם צאת הכוכבים.",
            musagimBsisiim_5tora = "חמישה חומשי תורה הם חמשת החלקים של התורה שבכתב ומייצגים את הבסיס הכי חשוב ליהדות";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musagim_bsisiim);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnShabbat = findViewById(R.id.btnShabat);
        btnSaveToShabat = findViewById(R.id.btnSaveToShabat);
        etShabat = findViewById(R.id.etShabat);
        btn5tora = findViewById(R.id.btnPesach);
        btnSaveTo5tora = findViewById(R.id.btnSavePesach);
        et5tora = findViewById(R.id.etPesach);
        btn5tora.setOnClickListener(this);
        btnSaveTo5tora.setOnClickListener(this);
        btnShabbat.setOnClickListener(this);
        btnSaveToShabat.setOnClickListener(this);
        settingData();
    }

    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("musagimBsisiim_shabat", "")=="") {
            editor.putString("musagimBsisiim_shabat", musagimBsisiim_shabat);
        }
        if(folderData.getString("musagimBsisiim_5tora", "")=="") {
            editor.putString("musagimBsisiim_5tora", musagimBsisiim_5tora);
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
        musagText.setText(folderData.getString("musagimBsisiim_" + musagName, ""));
    }
    @Override
    public void onClick(View view) {
        if (view == btnShabbat) {
            createDialogHesber("shabat", "שבת");
        }
        else if(view == btnSaveToShabat){
            if(etShabat.getText()!=null){
                addData("musagimBsisiim_shabat", musagimBsisiim_shabat, etShabat.getText().toString());
                etShabat.setText("");
                etShabat.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btn5tora) {
            createDialogHesber("5tora", "חמישה חומשי תורה");
        }
        else if(view == btnSaveTo5tora){
            if(et5tora.getText()!=null){
                addData("musagimBsisiim_5tora", musagimBsisiim_5tora, et5tora.getText().toString());
                et5tora.setText("");
                et5tora.setHint("הוסף את ידיעותיך במושג");
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
}