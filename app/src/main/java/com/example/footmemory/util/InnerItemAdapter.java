package com.example.footmemory.util;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.MainActivity;
import com.example.footmemory.R;
import com.example.footmemory.db.MyItem;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import me.shaohui.bottomdialog.BottomDialog;

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
    public InnerItemAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_item,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                final InnerItem innerItem = mInnerList.get(position);
                //Toast.makeText(view.getContext(),innerItem.getName(),Toast.LENGTH_SHORT).show();
                //BottomDialog.create()
                final BottomSheetDialog dialog = new BottomSheetDialog(parent.getContext());
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_win,null);
                TextView title = (TextView)v.findViewById(R.id.title_text);
                TextView back = (TextView)v.findViewById(R.id.title_back);
                TextView sure = (TextView)v.findViewById(R.id.title_sure);
                TextView base = (TextView)v.findViewById(R.id.pop_base);
                final EditText amount = (EditText)v.findViewById(R.id.pop_amount);
                title.setText(innerItem.getName());
                base.setText(""+innerItem.getBase()+"kg/单位");

                dialog.setContentView(v);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(),"已取消",Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (amount.getText().length()==0)
                            return;
                        double a;
                        try {
                            a = Double.valueOf(amount.getText().toString());
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(view.getContext(),"请输入合法的数字",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(a<=0)
                        {
                            Toast.makeText(view.getContext(),"请输入正数",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        MyItem item = new MyItem();
                        Date date = new Date();
                        item.setTime(date.getTime());
                        item.setName(innerItem.getName());
                        item.setAmount(innerItem.getBase()*a);
                        item.save();
                        Toast.makeText(view.getContext(),"已经存储",Toast.LENGTH_SHORT).show();
                        dialog.hide();
                        return;

                    }
                });

            }
        });

        //InnerItemAdapter.ViewHolder holder = new InnerItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerItemAdapter.ViewHolder holder, int position) {
        InnerItem innerItem = mInnerList.get(position);
        holder.innerName.setText(innerItem.getName());
        holder.innerBase.setText("碳足迹"+innerItem.getBase()+"kg/每单位");


    }

    @Override
    public int getItemCount() {
        return mInnerList.size();
    }
}
