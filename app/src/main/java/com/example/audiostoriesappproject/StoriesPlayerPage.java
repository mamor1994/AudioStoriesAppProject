package com.example.audiostoriesappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class StoriesPlayerPage extends AppCompatActivity {
    TextView title;
    ImageView storyImage;
    EditText story;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageReference;
    MyTts myTts;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories_player_page);
        myTts = new MyTts(this);
        title = findViewById(R.id.textView);
        storyImage = findViewById(R.id.imageView);
        story = findViewById(R.id.editTextTextMultiLine);
        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        String receivedText = getIntent().getStringExtra("mykey1");
        reference = database.getReference(receivedText);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int currentViews = snapshot.child("Views").getValue(Integer.class);
                currentViews++;
                reference.child("Views").setValue(currentViews);
                title.setText(snapshot.child("Title").getValue().toString());
                story.setText(snapshot.child("Description").getValue().toString());
                description = snapshot.child("Description").getValue().toString();
                String imageFile = snapshot.child("Image").getValue().toString();
                try {
                    File file = File.createTempFile("temp", "jpg");
                    StorageReference imageRef = storageReference.child(imageFile);
                    imageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            storyImage.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void playStory(View view) {
        myTts.speak(description);
    }
}
       // String mila =story.getText().toString();
      //  myTts.speak(mila);
      /*  try {
            if (description != null && !description.isEmpty()) {
                myTts.speak(description);
            } else {
                Toast.makeText(this, "Description is empty or null", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error while playing story: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackButtonClick(View view) {
        onBackPressed(); */


