package com.example.notes20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Input_activity extends AppCompatActivity {

    public static final String TAG = Input_activity.class.getSimpleName();
    EditText titletext;
    EditText descText;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference noteBook = db.collection("Notes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        titletext=findViewById(R.id.edit_title);
        descText=findViewById(R.id.edit_description);
    }

    public void Save(View v)
    {
        String title = titletext.getText().toString();
        String description = descText.getText().toString();

        Note note = new Note(title,description);
        noteBook.add(note);

        Log.i(TAG,"Value:"+title+"\nDescription:"+description);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}