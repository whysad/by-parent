 <div>
	 <form id="ff" method="post" >
	 	<input type="hidden" name="id" value="${asset.id}" />
	    <div>
	        <label for="name">名称:</label>
	        <input class="easyui-validatebox" type="text" name="name" value="${asset.name}" required="true"></input>
	    </div>
	    <div>
	        <label for="category">分类:</label>
	        <input class="easyui-combobox" name="category" id="category" value="${asset.category}" required="true" data-options="url:'/categories',
			 method:'get',
			 valueField:'id',
			 panelHeight:'auto',
			 textField:'text'"></input>
	    </div>
	</form>
</div>
<script>
	function editAsset(){
		$('#ff').form('submit', {
    			url:'/update_asset',
    			onSubmit: function(param){
		    	},
		    	success:function(data){
                	dlgUpdateAsset.dialog('close');
					$('#dg-assert').datagrid('reload');
        		}
		});
	}
</script>