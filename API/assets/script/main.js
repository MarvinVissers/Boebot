
/**
 * File with all main JavaScript functions
 */

/**
 * Function to get the log from the API
 * @param callback
 */
function fillLog(callback) {
    // Makking an http request
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        // Checking if its done
        if (httpRequest.readyState === 4) {
            // checking if its succesfull
            if (httpRequest.status === 200) {
                // Calling the function with the response
                callback(httpRequest.responseText);
            }
        }
    };
    // Setting the url of the API
    httpRequest.open('GET', "https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/log/?selector=ae026dd58cd57fd2&validator=4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08&action=get");
    httpRequest.send();
}

/**
 * Function to fill the log
 */
fillLog(function (resultAPI) {
    // Getting the log element
    var log = document.getElementById("log");

    // Emptying the log
    log.innerHTML = "";

    // Setting the JSON to an array
    var resultsArray = JSON.parse(resultAPI);
    console.log("JSON opgehaald");

    Object.keys(resultsArray).forEach(function(key) {
        // Filling the log
        log.innerHTML = log.innerHTML + resultsArray[key]["boebot"] + ": " + resultsArray[key]["text"] + "<br>";
    });
});

/**
 * Function to get boebot name and set it in the modal
 */
function setBoebotInModal(boebot) {
    console.log(boebot);

    // Setting the Boebot in the modal
    document.getElementById("boebotNameTest").value = boebot;
    document.getElementById("boebotNameRoute").value = boebot;
}

/**
 * Function to set a obstacle on the grid and save it in the database
 * @param direction the direction on the grid
 * @param x1 the starting point from top to bottom
 * @param y1 the starting point from left to right
 * @param x2 the end point from top to bottom
 * @param y2 the end point from left to right
 */
function toggleObstacle(direction, x1, y1, x2, y2) {
    /**
     * Setting the coordinates in the input fields
     */
    // Add obstacle start points
    document.getElementById("addStartX").value = x1;
    document.getElementById("addStartY").value = y1;
    // Add obstacle end points
    document.getElementById("addEndX").value = x2;
    document.getElementById("addEndY").value = y2;

    // Remove obstacle start points
    document.getElementById("removeStartX").value = x1;
    document.getElementById("removeStartY").value = y1;
    // Remove obstacle end points
    document.getElementById("removeEndX").value = x2;
    document.getElementById("removeEndY").value = y2;

    // // Setting the line active
    // document.getElementById(direction + x1 + y1).classList.toggle("grid--obstacle");
    // console.log("Eerste coordinaten " + x1 + "  " + y1);
    // console.log("Tweede coordinaten " + x2 + "  " + y2);
}

/**
 * Function to refresh the log
 */
function refreshLog() {
    // Reloading the page to load the new log
    window.location = "logPart"
    window.location.reload(true);
}