package com.example.botremote;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button connect;
    private Button forward;
    private Button stop;
    private Button moveRight;
    private Button moveLeft;
    private Button moveBackward;
    private TextView connectionStatus;
    private char[] connected = "Connected".toCharArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        connect = findViewById(R.id.connectButton);
        forward = findViewById(R.id.fowardButton);
        stop = findViewById(R.id.stopButton);
        connectionStatus = findViewById(R.id.connectionStatus);
        moveRight = findViewById(R.id.rightButton);
        moveLeft = findViewById(R.id.leftButton);
        moveBackward = findViewById(R.id.reverseButton);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(Client.clientSocket.isConnected()) {
                        return;
                    } else {
                        Client.startConnection("192.168.1.33", 6197);
                        connectionStatus.setText(connected, 0, connected.length);
                        connectionStatus.setTextColor(Color.GREEN);
                    }
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("moveForward");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        moveBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Client.sendMessage("moveBackward");
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
    }
}
