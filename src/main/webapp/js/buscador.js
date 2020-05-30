// Definimos variable a usar
var buscador = angular.module("buscador", []);
buscador.controller("busqueda_controller", function ($scope,$http){
    $scope.container_results = [];
    $scope.pantalla = "B";
    $scope.cadena = "";
    $scope.buscar = function(cadena){
        $http({method:'GET', url: 'api/search/prueba/' + cadena}).then(
                function successCallback(response){
                    $scope.container_results = response.data.resultados;
                    console.log(response);
                },
                function errorCallback(response){
                    window.alert('Ocurrió un error.' + response.status);
                }
            );    
    }
}


