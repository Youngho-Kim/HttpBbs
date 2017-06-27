package com.kwave.android.httpbbs;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by kwave on 2017-06-26.
 */

public class DataLoader {

    public void getData(String url, final CallBack callBack){
        new AsyncTask<String,Void,List<Bbs>>(){


            @Override
            protected List<Bbs> doInBackground(String... params) {

                String url = params[0];
                String result = getDataFromUrl(url);

                Gson gson = new Gson();
                Data_card data = gson.fromJson(result,Data_card.class);      // result의 스트링을 Gson으로 object 컨버팅

                return data.bbsList;
            }

            @Override
            protected void onPostExecute(List<Bbs> list) {  // UI에 붙일 수 있는 형태로 만들어줘야함
                callBack.setList(list);
            }
        }.execute(url);
    }

    public interface CallBack{
        public void setList(List<Bbs> list);
    }

    public String getDataFromUrl(String url){   // 다음에도 그대로 사용해도 됨
        StringBuilder result = new StringBuilder();

        try {
            Log.e("URL",""+url);
            // 1. 요청처리
            URL serverUrl = new URL(url);
            // 주소에 해당하는 서버의 socket을 연결
            HttpURLConnection con = (HttpURLConnection) serverUrl.openConnection();
            // outputStream으로 데이터를 요청
            con.setRequestMethod("GET");

            Log.e("con", "con : "+con);
            // 2. 응답처리
            // 응답의 유효성 검사
            int responseCode = con.getResponseCode();
            Log.e("responseCode", "responseCode : "+responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = // 줄 단위로 데이터를 읽기 위해서 버퍼에 담는다. 속도도 빨라짐
                        new BufferedReader(new InputStreamReader(con.getInputStream()));
                String temp = null;
                while((temp = br.readLine()) != null){
                    result.append(temp+"\n"); // readLine은 줄바꿈을 무시하기 때문에 string문자 뒤에 ""처리를 해준다.
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Log.e("result.toString()", "result.toString() : "+result.toString());


        return result.toString();
    }


}
