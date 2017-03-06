Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = "qtip";
	var initKeyId = "";
	var initKey = "";
	var id = "";
	var win;
	Ext.define("InitValue", {
		extend : "Ext.data.Model",
		fields : [ {
			name : "id",
			type : "long"
		}, {
			name : "name",
			type : "string"
		}, {
			name : "content",
			type : "string"
		} ]
	});

	var initValueStore = Ext.create('Ext.data.JsonStore', {
		model : 'InitValue',
		data: [
			{name: 'Ed',    content: 'ed@sencha.com'},
			{name: 'Tommy', content: 'tommy@sencha.com'}
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
	var columns = [ new Ext.grid.RowNumberer({
		header : '序号',
		width : "10%",
		align : 'center'
	}), {
		header : "初始化值名称",
		width : "30%",
		align : 'center',
		dataIndex : 'name',
		sortable : true
	}, {
		header : "初始化值值",
		width : "30%",
		align : 'center',
		dataIndex : 'content',
		sortable : true
	}];

	var toolbar = new Ext.Toolbar({
		items : [ "初始化值栏：", "-", {
			text : '添加初始化值',
			iconCls : 'add',
			handler : add
		}, {
			text : '修改初始化值',
			iconCls : 'update',
			handler : modify
		}, {
			text : '删除初始化值',
			iconCls : 'delete',
			handler : remove
		}, {
			text : '刷新',
			iconCls : 'refresh',
			handler : function() {
				initValueGrid.store.reload();
			}
		} ]
	});

	var imageKeys=["merchant_dp_fl","user_merchant_orderby","merchant_dp_fl_v2","personal_my_button_v2"];
	
	var initValueTree = Ext.create('Ext.tree.Panel', {
		rootVisible : false,
		border : true,
		animate : true,
		autoScroll : true,
		enableDD : false,
		containerScroll : true,
		minSize : 100,
		maxSize : 300,
		width : '20%',
		height : "100%",
		id : 'system_init_initvalue_tree',
		dockedItems : [ {
			xtype : 'toolbar',
			items : [ {
				text : '展开',
				handler : function() {
					initValueTree.expandAll();
				}
			}, {
				text : '收缩',
				handler : function() {
					initValueTree.collapseAll();
				}
			} ]
		} ],
		listeners : {
			"itemclick" : function(view, record, item, index, e) {
				initKeyId = record.raw.id.replace('system_init_initvalue_tree_', '');
				initKey = record.raw.text;
				initKey = initKey.substring(initKey.indexOf('(')+1,initKey.length-1);
				initValueGrid.show();
				initValueStore.load({
					params : {
						initKeyId : initKeyId
					}
				});
				//判断是否是imagekeys里面的值来决定是否显示imageurl
				var boolkey = 0;
				for(var i=0;i<imageKeys.length;i++){
					if(imageKeys[i] == initKey){
						boolkey = 1;
						break;
					}
				}
				if(boolkey == 0){
					initValueGrid.columns[4].hide();
					initValueGrid.columns[5].hide();
					initValueGrid.columns[6].hide();
				}else{
					initValueGrid.columns[4].show();
					initValueGrid.columns[5].show();
					initValueGrid.columns[6].show();
				}
			},
		}
	});

	var initValueGrid = Ext.create('Ext.grid.Panel', {
		width : '80%',
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
				win.setTitle("修改初始化值");
				win.show();
				id = record.raw.id;
				loadForm();
			}
		}
	})

	var initValuePanel = Ext.create('Ext.panel.Panel', {
		renderTo : Ext.getBody(),
		width : '100%',
		height : '100%',
		collapseMode : 'mini',
		margins : '0 0 0 0',
		autoScroll : false,
		layout : {
			type : 'hbox',
			align : 'stretch'
		},
		items : [ initValueTree, initValueGrid ]
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
				fieldLabel : '初始化值名称',
				name : 'name',
				emptyText : '请输入初始化值名称',
				blankText : '请输入初始化值名称',
				allowBlank : false
			}, {
				fieldLabel : '初始化值',
				name : 'datavalue',
				emptyText : '请输入初始化值',
				blankText : '请输入初始化值',
				allowBlank : false
			}, {
				xtype     : 'textareafield',
				fieldLabel : '初始化值描述',
				name : 'content',
				emptyText : '请输入初始化值描述',
				blankText : '请输入初始化值描述',
				allowBlank : false
			},{
				fieldLabel : '目标地址',
				name : 'targetUrl',
				allowBlank : true
			},{
				xtype     : 'filefield',
				fieldLabel : 'iosImageUrl',
				name : 'iosFileData',
				allowBlank : true,
				listeners : {  
	            	change: function (btn, v) {
	            		
	                    //得到选择的图片路径  
	                    var url = 'file://'  
	                            + form.form.findField('iosFileData').getValue();  
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
	                    		win.showImages["show_iosImageUrl"].setSrc(url);
	                        }  
	                    }  
	                }
	    		}
			},{
				xtype     : 'filefield',
				fieldLabel : 'androidImageUrl',
				name : 'androidFileData',
				margin:'20 0 20 0',
				allowBlank : true,
				listeners : {  
	            	change: function (btn, v) {
	            		
	                    //得到选择的图片路径  
	                    var url = 'file://'  
	                            + form.form.findField('androidFileData').getValue();  
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
	                    		win.showImages["show_androidImageUrl"].setSrc(url);
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
					win.showImages.show_iosImageUrl.setSrc("");
					win.showImages.show_androidImageUrl.setSrc("");
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
	
	
//	var noImageWin = new Ext.Window({
//		width : 350,
//		height : 300,
//		closeAction : 'hide',
//		plain : true,
//		modal : true,
//		items : form,
//		buttons : [ {
//			text : '提交',
//			formBind : true,
//			handler : submitForm
//		}, {
//			text : '重置',
//			handler : function() {
//				if (form.option == 'add') {
//					form.form.reset();
//				} else if (form.option == 'update') {
//					loadForm();
//				}
//
//			}
//		}, {
//			text : '关闭',
//			handler : function() {
//				win.hide();
//			}
//		} ]
//	});
	
	


	

	

	function add() {
		form.form.reset();
		form.option = 'add';
		win.setTitle("新增初始化值");
		win.showImages.show_iosImageUrl.setSrc("");
		win.showImages.show_androidImageUrl.setSrc("");
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
			win.setTitle("修改初始化值");
			win.show();
			id = gridList[0];
			loadForm();
		}
	}

	function submitForm() {
		if (form.option == 'add') {
			form.form.submit({
				clientValidation : true,
				waitMsg : '正在新增初始化值请稍后',
				waitTitle : '初始化值',
				url : basePath + '/system/initvalue/add',
				params : {
					'initKey.Id' : initKeyId
				},
				method : 'POST',
				success : function(form, action) {
					if(action.result.returnResult==200){
						win.hide(action);
						Ext.Msg.alert('提示', '新增初始化值成功');
						initValueStore.reload();
					}else
						Ext.Msg.alert('提示', action.result.returnDetail);

				},
				failure : function(form, action) {
					Ext.Msg.alert('提示', '新增初始化值失败:' + action.result.msg);
				}
			});
		} else if (form.option == 'update') {
			form.form.submit({
				clientValidation : true,
				waitMsg : '正在修改初始化值请稍后',
				waitTitle : '初始化值',
				url : basePath + '/system/initvalue/update',
				params : {
					id : id
				},
				method : 'POST',
				success : function(form, action) {
					if(action.result.returnResult==200){
						win.hide();
						Ext.Msg.alert('提示', '修改初始化值成功');
						initValueStore.reload();
					}else
						Ext.Msg.alert('提示', action.result.returnDetail);

				},
				failure : function(form, action) {
					Ext.Msg.alert('提示', '修改初始化值失败:' + action.result.msg);
				}
			});
		}
	}

	function loadForm() {
		Ext.Ajax.request({
			waitMsg : '正在读取初始化值数据请稍后',
			waitTitle : '提示',
			url : basePath + '/system/initvalue/get/',
			params:{
				"initkey.id" : initKeyId,
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
				Ext.Msg.alert('提示', '初始化值数据加载失败' + form.status);
			}
		});
	}

	function findInitKeyTree() {
		Ext.Ajax.request({
			url : basePath + '/system/initkey/getList',
			method : 'POST',
			success : function(response, options) {
				var initValueRoot = Ext.getCmp('system_init_initvalue_tree')
						.getRootNode();
				initValueRoot.removeAll();
				var nodeJson = JSON.parse(response.responseText);
				if (nodeJson.returnResult == 200) {
					var data = nodeJson.returnData;
					for (var i = 0; i < data.length; i++) {
						initValueRoot.appendChild({
							text : data[i].name+"("+data[i].datakey+")",
							id : "system_init_initvalue_tree_" + data[i].id,
							leaf : true
						});
					}
				}
				initValueTree.expandAll();
			},
			failure : function(response, options) {
				Ext.Msg.alert('操作', '读取失败！');
			}
		});
	}

	function remove() {
		var gridList = getGridList();
		var num = gridList.length;
		if (num == 0) {
			return;
		}
		Ext.MessageBox.confirm("提示", "您确定要删除所选初始化值吗？", function(btnId) {
			if (btnId == 'yes') {
				var msgTip = Ext.MessageBox.show({
					title : '提示',
					width : 250,
					msg : '正在删除初始化值，请稍后......'
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
							Ext.Msg.alert('提示', '删除初始化值成功');
							initValueStore.reload();
						}else
							Ext.Msg.alert('提示', result.returnDetail);
					},
					failure : function(response, options) {
						msgTip.hide();
						Ext.Msg.alert('提示', '删除初始化值请求失败！');
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

	findInitKeyTree();
	initValueGrid.hide();
});
