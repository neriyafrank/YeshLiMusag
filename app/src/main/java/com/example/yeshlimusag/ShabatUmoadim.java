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

public class ShabatUmoadim extends AppCompatActivity implements View.OnClickListener {
    Button btnKiddush, btnSaveToKiddush, btnPesach, btnSaveToPesach;
    Button close;
    EditText etKiddush, etPesach;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    final String shabatUmoadim_Kiddush = "ברכה הנאמרת על כוס יין בשבת וביום טוב. והיא מצוות עשה מן התורה לקדש את יום השבת בדברים, כמו שכתוב בתורה  \"זכור את יום השבת לקדשו\". כלומר, זכרהו זכירת שבח וקידוש וכו' . יש חובה מדברי סופרים(חכמים) - לקדש על היין(רמב\"ם הלכות שבת פרק כט, הלכות א-ו). אסור לטעום כלום מזמן כניסת השבת ועד לאחר הקידוש. על הכוס של הקידוש להכיל לפחות\"רביעית\" יין. לפני הקידוש מדיחים היטב את הכוס במים מבפנים ומבחוץ",
            shabatUmoadim_Pesach = "פסח הוא החג הראשון מבין שלושת המועדים ובו יש איסור אכילת חמץ וחיוב לאכול מצה";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shabat_umoadim);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnKiddush = findViewById(R.id.btnKiddush);
        btnSaveToKiddush = findViewById(R.id.btnSaveToKidush);
        etKiddush = findViewById(R.id.etKidush);
        btnPesach = findViewById(R.id.btnPesach);
        btnSaveToPesach = findViewById(R.id.btnSavePesach);
        etPesach = findViewById(R.id.etPesach);
        btnPesach.setOnClickListener(this);
        btnSaveToPesach.setOnClickListener(this);
        btnKiddush.setOnClickListener(this);
        btnSaveToKiddush.setOnClickListener(this);
        settingData();
    }
    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("shabatUmoadim_Kiddush", "")=="") {
            editor.putString("shabatUmoadim_Kiddush", shabatUmoadim_Kiddush);
        }
        if(folderData.getString("shabatUmoadim_Pesach", "")=="") {
            editor.putString("shabatUmoadim_Pesach", shabatUmoadim_Pesach);
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
        musagText.setText(folderData.getString("shabatUmoadim_" + musagName, ""));
    }
    public void onClick(View view) {
        if (view == btnKiddush) {
            createDialogHesber("Kiddush", "קידוש");
        }
        else if(view == btnSaveToKiddush){
            if(etKiddush.getText()!=null){
                addData("shabatUmoadim_Kiddush", shabatUmoadim_Kiddush, etKiddush.getText().toString());
                etKiddush.setText("");
                etKiddush.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btnPesach) {
            createDialogHesber("Pesach", "פסח");
        }
        else if(view == btnSaveToPesach){
            if(etPesach.getText()!=null){
                addData("shabatUmoadim_Pesach", shabatUmoadim_Pesach, etPesach.getText().toString());
                etPesach.setText("");
                etPesach.setHint("הוסף את ידיעותיך במושג");
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