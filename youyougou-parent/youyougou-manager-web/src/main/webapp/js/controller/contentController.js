/*****
 * 定义一个控制层 controller
 * 发送HTTP请求从后台获取数据
 ****/
app.controller("contentController",function($scope,$http,$controller,contentService,contentCategoryService,uploadService){

    //继承父控制器
    $controller("baseController",{$scope:$scope});

    //状态
    $scope.status=["无效","有效"];

    //查询
    $scope.search=function(page,rows){
        contentService.findPage(page,rows).success(
            function(response){
                $scope.paginationConf.totalItems=response.total;//总记录数
                $scope.list=response.records;//给列表变量赋值
            }
        );
    }
    //上传广告图
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(
            function(response){
                if(response.success){
                    $scope.entity.pic=response.message;
                }else{
                    alert("上传失败！");
                }
            }
        ).error(
            function(){
                alert("上传出错！");
            }
        );
    }
    //分类列表显示名字的key:value数据
    $scope.categoryNameList={};

    //加载广告分类列表
    $scope.findContentCategoryList=function(){
        contentCategoryService.findAll().success(
            function(response){
                $scope.contentCategoryList=response;
                //组装分类列表名字显示
                for(var i=0;i<response.length;i++){
                    var key = response[i].id;
                    var value = response[i].name;

                    //组装一个key：value格式的JSON数据
                    $scope.categoryNameList[key]=value;
                }
            }
        );
    }

    //添加或者修改方法
    $scope.save = function(){
        var result = null;
        if($scope.entity.id!=null){
            //执行修改数据
            result = contentService.update($scope.entity);
        }else{
            //增加操作
            result = contentService.add($scope.entity);
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

    //根据ID查询信息
    $scope.getById=function(id){
        contentService.findOne(id).success(function(response){
            //将后台的数据绑定到前台
            $scope.entity=response;
        });
    }

    //批量删除
    $scope.delete=function(){
        contentService.delete($scope.selectIds).success(function(response){
            //判断删除状态
            if(response.success){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        });
    }
});
