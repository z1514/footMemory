package com.example.footmemory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footmemory.util.InnerItem;
import com.example.footmemory.util.InnerItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddListFragment extends Fragment {
    private List<InnerItem> innerItemList = new ArrayList<>();
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_list_fragment,container,false);
        initList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.addlist_recycler_view);
        //FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final InnerItemAdapter adapter = new InnerItemAdapter(innerItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initList()
    {
        InnerItem innerItem1 = new InnerItem("涤纶织物/kg",25.7);
        InnerItem innerItem2 = new InnerItem("纯棉T恤/件",7);
        InnerItem innerItem3 = new InnerItem("洗衣液/L",0.8);
        innerItemList.add(innerItem1);
        innerItemList.add(innerItem2);
        innerItemList.add(innerItem3);

    }
}
