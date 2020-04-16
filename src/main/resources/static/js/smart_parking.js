/**
 * 当地图加载完成时，设置停车位的颜色
 */
function loadSpace() {
    var inUseNameList = new Array();
    var notInUseNameList = new Array();
    var pathname = window.location.pathname; //如"/parkinglot/detail/1"，这里有bug，当数字大于1位就不行了
    var parkinglot_id = pathname.charAt(pathname.length-1);
    console.log(pathname);
    $.ajax({
        method: "POST",
        url: "/parkinglot/"+parkinglot_id+"/space",
    })
        .done(function( spaceList ) {
            // console.log(spaceList);   //spaces为全体车位信息
            $.each(spaceList,function(i,space){
                // console.log(i, space);
                var spaceName = "车位"+space.id;
                if(space.inUse==1){ //表示车位被使用了
                    inUseNameList.push(spaceName);
                }else {
                    notInUseNameList.push(spaceName);
                }

            });
            map.changeModelColor({
                // id: [1, 2],
                name: inUseNameList,
                color: '#FF0000'}
            );
            map.changeModelColor({
                // id: [1, 2],
                name: notInUseNameList,
                color: '#00FF00'}
            );
        });
}

/**
 * 当地图加载完成时，显示最近停车信息
 */
function recentParkingInfo() {
    // var recentParkingList = new Array();
    var pathname = window.location.pathname; //如"/parkinglot/detail/1"，这里有bug，当数字大于1位就不行了
    var parkinglot_id = pathname.charAt(pathname.length-1);

    $.ajax({
        method: "POST",
        url: "/parkinglot/"+parkinglot_id+"/space/recent",
    })
        .done(function( spaceList ) {
            // console.log(spaceList);   //spaces为全体车位信息
            $.each(spaceList,function(i,space){
                console.log(i, space);
                if(space.inUse==1){ //表示车位被使用了
                    // recentParkingList.push(space);
                    var str = "{time},{carId}进入停车场，车位{space}占用。<br>"; //车辆进入显示格式
                    str = str.replace("{time}",moment(space.parkTime).fromNow());
                    str = str.replace("{carId}",space.carId);
                    str = str.replace("{space}",space.id);
                    $("#recentIn").append(str);
                }else {
                    var str = "{time},{carId}离开停车场，车位{space}空闲。<br>"; //车辆出去显示格式
                    str = str.replace("{time}",moment(space.parkTime).fromNow());
                    str = str.replace("{carId}",space.carId);
                    str = str.replace("{space}",space.id);
                    $("#recentOut").append(str);
                    // notInUseNameList.push(spaceName);
                }

            });
        })
}

/**
 * 点击地图图标后触发，查询点击的车位的信息。
 */
function clickParkingSpace(e) {
    console.log(e);
    if (e.data_.name.search("车位") == -1)  //如果点击的元素不是车位不用管
        return;
    var space_name = e.data_.name;          //停车位名称，形如"车位1000"
    console.log("你点击了:" + space_name);
    var parking_space_id = space_name.substr(2,4);  //截取车位id
    console.log(parking_space_id);
    var pathname = window.location.pathname; //如"/parkinglot/detail/1"，这里有bug，当数字大于1位就不行了
    var parkinglot_id = pathname.charAt(pathname.length-1);

    $.ajax({
        method: "POST",
        url: "/parkinglot/"+parkinglot_id+"/space",
        // data:{"parking_space_id":1095}
        data:{"parking_space_id":parking_space_id}
    })
        .done(function( spaceList ) {
             console.log(spaceList);   //spaces为全体车位信息
            $.each(spaceList,function(i,space){
                console.log(i, space);
                if(space.inUse==1){
                    $("#spaceIsInUse").text(space.id+"使用中,"+space.carId);
                }
                else {
                    $("#spaceIsInUse").text(space.id+"空闲");
                }
            });
        });
}


