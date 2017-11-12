package com.geng.dragsortbyrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private GridLayoutManager layoutManager;
    private DataAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(recyclerView.getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DataAdapter();
        recyclerView.setAdapter(adapter);
        adapter.updateData(DataHelper.buildData());



        MoveItemHelper helper =  new MoveItemHelper();
        helper.attachToRecyclerView(recyclerView);// 此方法中，源码中回复写ItemDecoration，所以要在后面添加自己的ItemDecoration 或者 重写 helper 的ItemDecoration实现部分



        recyclerView.addItemDecoration(new SpaceItemDecoration());



        adapter.setOnItemLongClickListener(new DataAdapter.ClickListener(){
            @Override
            void onLongClick(View view, int position) {
//                adapter.changeFlagState();
            }

            @Override
            void onClickDeleteFlag(View view, int position) {
                adapter.deleteData(position);

                // test
                TextView content = ((LinearLayout)(recyclerView.getParent())).findViewById(R.id.content);
                List<ItemModel> list = ((DataAdapter) recyclerView.getAdapter()).getData();
                StringBuffer sb = new StringBuffer("[");
                for (ItemModel m : list) {
                    sb.append(m.iconText).append(", ");
                }
                sb.append("]");
                content.setText(sb.toString());
            }
        });
    }

}
