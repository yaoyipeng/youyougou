app.service('goodsService',function($http){

    // 保存
    this.add=function (entity) {
        return $http.post('../goods/add.do',entity);
    }
    // 查询全部
    this.findAll=function(){
        return $http.get('../goods/findAll.do');
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../goods/findPage.do?page='+page+'&rows='+rows);
    }
    //查询实体
    this.findOne=function(id){
        return $http.get('../goods/findOne/'+id+'.do');
    }
    //修改
    this.update=function(entity){
        return $http.post('../goods/update.do',entity);
    }
    //删除
    this.delete=function(ids){
        return $http.post('../goods/delete.do?ids='+ids);
    }
    //条件查询
    this.search=function(page,rows,searchEntity){
        return $http.post('../goods/search.do?page='+page+"&rows="+rows,searchEntity);
    }

});