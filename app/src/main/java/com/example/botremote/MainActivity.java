package com.example.botremote;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button connect;
    private Button forward;
    private Button stop;
    private Button disconnect;
    private Button moveRight;
    private Button moveLeft;
    private TextView connectionStatus;
    private char[] connected = "Connected".toCharArray();
    private char[] disconnected = "Disconnected".toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect = findViewById(R.id.connectButton);
        forward = findViewById(R.id.fowardButton);
        stop = findViewById(R.id.stopButton);
        disconnect = findViewById(R.id.disconnectButton);
        connectionStatus = findViewById(R.id.connectionStatus);
        moveRight = findViewById(R.id.rightButton);
        moveLeft = findViewById(R.id.leftButton);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.startConnection("73.50.89.25", 6666);
                    connectionStatus.setText(connected, 0, connected.length);
                    connectionStatus.setTextColor(Color.GREEN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("forward");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("moveLeft");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("moveRight");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("stop");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.stopConnection();
                    Server.stop();
                    connectionStatus.setText(disconnected, 0, disconnected.length);
                    connectionStatus.setTextColor(Color.GRAY);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}