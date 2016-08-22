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
		.otherwise({
			templateUrl: 'partials/signin.html', controller: 'signinController'
		})
		;
}]);

mainApp.run(function($rootScope, $http) {
	$rootScope.state = "test_state";
	$rootScope.nonce = "DgkRrHXmyu3KLd0KDdfq";
	$rootScope.redirectUri = "http://www.example.org";
	$rootScope.signedIn = false;
	
	$rootScope.authUrl = "https://accounts.google.com/o/oauth2/auth?"
		+ "response_type=code"
		+ "&client_id=" + "387640011435-t6csd426r6j03sncvn5pbagg3gdr08ba.apps.googleusercontent.com"
		+ "&scope=email profile"
		+ "&state=" + $rootScope.state
		+ "&redirect_uri=" + $rootScope.redirectUri
		+ "&nonce=" + $rootScope.nonce;
	
	//$http.defaults.headers.common['X-Auth-Token'] = 'test-x-auth-token';
	var authToken = getQueryParameter('authToken');
	
	if (authToken != null) {
		$http.post("auth/token", authToken).success(function(response) {
			if (response.authToken) {
				$rootScope.signedIn = true;
				$rootScope.authToken = response.authToken;
				$rootScope.httpConfig = {
					headers:  {
						'X-Auth-Token' : response.authToken
					}
				};

				$http.defaults.headers.post['X-Auth-Token'] = response.authToken;
			}
		});
	}
	
});

function getQueryParameter(paramName) {
	var searchString = window.location.search;
	
	var parts = searchString.split("&");
	var value = null;
	for (var part of parts) {
		if (part.indexOf(paramName) >= 0)
			value = part.split("=")[1];
	}
	
	return value;
}
