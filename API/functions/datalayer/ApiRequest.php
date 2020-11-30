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
         * Function to get a single row of the database
         * 
         * @param id the id of the row
         * @return list array with details of the id
         */
        public function getDetails($id);

        /**
         * Function to add a row to the database
         * 
         * @param model with the new values
         */
        public function post($model);

        /**
         * Function to update a row in the database
         * 
         * @param model with the new details of the row
         */
        public function put($model);

        /**
         * Function to delete a row in the databse
         * 
         * @param id the id of the row in the database
         */
        public function delete($id);
    }
?>