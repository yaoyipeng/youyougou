app.controller('indexController' ,function($scope,$controller,loginService){
    //读取当前登录人
    $scope.showLoginName1=function(){
        loginService.loginName1().success(
            function(response){
                $scope.loginName=response.loginName;
            }
        );
    }
});