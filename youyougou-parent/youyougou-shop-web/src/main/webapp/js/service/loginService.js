app.service('loginService',function($http){
    //读取登录人名称
    this.loginName1=function(){
        return $http.get('../loginName/name.do');
    }
});