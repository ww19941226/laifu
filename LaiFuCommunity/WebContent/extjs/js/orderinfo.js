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
    params = {id:2017030936};
    paramStatus = {order_status:0};
    var win;
    
    Ext.define("InitValue", {
        extend : "Ext.data.Model",
        fields : [ {
            name : "id",
            type : "int"
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
        } ]
    });

    var initValueStore = Ext.create('Ext.data.JsonStore', {
        model :'InitValue',
        pageSize:20,
        /*data:[
            {order_creattime:'2017-02-23 10:34:59',order_money:'15.8',order_state:2,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生'},
            {order_creattime:'2017-02-22 10:34:59',order_money:'16.8',order_state:3,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生'},
            {order_creattime:'2017-02-21 10:34:59',order_money:'17.8',order_state:4,order_address:'广东深圳',order_phone:'18318260000',order_receivename:'李先生'}
        ]}*/
        proxy : {
        	type : 'ajax',
        	pageSize:20,
        	actionMethods:{create: 'POST'},
        	url : '/LaiFuCommunity/marketManage/order/getList',
        	extraParams: paramStatus,
        	reader : {
        		type : 'json',
        		root : 'returnData.data',
        		totalProperty: 'returnData.recordCount'
        	}
        },
        autoLoad: true
    });
    var columns = [ new Ext.grid.RowNumberer({
        header : '序号',
        width : "5%",
        align : 'center'
    }), {
        header : "下单时间",
        width : "18%",
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
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
        	if(record.data.order_state == 1){
        		return '未付款';
        	}else if(record.data.order_state == 2){
        		return '未接单';
        	}else if(record.data.order_state == 3){
        		return '已接单';
        	}else if(record.data.order_state == 4){
        		return '已发货';
        	}else if(record.data.order_state == 5){
        		return '交易成功';
        	}
        }
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
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
        	var id = record.raw.id;
        	if(record.data.order_state == 1){
        		return '<a href="javascript:void(0)" onclick="changeState('+id+')">收款</a>';
        	}else if(record.data.order_state == 2){
        		return '<a href="javascript:void(0)" onclick="changeState('+id+')">接单</a>';
        	}else if(record.data.order_state == 3){
        		return '<a href="javascript:void(0)" onclick="changeState('+id+')">发货</a>';
        	}else if(record.data.order_state == 4){
        		return '<a href="javascript:void(0)" onclick="changeState('+id+')">送货员确认已送达</a>';
        	}else if(record.data.order_state == 5){
        		return '交易成功';
        	}
        }
    }];

	changeState = function(id){
    	Ext.Ajax.request({
            waitMsg : '正在进行操作请稍后',
            waitTitle : '提示',
            url : '/LaiFuCommunity/marketManage/order/update',
            params:{
                id:id
            },
            method : 'POST',
            success : function(response, action) {
                var result = JSON.parse(response.responseText);
                if(result.returnResult==200){
                    Ext.Msg.alert('提示', '操作成功');
                    initValueGrid.store.reload();
                }else
                    Ext.Msg.alert('提示', result.returnDetail);
            },
            failure : function(form, action) {
                Ext.Msg.alert('提示', '操作失败' + form.status);
            }
        });
    
    }
    
    //根据订单状态查询数据列表
    //创建数据源[数组数据源]
    var orderStatusStore = new Ext.data.SimpleStore({
        fields: ['order_status', 'status_name'],
        data:[
        	['0','全部'],
            ['1','未付款'],
            ['2','未接单'],
            ['3','已接单'],
            ['4','已发货'],
            ['5','交易完成']
        ],
        autoLoad: true
    });
    //创建Combobox
    var orderStatusBox = new Ext.form.ComboBox({
        fieldLabel: '按状态查询',
        labelAlign : 'right',
        store: orderStatusStore,
        displayField: 'status_name',
        valueField: 'order_status',
        triggerAction: 'all',
        emptyText: '请选择查询条件',
        allowBlank: false,
        blankText: '请选择查询条件',
        editable: false,
        name:"order_status",
        value:'全部',
        mode: 'local',
        listeners : {
            change: function (order_status) {
				paramStatus.order_status = order_status.displayTplData[0].order_status;
				initValueStore.reload();
           	}
        }
    });

    var toolbar = new Ext.Toolbar({
        items : [ "订单信息管理：", "-",orderStatusBox,{
            text : '刷新',
            handler : function() {
                initValueGrid.store.reload();
            }
        } ]
    });



    var initValueGrid = Ext.create('Ext.grid.Panel', {
        width : '100%',
        dockedItems:[{
	        xtype: 'pagingtoolbar',
            emptyMsg: "没有数据",
            displayMsg:"",
	        store: initValueStore,
	        dock: 'bottom',
	        displayInfo: true
	    }],
	    store: initValueStore,
        columnLines : true,
        height : "100%",
        rowLines : true,
        multiSelect : true,
        autoScroll : true,
        tbar : toolbar,
        viewConfig : {
            forceFit : true,
            enableTextSelection:false,
            stripeRows : true
        },
        columns : columns,
        listeners : {
            'itemdblclick' : function(dataview, record, item, index, e) {
            	params.id = record.raw.id;
            	orderInfoGrid.store.reload();
            	win.show();
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
            name : "prouduct_photo1",
            type : "string"
        },{
            name : "prouduct_name",
            type : "string"
        },{
            name : "prouduct_price",
            type : "double"
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
        /*data: [
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5},
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5},
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5},
            {product:{prouduct_photo1:'www.baidu.com',prouduct_name:'饼干18',prouduct_price:7.5},orderItems_count:3,orderItems_subtotal:22.5}
        ]*/
        proxy : {
        	type : 'ajax',
        	actionMethods:{create: 'POST'},
        	url :'/LaiFuCommunity/marketManage/order/get',
        	extraParams: params,
        	reader : {
        		type : 'json',
        		root : 'returnData'
        	}
        }
    });
    var orderInfocolumns = [ new Ext.grid.RowNumberer({
        header : '序号',
        width : "10%",
        align : 'center'
    }), {
        header : "商品图片",
        width : "20%",
        align : 'center',
        dataIndex : 'prouduct_photo1',
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
            return '<input id="show" type="image" width="70px" height="70px"  src="/LaiFuCommunity/'+record.data.prouduct_photo1+'"/>'
        }
    },{
        header : "商品名称",
        width : "30%",
        align : 'center',
        dataIndex : 'prouduct_name',
        sortable : true,
    },{
        header : "单价",
        width : "15%",
        align : 'center',
        dataIndex : 'prouduct_price',
        sortable : true,
        renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
            return record.data.prouduct_price;
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
        store:orderInfoStore,
        columnLines : true,
        height : "100%",
        rowLines : true,
        multiSelect : true,
        autoScroll : true,
        viewConfig : {
            forceFit : true,
            enableTextSelection:false,
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
});

