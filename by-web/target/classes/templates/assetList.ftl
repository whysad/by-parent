<table id="dg-assert" class="easyui-datagrid" style="height:590px"
    data-options="url:'/assets',method:'GET',toolbar: '#tb',rownumbers:true,pagination:true,fitColumns:true,singleSelect:true">
    <thead>
		<tr>
			<th data-options="field:'modifyed',width:100, formatter:date">上新时间</th>
			<th data-options="field:'name',width:100,align:'right'">资产名</th>
			<th data-options="field:'category',width:100,align:'right'">分类</th>
			<th data-options="field:'isUse',width:100,align:'right', formatter:function(value){
				if(value){
					return '是';
				}else{
					return '否';
				}
			}">是否使用</th>
			<th data-options="field:'employee',width:100,formatter:formatNull">使用人员</th>
		</tr>
    </thead>
</table>


<div id="tb">
<@shiro.hasRole name="manager"> 
	<a href="#" onclick="addAsset()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
	<a href="#" onclick="updateAsset()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
	<a href="#" onclick="deletedAsset()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</@shiro.hasRole>
<@shiro.lacksPermission name="asset:declaration:approved">   
	<a href="#" onclick="declare()" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">申报</a>
</@shiro.lacksPermission>
<div style="text-align:center;float:right">
<input id="ss" class="easyui-searchbox" style="width:300px" data-options="searcher:doSearch,prompt:'Please Input Name'"></input>
</div>
</div>
<script>
    function doSearch(value){
    	$('#dg-assert').datagrid('load',{
    		name:value
		});
       
    }

	function declare(){
		var row = $('#dg-assert').datagrid("getSelected");
		if(row.isUse == 1){
			$.messager.alert('提示',"请不要想抢人家的东西");
		}else{
			$.messager.prompt('使用时间', '请输入预计使用时间，长期请输入0:', function(r){
				if (r){
					var data = {
						asset : row.id,
						term : r
					}
					$.ajax({ url: "/declare", data:data,type:'post',
						success: function(){
							$.messager.alert('提示',"申报成功，请耐心等待审批");
							$('#dg-assert').datagrid("reload");
			     		 }
		     		 });
				}
			});
		}
	}
	
	var dlgAddAsset;
	function addAsset(){
		dlgAddAsset = $("<div></div>");
		dlgAddAsset.dialog({
		    title: '添加资产',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    href: '/add_asset',
		    modal: true,
		    buttons:[{
				text:'添加',
				handler:function(){
					saveAsset();
				}
			},{
				text:'关闭',
				handler:function(){
					dlgAddAsset.dialog('close');
				}
			}]
		});
	}
	
	var dlgUpdateAsset;
	function updateAsset(){
		var row = $('#dg-assert').datagrid('getSelected');
		if (row == null) {
        	$.messager.alert('警告','请选择要编辑的数据');  
      		return;
    	}else{
    		dlgUpdateAsset = $("<div></div>");
			dlgUpdateAsset.dialog({
		  	  title: '编辑资产',
		 	  width: 400,
		  	  height: 200,
		      closed: false,
		      cache: false,
		      href: '/update_asset?id=' +row.id,
		      modal: true,
		      buttons:[{
				text:'确定',
				handler:function(){
					editAsset();
				}
			    },{
				text:'关闭',
				handler:function(){
					dlgUpdateAsset.dialog('close');
				}
			}]
		  });
    	} 
	}
	
	function deletedAsset(){
		var row = $('#dg-assert').datagrid('getSelected');  
		if(row == null){
        	$.messager.alert('警告','请选择要删除的数据');  
      		return;
    	}else{
    		$.messager.confirm('删除资产', '确定删除该资产吗？', function(r){
				if (r){
					var data = {
						id : row.id,
					}
					$.ajax({ 
						url: "/deleted_Asset",
						data:data,
						type:'post',
						success: function(){
							$.messager.alert('提示','删除成功'); 
							$('#dg-assert').datagrid('reload');
			     		 }
		     		 });
				}
			});
    		
    	} 
	}
</script>