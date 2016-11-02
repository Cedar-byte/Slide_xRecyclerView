package com.yuntong.myslide;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gxy.xrecyclerview.ProgressStyle;
import com.gxy.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemSlideHelper.Callback {

    private XRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (XRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LinearItemDecoration(Color.BLACK));
        recyclerView.setAdapter(new HAdapter());
        recyclerView.addOnItemTouchListener(new ItemSlideHelper(MainActivity.this,this));// 添加侧滑菜单的监听
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if(holder.itemView instanceof LinearLayout){
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            if(viewGroup.getChildCount() == 2){
                return viewGroup.getChildAt(1).getLayoutParams().width;
            }
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return recyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return recyclerView.findChildViewUnder(x, y);
    }
}
