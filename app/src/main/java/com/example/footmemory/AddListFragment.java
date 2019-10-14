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

import com.example.footmemory.db.Item;
import com.example.footmemory.util.InnerItem;
import com.example.footmemory.util.InnerItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddListFragment extends Fragment {
    private List<InnerItem> innerItemList = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();
    private List<Double> baseList = new ArrayList<>();
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
        nameList.add("涤纶织物/kg");
        nameList.add("纯棉T恤/件");
        nameList.add("洗衣液/L");
        nameList.add("粮食消费/kg");
        nameList.add("羊肉/kg");
        nameList.add("牛肉/kg");
        nameList.add("猪肉/kg");
        nameList.add("炸鸡");
        nameList.add("鸡蛋");
        nameList.add("土豆");
        nameList.add("米饭");
        nameList.add("扁豆");
        nameList.add("西红柿");
        nameList.add("牛奶");
        nameList.add("豆腐");
        nameList.add("西兰花");
        nameList.add("酸奶");
        nameList.add("花生");
        nameList.add("集中供暖/平米每天");
        nameList.add("天然气/立方米");
        nameList.add("用水/立方米");
        nameList.add("用电/度");
        nameList.add("飞机/km");
        nameList.add("火车/km");
        nameList.add("轮船/km");
        nameList.add("地铁/站");
        nameList.add("公共汽车/km");
        nameList.add("中耗油小汽车/km");
        nameList.add("城市垃圾/kg");
        nameList.add("洗发水/瓶");
        nameList.add("一次性筷子/双");
        nameList.add("纸制品/kg");
        nameList.add("塑料袋/100个");
        baseList.add(25.7);
        baseList.add(7.0);
        baseList.add(0.8);
        baseList.add(0.8);
        baseList.add(39.2);
        baseList.add(27.0);
        baseList.add(12.1);
        baseList.add(1.8);
        baseList.add(4.8);
        baseList.add(2.9);
        baseList.add(2.7);
        baseList.add(0.9);
        baseList.add(1.1);
        baseList.add(1.9);
        baseList.add(2.0);
        baseList.add(2.0);
        baseList.add(2.2);
        baseList.add(2.5);
        baseList.add(0.13);
        baseList.add(2.19);
        baseList.add(0.91);
        baseList.add(1.0);
        baseList.add(0.28);
        baseList.add(0.01);
        baseList.add(0.01);
        baseList.add(0.1);
        baseList.add(0.01);
        baseList.add(0.3);
        baseList.add(2.06);
        baseList.add(0.02);
        baseList.add(0.01);
        baseList.add(3.5);
        baseList.add(0.01);
        for(int i=0;i<baseList.size();i++)
        {
            InnerItem innerItem = new InnerItem(nameList.get(i),baseList.get(i));
            innerItemList.add(innerItem);
            List<Item> items = LitePal.where("itemname=?",nameList.get(i)).find(Item.class);
            if(items.size()==0)
            {
                Item item = new Item();
                item.setItemName(nameList.get(i));
                item.setAmount(baseList.get(i));
                item.save();
            }
        }


    }
}
