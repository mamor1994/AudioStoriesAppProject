package com.example.audiostoriesappproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ViewStoriesPage extends AppCompatActivity {
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stories_page);
        database = FirebaseDatabase.getInstance();
    }

    public void goAladdin(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        startActivity(intent);
        String story = "Aladdin";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }

    public void goRapunzel(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        String story = "Rapunzel";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }

    public void goSnowWhite(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        String story = "Snow White";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }

    public void goPigs(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        String story = "Pigs";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }

    public void goHansel(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        String story = "Hansel";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }

    public void goJack(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        String story = "Jack";
        intent.putExtra("mykey1", story);
        startActivity(intent);
    }
    public void recordVoice(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent,123);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==123 && resultCode ==RESULT_OK){
            ArrayList<String>recognizedText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (recognizedText != null && !recognizedText.isEmpty()){
                String chosenStory = recognizedText.get(0);
                goToStory(chosenStory);
            } else {
                showMessage("Error", "Please choose an existing story from the options");
            }
        }
    }
    private void goToStory(String recognizedText){
        Intent intent = new Intent(this,StoriesPlayerPage.class);

        if (recognizedText.contains("Aladdin")){
            intent.putExtra("mykey1","Aladdin");
        } else if (recognizedText.contains("Rapunzel")){
            intent.putExtra("mykey1","Rapunzel");
        } else if (recognizedText.contains("Snow White")){
            intent.putExtra("mykey1","Snow White");
        } else if (recognizedText.contains("Pigs")){
            intent.putExtra("mykey1","Pigs");
        } else if (recognizedText.contains("Hansel")){
            intent.putExtra("mykey1","Hansel");
        } else if (recognizedText.contains("Jack")){
            intent.putExtra("mykey1","Jack");
        }
        startActivity(intent);
    }

    public void showMessage(String title, String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .show();
    }

    public void onBackButtonClick(View view) {
        onBackPressed();
    }

}