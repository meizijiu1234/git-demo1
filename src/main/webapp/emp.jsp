<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理页面</title>
<!-- 引入EasyUi -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">   
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>   
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//页面加载触发函数
$(function() {	
	$('#win').window('close');  // close a window 
	$.getJSON('doinit_Emp.do',function(map){
		var lsdep=map.lsdep;
		var lswf=map.lswf;
		//正常方法拼接福利复选框
		for (var i = 0; i < lswf.length; i++) {
			var wf=lswf[i];
			$("#wf").append("<input type='checkbox' id='weids' name='weids' value='"+wf.wid+"'>"+wf.wname);	
		}
		//EasyUi生成下拉列表
		$('#depid').combobox({    
			data:lsdep,    
		    valueField:'did',    
		    textField:'dname',
		    value:1, 
		    panelHeight:150
		});
	}); 
});
/********分页查询begin********/
$(function() {
	$('#dg').datagrid({    
	    url:'findEmpViewAll_Emp.do', 
	    pagination:'true',//底部显示分页工具栏。
	    pageSize:'5',
	    pageList:[1,2,3,4,5],
	    sortName:'eid',
	    sortOrder:'asc',	  	   
	    singleSelect:true,//只允许选择一行。    
	    striped:true,//是否显示斑马线效果。 
	    columns:[[    
	        {field:'eid',title:'员工编号',align:'center',width:100},    
	        {field:'ename',title:'员工姓名',align:'center',width:100},    
	        {field:'sex',title:'员工性别',align:'center',width:100},    
	        {field:'sdate',title:'员工生日',align:'center',width:100},    
	        {field:'address',title:'员工地址',align:'center',width:100},    
	        {field:'picture',title:'员工照片',align:'center',width:100,
	        	formatter: function(value,row,index){
	        		return "<img alt='图片未找到' src='img/"+row.picture+"' width='70px' height='60px'>"
	        	}
	        },    
	        {field:'dname',title:'员工部门',align:'center',width:100},      
	        {field:'opt',title:'操作',align:'center',width:150,
	        	/* 单元格formatter(格式化器)函数，带3个参数：
				value：字段值。
				rowData：行记录数据。
				rowIndex: 行索引。  */
	        	formatter: function(value,row,index){					
	        		var bt1="<input type='button' value='修改' onclick=doEditById("+row.eid+")>";
	        		var bt2="<input type='button' value='删除' onclick=doDelById("+row.eid+")>";
	        		var bt3="<input type='button' value='详情' onclick=doFindById("+row.eid+")>";
	        		return bt1+bt2+bt3;
				}
	        }   	       				
	    ]]   
	});   
}); 

/********分页查询end********/

/********按钮的显示与隐藏begin********/
//添加与展示按钮功能放出修改表单
$(function() {
	$("#isave").click(function() {
		$("#suform").attr("style","display:block"); //展示添加页面
		$("#ishow").attr("style","display:block");//展示ishow按钮
		$("#dgdiv").attr("style","display:none"); //隐藏数据表单
			
	});
	$("#ishow").click(function() {
		var button = document.getElementById("ishow");
		button.style.display = "block";//初始隐藏展示按钮 ，添加表单默认隐藏 
		$("#ishow").attr("style","display:none");
		$("#suform").attr("style","display:none"); //隐藏添加页面
		$("#isave").attr("style","display:block");//展示isave按钮
		var table = document.getElementById("dgdiv");
		table.style.display = "block";//展示数据表单
		table.style.width = "875px";
		table.style.margin = " 0 auto";
	});
});

/********按钮的显示与隐藏end********/

/********编辑与删除按钮begin********/
function doEditById(eid){
	/**为了保证编辑时添加表单中有值导致添加的数据有误的问题
	1.在添加完成时清空表单数据残留（//已处理）
	2.在触发编辑button按钮时首先清空表单数据残留
		（1）直接清空表单所有数据（//已处理）
		（2）只处理业务上可能导致数据错误的内容**/
	//$('#suform').form('reset');		//清空添加表单数据残留
	$.getJSON('findByEid_Emp.do?eid='+eid,function(emp){
		//删除复选框选中
		$(":checkbox[name='weids']").each(function(){
			$(this).prop("checked",false);//设置默认属性
		});
		//表单赋值
		$('#suform').form('load',{
			'eid':emp.eid,
			'ename':emp.ename,
			'sex':emp.sex,
			'sdate':emp.sdate,
			'address':emp.address,			
			'depid':emp.depid,
			'emoney':emp.emoney
		});
		$("#img1").attr('src','img/'+emp.picture);
		//复选框循环写入
		var weids=emp.weids;
		$(":checkbox[name='weids']").each(function(){
			//循环遍历weids中的每一个值，与表单控件中的复选框属性做对比，如果相同，复选框默认选中
			for(var i=0 ; i<weids.length;i++){
				if($(this).val()==weids[i]){
					$(this).prop("checked",true);//设置默认属性	
				}
			}			
		});
	}); 
	$("#btedit").click(function() {
		$.messager.progress();	// 显示进度条
		$('#suform').form('submit', {
			url:'update_Emp.do',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(code){
				if (code==1) {
					$.messager.alert('提示','修改成功');  
					$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					$('#suform').form('reset');		//清空添加表单数据残留
				} else {
					$.messager.alert('提示','修改失败(薪资不能低于最低标准)');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});
	}); 
}
function doDelById(eid){  
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.getJSON('delByEid_Emp.do?eid='+eid,function(code){
	    		if (code==1) {
					$.messager.alert('提示','删除成功');  
					$('#dg').datagrid('reload');    // 重新载入当前页面数据  
				} else {
					$.messager.alert('提示','删除失败');    
				}
	    	}); 
	    }    
	}); 
}
function doFindById(eid){
	$.getJSON('findByEid_Emp.do?eid='+eid,function(emp){
		$("#eidText").html(emp.eid);
		$("#enameText").html(emp.ename);
		$("#sexText").html(emp.sex);
		$("#sdateText").html(emp.sdate);
		$("#addressText").html(emp.address);
		$("#dnameText").html(emp.dname);
		$("#emoneyText").html(emp.emoney);
		var wfList=emp.wfList;
		var wnames=[];
		for (var i = 0; i < wfList.length; i++) {
			var wf=wfList[i];
			wnames.push(wf.wname);
		}
		$("#nn").attr('src','img/'+emp.picture);
		var wfname=wnames.join(",");//将wnames数组中每一个值的转换成用逗号隔开的字符串
		$("#wfText").html(wfname);
		$('#win').window('open');  // open a window 		
	}); 
}
/********编辑与删除按钮end********/

