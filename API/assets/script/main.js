
/**
 * File with all main JavaScript functions
 */

/**
 * Function to close and open the navbar
 */
function toggleNavbar() {
    // Getting the navbar and close/open icon
    document.getElementById("navbar-toggle").classList.toggle("navbar-toggle--closed");
    document.getElementById("navbar").classList.toggle("navbar--close");

    // Making the content larger
    document.getElementById("content").classList.toggle("content--full-width");
}

// Function to add the active/hover styling to the current item of the navbar
function addActieveNavbar(item) {
    // Getting the item and adding the navbar--active class
    document.getElementById(item).classList.add("navbar--active");
}

/**
 * Function to convert local date|time of race to local date|time of user
 */
function convertRaceLocalDateTimeToUserLocalDateTime(dateRow, timeRow, date) {
    // Getting the td of the race date and time
    var tdRaceDate = document.getElementById(dateRow);
    var tdRaceTime = document.getElementById(timeRow);

    // Converting to local date
    var localDate = date.toLocaleDateString("en-GB", {
                        year: "numeric",
                        month: "2-digit",
                        day: "2-digit",
                    });

    // Filling the td with new 
    tdRaceDate.innerHTML = localDate;
    tdRaceTime.innerHTML = date.toLocaleTimeString();
}

/**
 * Function to open track info calender race
 */
function toggleTrackInfo(trackRow, race){
    // Getting the row and toggling the row--hidden class to make it visible or start hiding it
    document.getElementById(trackRow).classList.toggle("row--hidden");
    // Getting the race row and toggling the higliged class
    document.getElementById(race).classList.toggle("row--active");
}

/**
 * Function to change the bet status on bet-overview
 */
function changeStatus() {
    // Getting the combobox value
    var status = document.getElementById("status").value;
    // Getting the url of
    var url = window.location.href.toString();

    // Checking if bet status has been changed
    if (url.includes("status", 0)) {
        // Looking in the url for the parameter and value to replace with the new status
        var newUrl = url.replace(/\bstatus=[a-zA-Z]{1,50}\b/, 'status=' + status);

        // Refreshing the page with the new url
        location.replace(newUrl);
    } else {
        // The status has not yet been updated so the status is placed new in the url
        location.replace(url + "?status=" + status);
    }
}

/**
 * Function to open the delete modal of the bet
 */
function openDeleteBetModal(betID, betPoints) {
    // Putting the bet ID in the input field
    document.getElementById("betID").value = betID;
    // Putting the points in the input field
    document.getElementById("betPoints").value = betPoints;

    // Making the modal visible
    document.getElementById("deleteBetModal").classList.add("modal--visible");
    document.getElementById("deleteBetModal-content").classList.add("modal__content-show");
}

/**
 * Function to open the update modal of the bet
 */
function openUpdateBetModal(betID, betOn, betPointsNew, betPointsOld) {
    // Putting the bet ID in the input field
    document.getElementById("updateBetID").value = betID;
    // Putting the bet categorie in the input field
    document.getElementById("updateBetOnId").value = document.getElementById(betOn).value;
    // Putting the new bet points in the input field
    document.getElementById("updateBetPointsNew").value = document.getElementById(betPointsNew).value;
    // Putting the old bet points in the input field
    document.getElementById("updateBetPointsOld").value = betPointsOld;

    // Making the modal visible
    document.getElementById("updateModal").classList.add("modal--visible");
    document.getElementById("updateModal-content").classList.add("modal__content-show");
}

/**
 * Function to open the bankrupt modal of the profile
 */
function openBankruptModal() {
    // Making the modal visible
    document.getElementById("profileBankruptModal").classList.add("modal--visible");
    document.getElementById("profileBankruptModal-content").classList.add("modal__content-show");
}

/**
 * Function to close a modal
 */
function closeModal(modal, content) {
    document.getElementById(modal).classList.remove("modal--visible");
    document.getElementById(content).classList.remove("modal__content-show");
}

