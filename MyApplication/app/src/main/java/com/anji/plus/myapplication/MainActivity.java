package com.anji.plus.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.anji.plus.myapplication.customview.MyLogistPointBean;
import com.anji.plus.myapplication.customview.MyLogistPointView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyLogistPointView myLogistPointView;
    private Button btn_add;
    private Button btn_del;
    private ArrayList<MyLogistPointBean> mdatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLogistPointView = findViewById(R.id.myLogistPointView);
        btn_add = findViewById(R.id.btn_add);
        btn_del = findViewById(R.id.btn_del);

        mdatas.add(new MyLogistPointBean("12-13", "未生效"));
        mdatas.add(new MyLogistPointBean("13-23", "已生效"));
        mdatas.add(new MyLogistPointBean("14-34", "已调度"));

        mdatas.add(new MyLogistPointBean("15-12", "已调度"));
        mdatas.add(new MyLogistPointBean("15-22", "已调度"));
        mdatas.add(new MyLogistPointBean("15-31", "已发运"));

        myLogistPointView.setMyLogistPointBeans(mdatas);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdatas.add(new MyLogistPointBean("15", "哈哈"));
                myLogistPointView.setMyLogistPointBeans(mdatas);
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdatas.remove(mdatas.size() - 1);
                myLogistPointView.setMyLogistPointBeans(mdatas);
            }
        });
    }

}
