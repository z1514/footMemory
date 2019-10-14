package com.example.footmemory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainContentFragment extends Fragment {
    private List<TraceItem> traceItemList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_content_fragment,container,false);

        initList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.main_recycler_view);
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final TraceItemAdapter adapter = new TraceItemAdapter(traceItemList);
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initList();
                //adapter.notifyItemInserted(traceItemList.size()-1);
                //Thread.currentThread().sleep(1000);
                MyItem item = new MyItem();
                item.setAmount(100);
                item.setName("米饭");
                Date date = new Date();
                item.setTime(date.getTime());
                item.save();
                Item item1 = new Item();
                item1.setAmount(100);
                item1.setItemName("米饭");
                item1.save();
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.replaceFragment(new AddListFragment());

            }
        });
        return view;
    }

    private void initList()
    {
        TraceItem d1 = new TraceItem("米饭",200);
        traceItemList.add(d1);
        TraceItem d2 = new TraceItem("意大利面",1000);
        traceItemList.add(d2);
        TraceItem d3 = new TraceItem("跑步",250);
        traceItemList.add(d3);


    }
}
