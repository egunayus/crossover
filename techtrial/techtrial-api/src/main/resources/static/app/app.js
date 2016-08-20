var mainApp = angular.module("mainApp", ['ngRoute']);

mainApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/searchFlights', {
			templateUrl: 'partials/searchFlight.html', controller: 'searchFlightController'
		}).
		when('/myBookings', {
			templateUrl: 'partials/myBookings.html', controller: 'myBookingsController'
		})
		;
}]);
