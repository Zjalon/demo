common.webAjax({
    url: "sys/getPermissionList",
    data: {}
}, function (data, textStatus, jqXHR) {
    console.info(data);
    console.info(textStatus);
    console.info(jqXHR);
}, function (error) {
    console.info(error);
});