<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title></title>
        <link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css">
        <script src="../js/jquery-1.11.1.min.js"></script>
        <script src="<c:url value='/js/amcharts.js'/>"></script>
        <script src="<c:url value='/js/serial.js'/>"></script>

        <script>
            var chart;
			var chartData = '<%=request.getAttribute("chartData")%>';
			chartData = JSON.parse(chartData);
            /* var chartData = [
                {
                    "name": "USA",
                    "value": 3025,
                    "color": "#FF0F00"
                },
                {
                    "name": "China",
                    "value": 1882,
                    "color": "#FF6600"
                },
                {
                    "name": "Japan",
                    "value": 1809,
                    "color": "#FF9E01"
                },
                {
                    "name": "Germany",
                    "value": 1322,
                    "color": "#FCD202"
                },
                {
                    "name": "UK",
                    "value": 1122,
                    "color": "#F8FF01"
                },
                {
                    "name": "France",
                    "value": 1114,
                    "color": "#B0DE09"
                },
                {
                    "name": "India",
                    "value": 984,
                    "color": "#04D215"
                },
                {
                    "name": "Spain",
                    "value": 711,
                    "color": "#0D8ECF"
                },
                {
                    "name": "Netherlands",
                    "value": 665,
                    "color": "#0D52D1"
                },
                {
                    "name": "Russia",
                    "value": 580,
                    "color": "#2A0CD0"
                },
                {
                    "name": "South Korea",
                    "value": 443,
                    "color": "#8A0CCF"
                },
                {
                    "name": "Canada",
                    "value": 441,
                    "color": "#CD0D74"
                }
            ]; */


            AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "name";
                chart.startDuration = 1;
                chart.depth3D = 50;
                chart.angle = 30;
                chart.marginRight = -45;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridAlpha = 0;
                categoryAxis.axisAlpha = 0;
                categoryAxis.gridPosition = "start";

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.axisAlpha = 0;
                valueAxis.gridAlpha = 0;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.valueField = "value";
                graph.colorField = "color";
                graph.balloonText = "<b>[[category]]: [[value]]</b>";
                graph.type = "column";
                graph.lineAlpha = 0.5;
                graph.lineColor = "#FFFFFF";
                graph.topRadius = 1;
                graph.fillAlphas = 0.9;
                chart.addGraph(graph);

                // CURSOR
                var chartCursor = new AmCharts.ChartCursor();
                chartCursor.cursorAlpha = 0;
                chartCursor.zoomable = false;
                chartCursor.categoryBalloonEnabled = false;
                chartCursor.valueLineEnabled = true;
                chartCursor.valueLineBalloonEnabled = true;
                chartCursor.valueLineAlpha = 1;
                chart.addChartCursor(chartCursor);

                chart.creditsPosition = "top-right";

                // WRITE
                chart.write("chartdiv");
                $("a").empty();
            });
        </script>
    </head>

    <body>
    	<div style="text-align:center;margin-bottom: 80px;"><h1 style="margin-right: auto;margin-left: auto;">年度缴费统计</h2></div>
        <div id="chartdiv" style="width: 700px; height: 600px;"></div>
    </body>

</html>