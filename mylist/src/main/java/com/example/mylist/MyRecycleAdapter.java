package com.example.mylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyHolder> {
    ArrayList<MemoVO> data;

//    private String[] mDataset;
    public  MyRecycleAdapter(){}
    public  MyRecycleAdapter(ArrayList<MemoVO> data) { this.data = data;}
    public void setData(ArrayList<MemoVO> data) {this.data = data;}

//    public MyAdapter(String[] mDataset) { this.mDataset = mDataset; }

    @NonNull @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyHolder vh = new MyHolder(v);
        return vh; }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtName.setText(data.get(position).getTitle());
        holder.txtAddr.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() { return data.size(); }

    class  MyHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtAddr;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddr = itemView.findViewById(R.id.txtAddr);
        }

    }

    ///기존방법
//    ArrayList<Map<String,String>> data;
//
//    private String[] mDataset;
//    public MyAdapter(String[] mDataset) { this.mDataset = mDataset; }
//
//    @NonNull @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // create a new view
//        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.my_text_view, parent, false); MyViewHolder vh = new MyViewHolder(v); return vh; }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
//        holder.txtName.setText(data.get(position).get("name"));
//        holder.txtAddr.setText(data.get(position).get("addr"));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.txtName.setText(data.get(position).get("name"));
//    }
//    @Override
//    public int getItemCount() { return mDataset.length; }
//    return data.size();
//
//    class  MyHolder extends RecyclerView.ViewHolder{
//        TextView txtName;
//        TextView txtAddr;
//        public MyHolder(@NonNull View itemView){
//            super(itemView);
//            txtName = itemView.findViewById(R.id.txtName);
//            txtAddr = itemView.findViewById(R.id.txtAddr);
//        }
//
//    }

}
