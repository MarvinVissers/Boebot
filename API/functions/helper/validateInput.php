<?php
    /**
    * @author Marvin Vissers
    */
    class Validate {
        /*
        * Validaiton functions to help type less code
        */
        // Function to check if the input is empty
        private function validateEmpty($input) {
            if ($input == '' || $input = null) {
                // Input is empty
                return false;
            } else {
                // Input is filled
                return true;
            }
        }

        // Function to check if the input has the right pattern
        private function validateRegex($input, $pattern) {
            if (!preg_match("$pattern", $input)) {
                // Input is not equal to pattern
                return false;
            } else {
                // Input is equal to pattern
                return true;
            }
            
        }

        // Function to check if the input is between the required lengths
        private function validateLength($input, $minLength, $maxLength) {
            if (strlen($input) < $minLength || strlen($input) > $maxLength) {
                // Input is not between the min and max length
                return false;
            } else {
                // Input is between the min an max length
                return true;
            }
        }

        /*
        * Validation for all input fields 
        */
        // Validation for regular input
        public function validateInput($input, $regexPattern, $minLength, $maxLength) {
            // Checking if one of the validation functions return false
            if ($this->validateEmpty($input) && $this->validateRegex($input, $regexPattern) && $this->validateLength($input, $minLength, $maxLength)) {
                // All validations passed     
                return true;
            } else {
                // 1 or more validations failed  
                return false;
            }
        }

        // Validaiton for emails
        public function validateEmail($email) {
            // Checking mail is correct and between the required lengths
            if (!filter_var($email, FILTER_VALIDATE_EMAIL) || $this->validateLength($email, 5, 100)) {
                // All validation failed
                return true;
            } else {
                // One or more validations failed
                return false;
            }
        }

        // Checking if the password are the same
        public function comparePasswords($password, $passwordConfirm) {
            // Checking if the passwords are the same
            if ($password == $passwordConfirm) {
                return true;
            } else {
                return false;
            } 
        }
    }
?>