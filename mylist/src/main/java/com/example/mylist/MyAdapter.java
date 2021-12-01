package com.example.mylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    ArrayList<Map<String,String>> data;

    public MyAdapter(){}

    public MyAdapter(ArrayList<Map<String,String>> data){
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.list_item, viewGroup, false);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtAddr = view.findViewById(R.id.txtAddr);
        txtName.setText(data.get(i).get("name"));
        txtAddr.setText(data.get(i).get("addr"));

        return view;
    }
}
