//服务层
app.service('sellerService',function($http){

    //读取列表数据绑定到表单中
    this.findAll=function(){
        return $http.get('../seller/findAll.do');
    }
    //分页
    this.findPage=function(page,rows){
        return $http.get('../seller/findPage.do?page='+page+'&rows='+rows);
    }
    //查询实体
    this.findOne=function(id){
        return $http.get('../seller/findOne/'+id+'.do');
    }
    //增加
    this.add=function(entity){
        return  $http.post('../seller/add.do',entity );
    }

    //更改状态（审核）
    this.updateStatus=function(sellerId,status){
        return $http.get('../seller/updateStatus.do?sellerId='+sellerId+'&status='+status);
    }

    // 根据条件查询
    this.search=function(page,rows,searchEntity){
        return $http.post('../seller/search.do?page='+page+"&rows="+rows,searchEntity);
    }

});
