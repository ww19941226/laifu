/**
 * Created by Administrator on 2017/2/28.
 */
/**
 * Created by Administrator on 2017/2/22.
 */
Ext.onReady(function() {
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = "qtip";
    var id = "";
    var win;
    Ext.define("InitValue", {
        extend : "Ext.data.Model",
        fields : [ {
            name : "id",
            type : "long"
        }, {
            name : "order_creattime",
            type : "string"
        }, {
            name : "order_money",
            type : "string"
        }, {
            name : "order_state",
            type : "int"
        }, {
            name : "order_address",
            type : "string"
        }, {
            name : "order_phone",
            type : "string"
        }, {
            name : "order_receivename",
            type : "string"
        }, {
            name : "order_button",
            type : "string"
        } ]
    });

    var initValueStore = Ext.create('Ext.data.JsonStore', {
        model :'InitValue',
        data: [
            {order_creattime:'2017-02-23 10:34:59',order_money:'15.8',order_state:2,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生',order_button:'<a href="接单请求?id=">接单</a>'},
            {order_creattime:'2017-02-22 10:34:59',order_money:'16.8',order_state:2,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生',order_button:'<a href="接单请求?id=">接单</a>'},
            {order_creattime:'2017-02-21 10:34:59',order_money:'17.8',order_state:2,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生',order_button:'<a href="接单请求?id=">接单</a>'}
        ]
        //proxy : {
        //	type : 'ajax',
        //	url : basePath + '/system/initvalue/getList',
        //	reader : {
        //		type : 'json',
        //		root : 'returnData'
        //	}
        //},
        //autoLoad: true
    });
    var columns = [ new Ext.grid.RowNumberer({
        header : '序号',
        width : "5%",
        align : 'center'
    }), {
        header : "下单时间",
        width : "15%",
        align : 'center',
        dataIndex : 'order_creattime',
        sortable : true
    },{
        header : "金额",
        width : "10%",
        align : 'center',
        dataIndex : 'order_money',
        sortable : true
    },{
        header : "订单状态",
        width : "10%",
        align : 'center',
        dataIndex : 'order_state',
        sortable : true
    },{
        header : "收货人",
        width : "10%",
        align : 'center',
        dataIndex : 'order_receivename',
        sortable : true
    },{
        header : "收货电话",
        width : "15%",
        align : 'center',
        dataIndex : 'order_phone',
        sortable : true
    },{
        header : "收货地址",
        width : "20%",
        align : 'center',
        dataIndex : 'order_address',
        sortable : true
    },{
        header : "操作",
        width : "10%",
        align : 'center',
        dataIndex : 'order_button',
        sortable : true
    }];

    var toolbar = new Ext.Toolbar({
        items : [ "订单信息管理：", "-", {
            text : '刷新',
            handler : function() {
                initValueGrid.store.reload();
            }
        } ]
    });



    var initValueGrid = Ext.create('Ext.grid.Panel', {
        width : '100%',
        store : initValueStore,
        columnLines : true,
        height : "100%",
        rowLines : true,
        selType : "checkboxmodel",
        multiSelect : true,
        autoScroll : true,
        tbar : toolbar,
        viewConfig : {
            forceFit : true,
            enableTextSelection:true,
            stripeRows : true
        },
        columns : columns,
        listeners : {
            'itemdblclick' : function(dataview, record, item, index, e) {
                win.show();
                //id = record.raw.id;
                //loadForm();
            }
        }
    })

    var initValuePanel = Ext.create('Ext.panel.Panel', {
        renderTo : 'spInfoManage',
        width : '100%',
        height : '100%',
        collapseMode : 'mini',
        margins : '0 0 0 0',
        autoScroll : false,
        layout : {
            type : 'hbox',
            align : 'stretch'
        },
        items : [ initValueGrid ]
    })

    Ext.define("orderInfo", {
        extend : "Ext.data.Model",
        fields : [ {
            name : "product",
            type : "object"
        }, {
            name : "orderItems_count",
            type : "int"
        }, {
            name : "orderItems_subtotal",
            type : "double"
        } ]
    });

    var orderInfoStore = Ext.create('Ext.data.JsonStore', {
        model :'orderInfo',
        data: [
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5},
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5},
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5}
        ]
        //proxy : {
        //	type : 'ajax',
        //	url : basePath + '/system/initvalue/getList',
        //	reader : {
        //		type : 'json',
        //		root : 'returnData'
        //	}
        //},
    });
    var orderInfocolumns = [ new Ext.grid.RowNumberer({
        header : '序号',
        width : "10%",
        align : 'center'
    }), {
        header : "商品图片",
        width : "20%",
        align : 'center',
        dataIndex : 'product.prouduct_photo1',
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
            return '<input id="show" type="image" width="70px" height="70px"  src="'+record.data.product.prouduct_photo1+'"/>'
        }
    },{
        header : "商品名称",
        width : "30%",
        align : 'center',
        dataIndex : 'product.prouduct_name',
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
            return record.data.product.prouduct_name;
        }
    },{
        header : "单价",
        width : "15%",
        align : 'center',
        dataIndex : 'product.prouduct_price',
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
            return record.data.product.prouduct_price;
        }
    },{
        header : "数量",
        width : "10%",
        align : 'center',
        dataIndex : 'orderItems_count',
        sortable : true
    },{
        header : "小计",
        width : "15%",
        align : 'center',
        dataIndex : 'orderItems_subtotal',
        sortable : true
    }];

    var orderInfoGrid = Ext.create('Ext.grid.Panel', {
        width : '100%',
        store : orderInfoStore,
        columnLines : true,
        height : "100%",
        rowLines : true,
        multiSelect : true,
        autoScroll : true,
        viewConfig : {
            forceFit : true,
            enableTextSelection:true,
            stripeRows : true
        },
        columns : orderInfocolumns,
        buttons : [ {
            text : '关闭',
            handler : function() {
                win.hide();
            }
        } ]
    })

    //var initValuePanel = Ext.create('Ext.panel.Panel', {
    //    renderTo : 'spInfoManage',
    //    width : '100%',
    //    height : '100%',
    //    collapseMode : 'mini',
    //    margins : '0 0 0 0',
    //    autoScroll : false,
    //    layout : {
    //        type : 'hbox',
    //        align : 'stretch'
    //    },
    //    items : [ initValueGrid ]
    //})


    var win = new Ext.Window({
        width : 1000,
        height : 600,
        layout : 'auto',
        title:'订单详情',
        plain : true,
        modal : true,
        autoScroll:true,
        maximizable : false,
        draggable : false,
        closable : false,
        resizable : false,
        items : [ orderInfoGrid ]
    });

    function loadForm() {
        Ext.Ajax.request({
            waitMsg : '正在读取商品信息请稍后',
            waitTitle : '提示',
            url : basePath + '/system/initvalue/get/',
            params:{
                id:id
            },
            method : 'POST',
            success : function(response, action) {
                var result = JSON.parse(response.responseText);
                if(result.returnResult==200){
                    Ext.Object.each(result.returnData,
                        function(key, value, myself) {
                            if (form.form.findField(key) != null)
                                form.form.findField(key).setValue(value);
                        });
                    form.form.findField("iosFileData").setRawValue(result.returnData.iosImageUrl);
                    form.form.findField("androidFileData").setRawValue(result.returnData.androidImageUrl);
                    win.showImages.show_iosImageUrl.setSrc(result.returnData.iosImageUrl);
                    win.showImages.show_androidImageUrl.setSrc(result.returnData.androidImageUrl);
                }else
                    Ext.Msg.alert('提示', result.returnDetail);
            },
            failure : function(form, action) {
                Ext.Msg.alert('提示', '商品信息加载失败' + form.status);
            }
        });
    }
});

