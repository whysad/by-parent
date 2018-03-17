<div>
	 <form id="ff" method="post" >
	    <div>
	        <label for="name">名称:</label>
	        <input class="easyui-validatebox" type="text" id="name" name="name" required="true"></input>
	    </div>
	    <div>
	        <label for="category">分类:</label>
	        <input class="easyui-combobox" name="category" required="true" data-options="url:'/categories',
			 method:'get',
			 valueField:'id',
			 textField:'text'"></input>
	    </div>
	</form>
</div>
<script>
$(function(){
	$("#name").blur(
	    function()
	    {	   		
			$.ajax(
	    			{
	    				
	    		  url: "/validateName",
	    		  
	    		  cache: false,
	    		  
	    		  type: "GET",
	    		  
	    		  dataType:"json",
	    		  
	    		  async: true,
	    		  
         		  data: {name:$("#name").val()},
         		  
	    		  success: function(msg){ 	     
	    		     if (msg==true)
	    		     {
	    		     	$("#name").focus();
	    		      	$("#name").tooltip({
        					position: 'right',
       						content: '<span style="color:#fff">该资产名已存在</span>',
        					onShow: function(){
    							$(this).tooltip('tip').css({
    							backgroundColor: 'red',
    							borderColor: 'red'
    							});
        					}
   					 	});
	    		     }
	    		   },
	    		   
	    		  error:function(errordata){
	   				alert("wrong!!"+errordata);
	   			   }
	    		   
	    		}
	    		
	    		);
	
	    }
	    
	    );	    
});
	function saveAsset(){
		$('#ff').form('submit', {
    			url:'/save_asset',
    			onSubmit: function(param){
		    	},
		    	success:function(data){
                	dlgAddAsset.dialog('close');
					$('#dg-assert').datagrid("reload");
        		}
		});
	}
</script>