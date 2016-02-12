package com.dsy.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MPAndroidChartExample3 extends AppCompatActivity implements OnChartValueSelectedListener {
    private String[] mMonths = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
    };


    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mpandroid_chart_example3);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(12);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);

        // change the position of the y-labels
        YAxis yLabels = mChart.getAxisLeft();
        //yLabels.setValueFormatter(new MyYAxisValueFormatter());
        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setDrawGridLines(false);
        xLabels.setSpaceBetweenLabels(0);

        // mChart.setDrawXLabels(false);
        // mChart.setDrawYLabels(false);

        // setting data
        //mSeekBarX.setProgress(12);
        //mSeekBarY.setProgress(500);
        setData(12, 500);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(15f);
        l.setTextSize(15f);
        l.setFormToTextSpace(5f);
        l.setXEntrySpace(23f);

        mChart.getData().setHighlightEnabled(false);

        mChart.getLegend().setEnabled(false);

        mChart.animateY(1500);

        // mChart.setDrawLegend(false);
    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % mMonths.length]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            float val3 = (float) (Math.random() * mult) + mult / 3;

            yVals1.add(new BarEntry(new float[] { val1, val2, val3 }, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "");
        set1.setColors(getColors());
        set1.setStackLabels(new String[]{"전기", "가스", "상수도"});
        set1.setBarSpacePercent(30f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        //data.setValueFormatter(new MyYAxisValueFormatter());

        mChart.setData(data);

        //mChart.zoom(1.01f, 1f, 0, 0);
        //mChart.getViewPortHandler().setMaximumScaleX(1.01f);
        mChart.getViewPortHandler().setMinimumScaleX(1.01f);
        //mChart.getViewPortHandler().setMaximumScaleY(1f);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        BarEntry entry = (BarEntry) e;

        if (entry.getVals() != null)
            Log.i("VAL SELECTED", "Value: " + entry.getVals()[h.getStackIndex()]);
        else
            Log.i("VAL SELECTED", "Value: " + entry.getVal());
    }

    @Override
    public void onNothingSelected() {
        // TODO Auto-generated method stub

    }

    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = ColorTemplate.VORDIPLOM_COLORS[i];
        }

        colors[0] = Color.rgb(80, 210, 194);
        colors[1] = Color.rgb(252, 171, 83);
        colors[2] = Color.rgb(186, 119, 255);

        return colors;
    }
}