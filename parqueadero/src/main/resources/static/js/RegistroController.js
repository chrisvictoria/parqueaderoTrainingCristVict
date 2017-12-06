'use strict';
 
angular.module('parqueadero').controller('RegistroController',
    ['RegistroService', '$scope',  function( RegistroService, $scope) {
    	var self = this;
        self.registro = {};
        self.registros = [];
        
        self.submitRegistroEntrada = submitRegistroEntrada;
        self.submitRegistroSalida = submitRegistroSalida;
        self.getCarrosEnParqueadero = getCarrosEnParqueadero;
        self.getMotosEnParqueadero = getMotosEnParqueadero;
        self.createRegistroEntradaVehiculo = createRegistroEntradaVehiculo,
        self.createRegistroSalidaVehiculo = createRegistroSalidaVehiculo;
        self.reset = reset;
        
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        
        function submitRegistroEntrada() {
            console.log('submitRegistroEntrada');
            createRegistroEntradaVehiculo(self.registro);
        }
        
        function submitRegistroSalida() {
            console.log('submitRegistroSalida');
            createRegistroSalidaVehiculo(self.registro);
        }
        
        function createRegistroEntradaVehiculo(registro) {
        	console.log('createRegistroEntradaVehiculo');
        	RegistroService.createRegistroEntradaVehiculo(registro)
	        	.then(
	                    function (response) {
	                    	console.log('registroEntradaVehiculo created successfully');
	                    	self.successMessage = response;
	                        self.errorMessage='';
	                        self.done = true;
	                        self.registro={};
	                    },
	                    function (errResponse) {
	                        console.error('Error while creating registroEntradaVehiculo');
	                        self.errorMessage = 'Error creando registro de entrada: ' + errResponse.data.errorMessage;
	                        self.successMessage='';
	                    }
	        	);
        }
    }
    ]);