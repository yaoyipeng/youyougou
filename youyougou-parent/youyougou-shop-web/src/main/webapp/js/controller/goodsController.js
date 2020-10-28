app.controller('goodsController' ,function($scope,$controller,goodsService,uploadService){
    $controller('baseController',{$scope:$scope});//继承
    //添加或修改
    $scope.save=function(){
        //文本编辑器对象.html()表示获取文本编辑器内容
        $scope.entity.goodsDesc.introduction=editor.html();
        goodsService.add( $scope.entity  ).success(
            function(response){
                if(response.success){
                    alert('保存成功');
                    $scope.entity={};
                    editor.html("");//清空富文本编辑器
                }else{
                    alert(response.message);
                }
            }
        );
    }
    /**
     * 上传图片
     */
    $scope.uploadFile=function(){
        uploadService.uploadFile().success(function(response) {
            if(response.success){//如果上传成功，取出url
                $scope.image_entity.url=response.message;//设置文件地址
            }else{
                alert(response.message);
            }
        }).error(function() {
            alert("上传发生错误");
        });
    };

    $scope.entity={goods:{},goodsDesc:{itemImages:[]}};//定义页面实体结构
    //添加图片列表
    $scope.add_image_entity=function(){
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }

    //列表中移除图片
    $scope.remove_image_entity=function(index){
        $scope.entity.goodsDesc.itemImages.splice(index,1);
    }
});