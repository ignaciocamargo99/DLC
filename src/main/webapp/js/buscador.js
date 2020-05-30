// Definimos variable a usar
buscador = angular.module("buscador", []);
buscador.controller("busqueda_controller", function ($scope,$http){
    $scope.resultados=[];
    $scope.pantalla = "B";
    $scope.cadena = "";
    $scope.buscar = function(cadena){
        $http({method:'GET', url: 'api/search/param/' + cadena}).then(
                function successCallback(response){
                    $scope.resultados = response;
                    console.log(response);
                },
                function errorCallback(response){
                    window.alert('Ocurrió un error.' + response.status);
                }
            );    
    };
});


