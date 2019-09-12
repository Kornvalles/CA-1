/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getJokes() {
    fetch("https://kodebanditterne.dk/CA-1/api/joke/all")
  .then(res => res.json())
  .then(data => {
   
    let rows = data.map(function(name) {
        let row="<tr> <td>"+name.joke+"</td> <td>"+name.reference+
                "</td> <td>"+name.rating+"</td> </tr>";
        return row;
    })
    
    let rowsAsString = rows.join("");
    document.getElementById("tbody").innerHTML = rowsAsString;
})
}

document.getElementById("button").onclick = getStudents;