function drawLeftLine(){
    $("#echart").addClass("echart_div");  //设置绘图div背景颜色和高度

    var drawLine = echarts.init(document.getElementById('echart'),'vintage');
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        color:['#52F478','#FFCD8B'],
        icon: "circle",
        legend: {
            x:'200px',
            y:'10px',
            data:['进车辆','出车辆'],
            textStyle:{
                fontSize: 14,//字体大小
                color: '#BBF6FF'//字体颜色
            },

        },
        grid: {
            left: '0px',
            // right: '0px',
            bottom: '0px',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            name:'小时',
            nameGap:'15',
            show:true,
            boundaryGap:false,
            axisLabel:{
                textStyle:{
                    color:'#FFFFFF'
                }
            },
            splitArea : {
                show : false,
            },
            splitLine : {
                show :false,
            },
            axisLine:{
                lineStyle:{
                    color:'#BBF6FF',
                    width:2,
                },
                symbol:['none','arrow']
            },
            data: [0,2,4,6,8,10,12,14,16,18,20,22,24]
        },
        yAxis: {
            type: 'value',
            name:'数量',
            show:true,
            axisLabel:{
                textStyle:{
                    color:'#FFFFFF'
                }
            },
            splitArea : {
                show : false,
            },
            splitLine : {
                show :false,
            },
            axisLine:{
                lineStyle:{
                    color:'#BBF6FF',
                    width:2,
                },
                symbol:['none','arrow']
            },
        },
        series: [
            {
                name:'进车辆',
                type: 'line',
                smooth: true,
                lineStyle:{
                    normal:{
                        color:'#4EEE79',
                        width:3
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: 'red'
                        }, {
                            offset: 1,
                            color: 'yellow'
                        }]),
                    },
                },
                data:[4,10, 40, 60, 100, 120,140,150,130,100,60,30,20]
            },
            {
                name:'出车辆',
                type:'line',
                smooth: true,
                lineStyle:{
                    normal:{
                        color:'#FFCD8B',
                        width:3
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                            offset: 0,
                            color: '#4B4558'
                        }, {
                            offset: 1,
                            color: '#516359'
                        }]),
                    },
                },
                data:[3,8, 30, 50, 110, 100,80,100,120,90,60,20,10]
            }
        ]
    };
    if (option && typeof option === "object") {
        drawLine.setOption(option);
    }
}

function load_form(){


}

function submit_notice() {
    var title = $("#title").val();
    var description = $("#description").val();
    var tags = $("#tags").val();
    console.log(title);
    console.log(description);
    console.log(tags);
    var warning="";
    if(title==""){
        warning="标题不能为空";
    }
    else if(description==""){
        warning="描述不能为空";
    }
    else if(tags==""){
        warning="标签不能为空";
    }
    console.log(title);
    if(warning!=""){
        alert(warning);
    }else { //前端验证通过，提交后端请求
        console.log("=============");
        console.log(title);
        console.log(description);
        console.log(tags);
        $.ajax({
            contentType:'application/json',
            method: "POST",
            url: "/publish",
            data:JSON.stringify({
                'title':title,
                'description':description,
                'tags':tags
            })
        })
            .done(function( resultDto ) {
                console.log(resultDto);
                if(resultDto.status == 2000){
                    alert("发布成功，三秒后跳转...")
                    window.setTimeout(function () {
                        location.href = "/notices";
                    }, 1000);
                }
            });
    }

}

function selectTag(e) {
    var element_id = e.getAttribute("id");
    var element = $("#"+element_id);
    var new_tag = element.text();

    var tag_input = $("#tags");
    var tags = tag_input.text();
    if(element.hasClass("active_my_tag")){  //标签已经是激活状态则取消激活,并将该标签从标签输入栏移除掉
        element.removeClass("active_my_tag");
        element.addClass("inactive_my_tag");
        tag_input.text(tags.replace(new_tag+",",""));
    }
    else {      //标签不是激活状态就激活，并将该标签加入标签内栏目
        element.removeClass("inactive_my_tag");
        element.addClass("active_my_tag");
        console.log(tags);
        console.log(new_tag);
        tag_input.text(tags+""+new_tag+",");
    }
    // console.log(element.getAttribute("id"));

}

