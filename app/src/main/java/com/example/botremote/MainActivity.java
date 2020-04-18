package com.example.botremote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button connect;
    private Button forward;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Client client = new Client();
        connect = findViewById(R.id.connectButton);
        forward = findViewById(R.id.fowardButton);
        stop = findViewById(R.id.stopButton);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    client.startConnection("127.0.0.1", 6666);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    client.sendMessage("forward");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    client.sendMessage("stop");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}