/********(添加)保存与修改begin********/
$(function() {		
	$("#btok").click(function() {
		$.messager.progress();	// 显示进度条
		$('#suform').form('submit', {
			url:'save_Emp.do',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(code){
				if (code==1) {
					$.messager.alert('提示','添加成功');  
					$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					//$('#suform').form('reset');		//清空添加表单数据残留
				} else {
					$.messager.alert('提示','添加失败');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});
	}); 
});
/********保存与修改end********/
</script>
</head>
<body>
<p align="center">XXX有限责任公司员工管理系统</p>
<hr>
<!-- 分页查询列表 -->
<div id="dgdiv" style="width: 875px;margin: 0 auto;" ><!-- 将使 div元素内的内容在水平方向上居中，同时保持垂直方向上的默认对齐方式。 -->
	<table id="dg"></table>
	<p align="center">
		<input type="button" id="isave" name="isave" value="添加员工信息"/>
	</p>
</div>
<!-- 信息添加表单 -->
<form id="suform" name="suform" action="" method="post" enctype="multipart/form-data" style="display:none;">
 <table border="1px" width="450px" align="center">
  <tr align="center" bgcolor="#CCCCCCC">
     <td colspan="3">管理员工信息</td>
  </tr>
  <tr>
     <td align="center" width="70px">姓名</td>
     <td>  
       <input type="text" id="ename" name="ename">
     </td>
     <td rowspan="6">
     <a id="a2" href=""><img id="img1" alt="图片未找到" src="img/default.jpg" width="110px" height="110px"></a>
     </td>
  </tr>
  <tr>
     <td align="center">性别</td>
     <td>
     <input type="radio" id="sex" name="sex" value="男" checked="checked">男
     <input type="radio" id="sex1" name="sex" value="女">女
     </td>
  </tr>
  <tr>
     <td align="center">生日</td>
     <td>
      <input type="date" id="sdate" name="sdate" value="2004-01-01">
     </td>
  </tr>
  <tr>
     <td align="center">地址</td>
     <td>
      <input type="text" id="address" name="address">
     </td>
  </tr>
   <tr>
     <td align="center">照片</td>
     <td>
      <input type="file" id="pic" name="pic" >
     </td>
  </tr>  
  <tr>
     <td align="center">部门</td>
     <td>
 		<input type="text" id="depid" name="depid">
     </td>
  </tr>
  <tr>
     <td align="center">薪资</td>
     <td>
		<input type="text" id="emoney" name="emoney" value="2500">
     </td>
  </tr>
  <tr>
     <td align="center">福利</td>
     <td colspan="2">
     <span id="wf"></span>
     </td>
  </tr>  
  <tr align="center" bgcolor="#CCCCCC">
     <td colspan="3">
     	  <input type="hidden" id="eid" name="eid" >
          <input type="button" id="btok" name="btok" value="提交">
          <input type="button" id="btedit" name="btedit" value="编辑">
          <input type="button" id="btrest" name="btrest" value="重置">
      </td>
   </tr>
  </table>
</form>
<p align="center">
	<input type="button" id="ishow" name="ishow" value="展示员工信息" style="display:none;"/>
</p>
<!-- 详情展示弹窗 -->
<div id="win" class="easyui-window" title="详情展示页" style="width:370px;height:250px"   
        data-options="iconCls:'icon-save',modal:true">   
     <table border="1px" width="350px" align="center">
  <tr align="center" bgcolor="#CCCCCCC">
     <td colspan="3">详情展示页</td>
  </tr>
    <tr>
     <td align="center" width="70px">编号</td>
     <td>
     <span id="eidText"></span>
     </td>
      <td rowspan="7" width="131px">
     <a id="a1" href=""><img id="winimg" alt="图片未找到" src="img/default.jpg" width="140px" height="145px"></a>
     </td>
  </tr> 
  <tr>
     <td align="center">姓名</td>
     <td>  
      <span id="enameText"></span>
     </td>   
  </tr>
  <tr>
     <td align="center">性别</td>
     <td>
      <span id="sexText"></span>
     </td>
  </tr>
  <tr>
     <td align="center">生日</td>
     <td>
     <span id="sdateText"></span>
     </td>
  </tr>
  <tr>
     <td align="center">地址</td>
     <td>
    <span id="addressText"></span>
     </td>
  </tr> 
  <tr>
     <td align="center">部门</td>
     <td>
 		<span id="dnameText"></span>
     </td>
  </tr>
  <tr>
     <td align="center">薪资</td>
     <td>
		<span id="emoneyText"></span>
     </td>
  </tr>
  <tr>
     <td align="center">福利</td>
     <td colspan="2">
     <span id="wfText"></span>
     </td>
  </tr>
  </table>   
</div> 
</body>
</html>