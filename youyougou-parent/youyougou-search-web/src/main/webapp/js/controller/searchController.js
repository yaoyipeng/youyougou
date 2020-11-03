/*
* 搜索Controller实现
* */
app.controller('searchController',function ($scope,searchService) {
    //搜索方法
    $scope.search=function () {
        searchService.search($scope.entity).success(function (response) {
            // $scope.list=response;
            $scope.list=response.rows;
        })
    }
});