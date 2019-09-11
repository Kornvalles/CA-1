function getStudents() {
    fetch("https://kodebanditterne.dk/CA-1/api/student/all")
  .then(res => res.json())
  .then(data => {
   
    let rows = data.map(function(name) {
        let row="<tr> <td>"+name.username+"</td> <td>"+name.name+
                "</td> <td>"+name.color+"</td> </tr>";
        return row;
    })
    
    let rowsAsString = rows.join("");
    document.getElementById("tbody").innerHTML = rowsAsString;
})
}

document.getElementById("button").onclick = getStudents;


