package com.ashehata.catbootcamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.dhaval2404.colorpicker.ColorPickerDialog;
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog;
import com.github.dhaval2404.colorpicker.listener.ColorListener;
import com.github.dhaval2404.colorpicker.model.ColorShape;
import com.github.dhaval2404.colorpicker.model.ColorSwatch;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnSend;
    private EditText etMessage;
    private RecyclerView rvMessages;
    private DatabaseReference myRef;
    private MessagesAdapter adapter;
    private ShapeableImageView ivColor;
    private ImageView ivEmpty;
    private String chooseColor = "#2196F3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setup views
        btnSend = findViewById(R.id.btn_send);
        etMessage = findViewById(R.id.et_message);
        rvMessages = findViewById(R.id.rv_messages);
        ivColor = findViewById(R.id.iv_color);
        ivEmpty = findViewById(R.id.iv_empty);

        setupDB();
        displayMessages();
        sendNewMessage();
        setColorChooser();
    }

    private void setColorChooser() {
        ivColor.setOnClickListener(v -> {
            new MaterialColorPickerDialog
                    .Builder(this)
                    .setTitle("Pick a color")
                    .setColorShape(ColorShape.CIRCLE)
                    .setColorSwatch(ColorSwatch._300)
                    .setDefaultColor(R.color.blue_500)
                    .setColorRes(getResources().getIntArray(R.array.themeColors))
                    .setColorListener((color, colorHex) -> {
                        // Handle Color Selection
                        chooseColor = colorHex;
                        ivColor.setColorFilter(Color.parseColor(colorHex));
                    })
                    .show();
        });
    }

    private void displayMessages() {
        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Message> options
                = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(myRef, Message.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new MessagesAdapter(options);
        if (adapter.getItemCount() == 0) {
            ivEmpty.setVisibility(View.VISIBLE);
        } else ivEmpty.setVisibility(View.GONE);
        // Connecting Adapter class with the Recycler view*/
        rvMessages.setAdapter(adapter);
    }

    private void sendNewMessage() {
        btnSend.setOnClickListener(v -> {
            if (!etMessage.getText().toString().isEmpty()) {
                long currentTime = new Date().getTime();
                // EX: Redmi Note 4
                String deviceModel = Build.MODEL;

                // EX: Xiaomi
                String deviceManufacturer = Build.MANUFACTURER;

                // Write a newMessage to the database
                Message newMessage = new Message(etMessage.getText().toString(),
                        deviceModel,
                        deviceManufacturer,
                        currentTime,
                        chooseColor);

                myRef.child(currentTime + "").setValue(newMessage);
                etMessage.setText("");
            }
        });
    }

    private void setupDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference(/*"messages"*/);
    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}