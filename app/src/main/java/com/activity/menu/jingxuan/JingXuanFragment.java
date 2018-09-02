package com.activity.menu.jingxuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tunsh.greendaodemo.R;

/**
 * Name: MineFragment
 * Author: tunsh
 * Date: 2017-12-04 11:24
 */
public class JingXuanFragment extends Fragment {
    private LinearLayout llBookShort,llZongHe,llgirl,llYuanChuang;//书荒互助
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jingxuan_fragment_layout, container, false);
        llBookShort = (LinearLayout)view.findViewById(R.id.llBookShort);
        llZongHe = (LinearLayout)view.findViewById(R.id.llZongHe);
        llgirl = (LinearLayout)view.findViewById(R.id.llgirl);
        llYuanChuang = (LinearLayout)view.findViewById(R.id.llYuanChuang);
        llBookShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BookShortActivity.class);
                startActivity(intent);
            }
        });
        llZongHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyActivity.class);
                intent.putExtra("title","综合讨论区");
                intent.putExtra("tag","ramble");
                startActivity(intent);
            }
        });
        llgirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyActivity.class);
                intent.putExtra("title","女生区");
                intent.putExtra("tag","girl");
                startActivity(intent);
            }
        });
        llYuanChuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CurrencyActivity.class);
                intent.putExtra("title","原创区");
                intent.putExtra("tag","original");
                startActivity(intent);
            }
        });
        return view;
    }
}
