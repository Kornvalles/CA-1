
function getJokes() {
    fetch("https://kodebanditterne.dk/CA-1/api/joke/all")
            .then(res => res.json())
            .then(data => {

                let rows = data.map(function (name) {
                    let row = "<tr> <td>" + name.joke + "</td> <td>" + name.reference +
                            "</td> <td>" + name.rating + "</td> </tr>";
                    return row;
                })

                let rowsAsString = rows.join("");
                document.getElementById("tbody").innerHTML = rowsAsString;
            })
}
document.getElementById("button").onclick = getJokes;

function getRandomJoke() {
    fetch("https://kodebanditterne.dk/CA-1/api/joke/random")
            .then(res => res.json())
            .then(data => {

                let rows = data.map(function (name) {
                    let row = "<tr> <td>" + name.joke + "</td> <td>" + name.reference +
                            "</td> <td>" + name.rating + "</td> </tr>";
                    return row;
                })
                let rowsAsString = rows.join("");
                document.getElementById("tbody2").innerHTML = rowsAsString;
            })
}
document.getElementById("button2").onclick = getRandomJokes;
