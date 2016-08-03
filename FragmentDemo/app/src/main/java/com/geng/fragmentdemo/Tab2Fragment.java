package com.geng.fragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Tab2Fragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE = 10;

    public Tab2Fragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("msg", "  f2  onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("msg", "  f2  onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("msg", "  f2  onCreateView");
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Log.e("msg", "  f2  onViewCreated");
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_Popf2).setOnClickListener(this);
        view.findViewById(R.id.btn_Enter).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("msg", "  f2  onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("msg", "  f2  onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("msg", "  f2  onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("msg", "  f2  onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("msg", "  f2  onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("msg", "  f2  onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("msg", "  f2  onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("msg", "  f2  onDetach");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Popf2:// 弹出 f2
                getFragmentManager().popBackStack();
                break;
            case R.id.btn_Enter:// 进入main2Activity
                startActivityForResult(new Intent(getActivity(), Main2Activity.class), REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == REQUEST_CODE && resultCode == Main2Activity.RESULT_CODE) {
            Toast.makeText(getActivity(), "fragment 收到： "+data.getStringExtra("result"), Toast.LENGTH_LONG).show();
        }
    }
}
