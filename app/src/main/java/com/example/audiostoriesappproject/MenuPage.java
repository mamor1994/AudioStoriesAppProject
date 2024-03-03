package com.example.audiostoriesappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
    }

    public void goViewStories(View view){
        Intent intent = new Intent(this, ViewStoriesPage.class);
        startActivity(intent);
    }
    public void goFavorites(View view){
        Intent intent = new Intent(this, FavoritesPage.class);
        startActivity(intent);
    }

}