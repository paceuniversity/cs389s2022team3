package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void likePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.like_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void dislikePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.dislike_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}