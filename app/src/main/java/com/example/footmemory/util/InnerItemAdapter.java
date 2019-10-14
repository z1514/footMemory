package com.example.footmemory.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.R;

import java.util.List;

public class InnerItemAdapter extends RecyclerView.Adapter<InnerItemAdapter.ViewHolder>{
    private List<InnerItem> mInnerList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView innerName;
        TextView innerBase;

        public ViewHolder(View view)
        {
            super(view);
            innerName = (TextView)view.findViewById(R.id.inner_name);
            innerBase = (TextView)view.findViewById(R.id.inner_base);
        }


    }
    public InnerItemAdapter(List<InnerItem> innerList)
    {
        mInnerList = innerList;
    }

    @NonNull
    @Override
    public InnerItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_item,parent,false);
        InnerItemAdapter.ViewHolder holder = new InnerItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerItemAdapter.ViewHolder holder, int position) {
        InnerItem innerItem = mInnerList.get(position);
        holder.innerName.setText(innerItem.getName());
        holder.innerBase.setText(innerItem.getBase()+"kg");

    }

    @Override
    public int getItemCount() {
        return mInnerList.size();
    }
}
