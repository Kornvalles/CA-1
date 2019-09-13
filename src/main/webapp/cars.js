/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global fetch */

function getCars() {
    fetch("https://kodebanditterne.dk/CA-1/api/car/all")
            .then(res => res.json())
            .then(data => {

                let rows = data.map(function (name) {
                    let row = "<tr> <td>" + name.year + "</td> <td>" + name.make +
                            "</td> <td>" + name.model + "</td><td>" + name.price + "</td> </tr>";
                    return row;
                });

                let rowsAsString = rows.join("");
                document.getElementById("tbody").innerHTML = rowsAsString;
            });
}

document.getElementById("button").onclick = getCars;

function filterCars() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("carMakeInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("carTable");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

document.getElementById("carMakeInput").onkeyup = filterCars;