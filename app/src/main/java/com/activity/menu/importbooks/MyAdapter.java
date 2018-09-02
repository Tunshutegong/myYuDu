package com.activity.menu.importbooks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.menu.view.SmoothCheckBox;
import com.example.tunsh.greendaodemo.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: MyAdapter
 * Author: tunsh
 * Date: 2017-10-26 15:06
 */
public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<File> files;
    private List<File> checkFiles = new ArrayList<>();
    private SimpleDateFormat formatter;
    private Context context;
    private List<Boolean> booleanList;
    private OnCheckListener onCheckListener;

    public OnCheckListener getOnCheckListener() {
        return onCheckListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public MyAdapter(Context context, List<File> files, List<Boolean> booleanList){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.files = files;
        this.booleanList = booleanList;
        checkFiles.clear();
        formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    }
    @Override
    public int getCount() {
        if(files == null){
            return 0;
        }
        return files.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_layout,null);
            holder.tv = (TextView)view.findViewById(R.id.tv);
            holder.tvTime = (TextView)view.findViewById(R.id.tvTime);
            holder.cb = (CheckBox) view.findViewById(R.id.cb);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        if(files.get(i)!=null){
            holder.tv.setText(files.get(i).getName());
            holder.cb.setChecked(booleanList.get(i));
            holder.tvTime.setText(getByte(files.get(i).length())+"   "+getTime(files.get(i).lastModified()));
            holder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(booleanList.get(i)){
                        holder.cb.setChecked(false);
                        booleanList.set(i,false);
                        checkFiles.remove(files.get(i));
                    }else {
                        holder.cb.setChecked(true);
                        booleanList.set(i,true);
                        checkFiles.add(files.get(i));
                    }
                    onCheckListener.onCheckListener(checkFiles.size());
                    onCheckListener.onCheckListener(checkFiles);

                }
            });
        }

        return view;
    }
    class ViewHolder{
        TextView tv,tvTime;
        CheckBox cb;
    }


    public String getTime(long time){
        String result=formatter.format(time);
        return result;
    }


    public  String getByte(long size) {
        DecimalFormat df = new DecimalFormat("###.#");
        float f;
        if (size < 1024) {
            f = size / 1.0f;
            return (df.format(new Float(f).doubleValue()) + "B");
        } else if (size < 1024 * 1024) {
            f = (float) ((float) size / (float) 1024);
            return (df.format(new Float(f).doubleValue()) + "KB");
        } else if (size < 1024 * 1024 * 1024) {
            f = (float) ((float) size / (float) (1024 * 1024));
            return (df.format(new Float(f).doubleValue()) + "MB");
        } else {
            f = (float) ((float) size / (float) (1024 * 1024 * 1024));
            return (df.format(new Float(f).doubleValue()) + "GB");
        }
    }

    public interface OnCheckListener{
        void onCheckListener(int count);
        void onCheckListener(List<File> list);
    }

}
