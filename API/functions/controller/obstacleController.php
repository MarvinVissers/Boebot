<?php
    /**
     * @author Marivn Vissers
     */

    // Linking to classes in the database layer
    require_once("../functions/datalayer/database/obstacleDB.php");
    // Linking to the validation class
    require_once("../functions/helper/validateInput.php");

    class ObstacleController {
        // Variabels for the needed logDB en Validate classes
        private $obstacleDB;
        private $validate;

        public function __construct() {
            $this->obstacleDB = new ObstacleDB();
            $this->validate = new Validate();
        }

        /**
         * Function to add an obstacle to the database
         * @param obstacleModel the model of the obstacle
         */
        public function addObstacle($obstacleModel) {
            // Sending the variabels to the datalayer function
            $this->obstacleDB->addObstacle($obstacleModel);
            
            // Reloading the page when done
            header("Location: grid");

        }

        /**
         * Function to remove an obstacle to the database
         * @param obstacleModel the model of the obstacle
         */
        public function removeObstacle($obstacleModel) {
            // Sending the variabels to the datalayer function
            $this->obstacleDB->removeObstacle($obstacleModel);

            // Reloading the page when done
            header("Location: grid");
        }

        /**
         * Getting all the obstacles
         * @return array a list with all obstacles
         */
        public function getObstacles() {
            $listObstacles = array();

            $listObstacles = $this->obstacleDB->getObstacles();

            return $listObstacles;
        }

        /**
         * Function to remove all obstacles when a new grid is made
         */
        public function removeAllObstacles() {
            // Sending the variabels to the datalayer function
            $this->obstacleDB->removeAllObstacles();
        }

        /**
         * Checking if an obstacle is in the grid
         * @param $gridModel the line in the grid
         * @param $listObstacles the list of the obstacles
         * @return bool true if obstacles is on line false if it isn't
         */
        public function checkObstacleGrid($gridModel, $listObstacles) {
//            var_dump($gridModel);

            // Checking if the obstacle is on the line
            $obstacle = $this->loopThroughObstacleList($gridModel, $listObstacles, 0);

            if ($obstacle) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Function to loop throug the array and check if the obstacle is equal
         * @param $gridModel the line on the grid
         * @param $listObstacles the list with all the obstacles
         * @param $offset the offset of the obstacle array
         * @return bool true if obstacle is on the line false if it isn't
         */
        private function loopThroughObstacleList($gridModel, $listObstacles, $offset) {
            // Creating variabels for the grid line
            $row1 = $gridModel->getX1();
            $column1 = $gridModel->getY1();
            $row2 = $gridModel->getX2();
            $column2 = $gridModel->getY2();

            // Checking if there are stil obstacles in the array
            if ($offset < count($listObstacles)) {
                // Getting the offset of the array
                $listObstacleOffset = $listObstacles[$offset];

                // Creating variabels for the obstacle of the obstacle list
                $obstacleRow1 = $listObstacleOffset->getRow1();
                $obstacleColumn1 = $listObstacleOffset->getColumn1();
                $obstacleRow2 = $listObstacleOffset->getRow2();
                $obstacleColumn2 = $listObstacleOffset->getColumn2();

                // Checking if all for points are the same
                if ($obstacleRow1 == $row1 && $obstacleColumn1 == $column1 && $obstacleRow2 == $row2 && $obstacleColumn2 == $column2) {
                    // Obstacle found
                    return true;
                } else {
                    // Offset updaten
                    $offset++;

                    // Going throug the function again
                    return $this->loopThroughObstacleList($gridModel, $listObstacles, $offset);
                }
            } else {
                // Obstacle not found
                return false;
            }
        }
    }
?>