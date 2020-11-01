/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("contentService",function($http){

    //查询列表
    this.findPage=function(page,rows){
        return $http.get("/content/findPage.do?page="+page+"&rows="+rows);
    }
    //增加Content
    this.add=function(entity){
        return $http.post("/content/add.do",entity);
    }

    //保存
    this.update=function(entity){
        return $http.post("/content/update.do",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("/content/findOne/"+id+".do");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("/content/delete.do?ids="+ids);
    }

});
