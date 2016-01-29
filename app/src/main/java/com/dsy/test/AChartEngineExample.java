package com.dsy.test;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class AChartEngineExample extends AppCompatActivity {

    /** The main dataset that includes all the series that go into a chart. */
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    /** The main renderer that includes all the renderers customizing a chart. */
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    /** The most recently added series. */
    private XYSeries mCurrentSeries;
    /** The most recently created renderer, customizing the current series. */
    private XYSeriesRenderer mCurrentRenderer;
    /** Button for creating a new series of data. */
    private Button mNewSeries;
    /** Button for adding entered data to the current series. */
    private Button mAdd;
    /** Edit text field for entering the X value of the data to be added. */
    private EditText mX;
    /** Edit text field for entering the Y value of the data to be added. */
    private EditText mY;
    /** The chart view that displays the data. */
    private GraphicalView mChartView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the current data, for instance when changing screen orientation
        outState.putSerializable("dataset", mDataset);
        outState.putSerializable("renderer", mRenderer);
        outState.putSerializable("current_series", mCurrentSeries);
        outState.putSerializable("current_renderer", mCurrentRenderer);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        // restore the current data, for instance when changing the screen
        // orientation
        mDataset = (XYMultipleSeriesDataset) savedState.getSerializable("dataset");
        mRenderer = (XYMultipleSeriesRenderer) savedState.getSerializable("renderer");
        mCurrentSeries = (XYSeries) savedState.getSerializable("current_series");
        mCurrentRenderer = (XYSeriesRenderer) savedState.getSerializable("current_renderer");
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[] { 20, 30, 15, 20 });
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                    String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }

    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
                                                   List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
                            List<double[]> yValues, int scale) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    public void setData(){
        String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        }
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
                13.9 });
        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
        values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };

        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
                PointStyle.TRIANGLE, PointStyle.SQUARE };
        mRenderer = new XYMultipleSeriesRenderer();
        setRenderer(mRenderer, colors, styles);


        int length = mRenderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) mRenderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(mRenderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
                Color.LTGRAY, Color.LTGRAY);
        mRenderer.setXLabels(12);
        mRenderer.setYLabels(10);
        mRenderer.setShowGrid(true);
        mRenderer.setXLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setPanLimits(new double[]{-10, 20, -10, 40});
        mRenderer.setZoomLimits(new double[]{-10, 20, -10, 40});

        mDataset = buildDataset(titles, x, values);
        mCurrentSeries = mDataset.getSeriesAt(0);
        mCurrentSeries.addAnnotation("Vacation", 6, 30);

        mCurrentRenderer = (XYSeriesRenderer) mRenderer.getSeriesRendererAt(0);
        mCurrentRenderer.setAnnotationsColor(Color.GREEN);
        mCurrentRenderer.setAnnotationsTextSize(15);
        mCurrentRenderer.setAnnotationsTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achartengine_example);

        setData();

        // the top part of the UI components for adding new data points
        mX = (EditText) findViewById(R.id.xValue);
        mY = (EditText) findViewById(R.id.yValue);
        mAdd = (Button) findViewById(R.id.add);

        // set some properties on the main renderer
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
        mRenderer.setAxisTitleTextSize(16);
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(15);
        mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setPointSize(5);

        // the button that handles the new series of data creation
        mNewSeries = (Button) findViewById(R.id.new_series);
        mNewSeries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String seriesTitle = "Series " + (mDataset.getSeriesCount() + 1);
                // create a new series of data
                XYSeries series = new XYSeries(seriesTitle);
                mDataset.addSeries(series);
                mCurrentSeries = series;
                // create a new renderer for the new series
                XYSeriesRenderer renderer = new XYSeriesRenderer();
                mRenderer.addSeriesRenderer(renderer);
                // set some renderer properties
                renderer.setPointStyle(PointStyle.CIRCLE);
                renderer.setFillPoints(true);
                renderer.setDisplayChartValues(true);
                renderer.setDisplayChartValuesDistance(10);
                mCurrentRenderer = renderer;
                setSeriesWidgetsEnabled(true);
                mChartView.repaint();
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double x = 0;
                double y = 0;
                try {
                    x = Double.parseDouble(mX.getText().toString());
                } catch (NumberFormatException e) {
                    mX.requestFocus();
                    return;
                }
                try {
                    y = Double.parseDouble(mY.getText().toString());
                } catch (NumberFormatException e) {
                    mY.requestFocus();
                    return;
                }
                // add a new data point to the current series
                mCurrentSeries.add(x, y);
                mX.setText("");
                mY.setText("");
                mX.requestFocus();
                // repaint the chart such as the newly added point to be visible
                mChartView.repaint();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getLineChartView(this, mDataset, mRenderer);
            // enable the chart click events
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);
            mChartView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // handle the click event on the chart
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(AChartEngineExample.this, "No chart element", Toast.LENGTH_SHORT).show();
                    } else {
                        // display information of the clicked point
                        Toast.makeText(
                                AChartEngineExample.this,
                                "Chart element in series index " + seriesSelection.getSeriesIndex()
                                        + " data point index " + seriesSelection.getPointIndex() + " was clicked"
                                        + " closest point value X=" + seriesSelection.getXValue() + ", Y="
                                        + seriesSelection.getValue(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            boolean enabled = mDataset.getSeriesCount() > 0;
            setSeriesWidgetsEnabled(enabled);
        } else {
            mChartView.repaint();
        }
    }

    /**
     * Enable or disable the add data to series widgets
     *
     * @param enabled the enabled state
     */
    private void setSeriesWidgetsEnabled(boolean enabled) {
        mX.setEnabled(enabled);
        mY.setEnabled(enabled);
        mAdd.setEnabled(enabled);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}