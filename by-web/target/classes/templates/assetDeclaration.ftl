<table id="dg-approval" class="easyui-datagrid" style="height:590px"
    data-options="url:'/declarations',method:'GET',rownumbers:true,pagination:true,fitColumns:true,singleSelect:true">
    <thead>
		<tr>
			<th data-options="field:'create',width:100, formatter:date">申报时间</th>
			<th data-options="field:'asset',width:100,align:'right'">资产名</th>
			<th data-options="field:'category',width:100,align:'right'">分类</th>
			<th data-options="field:'term',width:100,align:'right',formatter:function(value,row,index){
				if(value==null){
					return '-';
				}else{
					return value+'天';
				}
			}">使用期限</th>
			<th data-options="field:'status',width:100,align:'right',formatter: function(value,row,index){
					if(value == 0){
						return '待审批 ';
					}else if(value == 1){
						return '已通过';
					}else if(value == 2){
						return '未通过';
					}
			}">状态</th>
		</tr>
    </thead>
</table>