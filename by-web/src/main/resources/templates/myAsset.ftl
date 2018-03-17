<table id="dg-asset" class="easyui-datagrid" style="height:590px"
    data-options="url:'/my_asset_data',method:'GET',rownumbers:true,pagination:true,fitColumns:true,singleSelect:true">
    <thead>
		<tr>
			<th data-options="field:'modifyed',width:100, formatter:date">获得时间</th>
			<th data-options="field:'name',width:100,align:'right'">资产名</th>
			<th data-options="field:'category',width:100,align:'right'">分类</th>
			<th data-options="field:'expireDate',width:100,align:'right',formatter:date">过期时间</th>
		</tr>
    </thead>
</table>