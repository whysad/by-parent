<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>资产管理系统</title>
  <!-- 导入主题样式文件 -->
  <link rel="stylesheet" href="/easyui/themes/gray/easyui.css">
  <!-- 图标 -->
  <link rel="stylesheet" href="/easyui/themes/icon.css">
  <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
  <!-- 项目公共样式 -->
  <link rel="stylesheet" href="/css/app.css">
  <!-- 第一脚本：jquery，必须是在第一位 -->
  <script src="/easyui/jquery.min.js" charset="utf-8"></script>
  <!-- easyui的核心 -->
  <script src="/easyui/jquery.easyui.min.js" charset="utf-8"></script>
  <!-- Easyui的扩展 -->
  <script src="/easyui/jquery.edatagrid.js" charset="utf-8"></script>
  <!-- Easyui的国际化 -->
  <script src="/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
  <!-- Easyui的bug修复包 -->
  <script src="/easyui/fixed.js" charset="utf-8"></script>
  <script src="/js/lib/xss.js" charset="utf-8"></script>
  <style type="text/css">
    	div{
    		opacity:0.95;
			filter:alpha(opacity=95);
    	}
   </style>
</head>
<body class="easyui-layout" style="background: url(../images/index.png) no-repeat; background-size: cover;   
-webkit-background-size: cover;   
-moz-background-size: cover;   
-o-background-size: cover;">
<div data-options="region:'north'" style="height: 70px;overflow: hidden;padding: 0 10px;">
  <div class="user-info">
    <span class="item" id="public_change_info"><i class="fa fa-user"></i> ${name}</span>
    <a class="item" href="/logout"><i class="fa fa-sign-out"></i> 注销</a>
  </div>
  <h1>资产管理系统</h1>
</div>
<div title="菜单" data-options="region:'west',iconCls:'fa fa-list'" style="width: 200px">
  <div class="easyui-accordion" data-options="fit:true,border:false">
		<ul class="crm-menu">
			<@shiro.hasRole name="admin">  
				<li onClick="openTabs()">用户管理</li>
			</@shiro.hasRole>
			<@shiro.hasRole name="manager"> 
				<li onClick="openTabs('员工列表','/employee_list')">员工管理</li> 
			</@shiro.hasRole>  
			<@shiro.hasRole name="employee"> 
				<div title="资产管理" data-options="iconCls:'fa fa-cogs'">
					<ul class="crm-menu">
						<li onClick="openTabs('资产列表','/asset_list')">资产列表</li>
						<@shiro.hasPermission name="asset:declaration:approved">
							<li onClick="openTabs('资产审批','/asset_approval')">资产审批</li>
						</@shiro.hasPermission>
						<@shiro.lacksPermission name="asset:declaration:approved"> 
							<li onClick="openTabs('资产申报记录','/asset_declaration')">资产申报记录</li>
						</@shiro.lacksPermission>
						<li onClick="openTabs('我的资产','/my_asset')">我的资产</li>
					</ul>	
				</div> 
			</@shiro.hasRole>  
		</ul>     
  </div>
</div>
<div id="myTabs" class="easyui-tabs" data-options="region:'center'">
	<div title="主页" data-options="href:'/desktop',closable:true" style="padding:20px;display:none;">
		
    </div>
</div>
<script>
  function openTabs(title,href){
  		var tab = $('#myTabs').tabs('getTab',title);
  		if(!tab){
	  		$('#myTabs').tabs('add',{
				title: title,
				href:href,
				iconCls:'icon-reload',
				closable:true
			});
		}else{
			$('#myTabs').tabs('select',title);
			tab.panel('refresh', href);
		}
  	}
  	function date(value) {
  			if(value==null){
  				return "-";
  			}
            var date = new Date(value);//long转换成date
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            return year + "-" + month + "-" + day;
        }
    function formatNull(value){
    	if(!value){
    		return "-";
    	}else{
    		return value;
    	}
    }
 </script>
</body>
</html>