/**
 * 折叠二级回复
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");  //当前选中的回复的id
    console.log("当前评论id"+id);
    var innerCommentContainer = $("#comment-" + id);   //该回复对应的回复框div
    if (innerCommentContainer.hasClass("in")) { //当前是展开状态,折叠
        innerCommentContainer.removeClass("in");
        e.classList.remove("active");
    } else { //当前是折叠状态,展开
        if (innerCommentContainer.children().length > 1) { //查询前，子元素只有一个（二级评论表单），查询后，会插入别的子元素，这种情况就无需重复调用getJSON方法
            // console.log("已查询，无需再查");
        } else {
            // console.log("第一次查询");
            $.getJSON("/noticeDetail/comment/" + id, function (data) {
                console.log(data);
                var commentDtos = data;
                $.each(commentDtos.reverse(), function (commentIndex) {
                    var div = $('<div/>');
                    div.addClass("col-lg-12")
                    var media_left = $('<div/>');
                    media_left.addClass("media-left");
                    // var img = $('<img src="#"/>');
                    // img.addClass("media-object");
                    // img.attr('src', commentDtos[commentIndex].user.avatarUrl);
                    // console.log(img.src);
                    // media_left.append(img);
                    div.append(media_left);
                    var media_body = $('<div/>');
                    media_body.addClass("media-body");
                    // media_body.html(commentDtos[commentIndex].user.name + ":&nbsp;&nbsp;" + commentDtos[commentIndex].content);
                    media_body.html( "管理员"+ ":&nbsp;&nbsp;" + commentDtos[commentIndex].content);
                    div.append(media_body);
                    var time_span1 = $('<span>');
                    time_span1.addClass("pull-right text-muted");
                    time_span1.html("评论时间:");
                    var time_span2 = $('<span>');
                    time_span2.html(moment(commentDtos[commentIndex].gmtCreate).format('YYYY-MM-DD HH:mm:ss'));
                    // time_span2.html(timestampToTime(commentDtos[commentIndex].gmtCreate));
                    time_span1.append(time_span2);
                    div.append(time_span1);
                    div.append($('<hr class="custom_hr col-lg-12">'));
                    console.log(div);

                    innerCommentContainer.prepend(div);
                })

            });
        }
        innerCommentContainer.addClass("in"); //展开二级评论框
        e.classList.add("active"); //使得图标高亮
    }
}


/**
 * 二级回复-点击二级回复详情页的“评论”按钮提交回复
 * @param e
 */
function inner_comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    console.log("input-" + commentId);
    console.log(commentId);
    console.log(content);
    // var localStorage = window.localStorage;
    // localStorage.setItem("reflashInnerComments", commentId);
    // console.log(localStorage.getItem("reflashInnerComments"));
    comment(commentId, content, 1); //二级回复type为1
}

/**
 * 提交回复(包括一级回复和二级回复)
 * @param parentId 被回复对象的id,当是提交一级回复时,表示所回复的问题的id,当提交二级回复时,表示被回复的评论的id
 * @param content
 * @param type 区分是一级回复还是二级回复
 */
function comment(parentId, content, type) {
    if (!content) {
        alert("回复内容不能为空，请输入回复内容！");
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/noticeDetail/reply",
        data: JSON.stringify(
            {
                "parentId": parentId,
                "content": content,
                "type": type
            }
        ),
        success: function (response) {
            console.log(response);
            if (response.status == 200) {
                // $("#comment_dialog_box").hide();
                alert(response.message);
                window.location.reload();
            } else {
                alert(response.message);
            }
        },
        dataType: "json"
    });
}