<%--
  Created by IntelliJ IDEA.
  User: THINK
  Date: 2018/9/26
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Data-Table 表格</title>
    <link rel="stylesheet" href="../frame/layui/css/layui.css">
    <!--<link rel="stylesheet" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">-->
    <link rel="stylesheet" href="../frame/static/css/style.css">
    <link rel="icon" href="../frame/static/image/code.png">
</head>

<body class="body">

<!-- 工具集 -->
<div class="my-btn-box">
			<span class="fl">
        <a class="layui-btn layui-btn-danger radius btn-delect" id="btn-delete-all">批量删除</a>
        <a class="layui-btn btn-add btn-default" id="btn-add">添加</a>
        <a class="layui-btn btn-add btn-default" id="btn-refresh"><i class="layui-icon">&#x1002;</i></a>
    </span>
    <span class="fr">
        <span class="layui-form-label">搜索条件：</span>
			<div class="layui-input-inline">
				<input type="text" autocomplete="off" placeholder="请输入搜索条件" class="layui-input">
			</div>
			<button class="layui-btn mgl-20">查询</button>
			</span>
</div>

<!-- 表格 -->
<div id="dateTable"  lay-filter="dateTable"></div>

<!--添加用户-->
<form class="layui-form layui-form-pane" action="" hidden="hidden" id="adduser_form">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登录名</label>
            <div class="layui-input-inline">
                <input type="text" name="loginName" id="date1" required="required" lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" required="required" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="loginPwd" required="required" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required="required" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="email" name="email" required="required" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" name="address" required="required" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-inline">
            <input type="checkbox" name="status" lay-skin="switch" lay-text="激活|未激" class="layui-btn-lg">
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <div class="layui-input-block">
            <textarea placeholder="请输入备注" name="remark" class="layui-textarea"></textarea>

        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <div class="layui-input-block layui-col-lg-offset5">
            <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="formDemo" id="submit_add">确认添加</button>

        </div>
    </div>



</form>


<%--分配角色--%>
<form class="layui-form layui-form-pane" action="" hidden="hidden" id="editRole_form">

    <input type="checkbox" name="" title="超级管理员" value="0" checked>
    <input type="checkbox" name="" title="经理">
    <input type="checkbox" name="" title="主管" disabled>

</form>




