app.controller('sellerController' ,function($scope,$controller,sellerService){

    $controller('baseController',{$scope:$scope});//继承

    //获取所有的TypeTemplate信息
    $scope.findAll=function(){
        sellerService.findAll().success(
            function(response){
                $scope.list = response;
            }
        );
    }

    //分页查询TypeTemplate信息
    $scope.findPage=function(page,rows){
        sellerService.findPage(page,rows).success(function(response){
            $scope.list = response.records;
            $scope.paginationConf.totalItems=response.total;
        });
    }

    //新增
    $scope.add=function(){
        sellerService.add( $scope.entity  ).success(
            function(response){
                if(response.success){
                    alert(response.message);
                    //如果注册成功，跳转到登录页
                    // location.href="shoplogin.html";
                }else{
                    alert(response.message);
                }
            }
        );
    }
    // 修改商家状态（审核）
    $scope.updateStatus=function(sellerId,status){
        sellerService.updateStatus(sellerId,status).success(
            function(response){
                if(response.success){
                    $scope.reloadList();//刷新列表
                }else{
                    alert(response.message);
                }
            }
        );
    }

    //根据id查询一个
    $scope.findOne=function(id){
        sellerService.findOne(id).success(
            function(response){
                $scope.entity= response;
            }
        );
    }

    $scope.searchEntity1={};//定义搜索对象
    //条件查询
    $scope.search=function(page,rows){
        sellerService.search(page,rows, $scope.searchEntity1).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.records;//给列表变量赋值
            }
        );
    }
});