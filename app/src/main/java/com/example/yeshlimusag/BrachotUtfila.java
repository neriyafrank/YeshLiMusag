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

public class BrachotUtfila extends AppCompatActivity implements View.OnClickListener {
    Button btnBirkatHamazon, btnSaveToBirkatHamazon, btnShacharit, btnSaveToShacharit;
    Button close;
    EditText etBirkatHamazon, etShacharit;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    final String brachotUtfila_birkatHamazon = "ארבע ברכות שמברכים אחר אכילת פת: ברכת הזן, ברכת הארץ, ברכת בונה ירושלים וברכת הטוב והמטיב. מצווה מן התורה לברך אחר אכילת מזון, שנאמר: \"ואכלת ושבעת וברכת את ה' אלוהיך על הארץ הטובה אשר נתן לך\". אין מברכים ברכת המזון אלא לאחר סעודה שאכלו בה פת או לאחר אכילת פת בלבד. שיעור גודלה של הפת שלאחר אכילתה חייבים לברך ברכת המזון הוא \"כזית\" ומעלה. ברכת המזון פוטרת את כל סוגי המאכלים והמשקאות שאכלו ושתו בתוך הסעודה",
            brachotUtfila_shacharit = "תפילת שחרית היא התפילה הראשונה מבין שלושת התפילות שמתפללים יהודים כל יום וזמנה לכתחילה מזריחת השמש עד סוף 4 שעות. חכמים אמרו שאברהם אבינו תקן את תפילת שחרית";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brachot_utfila);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnBirkatHamazon = findViewById(R.id.btnBirkatHamazon);
        btnSaveToBirkatHamazon = findViewById(R.id.btnSaveToBirkatHamazon);
        etBirkatHamazon = findViewById(R.id.etBirkatHamazon);
        btnShacharit = findViewById(R.id.btnShacharit);
        btnSaveToShacharit = findViewById(R.id.btnSaveShacharit);
        etShacharit = findViewById(R.id.etShacharit);
        btnShacharit.setOnClickListener(this);
        btnSaveToShacharit.setOnClickListener(this);
        btnBirkatHamazon.setOnClickListener(this);
        btnSaveToBirkatHamazon.setOnClickListener(this);
            settingData();
    }
    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("brachotUtfila_birkatHamazon", "")=="") {
            editor.putString("brachotUtfila_birkatHamazon", brachotUtfila_birkatHamazon);
        }
        if(folderData.getString("brachotUtfila_shacharit", "")=="") {
            editor.putString("brachotUtfila_shacharit", brachotUtfila_shacharit);
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
        musagText.setText(folderData.getString("brachotUtfila_" + musagName, ""));
    }
    @Override
    public void onClick(View view) {
        if (view == btnBirkatHamazon) {
            createDialogHesber("birkatHamazon", "ברכת המזון");
        }
        else if(view == btnSaveToBirkatHamazon){
            if(etBirkatHamazon.getText()!=null){
                addData("brachotUtfila_birkatHamazon", brachotUtfila_birkatHamazon, etBirkatHamazon.getText().toString());
                etBirkatHamazon.setText("");
                etBirkatHamazon.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btnShacharit) {
            createDialogHesber("shacharit", "תפילת שחרית");
        }
        else if(view == btnSaveToShacharit){
            if(etShacharit.getText()!=null){
                addData("brachotUtfila_shacharit", brachotUtfila_shacharit, etShacharit.getText().toString());
                etShacharit.setText("");
                etShacharit.setHint("הוסף את ידיעותיך במושג");
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
