<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>竞标人员页面</title>
<!--easyui支持引入  -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
/*********初始化开始***********/
$(function(){
	$('#detail').window('close');  // 详情窗口关闭 
	$('#btupdate').hide();//隐藏修改按钮
	$('#bbemp2').hide();
	//获取后台传递的页面初始化数据
	$.getJSON("doinit_emp.do",function(map){
		var lsct=map.lsct;
		var lspr=map.lspr;
		//处理复选框
		for(var i=0;i<lspr.length;i++){
			var pt=lspr[i];
			$("#pp").append("<input type='checkbox' id='pids' name='pids' value='"+pt.pid+"'/>"+pt.pname)
		}
		//处理公司下拉列表
		$('#cc').combobox({    
		    data:lsct,    
		    valueField:'cid',    
		    textField:'cname',
		    value:1,
		    panelHeight:90
		});  
	});
});
/*********初始化结束***********/
/*********公司添加begin**************/
 $(function(){
		$("#btsavecom").click(function(){
			$('#bbemp2').show();
			$.messager.progress();	// 显示进度条
			$('#bbemp2').form('submit', {
				url:'saveCom_emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功!!!');
						$('#bbemp2').hide();
						window.open("emp.jsp");
					}else{
						$.messager.alert('提示','保存失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});
		});
	});
/*********公司添加end**************/
/*********公司修改begin**************/
 $(function(){
		$("#btupdatecom").click(function(){
			$('#bbemp2').show();
			$.messager.progress();	// 显示进度条
			$('#bbemp2').form('submit', {
				url:'updateCom_emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','修改成功!!!');
						$('#bbemp2').hide();
					}else{
						$.messager.alert('提示','修改失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});
		});
	});
/*********公司修改end**************/
/*********添加begin**************/
 $(function(){
		$("#btsave").click(function(){
			$('#emp1').show();
			$('#bbemp1').hide();
			$.messager.progress();	// 显示进度条
			$('#bbemp').form('submit', {
				url:'save_emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功!!!');
					}else{
						$.messager.alert('提示','保存失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});
		});
	});
/*********添加end**************/
/********员工分页列表begin***********/
$(function(){
	$('#ee').datagrid({ 
	    url:'findPageAll_emp.do',
	    pagination:true,  //显示分页工具栏
	    pageNumber:1,
	    pageSize:5,
	    pageList:[5,10,15,20],
	    striped:true,  //斑马线效果
	    columns:[[    
	        {field:'eid',title:'编号',width:100,align:'center'},
	        {field:'ename',title:'姓名',width:100,align:'center'},
	        {field:'sex',title:'性别',width:100,align:'center'},
	        {field:'address',title:'地址',width:100,align:'center'},
	        {field:'sdate',title:'生日',width:100,align:'center'},
	        {field:'photo',title:'照片',width:100,align:'center',
	        	formatter: function(value,row,index){
	        		return '<img src=uppic/'+row.photo+'  width=90 height=70>'
	        	}	
	        },
	        {field:'cname',title:'公司名称',width:100,align:'center'},
	        {field:'opt',title:'操作',width:100,align:'center',
	        	formatter: function(value,row,index){
	        		var bt1="<input type='button' onclick=delById("+row.eid+") value='删除'/>"
	        		var bt2="<input type='button' onclick=findById("+row.eid+") value='编辑'/>"
	        		var bt3="<input type='button' onclick=findById1("+row.eid+") value='详情'/>"
	        		return bt1+bt2+bt3;
	        	}		
	         }
	    ]]    
	});  
}); 
/********员工分页列表end***********/
/************删除开始*****************/
 function delById(eid){
	 $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	$.get("delById_emp.do?eid="+eid,function(code){
		    		if(code=='1'){
						$.messager.alert('提示','删除成功!!!');
						$('#ee').datagrid('reload');    // 重新载入当前页面数据  
					}else{
						$.messager.alert('提示','删除失败!!!');
					}

		    	});  
		    }    
		});  
}
 /************删除结束*****************/
/***************查询单个(编辑)开始*****************/
 function findById(eid){
	 $.getJSON("findById_emp.do?eid="+eid,function(emp){
		 $('#btsave').hide();//隐藏保存按钮
		 $('#btupdate').show();//显示修改按钮
			//先删除竞标项目中的所有选中
			$(":checkbox[name='pids']").each(function(){
				//绑定属性，选中的改为未选中
				$(this).prop("checked",false);
			});
			//将返回值写入表单
			$('#bbemp').form('load',{
				'eid':emp.eid,
				'ename':emp.ename,
				'sex':emp.sex,
				'address':emp.address,
				'sdate':emp.sdate,
				'cid':emp.cid,
				'prmoney':emp.prmoney,
			});
            //处理图片
            $("#myphoto").attr('src','uppic/'+emp.photo)
            //处理复选框
            var pids=emp.pids;
            $(":checkbox[name='pids']").each(function(){
				for(var i=0;i<pids.length;i++){
					//前台拿到的pids的值跟后台拿到的pids的值比较
					if($(this).val()==pids[i]){
						//一致的话复选框选中
						$(this).prop("checked",true);	
					}
				}
			});
		});
	}
 /***************查询单个(编辑)结束*****************/
/*************修改开始**************/
$(function(){
	$("#btupdate").click(function(){
		$.messager.progress();	// 显示进度条
		$('#bbemp').form('submit', {
			url:'update_emp.do',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			//回调函数
			success: function(code){
				if(code=='1'){
					$.messager.alert('提示','修改成功!!!');
					$('#ee').datagrid('reload');    // 重新载入当前页面数据  
				}else{
					$.messager.alert('提示','修改失败!!!');
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});
	});
});
/*************修改结束**************/
/**************详情开始*****************/
function findById1(eid){
		 $.getJSON("findById_emp.do?eid="+eid,function(emp){
			 //赋值
			 $("#ename1").html(emp.ename);
			 $("#sex1").html(emp.sex);
			 $("#address1").html(emp.address);
			 $("#sdate1").html(emp.sdate);
			 $("#photo1").html(emp.photo);
			 $("#company").html(emp.cname);
			 $("#prmoney1").html(emp.prmoney);
			 $("#eid1").html(emp.eid);
			 //获取项目集合
			 var lspr=emp.lspr;
			 var ptnames=[];//项目名称数组
			 for(var i=0;i<lspr.length;i++){
				 var pt=lspr[i];
				 ptnames.push(pt.pname);
			 }
			 var prtname=ptnames.join(',');
			 $("#pp1").html(prtname); 
			 $("#myphoto1").attr('src','uppic/'+emp.photo); 
			 $('#detail').window('open');  // 打开窗口
	 });	
}
/**********切换界面**********/
$(function(){
	$("#btt").click(function(){
		$('#emp1').hide();
		$('#bbemp1').show();
		});
	});
 /**********切换界面**********/
</script>
</head>
<body>
<div id="bbemp1">
<p align="center">竞标人员信息</p>
<form action="" name="bbemp" id="bbemp" method="post" enctype="multipart/form-data">
  <table border="1px" width="600px" align="center">
    <tr bgcolor="green" align="center">
     <td colspan="3">竞标人员管理</td>
    </tr>
     <tr>
     <td>姓名</td>
     <td>                                                                   <!-- 必填项 -->
     <input type="text" id="ename"  name="ename" class="easyui-validatebox" data-options="required:true">
     </td>
     <td rowspan="7">
      <a href="uppic/default.jpg">
      <img alt="图片不存在" src="uppic/default.jpg" id="myphoto" width="260px" height="260px">
      </a>
     </td>
    </tr>
     <tr>
     <td>性别</td>
     <td>
      <input type="radio" id="sex" name="sex" value="男" checked="checked">男
      <input type="radio" id="sex" name="sex" value="女" >女
     </td>
    </tr>
     <tr>
     <td>地址</td>
     <td>
     <input type="text" id="address" name="address">
     </td>
    </tr>
     <tr>
     <td>生日</td>
     <td>
     <input type="date" id="sdate" name="sdate">
     </td>
    </tr>
     <tr>
     <td>照片</td>
     <td>
     <input type="file" id="pic" name="pic">
     </td>
    </tr>
     <tr>
     <td>公司</td>
     <td>
     <input type="text" id="cc" name="cid">
     </td>
    </tr>
     <tr>
     <td>竞拍价</td>
     <td>
     <input type="text" id="prmoney" name="prmoney" value="20000">
     </td>
    </tr>
     <tr>
     <td>竞拍项目</td>
     <td colspan="2">
     <span id="pp"></span>
     </td>
    </tr>
    <tr bgcolor="green" align="center">
     <td colspan="3">
     <input type="hidden" id="eid" name="eid">
     <input type="button" id="btsave"  name="btsave" value="保存">
     <input type="button" id="btupdate" name="btupdate" value="修改">
     <input type="reset" id="btrest" name="btrest" value="取消">
     <input type="button" id="btsavecom"  name="btsavecom" value="新增">
     <input type="button" id="btupdatecom"  name="btupdatecom" value="修改">
     </td>
    </tr>
  </table>
</form>
</div>
<!--详情窗口  -->
<div id="detail" class="easyui-window" title="竞标人员详情" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
       <table border="1px" width="550px" align="center">
     <tr>
     <td width="100px">姓名</td>
     <td width="200px">
     <span id="ename1"></span>
     </td>
     <td rowspan="7">
      <img id="myphoto1" alt="图片不存在" src="uppic/default.jpg" width="240px" height="260px">
     </td>
    </tr>
     <tr>
     <td>性别</td>
     <td>
      <span id="sex1"></span>
     </td>
    </tr>
     <tr>
     <td>地址</td>
     <td>
     <span id="address1"></span>
     </td>
    </tr>
     <tr>
     <td>生日</td>
     <td>
     <span id="sdate1"></span>
     </td>
    </tr>
     <tr>
     <td>照片</td>
     <td>
     <span id="photo1"></span>
     </td>
    </tr>
     <tr>
     <td>公司</td>
     <td>
     <span id="company"></span>
     </td>
    </tr>
     <tr>
     <td>竞拍价</td>
     <td>
     <span id="prmoney1"></span>
     </td>
    </tr>
     <tr>
     <td>竞拍项目</td>
     <td colspan="2">
     <span id="pp1"></span>
     </td>
    </tr>
    <tr >
     <td>编号</td>
     <td colspan="2">
     <span id="eid1"></span>
     </td>
    </tr>
  </table>
</div> 
<form action="" name="bbemp2" id="bbemp2" method="post" >
  <table border="1px" width="460px" align="center" height="40px">
    <tr bgcolor="green" align="center">
     <td colspan="2" bgcolor="green">公司管理</td>
    </tr>
     <tr>
     <td width="100px">公司名称</td>
     <td>
     <input type="text" id="cname"  name="cname" class="easyui-validatebox" data-options="required:true">
     </td>
     </tr>
  </table>
</form> 
<div id="emp1">
<hr>
<p align="center">竞标人员列表</p>
<input type="button" id="btt"  name="btt" value="返回添加">
<form action="" name="ee" id="ee" method="post" >

</form> 
</div>
</body>
</html>