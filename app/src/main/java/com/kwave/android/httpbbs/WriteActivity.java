package com.kwave.android.httpbbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener, DataSender.CallBack{

    EditText editTitle, editContent, editAuthor;
    Button btnPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        setView();

    }

    private void setView() {
        editTitle = (EditText) findViewById(R.id.editTitle);
        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editContent = (EditText) findViewById(R.id.editContent);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 1. 위젯에서 값 가져오기
        String title = editTitle.getText().toString();
        String author = editAuthor.getText().toString();
        String content = editContent.getText().toString();

        // 2. Bbs 객체에 값 담기 - id는 서버에서 자동으로 생성하므로 넘기지 않는다.
        Bbs bbs = new Bbs();
        bbs.title = title;
        bbs.author = author;
        bbs.content = content;

        // 3. Gson으로 json 값 담기
        Gson gson = new Gson();
        String jsonString = gson.toJson(bbs);
        // 확인
        Log.d("PostData", jsonString);

        // 4. json 전송
        DataSender sender = new DataSender();
        String url = "http://192.168.10.68:8080/bbs/json/insert/";
        sender.sendData(url, jsonString ,this);
    }

    @Override
    public void call(String result) {
        Log.d("WriteActivity", "전송결과="+result);
    }

}
