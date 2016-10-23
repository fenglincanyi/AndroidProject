package com.chinahr.android.common.im.message.relay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chinahr.android.common.event.EventManager;
import com.chinahr.android.common.utils.ACacheUtil;
import com.chinahr.android.common.utils.ToastUtil;
import com.chinahr.android.m.R;
import com.chinahr.android.m.base.ActionBarAdapter;
import com.chinahr.android.m.base.NewActionBarActivity;
import com.chinahr.android.m.util.Constants;
import com.chinahr.android.m.util.DialogUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by geng
 * on 2016/10/12.
 */
public class CustomRelayMessageActivity extends NewActionBarActivity implements OnItemClickListener {

    private ItemRemoveRecyclerView recyclerView;
    private TextView tvEdit;
    private RelayMessageAdapter messageAdapter;

    private List<RelayMessageBean> messageBeanList;
    private int editIndex = -1;

    public static final String MESSAGE_CONTENT_KEY = "message_content";
    public static final String MESSAGE_CONTENT_BACK_KEY = "message_content_back";
    public static final int RELAY_MESSAGE_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListener();

        setEditColor();
    }

    private void initView() {
        recyclerView = (ItemRemoveRecyclerView) findViewById(R.id.relay_message_rv);
        tvEdit = (TextView) findViewById(R.id.edit);
    }

    private void initData() {
        messageBeanList = (List<RelayMessageBean>) getIntent().getSerializableExtra(SelectRelayMessageActivity.MESSAGE_KEY);
        messageAdapter = new RelayMessageAdapter(messageBeanList);
//        messageAdapter.setOnItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.addItemDecoration(new ItemDividerDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void initListener() {
        recyclerView.setOnItemClickListener(this);
        setEditOnClickListener(this);
        setBackOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit:
                if (messageBeanList.size() >= 5) {
                    ToastUtil.showShortToast("您最多只能添加5条哦~");
                } else {
                    editIndex = -1;
                    startActivityForResult(new Intent(this, RelayMessageEditActivity.class), RELAY_MESSAGE_REQUEST_CODE);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
        super.onClick(view);
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_custom_relay_message;
    }

    @Override
    protected ActionBarAdapter.ShowStyle getShowStyle() {
        return ActionBarAdapter.ShowStyle.LARROW_MTEXT_RTEXT;
    }

    @Override
    protected int getTitleResId() {
        return R.string.relay_message_title;
    }

    @Override
    protected int getEditResId() {
        return R.string.relay_message_add;
    }

    @Override
    public void onItemClick(View view, int position) {
        if (messageBeanList.get(position) != null && !TextUtils.isEmpty(messageBeanList.get(position).relayMessage)) {
            editIndex = position;
            startActivityForResult(new Intent(this, RelayMessageEditActivity.class)
                    .putExtra(MESSAGE_CONTENT_KEY, messageBeanList.get(position).relayMessage), RELAY_MESSAGE_REQUEST_CODE);
        }
    }

    @Override
    public void onDeleteClick(final int position) {
        DialogUtil.showTwoButtonDialog(this, "确定删除该条快捷回复？", "取消", "确认",
                null,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (messageBeanList.size() <= 1) {
                            ToastUtil.showShortToast("已经是最后一条了哦~");
                            return;
                        }
                        if (!Constants.Check_Net) {
                            ToastUtil.showShortToast("您的网络不通，请稍后重试");
                            return;
                        }
                        messageBeanList.remove(position);
                        messageAdapter.resetItem();
                        updateLocalData();
                        setEditColor();
                        ToastUtil.showShortToast("删除成功");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RELAY_MESSAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String backContent = data.getStringExtra(MESSAGE_CONTENT_BACK_KEY);
            if (!TextUtils.isEmpty(backContent)) {
                long now = new Date().getTime();

                if (editIndex == -1) {// 添加
                    messageBeanList.add(new RelayMessageBean(now, backContent));
                    setEditColor();
                    ToastUtil.showShortToast("添加成功");
                } else {// 修改
                    messageBeanList.get(editIndex).t1 = now;
                    messageBeanList.get(editIndex).storageTime = now;
                    messageBeanList.get(editIndex).relayMessage = backContent;
                    ToastUtil.showShortToast("修改成功");
                }

                updateLocalData();
                // 调整item位置
                messageAdapter.resetItem();
            }
        }
    }

    /**
     * 更新本地存储数据
     */
    public void updateLocalData() {
        // 排序
        Collections.sort(messageBeanList);
        // 存储
        ACacheUtil.putRelayMessage(messageBeanList);
        EventBus.getDefault().post(new EventManager.RelayMessageEvent(true));
    }

    public void setEditColor() {
        if (messageBeanList.size() >= 5) {
            tvEdit.setTextColor(Color.parseColor("#dddddd"));
        } else {
            tvEdit.setTextColor(Color.parseColor("#FF5A5A"));
        }
    }
}
