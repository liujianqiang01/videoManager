<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>收益管理</title>

    <meta name="description" content="Static &amp; Dynamic Tables" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/assets/js/html5shiv.min.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">


<div class="main-container ace-save-state" id="main-container">
    <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
    </script>


    <div class="page-content">
        <div class="col-xs-12">
            <div class="table-header" style="margin-top: 10px;">
                收益列表
            </div>

            <!-- div.table-responsive -->

            <!-- div.dataTables_borderWrap -->
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <form method="get" action="/admin/settleAccount" id="settleAccountform">
                    <div class="row">
                        <div class="col-xs-10">
                            <div class="dataTables_length" id="dynamic-table_length">
                                <label>每页显示 <select name="pageSize" aria-controls="dynamic-table" class="form-control input-sm">
                                    <option <#if pageSize==10>selected</#if>  value="10">10</option>
                                    <option <#if pageSize==25>selected</#if> value="25">25</option>
                                    <option <#if pageSize==50>selected</#if> value="50">50</option>
                                    <option <#if pageSize==100>selected</#if> value="100">100</option></select> 条</label>
                                 <label>商户Id:
                                    <input type="search" name="menchantId" value="${menchantId!}" class="form-control input-sm" placeholder="" aria-controls="dynamic-table"></label>
                                <label>汇款状态: <select name="remittanceState" >
                                        <option <#if (remittanceState!2)==2>selected</#if> value="">全部</option>
                                        <option <#if (remittanceState!2)==0>selected</#if> value="0">未汇款</option>
                                        <option <#if (remittanceState!2)==1>selected</#if> value="1">已汇款</option>
                                    </select></label>
                            </div>
                        </div>
                        <div>
                            <button class="btn btn-white btn-info btn-bold" onclick="query()">
                                查询
                            </button>
                        </div>
                    </div>
                    </form>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" aria-describedby="dynamic-table_info">
                        <thead>
                        <tr role="row">
                            <th class="center sorting_disabled" rowspan="1" colspan="1" aria-label="">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" id="check_all">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th class="sorting_disabled" tabindex="0" rowspan="1" colspan="1" >ID</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >用户ID</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >结算总金额</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >提成金额</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >结算时间</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >汇款状态</th>
                            <th class="sorting_disabled" tabindex="0"  rowspan="1" colspan="1" >操作</th>

                        </tr>
                        </thead>

                        <tbody>
                        <#list list as list>
                        <tr role="row" class="odd">
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" name="checkbox" value="${list.id}" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td>
                                <a href="#">${list.id}</a>
                            </td>
                            <td>${list.merchantId!}</td>
                            <td>${list.settleAccountPrice!}</td>
                            <td>${list.ratePrice!}</td>
                            <td>
                                ${list.settleAccountTime?string("yyyy-MM-dd")}
                            </td>

                            <td>
                                <#if list.remittanceState==0>未汇款</#if>
                                <#if list.remittanceState==1>已汇款</#if>
                            </td>
                            <td>
                                <#if list.remittanceState==0>
                                    <div class="hidden-sm hidden-xs action-buttons">
                                       <a class="green" onclick="grant(${list.id})">
                                           发放
                                       </a>
                                    </div>
                                </#if>
                            </td>
                        </tr>
                        </#list>

                        </tbody>
                    </table>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">第${nowBegin}-${nowEnd}条，共${count}条
                            </div>
                        </div>
                        <div class="col-xs-6"><div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
                            <ul class="pagination" id="pagination"></ul>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div><!-- /.page-content -->


</div><!-- /.main-container -->
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="/assets/js/jquery-2.1.4.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="/assets/js/jquery.dataTables.min.js"></script>
<script src="/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="/assets/js/dataTables.buttons.min.js"></script>
<script src="/assets/js/dataTables.select.min.js"></script>

<!-- ace scripts -->
<script src="/assets/js/ace-elements.min.js"></script>
<script src="/assets/js/ace.min.js"></script>
<script src="/assets/js/page.js" ></script>

<script src="/assets/js/bootbox.js"></script>
<script src="/assets/js/utils.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $("#check_all").change(function () {
            if($("#check_all").is(':checked')){
                $("input[name=checkbox]").prop('checked', true);
            }else{
                $("input[name=checkbox]").prop('checked', false);
            }
        });
        $("select[name=pageSize]").change(function () {
            $("#settleAccountform").submit();
        });
        
        $("input[name=checkbox]").change(function () {
            var isAll = true;
            $("input[name=checkbox]").each(function () {
                if(!$(this).is(':checked')){
                    isAll = false;
                    $("#check_all").prop('checked', false);
                    return;
                }
            });
            if(isAll){
                $("#check_all").prop('checked', true);
            }
        });
        new page({pageMain:"pagination",nowPage:${page!'1'},count:${count},pageSize:${pageSize!'10'},
            url:"/admin/settleAccountform",params:"?pageSize=${pageSize}",pakey:"page"});

    });

    function query() {
        $("#merchantform").submit();
    };
    /**
     * 删除角色
     */
    function grant(id) {
        bootbox.confirm({
                message: "是否发放?",
                buttons: {
                    confirm: {
                        label: "发放",
                        className: "btn-sm",
                    },
                    cancel: {
                        label: "取消",
                        className: "btn-sm btn-primary",
                    }
                },
                callback: function(result) {
                    if(result) {
                        quickAjax({
                            url: '/admin/settleAccount/grant',
                            method:"POST",
                            data:{
                                id:id
                            },
                            success: function (response) {
                                if (response.code == 1){
                                    alert("发放成功",function(){
                                        location.reload();
                                    });
                                }
                                else {
                                    alert(response.msg);
                                }
                            }
                        });
                    }
                }
            })
    };

</script>
</body>
</html>
