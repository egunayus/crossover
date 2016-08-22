
mainApp.controller("bookFlightController", function($rootScope, $scope, $http, $location, $routeParams) {
	var url = "flightSchedule/" + $routeParams.flightScheduleId;
	
	
	$scope.data = {
		flightScheduleId : $routeParams.flightScheduleId,
		flightSchedule : null,
		flightBooking : null,
		flightInfo : null,
		date : null,
		numberOfPassengers : 1
	};
	
	$http.get(url, $rootScope.httpConfig).success( function(response) {
		$scope.data.flightSchedule = response; 
		if (response) {
			$scope.data.date = new Date(response.scheduledDate);
			$scope.data.flightInfo = response.flightInfo;
		}
	});
	
	$scope.bookFlight = function() {
		var url = "flightBooking/addFlightBooking";
		
		var requestBody = {
			flightSchedule : {
				id : $scope.data.flightScheduleId
			},
			numberOfPassengers : $scope.data.numberOfPassengers
		};
		
		$http.post(url, requestBody).success( function(response) {
			$scope.data.flightBooking = response; 
			$location.path("/payment").search({flightBookingId: response.id});
		});
	}

	$scope.decreaseQty = function() {
	    if($scope.data.numberOfPassengers - 1 < 1)
	        return;
	    else
	    	$scope.data.numberOfPassengers--;
	}

});