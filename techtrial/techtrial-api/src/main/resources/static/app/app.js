var mainApp = angular.module("mainApp", ['ngRoute']);

mainApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/searchFlights', {
			templateUrl: 'partials/searchFlight.html', controller: 'searchFlightController'
		})
		.when('/myBookings', {
			templateUrl: 'partials/myBookings.html', controller: 'myBookingsController'
		})
		.when('/bookFlight', {
			templateUrl: 'partials/bookFlight.html', controller: 'bookFlightController'
		})
		.when('/payment', {
			templateUrl: 'partials/payment.html', controller: 'paymentController'
		})
		.when('/checkin', {
			templateUrl: 'partials/checkin.html', controller: 'checkinController'
		})
		;
}]);

mainApp.run(function($rootScope, $http) {
	$http.defaults.headers.common['X-Auth-Token'] = 'test-x-auth-token';
});
