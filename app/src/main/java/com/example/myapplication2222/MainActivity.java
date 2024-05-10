package com.example.myapplication2222;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 인스턴스를 가져옵니다.
        Button mapButton = findViewById(R.id.map_button);
        Button cartButton = findViewById(R.id.cart_button); // 장바구니 버튼 추가

        // 버튼에 클릭 리스너를 설정합니다.
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MapActivity로 이동하는 Intent를 생성합니다.
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);  // 액티비티 전환
            }
        });
        // 장바구니 버튼에 클릭 리스너를 설정합니다.
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 장바구니 화면으로 이동하는 Intent를 생성합니다.
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);  // 액티비티 전환
            }
        });

        // 비콘 데이터를 수신하기 위한 비동기 작업 실행
        new BeaconReceiver().execute();
    }

    private class BeaconReceiver extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    // 비콘 데이터를 받아와서 맵에 표시하는 로직을 작성합니다.
                    // line 변수에는 라즈베리 파이에서 전송한 비콘의 MAC 주소가 포함됩니다.
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}