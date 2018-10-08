package com.example.ty395.fja.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ty395.fja.Adapter.CommentAdapter;
import com.example.ty395.fja.Adapter.PostAdater;
import com.example.ty395.fja.Item.CommentItem;
import com.example.ty395.fja.Item.Comment_Item;
import com.example.ty395.fja.R;

import java.util.ArrayList;

public class PostnowActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    int i=19;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_now);
        recyclerView=findViewById(R.id.recyclerview);
        ArrayList<CommentItem>comment_item=new ArrayList<>();
        PostAdater commentAdapter= new PostAdater(comment_item);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.INVISIBLE);
        final TextView text=findViewById(R.id.text);
        comment_item.add(new CommentItem("0","7777"));
        ImageView ic_back=findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ImageView ic_comment=findViewById(R.id.ic_comment);
        ic_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentDialog commentDialog= new CommentDialog(PostnowActivity.this);
                commentDialog.Date();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        ImageView ic_heart=findViewById(R.id.ic_heart);
        ic_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i +=1;
                text.setText(String.valueOf(i));
            }
        });
    }
}
