package com.example.footmemory;

import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.R;
import com.example.footmemory.db.Item;
import com.example.footmemory.db.MyItem;
import com.example.footmemory.util.TraceItem;
import com.example.footmemory.util.TraceItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainContentFragment extends Fragment{
    private List<TraceItem> traceItemList = new ArrayList<>();
    private double d = 0.0;
    private TextView hint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_content_fragment,container,false);
        hint = (TextView)view.findViewById(R.id.hint_view);
        initList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.main_recycler_view);
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final TraceItemAdapter adapter = new TraceItemAdapter(traceItemList);
        recyclerView.setAdapter(adapter);
        DecimalFormat df = new DecimalFormat("0.00");
        TextView fullAmount = (TextView)view.findViewById(R.id.full_amount);
        fullAmount.setText(df.format(d)+"kg");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initList();
                //adapter.notifyItemInserted(traceItemList.size()-1);
                //Thread.currentThread().sleep(1000);
//                MyItem item = new MyItem();
//                item.setAmount(100);
//                item.setName("米饭");
//                Date date = new Date();
//                item.setTime(date.getTime());
//                item.save();
//                Item item1 = new Item();
//                item1.setAmount(100);
//                item1.setItemName("米饭");
//                item1.save();
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.replaceFragment(new AddListFragment());
                mainActivity.navView.setCheckedItem(R.id.nav_cal);

            }
        });
        return view;
    }

    private void initList()
    {
        //获取时间
        long date = getDate();
        List<MyItem> list = LitePal.where("time>=? and time <?",""+date,""+date+24 * 3600 * 1000).find(MyItem.class);
        if (list.size()!=0)
        {
            hint.setVisibility(View.GONE);
        }
        for (MyItem item:list)
        {
            traceItemList.add(new TraceItem(item.getName(),item.getAmount(),item.getTime()));
        }

        for(TraceItem item:traceItemList)
        {
            d +=item.getAmount();
        }


    }

    private long getDate()
    {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date.getTime();

    }

}
