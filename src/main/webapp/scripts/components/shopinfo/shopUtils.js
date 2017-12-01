'use strict';

angular.module('bigeaterdashboardApp')
    .factory('shopUtils', function($log) {
    var validateDates = function (startDate, endDate, msg) {
        var valid = true;

        if (endDate == null) {
            msg.push({field: 'endDate', message: 'Must enter End date'});
            valid = false;
        }
        if (startDate == null) {
            msg.push({field: 'startDate', message: 'Must enter Start date'});
            valid = false;
        }
        if(valid) {
            if (endDate < startDate) {
                msg.push({field:'endDate', message:'End date is before start date'});
                valid = false;
            }
        }
        return valid;
    }

    var validateName = function(name, msg) {
        var valid = true;

        if (typeof name == "undefined" || !name || name.length == 0) {
            msg.push({field:'Name', message:'Shop name cannot be empty'});
            valid = false;
        }

        return valid;
    }

    // for the moment just display message and not field
    var createErrorString = function(msg) {
        var text = "";
        if(typeof msg != "undefined" || msg.length > 0) {
            for(var i =0; i < msg.length; i ++) {
                text += msg[i].message + " ";
            }
        }

        return text;
    }

    return {
        createErrorString : createErrorString,
        validateDates : validateDates,
        validateName : validateName
    }
});
