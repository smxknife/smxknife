$(function(){
    var param = [];

    param[0] = {
        name: 'hello',
        age: 1
    }
    param[1] = {
        name: 'test',
        age: 2
    }

    $.post("/child1/index", param, function(data) {
        $("#json").text(data)
    })
})