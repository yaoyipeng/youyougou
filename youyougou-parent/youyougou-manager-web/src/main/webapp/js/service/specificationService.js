//服务层
app.service('specificationService',function($http){

    //读取列表数据绑定到表单中
    this.findAll=function(){
        return $http.get('../specification/findAll.do');
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../specification/findPage.do?page='+page+'&rows='+rows);
    }
    //添加
    this.add=function(entity){
        return $http.post('../specification/add.do',entity);
    }

    //修改
    this.update=function(entity){
        return $http.post('../specification/update.do',entity);
    }
    // 根据id查询一个
    this.findOne=function (id) {
        return $http.get('../specification/findOne.do?id='+id);
    }
    // 批量删除
    this.delete=function (ids) {
        return $http.post('../specification/delete.do?ids='+ids);
    }
    //条件查询
    this.search=function(page,rows,searchEntity){
        return $http.post('../specification/search.do?page='+page+"&rows="+rows,searchEntity);
    }
    // 查询规格下拉列表
    this.selectOptionList=function () {
        return $http.get('../specification/optionlist.do');
    }
});