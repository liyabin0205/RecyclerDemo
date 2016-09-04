package com.app.recyclerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnChildClickListener {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add(String.format(Locale.CHINA, "第%03d条数据%s", i, i % 2 == 0 ? "" : ""));
        }
        adapter = new MyAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                }
                if (position == 1) {
                    return 2;
                }
                return 1;
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(3000);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setItemAnimator(animator);
        recycler.setAdapter(adapter);
        adapter.setOnChildClickListener(this);
    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        adapter.remove(position);
    }
}
