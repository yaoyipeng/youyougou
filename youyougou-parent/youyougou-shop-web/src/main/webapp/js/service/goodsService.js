app.service('goodsService',function($http){

    // 保存
    this.add=function (entity) {
        return $http.post('../goods/add.do',entity);
    }

});