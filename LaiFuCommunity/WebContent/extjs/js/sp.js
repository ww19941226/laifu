/**
 * Created by Administrator on 2017/2/22.
 */
Ext.onReady(function() {
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = "qtip";
    var id = "";
    var win;
    params = {category_id:1};
    Ext.define("InitValue", {
        extend : "Ext.data.Model",
        fields : [ {
            name : "id",
            type : "int"
        }, {
            name : "prouduct_creattime",
            type : "string"
        }, {
            name : "prouduct_name",
            type : "string"
        }, {
            name : "prouduct_price",
            type : "double"
        }, {
            name : "number",
            type : "int"
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
        }, {
            name : "product_photo1",
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
        width : "18%",
        align : 'center',
        dataIndex : 'prouduct_name',
        sortable : true
    },{
        header : "商品单价",
        width : "7%",
        align : 'center',
        dataIndex : 'prouduct_price',
        sortable : true
    },{
        header : "商品货存",
        width : "7%",
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
            	initValueStore.removeAll();
				initValueStore.reload();
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


    //一级分类
    //创建数据源[数组数据源]
    var Onecombostore = new Ext.data.ArrayStore({
        fields: ['category_id', 'category_name'],
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
    //创建Combobox
    var Onecombobox = new Ext.form.ComboBox({
        fieldLabel: '一级分类',
        labelAlign : 'right',
        store: Onecombostore,
        displayField: 'category_name',
        valueField: 'category_id',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择一级分类',
        editable: false,
        name:"category_id",
        mode: 'remote',
        listeners : {
            change: function (category_id) {
            form.form.findField("categorysecond_id").setValue("");
				params.category_id = category_id.value;
				Twocombobox.store.reload();
           	}
        }
    });

    //二级分类
    //创建数据源[数组数据源]
    var Twocombostore = new Ext.data.ArrayStore({
        fields: ['categorysecond_id', 'categorysecond_name'],
        proxy : {
        	type : 'ajax',
        	url : '/LaiFuCommunity/marketManage/categorySecond/getListForSp',
        	extraParams: params,
        	reader : {
        		type : 'json',
        		root : 'returnData'
        	}
        },
        autoLoad: true
    });
    //创建Combobox
    var Twocombobox = new Ext.form.ComboBox({
        fieldLabel: '二级分类',
        labelAlign : 'right',
        store: Twocombostore,
        displayField: 'categorysecond_name',
        valueField: 'categorysecond_id',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择二级分类',
        editable: false,
        name:"categorysecond_id",
        mode: 'remote'
    });
    
    //创建数据源[数组数据源]
    var jinkoucombostore = new Ext.data.SimpleStore({
        fields: ['is_imported', 'is_imported_name'],
        data:[['是','是'],['否','否']],
        autoLoad: true
    });
    //创建Combobox
    var jinkoucombobox = new Ext.form.ComboBox({
        fieldLabel: '是否进口',
        labelAlign : 'right',
        store: jinkoucombostore,
        displayField: 'is_imported_name',
        valueField: 'is_imported',
        triggerAction: 'all',
        emptyText: '请选择...',
        allowBlank: false,
        blankText: '请选择是否进口',
        editable: false,
        name:"is_imported",
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
            }, {
                fieldLabel : '产地',
                name : 'product_place',
                emptyText : '请输入产地',
                blankText : '请输入产地',
                allowBlank : false
            },jinkoucombobox,
                Onecombobox,Twocombobox,
                {
                    fieldLabel : '打折数',
                    name : 'prouduct_discount',
                    emptyText : '请输入打折数',
                    blankText : '请输入打折数',
                    allowBlank : false
                },{
                xtype: 'filefield',
                fieldLabel : '商品图片',
                name : 'file',
                id:'photo',
                allowBlank : true,
                listeners : {
                    change: function (btn, v) {

                        //得到选择的图片路径
                        var url = 'file://'
                            + form.form.findField('file').getValue();
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
//                                win.showImages["show_product_photo"].setSrc(url);
                                win.showImages["show_Image"].setSrc(url);
                                //上传
                                $.ajaxFileUpload({  
							        //处理文件上传操作的服务器端地址  
							        url:"/LaiFuCommunity/marketManage/uploadPicture",  
							        secureuri:false,                           //是否启用安全提交,默认为false   
							        fileElementId:'photo-button-fileInputEl',               		//文件选择框的id属性  
							        dataType:'text',                           //服务器返回的格式,可以是json或xml等  
							        success:function(data, status){ 			//服务器响应成功时的处理函数  
							        	data = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">','');
							        	data = data.replace('</pre>','');
							        	form.form.findField('photo_lujing').setValue(data);
							        },  
							        error:function(data, status, e){ //服务器响应失败时的处理函数  
							             alert("头像更改失败！");  
							        }  
							    });
                            }
                        }
                    }
                }
            },{
            		hidden: true, 
					hideLabel:true, 
                    fieldLabel : '上传图片后生成的图片路径',
                    name : 'photo_lujing',
                    readOnly:true
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
		win.showImages["show_Image"].setSrc("");
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
    var prouduct_name_add = form.form.findField('prouduct_name').getValue();
    var prouduct_price_add = form.form.findField('prouduct_price').getValue();
    var number_add = form.form.findField('number').getValue();
    var product_place_add = form.form.findField('product_place').getValue();
    var is_imported_add = form.form.findField('is_imported').getValue();
    var category_id_add = form.form.findField('category_id').getValue();
    var categorysecond_id_add = form.form.findField('categorysecond_id').getValue();
    var prouduct_discount_add = form.form.findField('prouduct_discount').getValue();
    var photo_lujing_add = form.form.findField('photo_lujing').getValue();
        if (form.option == 'add') {
            $.ajax({  
            	type:'post',
		        //处理文件上传操作的服务器端地址  
		        url:"/LaiFuCommunity/marketManage/product/add",  
		        data:{
                	prouduct_name_add:prouduct_name_add,
                	prouduct_price_add:prouduct_price_add,
                	number_add:number_add,
                	product_place_add:product_place_add,
                	is_imported_add:is_imported_add,
                	category_id_add:category_id_add,
                	categorysecond_id_add:categorysecond_id_add,
                	prouduct_discount_add:prouduct_discount_add,
                	photo_lujing_add
                },
		        success:function(data, status){ 			//服务器响应成功时的处理函数  
			        if(data.returnResult==200){
			        	win.hide();
	                    Ext.Msg.alert('提示', '添加商品信息成功');
	                    initValueStore.reload();
                    }
                    else{
                    	Ext.Msg.alert("添加商品信息失败！");  
                    }
		        },  
		        error:function(data, status, e){ //服务器响应失败时的处理函数  
		             Ext.Msg.alert("添加商品信息失败！");  
		        }  
		    });
        } else if (form.option == 'update') {
        
        	$.ajax({  
            	type:'post',
		        //处理文件上传操作的服务器端地址  
		        url:"/LaiFuCommunity/marketManage/product/update",  
		        data:{
		        	id:id,
                	prouduct_name_add:prouduct_name_add,
                	prouduct_price_add:prouduct_price_add,
                	number_add:number_add,
                	product_place_add:product_place_add,
                	is_imported_add:is_imported_add,
                	category_id_add:category_id_add,
                	categorysecond_id_add:categorysecond_id_add,
                	prouduct_discount_add:prouduct_discount_add,
                	photo_lujing_add
                },
		        success:function(data, status){ 			//服务器响应成功时的处理函数  
			        if(data.returnResult==200){
			        	win.hide();
	                    Ext.Msg.alert('提示', '修改商品信息成功');
	                    initValueStore.reload();
                    }
                    else{
                    	Ext.Msg.alert('提示', "修改商品信息失败！");  
                    }
		        },  
		        error:function(data, status, e){ //服务器响应失败时的处理函数  
		             Ext.Msg.alert('提示', "修改商品信息失败！");  
		        }  
		    });
        }
        $("#photo-button-fileInputEl").remove();
    }

    function loadForm() {
        Ext.Ajax.request({
            waitMsg : '正在读取商品信息请稍后',
            waitTitle : '提示',
            url : '/LaiFuCommunity/marketManage/product/get',
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
                    form.form.findField("category_id").setValue(result.returnData.category.category_id);
                    form.form.findField("categorysecond_id").setValue(result.returnData.categorySecond.categorySecond_id);
                    if(result.returnData.is_imported == 1){
                    	form.form.findField("is_imported").setValue("是");
                    }else{
                    	form.form.findField("is_imported").setValue("否");
                    }
                    form.form.findField("photo").setRawValue(result.returnData.product_photo1);
                    win.showImages.show_Image.setSrc("/LaiFuCommunity"+result.returnData.product_photo1);
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
                    url : '/LaiFuCommunity/marketManage/product/remove',
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

