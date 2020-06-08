<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">
    </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
		onContextMenu: function(e,easyUITree){
            e.preventDefault();
            $(this).tree('select',easyUITree.targets);
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },
        onAfterEdit : function(easyUITree){
        	var _tree = $(this);
        	if(easyUITree.id == 0){
        		// 新增节点
        		$.post("/content/category/create",{parentId:easyUITree.parentId,name:easyUITree.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				targets : easyUITree.targets,
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+easyUITree.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:easyUITree.id,name:easyUITree.text});
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var easyUITree = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (easyUITree?easyUITree.targets:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : easyUITree.id
            }]
        }); 
		var _node = tree.tree('find',0);
		tree.tree("select",_node.targets).tree('beginEdit',_node.targets);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',easyUITree.targets);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+easyUITree.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete/",{parentId:easyUITree.parentId,id:easyUITree.id},function(){
					tree.tree("remove",easyUITree.targets);
				});	
			}
		});
	}
}
</script>