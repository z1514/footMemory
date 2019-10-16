package com.example.footmemory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import java.util.ArrayList;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
//import com.github.mikephil.charting.utils.ValueFormatter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import static android.R.attr.x;

public class HistoryFragment extends Fragment {
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        LineChart chart = (LineChart) view.findViewById(R.id.chart);
        // 制作7个数据点（沿x坐标轴）
        int count = 7;
        ArrayList<Float> cdata = new ArrayList<Float>();
        ArrayList<Float> wdata = new ArrayList<Float>();
        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * 10+50);
            cdata.add(val);
        }
        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * 10+200);
            wdata.add(val);
        }
        LineData lineData = makeLineData(7, cdata, wdata, Color.RED, Color.GREEN);//设置碳足迹
       setChartStyle(chart, lineData, Color.WHITE);
        return view;
    }//end function

    // 设置chart显示的样式
    private void setChartStyle(LineChart mLineChart, LineData lineData, int color) {
//        // 是否在折线图上添加边框

        mLineChart.setDrawBorders(false);
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        mLineChart.setNoDataTextDescription("如果传给MPAndroidChart的数据为空，那么你将看到这段文字。@Zhang Phil");
        mLineChart.setDrawGridBackground(false);
        mLineChart.setGridBackgroundColor(Color.CYAN);
        mLineChart.setTouchEnabled(true);
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setPinchZoom(false);
        mLineChart.getAxisRight().setEnabled(false);
        // 让x轴在下面
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getXAxis().setDrawGridLines(false);
        // 设置背景
        mLineChart.setBackgroundColor(color);
        // 设置x,y轴的数据
       mLineChart.setData(lineData);

        // 设置比例图标示，就是那个一组y的value的
        Legend mLegend = mLineChart.getLegend();
        mLegend.setPosition(LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(15.0f);// 字体
        mLegend.setTextColor(Color.BLUE);// 颜色
    }
    private LineData makeLineData(int count,ArrayList<Float> cdata,ArrayList<Float> wdata,int ccolor,int wcolor) {
        ArrayList<String> x = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            x.add("周" + (i+1));
        }

        // y轴的数据
        ArrayList<Entry> yc = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val=cdata.get(i);
            Entry entry = new Entry(val, i);
            yc.add(entry);
        }
        ArrayList<Entry> yw = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val=wdata.get(i);
            Entry entry = new Entry(val, i);
            yw.add(entry);
        }
        // y轴数据集
        LineDataSet cLineDataSet = new LineDataSet(yc, "用户碳排放量");
        LineDataSet wLineDataSet = new LineDataSet(yw, "用户水排放量");
        // 用y轴的集合来设置参数
        // 线宽
        cLineDataSet.setLineWidth(3.0f);
        wLineDataSet.setLineWidth(3.0f);
        // 显示的圆形大小
        cLineDataSet.setCircleSize(5.0f);
        wLineDataSet.setCircleSize(5.0f);
        // 折线的颜色
        cLineDataSet.setColor(ccolor);
        cLineDataSet.setColor(Color.RED);
        // 圆球的颜色
        cLineDataSet.setCircleColor(Color.GREEN);
        wLineDataSet.setCircleColor(Color.GREEN);
        // 设置mLineDataSet.setDrawHighlightIndicators(false)后，
        // Highlight的十字交叉的纵横线将不会显示，
        // 同时，mLineDataSet.setHighLightColor(Color.CYAN)失效。
        cLineDataSet.setDrawHighlightIndicators(true);
        wLineDataSet.setDrawHighlightIndicators(true);
        // 按击后，十字交叉线的颜色
        cLineDataSet.setHighLightColor(Color.CYAN);
        wLineDataSet.setHighLightColor(Color.CYAN);
        // 设置这项上显示的数据点的字体大小。
        cLineDataSet.setValueTextSize(10.0f);
        wLineDataSet.setValueTextSize(10.0f);
        // 填充折线上数据点、圆球里面包裹的中心空白处的颜色。
        cLineDataSet.setCircleColorHole(Color.YELLOW);
        wLineDataSet.setCircleColorHole(Color.YELLOW);
        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(cLineDataSet);
        mLineDataSets.add(wLineDataSet);
        LineData mLineData = new LineData(x, mLineDataSets);
        return mLineData;
    }//end function makeData



}
