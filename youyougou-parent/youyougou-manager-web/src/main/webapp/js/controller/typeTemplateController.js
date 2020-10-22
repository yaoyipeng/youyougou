// 定义一个控制层 controller  发送HTTP请求从后台获取数据
app.controller("typeTemplateController",function($scope,$controller,typeTemplateService,brandService,specificationService){

    $controller('baseController',{$scope:$scope});//继承

    //准备select2的数据
    $scope.brandList={data:[]};//品牌列表
    //读取品牌列表
    $scope.findBrandList=function(){
        brandService.selectOptionList().success(
            function(response){
                $scope.brandList={data:response};
            }
        );
    }

    //规格列表
    $scope.specOptionList = {data: []};
    $scope.findSpecList=function () {
        specificationService.selectOptionList().success(function (response) {
            $scope.specOptionList= {data: response};
        });
    }


    //创建一个集合，用于存储扩展属性
    // $scope.entity={customAttributeItems:[]};
    //新增扩展属性行
    $scope.addTableRow=function(){
        $scope.entity.customAttributeItems.push({});
    }

    //删除扩展属性行
    $scope.deleTableRow=function(index){
        $scope.entity.customAttributeItems.splice(index,1);//删除
    }

    //获取所有的TypeTemplate信息
    $scope.findAll=function(){
        typeTemplateService.findAll().success(
            function(response){
                $scope.list = response;
            }
        );
    }

    //分页查询TypeTemplate信息
    $scope.findPage=function(page,rows){
        typeTemplateService.findPage(page,rows).success(function(response){
            $scope.list = response.list;
            $scope.paginationConf.totalItems=response.total;
        });
    }

    //根据id查询一个
    $scope.findOne=function(id){
        typeTemplateService.findOne(id).success(
            function(response){
                $scope.entity= response;
                $scope.entity.brandIds=  JSON.parse($scope.entity.brandIds);//转换品牌列表
                $scope.entity.specIds=  JSON.parse($scope.entity.specIds);//转换规格列表
                $scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
            }
        );
    }

    //添加或者修改方法
    $scope.save = function(){
        var result = null;
        if($scope.entity.id!=null){
            result = typeTemplateService.update($scope.entity);
        }else{
            result = typeTemplateService.add($scope.entity);
        }
        //判断操作流程
        result.success(function(response){
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
        typeTemplateService.delete($scope.selectIds).success(function(response){
            //判断删除状态
            if(response.success){
                alert(response.message);
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }

    $scope.searchEntity={};//定义搜索对象
    //条件查询
    $scope.search=function(page,rows){
        typeTemplateService.search(page,rows, $scope.searchEntity).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.list;//给列表变量赋值
            }
        );
    }
});
