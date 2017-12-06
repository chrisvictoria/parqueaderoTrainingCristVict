
var app = angular.module('parqueadero',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: 'http://localhost:8090/',
    PARQUEADERO_SERVICE_API : 'http://localhost:8090/registrar/',
    PARQUEADERO_REGISTRAR_ENTRADA : 'entradaVehiculo',
    PARQUEADERO_REGISTRAR_SALIDA : 'salidaVehiculo'
    CARROS_EN_PARQUEADERO : 'getCarrosEnParqueadero',
    MOTOS_EN_PARQUEADERO : 'getMotosEnParqueadero'
});