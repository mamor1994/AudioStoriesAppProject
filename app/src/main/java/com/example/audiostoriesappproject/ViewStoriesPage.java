package com.example.audiostoriesappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewStoriesPage extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference1,reference2,reference3,reference4,reference5;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stories_page);
        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("Story1");
        reference2 =database.getReference("Story2");
        reference3 =database.getReference("Story3");
        reference4 =database.getReference("Story4");
        reference5 =database.getReference("Story5");
        storageReference = FirebaseStorage.getInstance().getReference();

    }
    public void goAladin(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        startActivity(intent);
        String story = "Aladin";
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

}