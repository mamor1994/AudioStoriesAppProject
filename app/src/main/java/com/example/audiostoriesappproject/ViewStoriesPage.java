package com.example.audiostoriesappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ViewStoriesPage extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference1,reference2,reference3,reference4,reference5;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stories_page);
        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("Story1");
        reference2 =database.getReference("Story2");
        reference3 =database.getReference("Story3");
        reference4 =database.getReference("Story4");
        reference5 =database.getReference("Story5");
        storageReference = FirebaseStorage.getInstance().getReference();

    }
    public void story1(View view){
        Intent intent = new Intent(this, StoriesPlayerPage.class);
        startActivity(intent);


       /* reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); */

    }
}