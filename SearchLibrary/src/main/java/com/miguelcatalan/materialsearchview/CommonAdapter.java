package com.miguelcatalan.materialsearchview;

/**
 * Created by yangc on 2017/4/10.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mDatas;
    private LayoutInflater mInflater;
    private int mlayoutId;

    public CommonAdapter(Context context,List<T> datas,int layoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }
    public CommonAdapter(Context context,int layoutId) {
        this.mContext = context;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
        this.mDatas=new ArrayList<>();
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void  addAll(List<T> list){
        if (mDatas==null){
            mDatas=new ArrayList<>();
        }
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void    clear(){
        if (mDatas==null){
            mDatas=new ArrayList<>();

        }
        mDatas.clear();
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, mlayoutId, position);
        convert(viewHolder,getItem(position),position);
        return viewHolder.getConvertView();
    }

    public abstract void convert(ViewHolder viewHolder,T t,int position);

}