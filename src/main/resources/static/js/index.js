var allTeachers;
function getTeacher() {
    const xhttp = new XMLHttpRequest();
    function myFunction() {
        const responseText=this.responseText;
        const responseArr=JSON.parse(responseText);
        allTeachers=responseArr
        console.log(responseArr);
        var  tr=document.getElementById("teacher-row-template").innerHTML;
        var tblBody= "";
        for (var i = 0; i < responseArr.length; i++) {
            var teacher=responseArr[i];

          var newTr= tr
                .replaceAll(":id",teacher.id)
                .replaceAll(":index",i)
                .replace(":name",teacher.name)
                .replace(":surname",teacher.surname)
                .replace(":age",teacher.age)
                .replace(":university",teacher.university.name)
                .replace(":email",teacher.email);
          tblBody+= newTr;
        }
        document.getElementById("tbl-teachers-body").innerHTML=tblBody;
    }
    xhttp.onload = myFunction;
    xhttp.open("GET", "http://localhost:8080/teachers?" +
        "name=" + val("name") +"&"+
        "surname=" + val("surname") +"&"+
        "email=" + val("email") +"&"+
        "age=" + val("age") +"&"+
        "university_id=" + val("university_id"));

    xhttp.send();
}

function val(elementId){
    var value=document.getElementById(elementId).value;
    return value!==null && value!=='undefined'? value: '';
}

var selectedId;
function select(id) {
    selectedId=id;
}
function deleteTeacher(id) {
    const xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            getTeacher(); // yenilənmiş listi çağırır
        }
    }

    xhttp.open("DELETE", "http://localhost:8080/teachers?id=" + id);
    xhttp.send();
}

function insertTeacher(){
    const xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            getTeacher(); // yenilənmiş listi çağırır
        }
    }
    var teacher ={
        name:document.getElementById("create_name").value,
        surname:document.getElementById("create_surname").value,
        age:document.getElementById("create_age").value,
        email:document.getElementById("create_email").value,
        university:{
            id:document.getElementById("create_university_id").value
        }
    };

    xhttp.open("POST", "http://localhost:8080/teachers");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(teacher));
}
function selectForUpdate(index){
    var selectedTeacher=allTeachers[index];
    document.getElementById("update_name").value=selectedTeacher.name;
    document.getElementById("update_surname").value=selectedTeacher.surname;
    document.getElementById("update_age").value=selectedTeacher.age;
    document.getElementById("update_email").value=selectedTeacher.email;
    document.getElementById("update_university_id").value=selectedTeacher.university.id;
    selectedId=selectedTeacher.id;
}

function updateTeacher(){
    const xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            getTeacher(); // yenilənmiş listi çağırır
        }
    }
    var teacher ={
        id:selectedId,
        name:document.getElementById("update_name").value,
        surname:document.getElementById("update_surname").value,
        age:document.getElementById("update_age").value,
        email:document.getElementById("update_email").value,
        university:{
            id:document.getElementById("update_university_id").value
        }
    };

    xhttp.open("PUT", "http://localhost:8080/teachers");
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(teacher));
}
window.addEventListener("load",getTeacher);