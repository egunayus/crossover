
mainApp.controller("myBookingsController", function($rootScope, $scope, $http, $location) {
	if (! $rootScope.signedIn)
		return;

	var url = "flightBooking/userBookings";
	
	$scope.data = {
		bookings : null
	};
	
	/**
	 * parses a given booking object to determine possible actions like payment, checkin, ticket printing, etc. 
	 */
	$scope.parseBooking = function(booking) {
		booking.paymentRequired = false;
		booking.checkinRequired = false;
		booking.canPrintTicket = false;
		
		if (booking.status == 'RESERVED')
			booking.paymentRequired = true; 
		else if (booking.status == 'PAID') {
			booking.paymentRequired = false; 
			
			for (var ticket of booking.tickets) {
				ticket.checkinRequired = (ticket.checkinDate == null);
				ticket.canPrintTicket = (ticket.checkinDate != null);
				
				if (booking.flightSchedule.isCheckinPossible && ticket.checkinDate == null) 
					booking.checkinRequired = true;
				
				if (ticket.checkinDate != null)
					booking.canPrintTicket = true;
			}
		}
	};
	
	$http.get(url, $rootScope.httpConfig).success( function(response) {
		$scope.data.bookings = response; 
		
		/*
		 * parse the booking and ticket information to determine possible actions like payment, check in, etc.
		 */
		for (var booking of $scope.data.bookings) {
			$scope.parseBooking(booking);
		}
	});
	
	$scope.statusLabel = function(bookingStatus) {
		if (bookingStatus == 'RESERVED')
			return 'label-warning';
		if (bookingStatus == 'PAID')
			return 'label-success';
	};
	
	$scope.go = function(path) {
		$location.path (path);
	};
	
});