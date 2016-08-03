package com.geng.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Tab1Fragment extends Fragment implements View.OnClickListener {

    public Tab1Fragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("msg", "  f1  onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("msg", "  f1  onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("msg", "  f1  onCreateView");
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Log.e("msg", "  f1  onViewCreated");
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_Popf1).setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("msg", "  f1  onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("msg", "  f1  onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("msg", "  f1  onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("msg", "  f1  onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("msg", "  f1  onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("msg", "  f1  onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("msg", "  f1  onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("msg", "  f1  onDetach");
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
            case R.id.btn_Popf1:// 弹出 f1
                getFragmentManager().popBackStack();
                break;
        }
    }
}
