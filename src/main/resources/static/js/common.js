;
var common = function ($) {
    var defaults = {
        type: "POST",
        url: "",
        async: true,
        timeout: 5000,
        dataType: "json"
    };

    function webAjax(options, success, error) {
        var options = $.extend(defaults, options);
        $.ajax({
            type: options.type,
            url: '/web/' + options.url + '.json',
            async: options.async,
            timeout: options.timeout,
            dataType: options.dataType,
            data: options.data,
            beforeSend: function (xhr) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                xhr.setRequestHeader(header, token);
                console.info("beforeSend");
            },
            success: success,
            error: error,
            complete: function () {
                console.log('complete');
            }
        })
    };

    function apiAjax(options, success, error) {
        var options = $.extend(defaults, options);
        $.ajax({
            type: options.type,
            url: '/api/' + options.url + '.json',
            async: options.async,
            timeout: options.timeout,
            dataType: options.dataType,
            data: options.data,
            beforeSend: function (xhr) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                xhr.setRequestHeader(header, token);
                console.info("beforeSend");
            },
            success: success,
            error: error,
            complete: function () {
                console.log('complete');
            }
        })
    }
    ;
    return {
        webAjax: webAjax,
        apiAjax: apiAjax
    };
}(jQuery);