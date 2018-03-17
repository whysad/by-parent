<table id="dg-approval" class="easyui-datagrid" style="height:590px"
    data-options="url:'/declarations',method:'GET',rownumbers:true,pagination:true,fitColumns:true,singleSelect:true">
    <thead>
		<tr>
			<th data-options="field:'create',width:100, formatter:date">申报时间</th>
			<th data-options="field:'employee',width:100">员工名</th>
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
						return '待审批 |  '+approval(row.id);
					}else if(value == 1){
						return '已通过';
					}else if(value == 2){
						return '未通过';
					}
			}">状态</th>
		</tr>
    </thead>
</table>
<script>
	function approval(id){
		return "<a  onclick='approvalButton("+id+",1)'>通过</a>  |  "+"<a  onclick='approvalButton("+id+",2)'>驳回</a>";
	}
	 function approvalButton(id,isApproval){
		 $.post("/update_approval_status",
		  {
		    id:id,
		    status:isApproval
		  },
		  function(data,status){
		    $('#dg-approval').datagrid('reload');
		  });
	}
</script>