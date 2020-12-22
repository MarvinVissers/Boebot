<?php
    /**
     * @author Marvin Vissers
     * 
     * Interface for the basic API requests
     */
    interface ApiRequest {
        /**
         * Function to get everthing out of a table
         * 
         * @return list array with all rows of the table
         */
        public function getAll();

        /**
         * Function to add a row to the database
         * 
         * @param model with the new values
         */
        public function post($model);
    }
?>