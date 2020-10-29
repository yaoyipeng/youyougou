/***
 * 创建一个服务层
 * 抽取发送请求的一部分代码
 * */
app.service("typeTemplateService",function($http){

    //查询全部
    this.findAll=function(){
        return $http.get("../typeTemplate/findAll.do");
    }

    //分页查询
    this.findPage=function(page,rows){
        return $http.get("../typeTemplate/findPage.do?page="+page+"&rows="+rows);
    }

    //增加TypeTemplate
    this.add=function(entity){
        return $http.post("../typeTemplate/add.do",entity);
    }

    //修改
    this.update=function(entity){
        return $http.post("../typeTemplate/update.do",entity);
    }

    //根据ID查询
    this.findOne=function(id){
        return $http.get("../typeTemplate/findOne/"+id+".do");
    }

    //批量删除
    this.delete=function(ids){
        return $http.post("../typeTemplate/delete.do",ids);
    }

    //条件查询
    this.search=function(page,rows,searchEntity){
        return $http.post('../typeTemplate/search.do?page='+page+"&rows="+rows,searchEntity);
    }

    // 查询分类下拉列表
    this.selectOptionList=function(){
        return $http.get('../typeTemplate/selectOptionList.do');
    }

    //查询规格列表
    this.findSpecList=function(id){
        return $http.get('../typeTemplate/findSpecList/'+id+'.do');
    }
});
