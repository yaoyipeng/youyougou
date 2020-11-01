app.controller('goodsController' ,function($scope,$controller,goodsService,itemCatService){
    $controller('baseController',{$scope:$scope});//继承

    $scope.status=['未审核','已审核','审核未通过','关闭'];//商品状态
    $scope.itemCatList=[];//商品分类列表
    //加载商品分类列表
    $scope.findItemCatList=function(){
        itemCatService.findAll().success(
            function(response){
                for(var i=0;i<response.length;i++){
                    $scope.itemCatList[response[i].id]=response[i].name;
                }
            }
        );
    }

   /* //读取列表数据绑定到表单中
    $scope.findAll=function(){
        goodsService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }*/
    //批量删除
    $scope.delete=function(){
        //获取选中的复选框
        goodsService.delete($scope.selectIds).success(
            function(response){
                if(response.success){
                    alert(response.message);
                    $scope.reloadList();//刷新列表
                }else {
                    alert(response.message);
                }
            }
        );
    }
    $scope.searchEntity={};//定义搜索对象
    //条件查询
    $scope.search=function(page,rows){
        goodsService.search(page,rows, $scope.searchEntity).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.records;//给列表变量赋值
            }
        );
    }

    //更改状态
    $scope.updateStatus=function(status){
        goodsService.updateStatus($scope.selectIds,status).success(
            function(response){
                if(response.success){//成功
                    alert(response.message);
                    $scope.reloadList();//刷新列表
                    $scope.selectIds=[];//清空ID集合
                }else{
                    alert(response.message);
                }
            }
        );
    }
});