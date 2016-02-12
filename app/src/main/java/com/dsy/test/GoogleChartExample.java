package com.dsy.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleChartExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_chart_example);
        WebView webview = (WebView) findViewById(R.id.webview);
        /*
        String content = "<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['corechart']});"
                + "      google.charts.setOnLoadCallback(drawChart);"
                + "      function drawChart() {"
                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Element', '매출', { role: 'style' }],"
                + "          ['1월',  1000, '#e87333'],"//x좌표 값, y좌표 값, 색상
                + "          ['2월',  1170, '#887333'],"
                + "          ['3월',  550, '#c87333'],"
                + "          ['4월',  890, '#d87333'],"
                + "          ['5월',  782, '#287333'],"
                + "          ['6월',  652, '#787333'],"
                + "          ['7월',  900, '#a87333'],"
                + "          ['8월',  590, '#c87333'],"
                + "          ['9월',  753, '#b87333'],"
                + "          ['10월',  965, '#357333'],"
                + "          ['11월',  785, '#677333'],"
                + "          ['12월',  1030, '#787333']"
                + "        ]);"
                + "        var options = {"
                //+ "          title: 'Monthly Sales',"
                //+ "          hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}"
                //+ "             width:800, height:500"
                + "             legend: { position: \"none\" },"
                + "        };"
                + "        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));"
                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>"
                + "  </body>"
                + "</html>";
        */

        String content = "<html>\n" +
                "   <head>\n" +
                "   <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
                "   <script type=\"text/javascript\" src=\"//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                "   <script type=\"text/javascript\">\n" +
                "   google.charts.load(\"current\", {packages:['corechart']});\n" +
                "   google.charts.setOnLoadCallback(drawChart);\n" +
                "   function drawChart() {\n" +
                "     var data = google.visualization.arrayToDataTable([\n" +
                "          ['Monthly', '전기', '가스', '상하수도', { role: 'annotation' } ],\n" +
                "          ['1월', 18000, 56000, 28000, ''],\n" +
                "          ['2월', 17000, 36000, 30000, ''],\n" +
                "          ['3월', 18000, 56000, 28000, ''],\n" +
                "          ['4월', 17000, 36000, 30000, ''],\n" +
                "          ['5월', 18000, 56000, 28000, ''],\n" +
                "          ['6월', 17000, 36000, 30000, ''],\n" +
                "          ['7월', 18000, 56000, 28000, ''],\n" +
                "          ['8월', 17000, 36000, 30000, ''],\n" +
                "          ['9월', 18000, 56000, 28000, ''],\n" +
                "          ['10월', 17000, 36000, 30000, ''],\n" +
                "          ['11월', 18000, 56000, 28000, ''],\n" +
                "          ['12월', 17000, 36000, 30000, '']\n" +
                "        ]);\n" +
                "    var view = new google.visualization.DataView(data);\n" +
                "    var options = {\n" +
                "      width: 650,\n" +
                "      height: 250,\n" +
                "      legend: { position: 'bottom', maxLines: 3 },\n" +
                //"      bar: { groupWidth: '25%' },\n" +
                "      isStacked: true,\n" +
                "    };\n" +
                "    var chart = new google.visualization.ColumnChart(document.getElementById(\"columnchart_values\"));\n" +
                "    chart.draw(view, options);\n" +
                "  }\n" +
                "  </script>" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"columnchart_values\" style=\"width: 650px; height: 250px;\"></div>\n" +
                "  </body>\n" +
                "</html>";

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setUseWideViewPort(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webview.requestFocusFromTouch();
        //webview.addJavascriptInterface();//이걸로 자바랑 자바스크립트 데이터 연결

        try {
            URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        webview.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
        //webview.loadUrl("file:///android_asset/Code.html"); // Can be used in this way too.
    }
}
