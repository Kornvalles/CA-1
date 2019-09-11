var url = "/student/all";

function getStudents(url) {
    fetch(url)
  .then(res => res.json())
  .then(data => {
   
    let rows = data.map(function(name) {
        let row="<tr> <td>"+name.name+"</td> <td>"+name.studentid+
                "</td> <td>"+name.color+"</td> </tr>";
        return row;
    })
    
    let rowsAsString = rows.join("");
    document.getElementById("tbody").innerHTML = rowsAsString;
})
}

document.getElementById("button").onclick = getStudents;


