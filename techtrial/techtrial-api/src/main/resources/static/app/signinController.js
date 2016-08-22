
mainApp.controller("signinController", function($rootScope, $scope, $http, $location) {
	var searchString = window.location.search;
	if (searchString.indexOf('code') < 0)
		return;

	var code = getQueryParameter('code');
	var state = getQueryParameter('state');
	
	
	// call authentication from server API if state & code are not empty
	if (code && state) {
		var url = "auth/google";
		
		var requestBody = {
			state : state,
			code : code,
			redirectUri : $rootScope.redirectUri
		};
		
		$http.post(url, requestBody).success( function(response) {
			if (response.authToken) {
				$rootScope.signedIn = true;
				$rootScope.authToken = response.authToken;
				$rootScope.httpConfig = {
					headers:  {
						'X-Auth-Token' : response.authToken
					}
				};

				$http.defaults.headers.post['X-Auth-Token'] = response.authToken;
				
				$location.path("/searchFlights");
			} else
				alert(response.error + ' : ' + response.errorDescription);
		});
		
	}
	
});