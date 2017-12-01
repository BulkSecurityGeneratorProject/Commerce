(function ($, window) {
    function showToast(obj) {
        if(obj.state == "failure") {
            obj.state = "error";
        }
        toastr.options = {
            "closeButton" : true,
            "positionClass" : "toast-bottom-full-width",
            "timeOut" : "2000",
            "extendedTimeOut" : "2000"

        }
        return toastr[obj.state](obj.message);
    }

    function modalToast(obj) {
        toastr.options = {
            "closeButton" : false,
            "positionClass" : "toast-bottom-full-width",
            "timeOut" : "0",
            "extendedTimeOut" : "0",
            "tapToDismiss" : false
        }

        var message = '<div class=""><div class="fl">' + obj.msg + '</div> <div class="actionList"><ul><li><button class="btn btn-default FailModal">Cancel</button></li><li><button class="btn btn-default PassModal" type="button">Ok</button></li></ul></div><div>';
        $toast = toastr["warning"](message);
        if ($toast.find('.PassModal').length) {
            $toast.delegate('.PassModal', 'click', function () {
                obj.callbacks.pass();
                $toast.remove();
            });
        }
        if ($toast.find('.FailModal').length) {
            $toast.delegate('.FailModal', 'click', function () {
                obj.callbacks.fail();
                $toast.remove();
            });
        }
    }

    function checkInputs(dom, obj) {
        var parent = dom.parent();
        var bool = true
        var alert = obj;
        $("input", parent).each(function(e) {
            if($(this).val() == "" && $(this).is(":enabled")) {
                $(this).addClass("error");
                bool = false;
            }
        });
        if(bool) {
            toastr.clear(toasty);
        }else{
            var toasty = Utils.Toast(alert);
        }
        return bool;
    }

    function showToastStatusMsg(status, message) {
        if(status.status == 'success') {
            toastr.success(message + ' - Success');
        }
        else {
            toastr.error(message + ' - Failed - ' + getErrorString(status.exceptionName));
        }
    }

    window.Utils = {
        Toast: showToast,
        ModalToast: modalToast,
        Check: checkInputs,
        ShowToastStatusMsg: showToastStatusMsg
    }
})(jQuery, window)
