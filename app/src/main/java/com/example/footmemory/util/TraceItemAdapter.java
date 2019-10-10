package com.example.footmemory.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.R;

import java.util.List;

public class TraceItemAdapter extends RecyclerView.Adapter<TraceItemAdapter.ViewHolder> {
    private List<TraceItem> mTraceList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView traceName;
        TextView traceAmount;

        public ViewHolder(View view)
        {
            super(view);
            traceName = (TextView)view.findViewById(R.id.trace_name);
            traceAmount = (TextView)view.findViewById(R.id.trace_amount);
        }


    }
    public TraceItemAdapter(List<TraceItem> traceList)
    {
        mTraceList = traceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trace_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TraceItem traceItem = mTraceList.get(position);
        holder.traceName.setText(traceItem.getName());
        holder.traceAmount.setText(""+traceItem.getAmount());

    }

    @Override
    public int getItemCount() {
        return mTraceList.size();
    }
}
