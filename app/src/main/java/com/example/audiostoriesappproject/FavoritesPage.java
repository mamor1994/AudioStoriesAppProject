package com.example.audiostoriesappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class FavoritesPage extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageReference;
    TextView stats, fairytale, favFairytale;
    ImageView storyImage;
    String story ="";
    String view = "";
    String favstory = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_page);
        fairytale = findViewById(R.id.textView);
        stats = findViewById(R.id.textView2);
        favFairytale = findViewById(R.id.textView3);
        storyImage = findViewById(R.id.imageView2);
        database = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long maxViews = 0;
                String bestImage ="";
                for (DataSnapshot childSnapshot : snapshot.getChildren()){
                    String title = childSnapshot.child("Title").getValue(String.class);
                    Long views = childSnapshot.child("Views").getValue(Long.class);
                    String image = childSnapshot.child("Image").getValue(String.class);
                    if (title != null && views != null) {
                        if (views > maxViews){
                            maxViews = views;
                            favstory = title;
                            bestImage = image;
                        }
                        story += title+"\n\n";
                        view += views+"\n\n";
                    }
                }
                fairytale.setText(story);
                stats.setText(view);
                favFairytale.setText("Your favorite fairytale is \n"+ favstory +" with "+ maxViews +" views!");
                try {
                    File file = File.createTempFile("temp", "jpg");
                    StorageReference imageRef = storageReference.child(bestImage);
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
}