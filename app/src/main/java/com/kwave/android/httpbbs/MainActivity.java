package com.kwave.android.httpbbs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataLoader.CallBack{

    RecyclerView listView;       // 리사이클러뷰 변수를 만들어준다.
    CustomAdapter CardAdapter;
    Button btnWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);         // Activity_Main.xml을 인플레이트 시키고 객체로 생성한다.
        listView = (RecyclerView) findViewById(R.id.listView);  // 인플레이트 된 객체에 위젯의 주소값을 넘겨준다.


        // 1. 데이터
//        ArrayList<Data> datas = new ArrayList<>();        // 데이터를 생성하기 위해 ArrayList를 Data의 형식으로 만든다.
//        datas = Loader.getData(this);                     // Data 타입으로 만들어진 객체에 MainActivity 클래스에 생성된 변수들이 들어간다 떄로는 그 자신이 들어갈 때도 있다..
        ArrayList<Data> datas = Loader.getData(this);
        // 2. 어댑터
        CustomRecycler adapter = new CustomRecycler(datas,this);

        // 3. 연결
        listView.setAdapter(adapter);

        // 4. 레이아웃 매니저
        listView.setLayoutManager(new LinearLayoutManager(this));



        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });






        //---------------------------------------------------------------------------------------------
        DataLoader loader = new DataLoader();
        loader.getData("http://192.168.10.68:8080/bbs/json/list",this);
        CardAdapter = new CustomAdapter();
        listView.setAdapter(CardAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));





    }
    @Override
    public void setList(List<Bbs> list) {
        // 결과처리 값 세팅
        CardAdapter.setList(list);
        CardAdapter.notifyDataSetChanged();
    }
}



class CustomRecycler extends RecyclerView.Adapter<CustomRecycler.Holder>  { // 리사이클러 뷰에는 반드시 오버라이드 해야하는 것들이 있음
    // 제네릭 자리에 뷰홀더를 집어 넣어준다.(강제사항)
    ArrayList<Data> datas;
    Context context;

    //1. 생성자 만들기
    public CustomRecycler(ArrayList<Data> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }


    // List View에서 convertView == null 일때 처리
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);      // XML을 inflater를 통해서 객체로 생성
        // 위와 같이 설정할 경우 부모xml의 형식을 따라각 리스트가 설정이 되기 때문에 1개의 리스트의 크기가 전체 크기로 나오게 된다.
        // 이때 해결할 방법은 아래처럼 하던가 item_list.xml에서 android:layout_height="match_parent"를 android:layout_height="wrap_parent"으로 바꿔주면된다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);      // XML을 inflater를 통해서 객체로 생성
//        Holder holder = new Holder(view);

        return new Holder(view);        // 만들어진 객체를 홀더로 넘겨줌
    }


    // 각 데이터 셀이 나타날때 호출되는 함수
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 1. 데이터를 꺼내고
        Data data = datas.get(position);
        // 2. 데이터를 세팅
        holder.setNo(data.id);
        holder.setTitle(data.title);
        holder.setAuthor(data.author);
//        holder.setContent(data.content);

    }


    // 데이터의 전체 개수
    @Override
    public int getItemCount() {
        return datas.size();
    }


    class Holder extends RecyclerView.ViewHolder { // 내부 클래스는 해당하는 어댑터나 클래스에서만 사용하고자 할때 만든다.
        TextView no;
        TextView title;
        TextView author;
        TextView content;

        public Holder(View itemView) {               // 또한, Holder는 ViewHolder를 상속해야한다.
            super(itemView);
            no = (TextView) itemView.findViewById(R.id.txtNo);
            title = (TextView) itemView.findViewById(R.id.txtTitle);
            author = (TextView) itemView.findViewById(R.id.txtAuthor);
//            content = (TextView) itemView.findViewById(R.id.txtContent);

        }

        public void setNo(int no) {
            this.no.setText(no+"");
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setAuthor(String author) {
            this.author.setText(author);;
        }

//        public void setContent(String content) {
//            this.content.setText(content);
//        }
    }
}






class Loader {
    public static ArrayList<Data> getData(Context context){
        ArrayList<Data> result = new ArrayList<>();
        for(int i=1 ; i<=10 ; i++){
            Data data = new Data();
            data.id = i;
            data.title = "타이틀";
            data.author = "나야나";
//            data.content = "너만을 기다리는";
            result.add(data);
        }
        return result;
    }
}

class Data {
    public int id;
    public String title;
    public String author;
//    public String content;



}
