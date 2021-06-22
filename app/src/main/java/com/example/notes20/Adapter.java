package com.example.notes20;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder>
{
    ArrayList<Note> listItems;
    Context context;
    FirebaseFirestore firestore;

    public Adapter(ArrayList<Note> mlistitems, Context context)
    {
        listItems=mlistitems;
        this.context=context;
        firestore=FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return  new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ExampleViewHolder holder, int position)
    {
        Note note = listItems.get(position);
        holder.textView.setText(note.getTitle());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("Notes").document(note.getDocumentID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("Delete status","Deleted");
                        MainActivity.adapter.notifyItemRemoved(position);
                    }
                });
            }
        });

       // holder.cardView.


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public  static  class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView delete;
        CardView cardView;
        public ExampleViewHolder(@NonNull  View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.cardTextView);
            delete=itemView.findViewById(R.id.delete);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }

}
