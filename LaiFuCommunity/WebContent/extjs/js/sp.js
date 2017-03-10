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
            name : "prouduct_creattime",
            type : "string"
        }, {
            name : "prouduct_name",
            type : "string"
        }, {
            name : "prouduct_price",
            type : "string"
        }, {
            name : "number",
            type : "string"
        }, {
            name : "product_place",
            type : "string"
        }, {
            name : "is_imported",
            type : "string"
        }, {
            name : "prouduct_discount",
            type : "double"
        }, {
            name : "prouduct_deal",
            type : "int"
        }, {
            name : "product_category_name",
            type : "string"
        }, {
            name : "product_categorySecond_name",
            type : "string"
        } ]
    });

    var initValueStore = Ext.create('Ext.data.JsonStore', {
        model :'InitValue',
        pageSize:20,
        /*data: [
            {prouduct_creattime:'2017-02-23 10:34:59',prouduct_name:'奥利奥饼干1',prouduct_price:'15.8',number:'200',product_placeId:2,is_imported:1,prouduct_discount:10,prouduct_deal:25,product_categoryId:2},
            {prouduct_creattime:'2017-02-22 10:34:59',prouduct_name:'奥利奥饼干2',prouduct_price:'16.8',number:'100',product_placeId:2,is_imported:1,prouduct_discount:10,prouduct_deal:25,product_categoryId:2},
            {prouduct_creattime:'2017-02-21 10:34:59',prouduct_name:'奥利奥饼干3',prouduct_price:'17.8',number:'300',product_placeId:2,is_imported:1,prouduct_discount:10,prouduct_deal:25,product_categoryId:2}
        ]*/
        proxy : {
        	type : 'ajax',
        	pageSize:20,
        	actionMethods:{create: 'POST'},
        	url : '/LaiFuCommunity/marketManage/product/getList',
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
        header : "录入时间",
        width : "10%",
        align : 'center',
        dataIndex : 'prouduct_creattime',
        sortable : true
    },{
        header : "商品名称",
        width : "23%",
        align : 'center',
        dataIndex : 'prouduct_name',
        sortable : true
    },{
        header : "商品单价",
        width : "10%",
        align : 'center',
        dataIndex : 'prouduct_price',
        sortable : true
    },{
        header : "商品货存",
        width : "10%",
        align : 'center',
        dataIndex : 'number',
        sortable : true
    },{
        header : "产地",
        width : "10%",
        align : 'center',
        dataIndex : 'product_place',
        sortable : true
    },{
        header : "是否进口",
        width : "5%",
        align : 'center',
        dataIndex : 'is_imported',
        sortable : true
    },{
        header : "打折数",
        width : "5%",
        align : 'center',
        dataIndex : 'prouduct_discount',
        sortable : true
    },{
        header : "成交量",
        width : "10%",
        align : 'center',
        dataIndex : 'prouduct_deal',
        sortable : true
    },{
        header : "一级分类",
        width : "10%",
        align : 'center',
        dataIndex : 'product_category_name',
        sortable : true
    },{
        header : "二级分类",
        width : "10%",
        align : 'center',
        dataIndex : 'product_categorySecond_name',
        sortable : true
    }];

    var toolbar = new Ext.Toolbar({
        items : [ "商品信息管理：", "-", {
            text : '添加商品信息',
            handler : add
        }, {
            text : '修改商品信息',
            handler : modify
        }, {
            text : '删除商品信息',
            handler : remove
        }, {
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
                form.form.reset();
                form.option = 'update';
                win.setTitle("修改商品信息");
                win.show();
                id = record.raw.id;
                loadForm();
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

    //产地
    //创建数据源[数组数据源]
    var combostore = new Ext.data.ArrayStore({
        fields: ['categorySecondplace_id', 'categorySecond_name'],
        data: [[1, '欧美'], [2, '泰国'], [3, '港澳台']]
    });
    //创建Combobox
    var combobox = new Ext.form.ComboBox({
        fieldLabel: '产地',
        labelAlign : 'right',
        store: combostore,
        displayField: 'categorySecond_name',
        valueField: 'categorySecondplace_id',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择产地',
        editable: false,
        mode: 'local'
    });

    //一级分类
    //创建数据源[数组数据源]
    var Onecombostore = new Ext.data.ArrayStore({
        fields: ['categorySecondplace_id', 'categorySecond_name'],
        data: [[1, '饼干'], [2, '糖果'], [3, '日常用品']]
    });
    //创建Combobox
    var Onecombobox = new Ext.form.ComboBox({
        fieldLabel: '一级分类',
        labelAlign : 'right',
        store: Onecombostore,
        displayField: 'categorySecond_name',
        valueField: 'categorySecondplace_id',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择一级分类',
        editable: false,
        mode: 'local'
    });

    //二级分类
    //创建数据源[数组数据源]
    var Twocombostore = new Ext.data.ArrayStore({
        fields: ['categorySecondplace_id', 'categorySecond_name'],
        data: [[1, '饼干'], [2, '糖果'], [3, '日常用品']]
    });
    //创建Combobox
    var Twocombobox = new Ext.form.ComboBox({
        fieldLabel: '二级分类',
        labelAlign : 'right',
        store: Twocombostore,
        displayField: 'categorySecond_name',
        valueField: 'categorySecondplace_id',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择二级分类',
        editable: false,
        mode: 'local'
    });


    var form = Ext.create('Ext.form.Panel', {
        xtype : 'form',
        items : [ {
            layout : 'column',
            autoScroll:true,
            border : false,
            defaultType : 'textfield',
            defaults : {
                width : 300,
                labelWidth : 100,
                margin : '20 0 0 0',
                labelAlign : 'right'
            },
            items : [ {
                fieldLabel : '商品名称',
                name : 'prouduct_name',
                emptyText : '请输入商品名称',
                blankText : '请输入商品名称',
                allowBlank : false
            }, {
                fieldLabel : '单价',
                name : 'prouduct_price',
                emptyText : '请输入单价',
                blankText : '请输入单价',
                regex: /^\d+(\.\d{1,2})?$/,
                allowBlank : false
            }, {
                fieldLabel : '货存',
                name : 'number',
                emptyText : '请输入货存',
                blankText : '请输入货存',
                allowBlank : false
            },
                combobox,Onecombobox,Twocombobox,
                {
                    fieldLabel : '打折数',
                    name : 'prouduct_discount',
                    emptyText : '请输入打折数',
                    blankText : '请输入打折数',
                    value:10,
                    allowBlank : false
                },{
                xtype     : 'filefield',
                fieldLabel : '商品图片',
                name : 'prouduct_photo1',
                allowBlank : true,
                listeners : {
                    change: function (btn, v) {

                        //得到选择的图片路径
                        var url = 'file://'
                            + form.form.findField('prouduct_photo1').getValue();
                        var img_reg = /\.([jJ][pP][gG]){1}$|\.([jJ][pP][eE][gG]){1}$|\.([gG][iI][fF]){1}$|\.([pP][nN][gG]){1}$|\.([bB][mM][pP]){1}$/;
                        //是否是规定的图片类型
                        if (img_reg.test(url)) {
                            if (Ext.isIE) {
//	                                coverImage.setSrc(Ext.BLANK_IMAGE_URL);
//	                                coverImage.imgEl.dom.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = document.selection.createRange().text;

                            }// 支持FF
                            else {
                                var file = btn.fileInputEl.dom.files[0];
                                var url = URL.createObjectURL(file);  //通过createObjectURL获取url
//	                    		var url = "D:\\work\\soft\\aaa.png";
//                                win.showImages["show_prouduct_photo1"].setSrc(url);
                                win.showImages["show_Image"].setSrc(url);
                            }
                        }
                    }
                }
            } ]
        } ]

    });


    var win = new Ext.Window({
        width : 370,
        height : 450,
        closeAction : 'hide',
        layout : 'auto',
        plain : true,
        modal : true,
        autoScroll:true,
        maximizable : false,
        draggable : false,
        closable : false,
        resizable : false,
        items : form,
        showImages:{},
        buttons : [ {
            text : '提交',
            formBind : true,
            handler : submitForm
        }, {
            text : '重置',
            handler : function() {
                if (form.option == 'add') {
                    form.form.reset();
                    win.showImages.show_Image.setSrc("");
                } else if (form.option == 'update') {
                    loadForm();
                }

            }
        }, {
            text : '关闭',
            handler : function() {
                win.hide();
            }
        } ]
    });


    var getShowImage=function(obj){
        var imgObj={
            name : 'browseImage',
            fieldLabel : "预览图片",
            xtype : 'image',
            width: 148,
            margin:'10 110 10 110',
//					constrain: true,
//					floating:true,
            style: 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);'
        };

        return Ext.create('Ext.Img',imgObj);
    }

    win.showImages.show_Image=getShowImage({width: 330,height: 220,name:"show_Image"});
    form.items.add(win.showImages.show_Image);




    function add() {
        form.form.reset();
        form.option = 'add';
        win.setTitle("新增商品");

        win.show();
    }

    function modify() {
        var gridList = getGridList();
        var num = gridList.length;
        if (num > 1) {
            Ext.MessageBox.alert("提示", "每次只能修改一条初始化值信息")
        } else if (num == 1) {
            form.form.reset();
            form.option = 'update';
            win.setTitle("修改商品信息");
            win.show();
            id = gridList[0];
            loadForm();
        }
    }

    function submitForm() {
        if (form.option == 'add') {
            form.form.submit({
                clientValidation : true,
                waitMsg : '正在新增商品请稍后',
                waitTitle : '商品信息',
                url : basePath + '/system/initvalue/add',
                params : {
                    'initKey.Id' : initKeyId
                },
                method : 'POST',
                success : function(form, action) {
                    if(action.result.returnResult==200){
                        win.hide(action);
                        Ext.Msg.alert('提示', '新增商品成功');
                        initValueStore.reload();
                    }else
                        Ext.Msg.alert('提示', action.result.returnDetail);

                },
                failure : function(form, action) {
                    Ext.Msg.alert('提示', '新增商品失败:' + action.result.msg);
                }
            });
        } else if (form.option == 'update') {
            form.form.submit({
                clientValidation : true,
                waitMsg : '正在修改商品信息请稍后',
                waitTitle : '商品信息',
                url : basePath + '/system/initvalue/update',
                params : {
                    id : id
                },
                method : 'POST',
                success : function(form, action) {
                    if(action.result.returnResult==200){
                        win.hide();
                        Ext.Msg.alert('提示', '修改商品信息成功');
                        initValueStore.reload();
                    }else
                        Ext.Msg.alert('提示', action.result.returnDetail);

                },
                failure : function(form, action) {
                    Ext.Msg.alert('提示', '修改商品信息失败:' + action.result.msg);
                }
            });
        }
    }

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



    function remove() {
        var gridList = getGridList();
        var num = gridList.length;
        if (num == 0) {
            return;
        }
        Ext.MessageBox.confirm("提示", "您确定要删除所选商品吗？", function(btnId) {
            if (btnId == 'yes') {
                var msgTip = Ext.MessageBox.show({
                    title : '提示',
                    width : 250,
                    msg : '正在删除商品，请稍后......'
                });
                Ext.Ajax.request({
                    url : basePath + '/system/initvalue/remove',
                    params : {
                        ids : gridList
                    },
                    method : 'POST',
                    success : function(response, action) {
                        var result = JSON.parse(response.responseText);
                        if(result.returnResult==200){
                            Ext.Msg.alert('提示', '删除商品成功');
                            initValueStore.reload();
                        }else
                            Ext.Msg.alert('提示', result.returnDetail);
                    },
                    failure : function(response, options) {
                        msgTip.hide();
                        Ext.Msg.alert('提示', '删除商品请求失败！');
                    }
                });
            }
        })
    }

    function getGridList() {
        var recs = initValueGrid.getSelectionModel().getSelection();
        var list = [];
        if (recs.length == 0) {
            Ext.MessageBox.alert('提示', '请选择要进行操作的初始化值！');
        } else {
            for (var i = 0; i < recs.length; i++) {
                var rec = recs[i];
                list.push(rec.get('id'))
            }
        }
        return list;
    }

});

