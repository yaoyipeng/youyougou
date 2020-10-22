//控制层
app.controller('specificationController' ,function($scope,$controller,specificationService){

    $controller('baseController',{$scope:$scope});//继承

    // $scope.entity={specificationOptionList:[]};
    //增加规格选项行
    $scope.addTableRow=function(){
        $scope.entity.specificationOptionList.push({});
    }

    //批量选项删除
    $scope.deleteTableRow=function(index){
        $scope.entity.specificationOptionList.splice(index,1);//删除
    }

    //读取列表数据绑定到表单中
    $scope.findAll=function(){
        specificationService.findAll().success(
            function(response){
                $scope.list=response;
            }
        );
    }

    //分页
    $scope.findPage=function(page,rows){
        specificationService.findPage(page,rows).success(
            function(response){
                $scope.list=response.list;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    // 查询一个
    $scope.findOne=function(id){
        specificationService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }
    //添加
    $scope.save=function(){
        var serviceObject;  // 服务层对象
        if ($scope.entity.id!=null){//    如果有id
            serviceObject=specificationService.update($scope.entity);//  修改
        }else {
            serviceObject=specificationService.add($scope.entity);//  添加
        }
        //判断操作流程
        serviceObject.success(function(response){
            //判断执行状态
            if(response.success){
                alert(response.message);
                //重新加载新的数据
                $scope.reloadList();
            }else{
                //打印错误消息
                alert(response.message);
            }
        });
    }
    //批量删除
    $scope.delete=function(){
        //获取选中的复选框
        specificationService.delete($scope.selectIds).success(
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
        specificationService.search(page,rows, $scope.searchEntity).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.list;//给列表变量赋值
            }
        );
    }

});