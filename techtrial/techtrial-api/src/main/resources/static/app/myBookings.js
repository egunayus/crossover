
mainApp.controller("myBookingsController", function($scope, $http) {
	var url = "flightBooking/userBookings";
	
	$scope.data = {
	};
	
	$http.get(url).success( function(response) {
		$scope.data.bookings = response; 
	});
	
	$scope.statusLabel = function(bookingStatus) {
		if (bookingStatus == 'RESERVED')
			return 'label-warning';
		if (bookingStatus == 'PAID')
			return 'label-success';
	};
	
});