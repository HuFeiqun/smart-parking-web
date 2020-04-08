

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