/**
 * Function to toggle edit stage
 */
function toggleEditBetState(betID) {
    // Getting the bet card and setting the card in edit state
    var cardEdit = document.getElementById(betID).getElementsByClassName("card__edit");
    var cardInfo = document.getElementById(betID).getElementsByClassName("card__info");

    for (let index = 0; index < cardEdit.length; index++) {
        // Getting the edit states and displaying them
        cardEdit[index].classList.toggle("card--hide");
    }

    for (let index = 0; index < cardInfo.length; index++) {
        // Getting the info states and hi them
        cardInfo[index].classList.toggle("card--hide");
    }
}

/**
 * Function to change categorie in the Next Grand Prix page
 */
function changeBetCategorie(categorie) {
    // Getting the url of
    var url = window.location.href.toString();

    // Checking if bet categorie has been changed
    if (url.includes("categorie", 0)) {
        // Looking in the url for the parameter and value to replace with the new categorieID
        var newUrl = url.replace(/\bcategorie=[0-9]{1,50}\b/, 'categorie=' + categorie);

        // Refreshing the page with the new url
        location.replace(newUrl);
    } else {
        // The categorieID has not yet been updated so the categorieID is placed new in the url
        location.replace(url + "?categorie=" + categorie);
    }
}

/**
 * Function to toggle bet categorie in the Next Grand Prix page
 */
function toggleBetCategorie(categorie) {
    // Chaning the icon in the bet categorie head
    document.getElementById("betCategorieIcon" + categorie).classList.toggle("bet-categorie--icon--open");

    // Setting the bet categorie body open
    document.getElementById("betCategorieBody" + categorie).classList.toggle("bet-categorie__body--open");
}

/**
 * Function to calculate the potential payout of a bet
 * 
 * @param categorie The categorie of the bet. This is an int
 * @param total This is wheter the total of the bets should be updated
 */
function betPointsCategoriePossilbePayout(categorie, total) {
    // Getting the bet on odds
    var odds = document.getElementById("betOnCategorieOdds" + categorie);
    // Getting the points the user is betting
    var userPoints = document.getElementById("betOnCategoriePoints" + categorie);
    // Getting the input where the potential payout is going to
    var possiblePayout = document.getElementById("betOnCategoriePotentialPayout" + categorie);

    // Calculating the possible payout
    possiblePayout.value = Math.round(odds.value * userPoints.value);

    // Updating the total possible payout
    if (total === true) {
        totalBetPointsPossilbePayout();
    }

    // Updating the points in the webstorage
    switch (categorie) {
        case 4: setPointsCategorie(4, userPoints.value);
            break;
        case 3: setPointsCategorie(3, userPoints.value);
            break;
        case 2: setPointsCategorie(2, userPoints.value);
            break;
        default: setPointsCategorie(1, userPoints.value);
            break;
    }
}

/**
 * Function to calculate the total potential payout of the bets
 */
function totalBetPointsPossilbePayout() {
    // Getting the span for the total possible payout
    var totalPayoutSpan = document.getElementById("betTotalPossilbePayout");
    let total = 0;

    for (let index = 1; index <= 4; index++){
        // Getting the possible payout per categorie and adding them to the total
        let totalCategorie = document.getElementById("betOnCategoriePotentialPayout" + index).value
        
        // Checking if value is higher than 0
        if (totalCategorie > 0) {
            total = +total + +totalCategorie;
        }      
    }

    // Setting the total in the span
    totalPayoutSpan.innerHTML = total;
}

/**
 * Function to remove the underscore from circuit names on betting overview page
 */
// function removeUnderScoreCircuitName() {
//     var betCards = document.getElementsByClassName("card-subtitle");

//     // Looping through the cards
//     for (let i = 0; i < betCards.length; i++) {
//         const card = betCards[i].innerHTML;
        
//         card.replace("_", " ");
//     }
// }
