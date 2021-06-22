package com.example.notes20;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference noteBook = db.collection("Notes");

    ArrayList<Note> lists;
    static Adapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lists = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteBook.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                {
                    error.printStackTrace();
                    return;
                }
                lists=new ArrayList<>();
                for(QueryDocumentSnapshot documentSnapshot:value)
                {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentID(documentSnapshot.getId());
                    lists.add(note);
                }
                Log.i("this was called","called");
                adapter= new Adapter(lists,MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });
    }


    public void Activity2(View v)
    {
        Intent intent = new Intent(this,Input_activity.class);
        startActivity(intent);

    }
}