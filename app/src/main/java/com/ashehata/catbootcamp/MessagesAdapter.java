package com.ashehata.catbootcamp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessagesAdapter extends FirebaseRecyclerAdapter<
        Message, MessagesAdapter.MessagesViewHolder> {


    public MessagesAdapter(
            @NonNull FirebaseRecyclerOptions<Message> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull MessagesViewHolder holder,
                     int position, @NonNull Message model) {

        holder.bind(model);
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public MessagesViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.root_message, parent, false);
        return new MessagesViewHolder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    static class MessagesViewHolder
            extends RecyclerView.ViewHolder {

        TextView userName, time, content;
        ShapeableImageView shapeableImageView;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.txt_user);
            time = itemView.findViewById(R.id.txt_time);
            content = itemView.findViewById(R.id.txt_content);
            shapeableImageView = itemView.findViewById(R.id.image_color);
        }

        public void bind(Message model) {
            userName.setText("A.Shehata");
            time.setText(Helper.getSinceTime(model.getTime()));
            content.setText(model.getContent());
            shapeableImageView.setBackgroundColor(Color.parseColor(model.getColor()));
        }

    }
}