<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript">




    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table','element'], function() {

        // 操作对象
        var form = layui.form,
            element = layui.element,
            table = layui.table,
            layer = layui.layer,
            vipTable = layui.vip_table,
            $ = layui.jquery,
            active ={
                getCheckData: function(){ //获取选中数据
                    var checkStatus = table.checkStatus('idTest')
                        ,data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                }
            };





        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable' //指定原始表格元素选择器（推荐id选择器）
            ,
            height: vipTable.getFullHeight() //容器高度
            ,
            cols: [
                [ //标题栏
                    {
                        checkbox: true,
                        sort: true,
                        fixed: true,
                        space: true
                    }, {
                    field: 'userId',
                    title: 'ID',
                    width: 200
                }, {
                    field: 'loginName',
                    title: '登录名',
                    width: 120
                }, {
                    field: 'userName',
                    title: '用户名',
                    width: 120
                }, {
                    field: 'email',
                    title: '邮箱',
                    width: 150
                }, {
                    field: 'address',
                    title: '地址',
                    width: 150
                }, {
                    field: 'status',
                    title: '状态',
                    width: 80,
                    templet: '#titleTpl'
                },{
                    field: 'remark',
                    title: '备注',
                    width: 70
                }, {
                    fixed: 'right',
                    title: '操作',
                    width: 180,
                    align: 'right',
                    toolbar: '#barOption'
                } //这里的toolbar值是模板元素的选择器
                ]
            ],
            id: 'dataCheck',
            url: '${pageContext.request.contextPath}/findUserListServlet',
            //url: '../json/data_table.json',
            method: 'get',
            page: true,
            limits: [10, 20, 30,60],
            limit: 10 //默认采用30
            ,
            //loading: false,
            done: function(res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                console.log(res);

                //得到当前页码
                console.log(curr);

                //得到数据总量
                console.log(count);
            }
        });

        // 获取选中行
        table.on('checkbox(dataCheck)', function(obj) {
            layer.msg('123');
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        // 刷新
        $('#btn-refresh').on('click', function() {
            tableIns.reload();
        });

        //添加
        $('#btn-add').on('click', function() {

            layer.open({
                type: 1,
                content: $("#adduser_form"),
                area: ['750px', '470px']
            });
        });
        $("#submit_add").on('click',function () {

            //alert("----");
            $.post("${pageContext.request.contextPath}/addUserServletAjax",$("#adduser_form").serialize(),function (reslut) {

                layer.msg(reslut);

            });
        });



        //批量删除
        $("#btn-delete-all").on('click',function () {
            //获取所有选中的数据
            var checkStatus = table.checkStatus('dataCheck');
            var array =  checkStatus.data; //获取选中的数据

            if(array.length<=0) //如果没有数据被选中 提示需要选中数据
            {
                layer.msg("请选择需要删除的数据!!");
                return;
            }
            layer.confirm('是否删除?', {icon: 3, title:'提示'}, function(index){
                //只需要获取id即可：传参到数据库  http://localhsot:8080/sss?userId=0&userId=1
                //封装参数格式：userId=0&userId=1
                var ids = "";
                for(var i=0;i<array.length;i++)
                {
                    if(i==(array.length-1)){
                        ids = ids+"userId="+array[i].userId;
                    }else{
                        ids = ids+"userId="+array[i].userId+"&";
                    }
                }
                //2.通过 ajax将id传给服务器
                $.get("${pageContext.request.contextPath}/DelUserServlet?"+ids,function (result) {
                   if(result>0)
                   {
                       layer.open({
                           title: '成功'
                           ,content: '删除成功!!'
                       });
                       //刷新表格
                       tableIns.reload();

                   }else{
                       layer.open({
                           title: '异常信息'
                           ,content: '删除失败!!'
                       });
                   }
                });
                console.log(ids);
                layer.close(index);
            });

        })



        //转换状态
        table.on('tool(dateTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'edit'){ //修改
                //do somehing
            } else if(layEvent === 'stop'){ //禁用
                layer.confirm('真的要禁用该账户么', function(index){

                    if(data.status==1)
                    {
                        layer.msg("该用户已经被禁用！！！")
                        return;
                    }

                    //开启ajax请求 禁用
                    $.get("${pageContext.request.contextPath}/stopAccountServlet?userId="+data.userId,function (reslut) {


                        if(reslut>0)
                        {
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            tableIns.reload(); //重载数据
                            /*obj.update({
                                status: 1
                            });*/
                        }

                    });

                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'editRole'){ //编辑

                //修改页面id:editRole_form

                //1.发起ajax请求(同步)

                $.ajax({
                    type:"get",
                    url:'${pageContext.request.contextPath}/findRoleByUserServlet?userId='+data.userId,
                    async:false, //同步
                    dataType:'json',
                    success:function (result) {
                        //重新加载html分配角色form表单
                        $("#editRole_form").empty();//清空
                        $.each(result,function (index,role) {

                            var $input;
                            if (role.roleActive == 0) {
                                $input = $("<input type='checkbox' name='roleId' value='" + role.roleId + "' title='" + role.roleName + "' " + role.checked + " />")
                            }
                            else
                            {
                                $input = $("<input type='checkbox' name='roleId' value='"+role.roleId+"' title='"+role.roleName+"' "+role.checked+" disabled />")
                            }
                            $("#editRole_form").append($input);
                            // <input type="checkbox" name="" title="超级管理员" value="0" checked>
                        });

                        //最后添加一个隐藏表单元素，存放userId
                        $("<input type='text' name='userId' value='"+data.userId+"' hidden />").appendTo($("#editRole_form"));



                       // element.init();
                        form.render("checkbox")
                        //layer.msg(result.length);
                    },
                    error:function (result) {
                        layer.msg("-----"+result);
                    }


                });



                //do something
                layer.open({
                    title:'分配角色',
                    type: 1,
                    content: $("#editRole_form"),
                    area: ['550px', '300px'],
                    btn:['确认','取消'],
                    yes:function () {

                        //确定按钮的操作 $("#editRole_form").serialize():将整个表单参数序列化 后台可以根据name直接获取
                        $.post("${pageContext.request.contextPath}/updateRoleByUser?",$("#editRole_form").serialize(),function (result) {

                            if(result==1)
                            {
                                layer.msg("操作成功!!!");
                                layer.closeAll();
                            }
                            else
                            {
                                layer.msg("操作失败,请重试!!!");
                            }

                        })


                    }

                });


            }
        });



    });



</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-mini" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="stop">停用</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="editRole">分配角色</a>
</script>

<%--转换状态--%>
<script type="text/html" id="titleTpl">
    {{#  if(d.status == 0){ }}
    <a class="layui-btn layui-btn-mini">激活</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-mini layui-bg-gray">未激活</a>
    {{#  } }}
</script>





</body>

</html>
