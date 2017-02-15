<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <link rel="stylesheet" href="style.css" type="text/css">
        <script src="<c:url value='/js/amcharts.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/js/pie.js'/>" type="text/javascript"></script>
<script src="../js/jquery-1.11.1.min.js"></script>
        <script>
            var chartp;
            var chartDatap = '<%=request.getAttribute("chartDatap")%>';
			chartDatap = JSON.parse(chartDatap);
             /*chartDatap = [
                {
                    "name": "United States",
                    "value": 9252
                },
                {
                    "name": "China",
                    "value": 1882
                },
                {
                    "name": "Japan",
                    "value": 1809
                },
                {
                    "name": "Germany",
                    "value": 1322
                },
                {
                    "name": "United Kingdom",
                    "value": 1122
                },
                {
                    "name": "France",
                    "value": 1114
                },
                {
                    "name": "India",
                    "value": 984
                },
                {
                    "name": "Spain",
                    "value": 711
                }
            ]; */

            AmCharts.ready(function () {
                // PIE CHART
                chartp = new AmCharts.AmPieChart();
                // title of the chart
                //chartp.addTitle("Visitors countries", 16);

                chartp.dataProvider = chartDatap;
                chartp.titleField = "name";
                chartp.valueField = "value";
                chartp.sequencedAnimation = true;
                chartp.startEffect = "elastic";
                chartp.innerRadius = "30%";
                chartp.startDuration = 2;
                chartp.labelRadius = 15;
                chartp.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
                // the following two lines makes the chart 3D
                chartp.depth3D = 10;
                chartp.angle = 15;

                // WRITE
                chartp.write("chartpdiv");
                $("a").empty();
            });
            
        </script>
    </head>

    <body>
    <div style="text-align:center;"><h1 style="margin-right: auto;margin-left: auto;">费用百分比</h2></div>
        <div id="chartpdiv" style="width:750px; height:700px;margin-left: auto;margin-right: auto;"></div>
    </body>

</html>