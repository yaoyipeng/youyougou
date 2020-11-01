/*****
 * 定义一个控制层 controller
 * 发送HTTP请求从后台获取数据
 ****/
app.controller("contentController",function($scope,contentService){

    //广告集合
    $scope.contentList={};

    //根据categoryid查询广告
    $scope.getByCategoryId=function (categoryid) {
        contentService.findByCategoryid(categoryid).success(function (response) {
            //存储所有广告
            $scope.contentList[categoryid]=response;
        })
    }

});
