/**
 * Created by w_w on 2016/9/1.
 * 此为chart.js插件的js代码，复制过来的
 */
var data = {
    labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
    datasets : [
        {
            barItemName: "name2",
            fillColor : "#47CFE7",
            strokeColor : "rgba(151,187,205,1)",
            data : [28,48,40,19,96,27,100,56,98,58,12,25]
        }
    ]
};

var chartBar = null;
window.onload = function() {
    var ctx = document.getElementById("myChart").getContext("2d");
    chartBar = new Chart(ctx).Bar(data);
    initEvent(chartBar, clickCall);
}

function clickCall(evt){
    var activeBar = chartBar.getBarSingleAtEvent(evt);
    if ( activeBar !== null )
        alert(activeBar.label + ": " + activeBar.barItemName + " ____ " + activeBar.value);
}

function initEvent(chart, handler) {
    var method = handler;
    var eventType = "click";
    var node = chart.chart.canvas;
    if (node.addEventListener) {
        node.addEventListener(eventType, method);
    } else if (node.attachEvent) {
        node.attachEvent("on" + eventType, method);
    } else {
        node["on" + eventType] = method;
    }
}
