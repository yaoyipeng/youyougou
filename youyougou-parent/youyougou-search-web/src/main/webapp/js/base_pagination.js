//创建一个应用
var app = angular.module('youyougou',['pagination']);
app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true);
}]);