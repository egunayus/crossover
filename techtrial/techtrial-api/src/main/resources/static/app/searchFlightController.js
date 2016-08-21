
mainApp.controller("searchFlightController", function($scope, $http) {
	var url = "destination/all";
	
	initDatePicker = function() {
        // Regular datepicker
        $('#date').datepicker({
            dateFormat: 'yy-mm-dd',
            prevText: '<i class="fa fa-angle-left"></i>',
            nextText: '<i class="fa fa-angle-right"></i>'
        });
		
	};
	
	initValidation = function() {
        $("#searchFlightForm").validate({                   
            // Rules for form validation
            rules:
            {
                from: {
                	selectcheck: true,
                	notEqual: "#to"
                },
                to: {
                	selectcheck: true,
                	notEqual: "#from"
                },
                date: {
                    required: true
                }
            },
            // Messages for form validation
            messages:
            {
                from: {
                	selectcheck: 'Please select From',
                	notEqual : "Please select a different destination"
                },
                to: {
                	selectcheck: 'Please select To',
                	notEqual : "Please select a different destination"
                },
                date: {
                    required: 'Please enter some date'
                }
            },
            
            // Do not change code below
            errorPlacement: function(error, element)
            {
                error.insertAfter(element.parent());
            }
        });
            
	};
	
	jQuery.validator.addMethod('selectcheck', function (value) {
		return (value != '? object:null ?');
	});
    
	jQuery.validator.addMethod("notEqual", function(value, element, param) {
		return this.optional(element) || value != $(param).val();
	}, "This has to be different...");
    
	initDatePicker();
	initValidation();
	
	$scope.data = {
		from : null,
		to : null,
		date : null,
		flexDaysCount : 2,
		page : 0,
		size : 2,
		availableFlights : null
	};
		
	$http.get(url).success( function(response) {
		$scope.data.destinations = response; 
	});
	
	$scope.searchFlights = function() {
		if (! $("#searchFlightForm").valid())
			return;
		
		$scope.data.date = $("#date").val();
		
		var url = 'flightSchedule/search?from=' + $scope.data.from 
					+ '&to=' + $scope.data.to 
					+ '&tripDate=' + $scope.data.date + 'T00:00:00'
					+ '&flexDaysCount=' + $scope.data.flexDaysCount
					+ '&page=' + $scope.data.page
					+ '&size=' + $scope.data.size;
		
		$http.get(url).success (function(response) {
			$scope.data.pageResult = response;
			$scope.data.availableFlights = response.content;
		});
	};
	
	$scope.queryPage = function(page) {
		$scope.data.page = page;
		$scope.searchFlights();
	};
	
});