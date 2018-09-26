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
<div id="dateTable"></div>

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
                <input type="password" name="password" required="required" lay-verify="required" autocomplete="off" class="layui-input">
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
        <label class="layui-form-label">邮箱地址</label>
        <div class="layui-input-block">
            <input type="email" name="email" required="required" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">请选择地址</label>
        <div class="layui-input-inline">
            <select name="quiz1">
                <option value="">请选择省</option>
                <option value="浙江" selected="">浙江省</option>
                <option value="你的工号">江西省</option>
                <option value="你最喜欢的老师">福建省</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="quiz2">
                <option value="">请选择市</option>
                <option value="杭州">杭州</option>
                <option value="宁波" disabled="">宁波</option>
                <option value="温州">温州</option>
                <option value="温州">台州</option>
                <option value="温州">绍兴</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="quiz3">
                <option value="">请选择县/区</option>
                <option value="西湖区">西湖区</option>
                <option value="余杭区">余杭区</option>
                <option value="拱墅区">临安市</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-inline">
            <input type="checkbox" name="zzz" lay-skin="switch" lay-text="激活|未激" class="layui-btn-lg">
        </div>
    </div>


    <div class="layui-form-item layui-form-text">
        <div class="layui-input-block">
            <textarea placeholder="请输入备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="demo2">确认添加</button>
        </div>
    </div>



</form>

<script type="text/javascript" src="../frame/layui/layui.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<script type="text/javascript">
    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table'], function() {

        // 操作对象
        var form = layui.form,
            table = layui.table,
            layer = layui.layer,
            vipTable = layui.vip_table,
            $ = layui.jquery;

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
                    width: 180
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
                    width: 120
                }, {
                    field: 'address',
                    title: '地址',
                    width: 180
                }, {
                    field: 'status',
                    title: '状态',
                    width: 70
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
            limits: [2,10, 20, 30,60],
            limit: 2 //默认采用30
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

        //添加 批量删除
        $('#btn-add').on('click', function() {

            layer.open({

                type: 1,
                content: $("#adduser_form"),
                area: ['750px', '470px']
            });

        });

        // you code ...

    });
</script>
<!-- 表格操作按钮集 -->
<script type="text/html" id="barOption">
    <a class="layui-btn layui-btn-mini" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-mini layui-btn-normal" lay-event="stop">停用</a>
    <a class="layui-btn layui-btn-mini layui-btn-danger" lay-event="editRole">修改权限</a>
</script>
</body>

</html>
