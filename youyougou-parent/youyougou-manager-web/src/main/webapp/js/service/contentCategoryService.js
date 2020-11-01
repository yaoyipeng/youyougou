/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentCategoryService",function($http){

    //查询列表
    this.findAll=function(){
        return $http.get("/contentCategory/findAll.do");
    }


    //查询列表
    this.search=function(page,rows,searchEntity){
        return $http.post("/contentCategory/search.do?page="+page+"&rows="+rows,searchEntity);
    }

    //增加ContentCategory
    this.add=function(entity){
        return $http.post("/contentCategory/add.do",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/contentCategory/update.do",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/contentCategory/findOne/"+id+".do");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/contentCategory/delete.do?ids="+ids);
    }

});
