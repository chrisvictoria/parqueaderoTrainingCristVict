'use strict';

angular.module('parqueadero').factory('RegistroService',
		['$localStorage', '$http', '$q', 'urls',
			function ($localStorage, $http, $q, urls) {
				var factory = {
						loadCarrosEnParqueadero: loadCarrosEnParqueadero,
						loadMotosEnParqueadero: loadMotosEnParqueadero,
						getCarrosEnParqueadero: getCarrosEnParqueadero,
		                getMotosEnParqueadero: getMotosEnParqueadero,
		                createRegistroEntradaVehiculo: createRegistroEntradaVehiculo,
		                createRegistroSalidaVehiculo: createRegistroSalidaVehiculo
		        };
				
				return factory;
				
				function loadCarrosEnParqueadero() {
					console.log('loadCarrosEnParqueadero');
	                var deferred = $q.defer();
	                $http.get(urls.PARQUEADERO_SERVICE_API+urls.CARROS_EN_PARQUEADERO)
	                	.then(
	                			function (response) {
	                                console.log('Fetched successfully all carros en parqueadero');
	                                $localStorage.carrosEnParqueadero = response.data;
	                                deferred.resolve(response);
	                            },
	                            function (errResponse) {
	                                console.error('Error while loading all carros en parqueadero');
	                                deferred.reject(errResponse);
	                            }
	                	);
	                return deferred.promise;
				}
				
				function loadMotosEnParqueadero() {
					console.log('loadMotosEnParqueadero');
	                var deferred = $q.defer();
	                $http.get(urls.PARQUEADERO_SERVICE_API+urls.MOTOS_EN_PARQUEADERO)
	                	.then(
	                			function (response) {
	                                console.log('Fetched successfully all motos en parqueadero');
	                                $localStorage.motosEnParqueadero = response.data;
	                                deferred.resolve(response);
	                            },
	                            function (errResponse) {
	                                console.error('Error while loading all motos en parqueadero');
	                                deferred.reject(errResponse);
	                            }
	                	);
	                return deferred.promise;
				}
				
				function getCarrosEnParqueadero(){
					return $localStorage.carrosEnParqueadero;
				}
				
				function getMotosEnParqueadero(){
					return $localStorage.motosEnParqueadero;
				}
				
				function createRegistroEntradaVehiculo(registro){
					console.log('Creating registro entrada');
					var deferred = $q.defer();
					$http.post(urls.PARQUEADERO_SERVICE_API+urls.PARQUEADERO_REGISTRAR_ENTRADA, registro)
						.then(
								 function (response) {
									 	loadCarrosEnParqueadero();
									 	loadMotosEnParqueadero();
			                            deferred.resolve(response.data);
			                     },
			                     function (errResponse) {
			                           console.error('Error while creating registro entrada vehiculo : '+errResponse.data.errorMessage);
			                           deferred.reject(errResponse);
			                     }
					);
	                return deferred.promise;
				}
				
				function createRegistroSalidaVehiculo(){
					console.log('Creating registro salida');
					var deferred = $q.defer();
					$http.post(urls.PARQUEADERO_SERVICE_API+urls.PARQUEADERO_REGISTRAR_SALIDA, registro)
						.then(
							 function (response) {
								 	loadCarrosEnParqueadero();
								 	loadMotosEnParqueadero();
		                            deferred.resolve(response.data);
		                     },
		                     function (errResponse) {
		                           console.error('Error while creating registro salida vehiculo : '+errResponse.data.errorMessage);
		                           deferred.reject(errResponse);
		                     }
					);
					return deferred.promise;
				}
		}
]);