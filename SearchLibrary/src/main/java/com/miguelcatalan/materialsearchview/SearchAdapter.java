package com.miguelcatalan.materialsearchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Suggestions Adapter.
 *
 * @author Miguel Catalan Bañuls
 */
public class SearchAdapter<T> extends BaseAdapter {

    private ArrayList<T> data;
    private LayoutInflater inflater;
    private int res;
    private  Context context;

    public SearchAdapter(Context context, int res) {
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        this.res = res;
        this.context=context;
    }

    public SearchAdapter(Context context, ArrayList<T> list, int res) {
        inflater = LayoutInflater.from(context);
        data = list;
        this.res = res;
        this.context=context;

    }

    public  void  addAlL(List<T> list){
        if (data==null){
            data=new ArrayList<>();
        }
        data.addAll(list);
        notifyDataSetChanged();

    }
    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuggestionsViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(res, parent, false);
            viewHolder = new SuggestionsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SuggestionsViewHolder) convertView.getTag();
        }
        viewHolder.setDataView(getItem(position), position);
        return convertView;
    }

    protected class SuggestionsViewHolder {

        private SuggestionsViewHolder(View convertView) {
            bindViewHolder(convertView);
        }

        private void  setDataView(T data, int position) {
            setData(data,position);
        }
    }
    protected    void  setData(T data, int position) {

    }
    protected   void    bindViewHolder(View convertView){

    }
}