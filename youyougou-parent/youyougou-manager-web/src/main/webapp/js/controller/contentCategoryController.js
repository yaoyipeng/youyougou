/*****
 * 定义一个控制层 controller
 * 发送HTTP请求从后台获取数据
 ****/
app.controller("contentCategoryController",function($scope,$http,$controller,contentCategoryService){

    //继承父控制器
    $controller("baseController",{$scope:$scope});

    //获取所有的ContentCategory信息
    $scope.search=function(page,rows){
        //发送请求获取数据
        contentCategoryService.search(page,rows,$scope.searchEntity).success(function(response){
            //集合数据
            $scope.list = response.records;
            //分页数据
            $scope.paginationConf.totalItems=response.total;
        });
    }

    //添加或者修改方法
    $scope.save = function(){
        var result = null;
        if($scope.entity.id!=null){
            //执行修改数据
            result = contentCategoryService.update($scope.entity);
        }else{
            //增加操作
            result = contentCategoryService.add($scope.entity);
        }
        //判断操作流程
        result.success(function(response){
            //判断执行状态
            if(response.success){
                //重新加载新的数据
                $scope.reloadList();
            }else{
                //打印错误消息
                alert(response.message);
            }
        });
    }

    //根据ID查询信息
    $scope.getById=function(id){
        contentCategoryService.findOne(id).success(function(response){
            //将后台的数据绑定到前台
            $scope.entity=response;
        });
    }

    //批量删除
    $scope.delete=function(){
        contentCategoryService.delete($scope.selectIds).success(function(response){
            //判断删除状态
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }
});