package com.example.cicra.texttospeech;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText txt;
    com.rilixtech.materialfancybutton.MaterialFancyButton sound,micro;
    TextToSpeech tts;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt= findViewById(R.id.editText);
        sound=findViewById(R.id.textto);
        micro=findViewById(R.id.speechto);



        tts= new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){

                        Toast.makeText(MainActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
                    }
                    else{
                      //  ConvertTextToSpeech();
                    }
                }else{

                    Toast.makeText(MainActivity.this, "Initilization Failed!", Toast.LENGTH_SHORT).show();
                 }
            }
        });


        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if (i.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(i,10);
                }else {
                    Toast.makeText(MainActivity.this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConvertTextToSpeech();
            }
        });



    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }


    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        text = txt.getText().toString();
        if (text == null || "".equals(text)) {

            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else {


            tts.speak(text + " ", TextToSpeech.QUEUE_FLUSH, null);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if (resultCode==RESULT_OK && data !=null){
                    ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String txt1=" ";
                    if (txt.getText().toString().length()>0){
                        txt1+=txt.getText().toString();
                        txt1+=" ";
                    }
                    txt1+=stringArrayListExtra.get(0);
                    txt.setText(txt1);
                    txt.setSelection(txt.getText().length());
                }
                break;


        }
    }
}
