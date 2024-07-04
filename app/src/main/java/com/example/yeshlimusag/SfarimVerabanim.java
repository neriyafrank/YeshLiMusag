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

public class SfarimVerabanim extends AppCompatActivity implements View.OnClickListener {
    Button btnTalmudBavli, btnSaveToTalmudBavli, btnRambam, btnSaveToRambam;
    Button close;
    EditText etTalmudBavli, etRambam;
    Dialog dialogHesber;
    TextView musagText, textTitle;
    SharedPreferences folderData;
    char gershs = '"';
    final String sfarimVerabanim_talmudBavli = "תלמוד בבלי הוא ספר שנכתב על ידי אביי ורבא ומסכם את פירוש המשנה בתוספת אגדות וסיפורים שנכתבו על ידי האמוראים",
            sfarimVerabanim_rambam = "הרמב" + '"' + "ם הוא אחד מגדולי הראשונים ועל פי ספריו ההלכתיים פוסקים הלכה הרבה רבנים בימינו";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfarim_verabanim);
        folderData = getApplicationContext().getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        btnTalmudBavli = findViewById(R.id.btnTalmudBavli);
        btnSaveToTalmudBavli = findViewById(R.id.btnSaveToTalmudBavli);
        etTalmudBavli = findViewById(R.id.etTalmudBavli);
        btnRambam = findViewById(R.id.btnRambam);
        btnSaveToRambam = findViewById(R.id.btnSaveRambam);
        etRambam = findViewById(R.id.etRambam);
        btnRambam.setOnClickListener(this);
        btnSaveToRambam.setOnClickListener(this);
        btnTalmudBavli.setOnClickListener(this);
        btnSaveToTalmudBavli.setOnClickListener(this);
        settingData();
    }
    private void settingData() {
        SharedPreferences.Editor editor = folderData.edit();
        if(folderData.getString("sfarimVerabanim_talmudBavli", "")=="") {
            editor.putString("sfarimVerabanim_talmudBavli", sfarimVerabanim_talmudBavli);
        }
        if(folderData.getString("sfarimVerabanim_rambam", "")=="") {
            editor.putString("sfarimVerabanim_rambam", sfarimVerabanim_rambam);
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
        musagText.setText(folderData.getString("sfarimVerabanim_" + musagName, ""));
    }
    @Override
    public void onClick(View view) {
        if (view == btnTalmudBavli) {
            createDialogHesber("talmudBavli", "תלמוד בבלי");
        }
        else if(view == btnSaveToTalmudBavli){
            if(etTalmudBavli.getText()!=null){
                addData("sfarimVerabanim_talmudBavli", sfarimVerabanim_talmudBavli, etTalmudBavli.getText().toString());
                etTalmudBavli.setText("");
                etTalmudBavli.setHint("הוסף את ידיעותיך במושג");
                Toast.makeText(this, "נשמר!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view == btnRambam) {
            createDialogHesber("rambam","הרמב" + '"' + "ם");
        }
        else if(view == btnSaveToRambam){
            if(etRambam.getText()!=null){
                addData("sfarimVerabanim_rambam", sfarimVerabanim_rambam, etRambam.getText().toString());
                etRambam.setText("");
                etRambam.setHint("הוסף את ידיעותיך במושג");
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