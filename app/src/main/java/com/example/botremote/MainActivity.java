package com.example.botremote;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final TextView connectionStatus = findViewById(R.id.connectionStatus);
        Button connect = findViewById(R.id.connectButton);
        Button moveForward = findViewById(R.id.fowardButton);
        Button stop = findViewById(R.id.stopButton);
        Button moveRight = findViewById(R.id.rightButton);
        Button moveLeft = findViewById(R.id.leftButton);
        Button moveBackward = findViewById(R.id.reverseButton);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(!(Client.clientSocket.isConnected())) {
                        char[] connected = "Connected".toCharArray();
                        Client.startConnection("73.50.89.25");
                        connectionStatus.setText(connected, 0, connected.length);
                        connectionStatus.setTextColor(Color.GREEN);
                    }
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        });

        moveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client.sendMessage("moveForward");
            }
        });

        moveBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client.sendMessage("moveBackward");
            }
        });

        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client.sendMessage("moveLeft");
            }
        });

        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client.sendMessage("moveRight");
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client.sendMessage("stop");
            }
        });
    }
}
