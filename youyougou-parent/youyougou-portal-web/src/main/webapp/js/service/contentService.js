/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentService",function($http){

    // 根据分类ID查询广告列表
    this.findByCategoryid=function (categoryid) {
        return $http.get('/content/findByCategoryId.do?categoryId='+categoryid);
    }

});
