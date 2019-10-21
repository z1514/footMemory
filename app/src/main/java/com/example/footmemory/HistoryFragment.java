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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.footmemory.db.MyItem;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
//import com.github.mikephil.charting.utils.ValueFormatter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import org.litepal.LitePal;

import static android.R.attr.x;

public class HistoryFragment extends Fragment {
    BarChart mbarChart;
    LineChart chart;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        mbarChart = (BarChart)view.findViewById(R.id.mBarChart);
        draw();
        chart = (LineChart) view.findViewById(R.id.chart);

        // 制作7个数据点（沿x坐标轴）
        int count = 7;
        ArrayList<Float> cdata = new ArrayList<Float>();
        ArrayList<Float> wdata = new ArrayList<Float>();
        float val[] = new float[7];
        List<MyItem> myItemList = LitePal.findAll(MyItem.class);
        Date d = new Date();
        d.setSeconds(0);
        d.setMinutes(0);
        d.setHours(0);
        for(MyItem item:myItemList)
        {
            Date d1 = new Date(item.getTime());
            d1.setSeconds(0);
            d1.setMinutes(0);
            d1.setHours(0);

            if(getDay(d1,d)>=0&&getDay(d1,d)<=6)
            {
                val[6-getDay(d1,d)]+=item.getAmount();
            }


        }
        for (int i = 0; i < count; i++) {

            cdata.add(val[i]);
        }
        LineData lineData = makeLineData(7, cdata, Color.RED, Color.GREEN);//设置碳足迹
       setChartStyle(chart, lineData, Color.WHITE);

        return view;
    }//end function
//设置柱状图的样式和数据
        public void draw(){
        //设置barchart的样式
            //设置描述
            mbarChart.setDescription("本月足迹柱状图");
            //关闭柱状图的阴影
            mbarChart.setDrawBarShadow(false);
            //设置柱状图的数据在上方显示
            mbarChart.setDrawValueAboveBar(true);
            //mbarChart.setBackgroundColor(Color.WHITE);
            //mbarChart.setPinchZoom(false);
            mbarChart.setDrawGridBackground(false);

        //设置X轴的位置，默认在上方
            XAxis xAxis = mbarChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            //不显示纵向分割线
            xAxis.setDrawGridLines(false);
            //隐藏右边坐标轴
            //mbarChart.getAxisRight().setEnabled(false);

            //模拟数据
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        List<MyItem> myItemList = LitePal.findAll(MyItem.class);
        float val[] = new float[31];
        for(MyItem item:myItemList)
        {
            Date d = new Date(item.getTime());
            val[d.getDate()-1]+=item.getAmount();
        }
        for(int i=0;i<30;i++)
        {
            BarEntry entry = new BarEntry(val[i],i);
            yVals1.add(entry);
        }

        BarDataSet barDataSet =new BarDataSet(yVals1, "一个月碳排放量");
        //barDataSet.setBarSpacePercent(50f);
        barDataSet.setColor(Color.rgb(0xff,0x14,0x93));
        ArrayList<String> x =new ArrayList<String>(31);
        for(int i=0;i<31;i++)
        {
            x.add(i+1+"日");
        }
        BarData barData = new BarData(x,barDataSet);
        mbarChart.setData(barData);
        //设置空白部分所占的比例
        barDataSet.setBarSpacePercent((float)(70));
            //设置一页最大显示个数为7，超出部分就滑动
            // float ratio = (float)30/(float)7;
            // chart.zoom(ratio,1f,0,0);
            //设置从xy轴出来的动画
            mbarChart.animateXY(1500,1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
    }

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
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(15.0f);// 字体
        mLegend.setTextColor(Color.BLUE);// 颜色
    }
    private LineData makeLineData(int count,ArrayList<Float> cdata,int ccolor,int wcolor) {
        ArrayList<String> x = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            x.add(""+(i+1));
        }
        // y轴的数据
        ArrayList<Entry> yc = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float val=cdata.get(i);
            Entry entry = new Entry(val, i);
            yc.add(entry);
        }
        // y轴数据集
        LineDataSet cLineDataSet = new LineDataSet(yc, "用户碳排放量");
        // 用y轴的集合来设置参数
        int red = (0xff1493 & 0xff0000) >> 16;
        int green = (0xff1493 & 0x00ff00) >> 8;
        int blue = (0xff1493 & 0x0000ff);
        //设置线的颜色
        cLineDataSet.setColor(Color.rgb(red,green,blue));
        //设置数据点圆形的颜色
        cLineDataSet.setCircleColor(Color.rgb(red,green,blue));
        //设置填充圆形中间的颜色
        cLineDataSet.setCircleColorHole(Color.rgb(red,green,blue));
        //设置折线的宽度
        cLineDataSet.setLineWidth(1f);
        //设置折线点圆点半径
        cLineDataSet.setCircleSize(4f);
        //设置一页最大显示个数为7，超出部分就滑动
//         float ratio = (float)count/(float)31;
//        chart.zoom(ratio,1f,0,0);
        //设置从xy轴出来的动画
        chart.animateXY(1500,1500, Easing.EasingOption.EaseInSine, Easing.EasingOption.EaseInSine);
        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        //曲线图
        cLineDataSet.setDrawCubic(true);
        //填充色
        cLineDataSet.setDrawFilled(true);
        cLineDataSet.setFillColor(Color.rgb(0x7f,0xff,0x00));
        mLineDataSets.add(cLineDataSet);
        LineData mLineData = new LineData(x, mLineDataSets);
        return mLineData;
    }//end function makeData

    public static int getDay(Date d1,Date d2)
    {
        Calendar cld = Calendar.getInstance();
        cld.setTime(d1);
        int day1 = cld.get(Calendar.DAY_OF_YEAR);
        cld.setTime(d2);
        int day2 = cld.get(Calendar.DAY_OF_YEAR);
        return day2 -day1;
    }



}
