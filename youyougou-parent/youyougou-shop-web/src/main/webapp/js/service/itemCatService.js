// 分类服务层
app.service('itemCatService',function($http){
    //根据上级ID查询下级列表
    this.findByParentId=function(parentId){
        return $http.get('../itemCat/findByParentId.do?parentId='+parentId);
    }
    // 添加分类
    this.add=function(entity){
        return $http.post('../itemCat/add.do',entity);
    }
    //根据id 查询一个
    this.findOne=function (id) {
        return $http.get('../itemCat/findOne/'+id+'.do');
    }
    //修改
    this.update=function (entity) {
        return $http.post('../itemCat/update.do',entity);
    }

    //批量删除
    this.delete=function(ids){
        return $http.post('../itemCat/delete.do?ids='+ids);
    }

});