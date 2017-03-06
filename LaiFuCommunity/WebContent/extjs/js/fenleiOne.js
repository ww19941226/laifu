/**
 * Created by Administrator on 2017/2/24.
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
            name : "category_id",
            type : "long"
        }, {
            name : "category_name",
            type : "string"
        } ]
    });

    var initValueStore = Ext.create('Ext.data.JsonStore', {
        model :'InitValue',
        /*data: [
            {category_id:'1',category_name:'饼干'},
            {category_id:'2',category_name:'日常'},
            {category_id:'3',category_name:'零食'}
        ]*/
        proxy : {
        	type : 'ajax',
        	url : '/LaiFuCommunity/marketManage/categories/getList',
        	reader : {
        		type : 'json',
        		root : 'returnData'
        	}
        },
        autoLoad: true
    });
    var columns = [ new Ext.grid.RowNumberer({
        header : '序号',
        width : "15%",
        align : 'center'
    }), {
        header : "一级分类名称",
        width : "15%",
        align : 'center',
        dataIndex : 'category_name'
    }];

    var toolbar = new Ext.Toolbar({
        items : [ "一级分类管理：", "-", {
            text : '添加一级分类',
            handler : add
        }, {
            text : '修改一级分类',
            handler : modify
        }, {
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
                form.form.reset();
                form.option = 'update';
                win.setTitle("修改一级分类");
                win.show();
                id = record.raw.id;
                loadForm();
            }
        }
    })

    var initValuePanel = Ext.create('Ext.panel.Panel', {
        renderTo : 'fenleiOneManage',
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
                fieldLabel : '一级分类名称',
                name : 'category_name',
                emptyText : '请输入一级分类名称',
                blankText : '请输入一级分类名称',
                allowBlank : false
            } ]
        } ]

    });

    var win = new Ext.Window({
        width : 370,
        height : 300,
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
        buttons : [ {
            text : '提交',
            formBind : true,
            handler : submitForm
        }, {
            text : '重置',
            handler : function() {
                if (form.option == 'add') {
                    form.form.reset();
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



    function add() {
        form.form.reset();
        form.option = 'add';
        win.setTitle("新增一级分类");
        win.show();
    }

    function modify() {
        var gridList = getGridList();
        var num = gridList.length;
        if (num > 1) {
            Ext.MessageBox.alert("提示", "每次只能修改一条一级分类")
        } else if (num == 1) {
            form.form.reset();
            form.option = 'update';
            win.setTitle("修改一级分类");
            win.show();
            id = gridList[0];
            loadForm();
        }
    }

    function submitForm() {
        if (form.option == 'add') {
            form.form.submit({
                clientValidation : true,
                waitMsg : '正在新增一级分类请稍后',
                waitTitle : '一级分类',
                url : '/LaiFuCommunity/marketManage/categories/add',
                method : 'POST',
                success : function(form, action) {
                    if(action.result.returnResult==200){
                        win.hide(action);
                        Ext.Msg.alert('提示', '新增一级分类成功');
                        initValueStore.reload();
                    }else
                        Ext.Msg.alert('提示', action.result.returnDetail);

                },
                failure : function(form, action) {
                    Ext.Msg.alert('提示', '新增一级分类失败:' + action.result.msg);
                }
            });
        } else if (form.option == 'update') {
            form.form.submit({
                clientValidation : true,
                waitMsg : '正在修改一级分类请稍后',
                waitTitle : '一级分类',
                url : basePath + '/system/initvalue/update',
                params : {
                    id : id
                },
                method : 'POST',
                success : function(form, action) {
                    if(action.result.returnResult==200){
                        win.hide();
                        Ext.Msg.alert('提示', '修改一级分类成功');
                        initValueStore.reload();
                    }else
                        Ext.Msg.alert('提示', action.result.returnDetail);

                },
                failure : function(form, action) {
                    Ext.Msg.alert('提示', '修改一级分类失败:' + action.result.msg);
                }
            });
        }
    }

    function loadForm() {
        Ext.Ajax.request({
            waitMsg : '正在读取一级分类数据请稍后',
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
                Ext.Msg.alert('提示', '一级分类数据加载失败' + form.status);
            }
        });
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

