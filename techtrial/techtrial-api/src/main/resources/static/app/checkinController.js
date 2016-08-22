
mainApp.controller("checkinController", function($rootScope, $scope, $http, $routeParams) {
	
	CheckoutForm.initCheckoutForm();
	
	$scope.data = {
		flightBookingId : $routeParams.flightBookingId,
		flightBooking : null,
		tickets : null,
		seatRowArr : [], // row no 1, 2, 3, etc.
		seatNoArr : []   // seat no A, B, C, etc.
		
	};
	
	$scope.refreshBookingTable = function(flightBookingId) {
		$http.get("flightBooking/" + flightBookingId, $rootScope.httpConfig).success( function(response) {
			$scope.data.flightBooking = response; 
	
			if (response) {
				$scope.data.tickets = response.tickets;
				
				for (var ticket of $scope.data.tickets) {
					ticket.checkinRequired = (ticket.checkinDate == null);
					ticket.canPrintTicket = (ticket.checkinDate != null);
				}
	
				var plane = response.flightSchedule.plane;
				for (var i = 1; i <= plane.seatRows.length; i++)
					$scope.data.seatRowArr.push(i);
				
				// extract the seat numbers from the first row
				for (var seatGroup of plane.seatRows[0].seatGroups) {
					for (var seat of seatGroup.seats) {
						$scope.data.seatNoArr.push(seat.seatNumber);
					}
				}
				
			}
		});
	};
	
	$scope.refreshBookingTable($scope.data.flightBookingId);
	
	$scope.checkin = function(ticketNo) {
		var url = "flightTicket/checkin";

		var ticket = $scope.data.tickets[ticketNo];
		
		var requestBody = ticket;
		
		$http.post(url, requestBody).success( function(response) {
			$scope.refreshBookingTable($scope.data.flightBookingId);
		}).error( function(response) {
			alert(response.message);
		});

	}

});