
mainApp.controller("paymentController", function($scope, $http, $routeParams) {
	var url = "flightBooking/" + $routeParams.flightBookingId;
	
	CheckoutForm.initCheckoutForm();
	
	$scope.data = {
		flightBookingId : $routeParams.flightBookingId,
		flightBooking : null,
		passengers : [],
		creditCard : {
			type : null,
			cardOwner : null,
			number : null,
			cvv2 : null,
			expirationMonth : null,
			expirationYear : null,
		}
	};
		
	$http.get(url).success( function(response) {
		$scope.data.flightBooking = response; 

		if (response) {
			for (var i = 0; i < response.numberOfPassengers; i++) {
				
				var passenger = {
					name : null,
					surname : null,
					email : null,
					phone : null
				};
				
				$scope.data.passengers.push(passenger);
			}
		}
	});
	
	
	$scope.commitPayment = function() {
		if (! $("#paymentForm").valid())
			return;

		var url = "flightBooking/completePayment";

		var ticketsArr = [];
		for (var passengerObj of $scope.data.passengers) {
			var ticket = {
				passenger : passengerObj	
			};
			
			ticketsArr.push(ticket);
		}
		
		$scope.data.creditCard.number = $("#card").val();
		$scope.data.creditCard.cvv2 = $("#cvv").val();
		$scope.data.creditCard.expirationYear = $("#year").val();
		
		var requestBody = {
			flightBooking : {
				id : $scope.data.flightBookingId,
				tickets : ticketsArr
			},
			creditCard : $scope.data.creditCard
		};
		
		$http.post(url, requestBody).success( function(response) {
			$scope.data.flightBooking = response; 
		});

	}

});