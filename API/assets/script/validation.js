/**
 * @author Marvin Vissers
 * 
 * Front-end validation for the website
 * 
 */

/**
 * Variabels that will be used through the document
 */
// buttons
const btnForm = document.getElementById("btnForm");
// Input feedback styling
const inputGood = "input--good";
const inputBad = "input--bad";
// Feedback message styling
const feedbackGood = "feedback--good";
const feedbackBad = "feedback--bad"; 

/**
 * Login form validaiton
 */
// Login Username validation
function loginUsernameValidation() {
    // Getting the username and the username feedback
    const username = document.getElementById("username");  
    const usernameFeedback = document.getElementById("usernameFeedback");

    // checking if username empty
    if (username.value == '') {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must be filled in.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if the username has any spaces
    else if(username.value.match("[\\s]")) {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must not have any spaces.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if the username is shorter then 25 characters
    else if (username.value.length > 25) {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must be shorter then 25 characters.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // If everything is good, than this
    else {
        // Removing classes for bad feedback
        username.classList.remove(inputBad);
        usernameFeedback.classList.remove(feedbackBad);

        // Adding classes for good feedback
        username.classList.add(inputGood);
        usernameFeedback.classList.add(feedbackGood);

        // Enabeling the button
        btnForm.disabled = false;
    }
}

// Login password validation
function loginPasswordValidation() {
    // Getting the password and the password feedback
    const password = document.getElementById("password");  
    const passwordFeedback = document.getElementById("passwordFeedback")

    // checking if its empty
    if (password.value == '') {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be filled in.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if the password has any spaces
    else if(password.value.match("[\\s]")) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must cannot cotain spaces.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if the password is shorter then 20 characters
    else if (password.value.length > 20) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be shorter then 20 characters.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // If everything is good, than this
    else {
        // Removing classes for bad feedback
        password.classList.remove(inputBad);
        passwordFeedback.classList.remove(feedbackBad);

        // Adding classes for good feedback
        password.classList.add(inputGood);
        passwordFeedback.classList.add(feedbackGood);

        // Enabeling the button
        btnForm.disabled = false;
    }
}

/**
 * 
 * Registration form validaiton
 * 
 */
// Email validation
function emailValidation() {
    // Getting the email and the email feedback
    const email = document.getElementById("email");  
    const emailFeedback = document.getElementById("feedbackEmail");

    // checking if its empty
    if (email.value == '') {
        // Removing classes for good feedback
        email.classList.remove(inputGood);
        emailFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        email.classList.add(inputBad);
        emailFeedback.classList.add(feedbackBad);

        // Adding feedback message
        emailFeedback.innerHTML = "* Email must be filled in.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Check if email is valid or not
    else if (!email.value.match(/^[^@\s]+@[^@\s\.]+\.[^@\.\s]+$/)) {
        // Removing classes for good feedback
        email.classList.remove(inputGood);
        emailFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        email.classList.add(inputBad);
        emailFeedback.classList.add(feedbackBad);

        // Adding feedback message
        emailFeedback.innerHTML = "* Email is not valid.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if email is 100 characters or shorter
    else if (email.value.length > 100) {
        // Removing classes for good feedback
        email.classList.remove(inputGood);
        emailFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        email.classList.add(inputBad);
        emailFeedback.classList.add(feedbackBad);

        // Adding feedback message
        emailFeedback.innerHTML = "* Email must be shorter then 100 characters.";

        // Disabeling the button
        btnForm.disabled = true;
    }
    
    // If everything is good, than this
    else {
        // Removing classes for bad feedback
        email.classList.remove(inputBad);
        emailFeedback.classList.remove(feedbackBad);

        // Adding classes for good feedback
        email.classList.add(inputGood);
        emailFeedback.classList.add(feedbackGood);

        // Adding feedback message
        emailFeedback.innerHTML = "OK";

        // Enabeling the button
        btnForm.disabled = false;
    }
}

// Username validation
function usernameValidation() {
    // Getting the username and the username feedback
    const username = document.getElementById("username");  
    const usernameFeedback = document.getElementById("feedbackUsername");

    // checking if its empty
    if (username.value == '') {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must be filled in.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if there are only letters filled in
    else if (!username.value.match(/^[a-zA-Z0-9_.-]*$/)) {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must consist of letters and numbers.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking if the lastname is shorter then 50 characters
    else if (username.value.length > 25) {
        // Removing classes for good feedback
        username.classList.remove(inputGood);
        usernameFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        username.classList.add(inputBad);
        usernameFeedback.classList.add(feedbackBad);

        // Adding feedback message
        usernameFeedback.innerHTML = "* Username must be shorter then 25 characters.";

        // Disabeling the button
        btnForm.disabled = true;
    }

    // If everything is good, than this
    else {
        // Removing classes for bad feedback
        username.classList.remove(inputBad);
        usernameFeedback.classList.remove(feedbackBad);

        // Adding classes for good feedback
        username.classList.add(inputGood);
        usernameFeedback.classList.add(feedbackGood);

        // Adding feedback message
        usernameFeedback.innerHTML = "OK";

        // Removing the disabeld from the button
        btnForm.disabled = false;
    }
}

// Creating a password validation, created password must a decently strong
function passwordValidation() {
    // Getting the password, passwordConfrim and the password feedback
    const password = document.getElementById("password");
    const passwordConfirm = document.getElementById("passwordConfirm");
    const passwordFeedback = document.getElementById("feedbackPassword");

    // checking if its empty
    if (password.value == '') {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(inputBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be filled in";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Checking if the password has a lowercase letter
    else if (!password.value.match(/[a-z]/g)) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be filled in";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Checking if the password has an Uppercase letter
    else if(!password.value.match(/[A-Z]/g)) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must contain upper case letter";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Checkng is password contains a number
    else if (!password.value.match(/[0-9]/g)) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must contain a number";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Checing if passwords contain a special charater
    else if (!password.value.match(/[@+_.!?|]/g)) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must contain a special charater.";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }
    
    // Checking if passwords if longer than 7 caracther
    else if (password.value.length < 8) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be 8 charatcers or longer.";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Checking if passwords is 20 characters or shorter
    else if (password.value.length > 20) {
        // Removing classes for good feedback
        password.classList.remove(inputGood);
        passwordFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        password.classList.add(inputBad);
        passwordFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordFeedback.innerHTML = "* Password must be 20 charatcers or shorter.";

        // Disabeling the button and the passwordConfirm
        passwordConfirm.disabled = true;
        btnForm.disabled = true;
    }

    // Evertything is in the password
    else {
        // Removing classes for good feedback
        password.classList.remove(inputBad);
        passwordFeedback.classList.remove(feedbackBad);

        // Adding classes for bad feedback
        password.classList.add(inputGood);
        passwordFeedback.classList.add(feedbackGood);

        // Adding feedback message
        passwordFeedback.innerHTML = "OK";

        // Removing the disabeld from the button and the passwordConfrim
        passwordConfirm.disabled = false;
        btnForm.disabled = false;
    }
}

// Confirming the new password validation
function passwordConfirmValidation() {
    const password = document.getElementById("password");
    const passwordConfirm = document.getElementById("passwordConfirm");
    const passwordConfirmFeedback = document.getElementById("feedbackPasswordConfirm");

    // Checking if new password matches passwordconfirm
    if (passwordConfirm.value.localeCompare(password.value) === 0) {
        // Removing classes for bad feedback
        passwordConfirm.classList.remove(inputBad);
        passwordConfirmFeedback.classList.remove(feedbackBad);

        // Adding classes for good feedback
        passwordConfirm.classList.add(inputGood);
        passwordConfirmFeedback.classList.add(feedbackGood);

        // Adding feedback message
        passwordConfirmFeedback.innerHTML = "OK";

        // Removing the disabeld from the button
        btnForm.disabled = false;
    }

    // passwords do not match
    else{
        // Removing classes for good feedback
        passwordConfirm.classList.remove(inputGood);
        passwordConfirmFeedback.classList.remove(feedbackGood);

        // Adding classes for bad feedback
        passwordConfirm.classList.add(inputBad);
        passwordConfirmFeedback.classList.add(feedbackBad);

        // Adding feedback message
        passwordConfirmFeedback.innerHTML = "* Passwords don't match";

        // Enableing the button
        btnForm.disabled = true;
    }
}

/**
 * 
 * Place bet validation
 * 
 */

/**
 * Function to check if the points the user is betting are valid
 * 
 * @param betCategorieID the categorie of the bet. This is the database ID
 * @param userPoints the points the user has on that moment
 */
function betPointsCategorieValidation(betCategorieID, userPoints) {
    // Getting the points bet by the user
    var betOnCategoriePoints = document.getElementById("betOnCategoriePoints" + betCategorieID);

    // Checking if the input is only numbers
    if (!betOnCategoriePoints.value.match(/^[0-9]+$/)) {
        // Disabeling the button
        btnForm.disabled = true;
    }

    // Checking the length
    else if (betOnCategoriePoints.value.length > 11 || betOnCategoriePoints.value.length == 1) {
        // Disabeling the button
        btnForm.disabled = true;
    } 

    // Checking if user is betting more than he/she has
    else if (betOnCategoriePoints.value > userPoints) {
        btnForm.disabled = true;
    }
    
    // Everything is oke
    else {
        // Enableing the button
        btnForm.disabled = false;
    }
}
