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
    var desc = $("#desc").val();
    var tags = $("#tags").val();
    console.log(title);
    console.log(desc);
    console.log(tags);
    var warning="";
    if(title==""){
        warning="标题不能为空";
    }
    else if(desc==""){
        warning="描述不能为空";
    }
    else if(tags==""){
        warning="标签不能为空";
    }
    console.log(title);
    if(warning!=""){
        alert(warning);
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

