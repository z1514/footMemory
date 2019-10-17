package com.example.footmemory.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.MainActivity;
import com.example.footmemory.R;
import com.example.footmemory.db.MyItem;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
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
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trace_item,parent,false);
        //final TextView textView = (TextView)view.findViewById(R.id.full_amount);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final int position = holder.getAdapterPosition();
                final TraceItem item = mTraceList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
                dialog.setTitle("删除");
                dialog.setMessage("是否执行删除操作");
                dialog.setCancelable(false);

                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LitePal.deleteAll(MyItem.class,"time=?",""+item.getTime());
                        mTraceList.remove(position);
                        TraceItemAdapter.super.notifyItemRemoved(mTraceList.size()-1);
                        TextView textView = view.getRootView().findViewById(R.id.full_amount);
                        TextView hint = view.getRootView().findViewById(R.id.hint_view);
                        double d = 0.00;
                        DecimalFormat df = new DecimalFormat("0.00");
                        for(TraceItem item1:mTraceList)
                        {
                            d+=item1.getAmount();
                        }
                        textView.setText(df.format(d));
                        //double d = Double.valueOf(""+textView.getText());
                        //d -= item.getAmount();
                        //DecimalFormat df = new DecimalFormat("0.00");
                        //textView.setText(df.format(d));
                        //MainActivity activity = (Activity)view.getContext();
                        if(mTraceList.size()==0)
                        {
                            hint.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(parent.getContext(),"已经删除",Toast.LENGTH_SHORT);

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dialog.show();


            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.00");
        TraceItem traceItem = mTraceList.get(position);
        holder.traceName.setText(traceItem.getName());
        holder.traceAmount.setText(""+df.format(traceItem.getAmount())+"kg");


    }

    @Override
    public int getItemCount() {
        return mTraceList.size();
    }
}
