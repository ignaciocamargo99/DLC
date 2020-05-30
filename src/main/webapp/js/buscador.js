// Definimos variable a usar
var buscador = angular.module("buscador", []);
buscador.controller("busqueda_controller", function ($scope,$http){
    $scope.container_results = [];
    $scope.pantalla = "B";
    $scope.cadena = "";
    $scope.buscar = function(cadena){
        $http({method:'GET', url: ('localhost:8080/DLC/api/search/param/' + cadena)}).then(
                function successCallback(response){
                    $scope.container_results = response.data.container_results;
                    console.log(response);
                },
                function errorCallback(response){
                    window.alert('Ocurrió un error.' + response.status);
                }
            );    
    };
});


