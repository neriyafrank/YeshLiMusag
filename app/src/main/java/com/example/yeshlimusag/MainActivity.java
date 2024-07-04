package com.example.yeshlimusag;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton img1, img2, img3, img4, img5, img6;
    ImageView changeBackground;
    Button btnChangeBackground;
    LinearLayout l1;
    SharedPreferences folderData;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        folderData= getSharedPreferences("folderMusagim", Context.MODE_PRIVATE);
        l1 = (LinearLayout) findViewById(R.id.l1);
        changeBackground = findViewById(R.id.changeBackground);
        img1 = (ImageButton) findViewById(R.id.musagimBsisiim);
        img2 = (ImageButton) findViewById(R.id.shabatUmoadim);
        img3 = (ImageButton) findViewById(R.id.berachotUtfila);
        img4 = (ImageButton) findViewById(R.id.sfarimVeAnashim);
        img5 = (ImageButton) findViewById(R.id.toraBealPe);
        img6 = (ImageButton) findViewById(R.id.musagimShonim);
        btnChangeBackground = (Button) findViewById(R.id.btnChangeBackground);
        btnChangeBackground.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == img1){
            Intent intentImg1 = new Intent(this, MusagimBsisiim.class);
            startActivity(intentImg1);
        }
        else if(view == img2){
            Intent intentImg2 = new Intent(this, ShabatUmoadim.class);
            startActivity(intentImg2);
        }
        else if(view == img3){
            Intent intentImg3 = new Intent(this, BrachotUtfila.class);
            startActivity(intentImg3);
        }
        else if(view == img4){
            Intent intentImg4 = new Intent(this, SfarimVerabanim.class);
            startActivity(intentImg4);
        }
        else if(view == img5){
            Intent intentImg5 = new Intent(this, ToraBealPe.class);
            startActivity(intentImg5);
        }
        else if(view == img6){
            Intent intentImg6 = new Intent(this, MusagimShonim.class);
            startActivity(intentImg6);
        }
        else if(view == btnChangeBackground){
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            takeImage.launch(intent);
        }
    }
    ActivityResultLauncher<Intent> takeImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null){
                    Uri uri = result.getData().getData();
                    changeBackground.setImageURI(uri);
                    l1.setBackground(changeBackground.getDrawable());
                }
            }
    );
}