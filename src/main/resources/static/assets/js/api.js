function request(url, { type, data, contentType, dataType}) {
    let res;
    $.ajax({
        //the sent url
        url,
        //request type
        type: type || "GET",
        data: data && JSON.stringify(data),
        //sent data type
        contentType : contentType || "application/json",
        //return data type
        dataType: dataType || 'json',
        async:false,
        success: function(data){
            res = data
        },
        error: function(jqXHR){
            console.log("request error in ", url)
        }
    });
    return res
}

function modifyPwd(data) {
    return request(`/api/user/modify/password?password=${data}`, { type: "POST" });
}

function saveCourse(data) {
    return request(`/api/course`, { type: "POST", data });
}

function listCourse(data) {
    return request(`/api/course/list`, { type: "POST", data });
}

function listAll(data) {
    return request(`/api/course/listAll`, { type: "POST", data });
}

function listEnrolled(data) {
    return request(`/api/courseUser/listEnrolled`, { type: "POST", data });
}

function electiveByCourseId(courseId) {
    return request(`/api/courseUser/${courseId}`, { type: "GET" });
}

function dropByCourseId(courseId) {
    return request(`/api/courseUser/${courseId}/drop`, { type: "GET" });
}

function courseuserDetail(courseId){
    return request(`/api/courseUser/${courseId}/detail`, { type: "GET" });
}
