package com.hwy.sidebar;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hwy.library.SideBar;
import com.hwy.sidebar.bean.SortBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private SideBar mSideBar;
    private ListView mListView;

    // 数据集
    private List<SortBean> mData;

    // 弹窗的颜色列表
    private int[] mzDialogColors = {
            Color.parseColor("#FDBC3A"),
            Color.parseColor("#F95C31"),
            Color.parseColor("#EE2932"),
            Color.parseColor("#6053E9"),
            Color.parseColor("#268FEA"),
            Color.parseColor("#20C0CE"),
            Color.parseColor("#41BF6E")};

    // 可更换的字符列表
    static String[] mLetters = {
            "★", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"
            , "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mSideBar = findViewById(R.id.sideBar);
        mListView = findViewById(R.id.listview);

        // 更新默认的字符列表
//        mSideBar.setLetters(mLetters);
        mSideBar.addDialogColors(mzDialogColors);
//        mSideBar.setSideBarPressColor(Color.TRANSPARENT);

        // 设置回调监听
        mSideBar.setOnLetterUpdateListener(new SideBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {

                Log.e("TAG", "letter = " + letter);
                int position = calculatePosition(letter);
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });

        init();

    }

    private void init() {
        mData = new ArrayList<>();
        char letter = 'A';

        // 这里直接按照顺序添加，实际开发中需要对数据进行排序
        while (letter <= 'Z') {
            int count = 0;
            while (count <= 0) {
                count = new Random().nextInt() % 5;
            }
            for (int i = 0; i < count; i++) {
                mData.add(new SortBean(String.valueOf(letter), letter + "----position = " + i));
            }
            letter++;
        }

        for (int i = 0; i < 5; i++) {
            mData.add(new SortBean("#", "#----position = " + i));
        }

        // 设置适配器
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mData.size();
            }

            @Override
            public Object getItem(int position) {
                return mData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                convertView = View.inflate(mContext, R.layout.adapter_sidebar, null);

                TextView tv = convertView.findViewById(R.id.tv_name);
                TextView tvLetter = convertView.findViewById(R.id.tv_letter);
                tv.setText(mData.get(position).getLetter());
                tvLetter.setText(mData.get(position).getFirstLetter());
                if (isFirstLetter(position)) {
                    tvLetter.setVisibility(View.VISIBLE);
                } else {
                    tvLetter.setVisibility(View.GONE);
                }

                return convertView;
            }
        });

    }

    /**
     * 计算首字母第一次出现的位置
     *
     * @param letter
     * @return
     */
    private int calculatePosition(String letter) {
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).getFirstLetter().equals(letter)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断首字母是不是第一次出现
     *
     * @param position
     * @return
     */
    private boolean isFirstLetter(int position) {
        if (position == 0) {
            return true;
        }
        return !mData.get(position).getFirstLetter().equals(mData.get(position - 1).getFirstLetter());
    }

}
