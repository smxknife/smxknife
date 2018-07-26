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

    var model = {}
    var regions = []
    regions.push('zhejiang')
    regions.push('shanghai')
    model.regions = regions.join(",")

    var industrys = []
    industrys.push('gognye')
    industrys.push('shengchan')
    model.industrys = industrys.join(",")

    $.post("/child1/cond", model, function (data) {
        console.log(data)
    })

    $.post("/child1/list", {conds: regions}, function (data) {
        console.log(data)
    })

    var conds = []
    conds.push({region:'zj', industry: 'gognye'})
    conds.push({region:'sh', industry: 'shengchan'})

    $.ajax({
        type: 'post',
        dataType: 'json',
        url: '/child1/list/obj',
        contentType: 'application/json',
        data: JSON.stringify(conds),
        success: function (data) {
            console.log(data)
        }
    })
})