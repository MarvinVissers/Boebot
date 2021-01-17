package Controller;

import Model.Node;
import Model.Obstacle;

import java.util.ArrayList;

public class CalculateRouteController {

    public CalculateRouteController() {

    }

    /**
     * Function to calculate the fastest route
     * @param startNode the starting point
     * @param endNode the ending point
     * @param obstacleList the list of obstacles on the way
     * @return the list with the fastest route
     */
    public ArrayList<Node> getFastestRoute(Node startNode, Node endNode, ArrayList<Obstacle> obstacleList) {
        // Getting the grid size plus 1 because it starts at 0
        int iRouws = endNode.getRow() + 1;
        int iColumns = endNode.getCol() + 1;

        // Calculating the grid size
        int iGridSize = iRouws * iColumns;

        // Getting the fastest route
        ArrayList<Node> fastestRoute = loopThroughGrid(startNode, startNode, endNode, obstacleList, new ArrayList<Node>(), iGridSize, 0);

        System.out.println(fastestRoute);
        System.out.println(obstacleList);

        // Giving back the fastest route
        return fastestRoute;
    }

    /**
     * Function to go through the grid and check for options
     * @param startNode the starting node of the Boebot
     * @param endNode the ending node for the the Boebot
     * @param obstacleList the list with obstacles
     * @param fastestRoute the array with the fastest route
     * @param iGridSize the size of the grid
     * @param iOffset the offset of the grid size, the amount of possible options
     * @return the fastest route the end node
     */
    private ArrayList<Node> loopThroughGrid(Node startNode, Node currentPoint, Node endNode, ArrayList<Obstacle> obstacleList, ArrayList<Node> fastestRoute, int iGridSize, int iOffset) {
        if (iOffset < iGridSize) {
            // Checking if next point is with obstacle
            ArrayList<Node> currentNodeOptions = checkAroundCurrentPoint(startNode, endNode);
            ArrayList<Node> currentNodeOptionsFiltered = filterObstacleOptions(startNode, currentNodeOptions, obstacleList, new ArrayList<Node>(), 0);
//            System.out.println("Filtered options = " + currentNodeOptionsFiltered.size());

            // Creating a variable for the last point
            Node lastPoint = startNode;

            // Updating the last point
            if (fastestRoute.size() > 0) {
                int iLastPoint = iOffset - 1;
                lastPoint.setRow(fastestRoute.get(iLastPoint).getRow());
                lastPoint.setCol(fastestRoute.get(iLastPoint).getCol());
            }

            // Getting the best option
            Node bestOption = getNextOption(lastPoint, currentPoint, endNode, currentNodeOptionsFiltered);
            System.out.println(bestOption);

            // Adding the point to the list
            fastestRoute.add(bestOption);

            // Setting the new current point
            currentPoint.setRow(bestOption.getRow());
            currentPoint.setCol(bestOption.getCol());

            if (currentPoint.getRow() == endNode.getRow() && currentPoint.getCol() == endNode.getCol()) {
                return fastestRoute;
            }

            // Offset updaten
            iOffset++;
            // Going through the function again
            return loopThroughGrid(startNode, currentPoint, endNode, obstacleList, fastestRoute, iGridSize, iOffset);
        } else {
            return fastestRoute;
        }
    }

    /**
     * Function to check for options around the Boebot
     * @param currentPoint the current point of the Boebot
     * @param endNode the ending node of the Boebot
     * @return array with possible options
     */
    private ArrayList<Node> checkAroundCurrentPoint(Node currentPoint, Node endNode) {
        // Creating an arraylist for the options
        ArrayList<Node> currentPointOptions = new ArrayList<>();

        // Int for the next row
        int iNextRow = currentPoint.getRow() + 1;
        int iNextCol = currentPoint.getCol() + 1;
        int iLastRow = currentPoint.getRow() - 1;
        int iLastCol = currentPoint.getCol() - 1;

        // Checking if the boebot is on the start row
        if (currentPoint.getRow() == 0) {
            // 1 less row option
            currentPointOptions.add(new Node(iNextRow, currentPoint.getCol()));
        }
        // Checking if the boebot is on the end row
        if (currentPoint.getRow() == endNode.getRow()) {
            // 1 less row option
            currentPointOptions.add(new Node(currentPoint.getRow(), iNextCol));
        }
        // Checking if the boebot is on the start column
        if (currentPoint.getCol() == 0) {
            // No new column options
            currentPointOptions.add(new Node(currentPoint.getRow(), iNextCol));
        }
        // Checking if the boebot is on the end column
        if (currentPoint.getCol() == endNode.getCol()) {
            // No new column options
            currentPointOptions.add(new Node(iNextRow, currentPoint.getCol()));
        }
        // Checking if the boebot is on none of the four
        if (currentPoint.getRow() != 0 && currentPoint.getRow() != endNode.getRow() && currentPoint.getCol() != 0 && currentPoint.getCol() != endNode.getCol()) {
            // Adding options for the column and row
            currentPointOptions.add(new Node(iNextRow, currentPoint.getCol()));
            currentPointOptions.add(new Node(currentPoint.getRow(), iNextRow));

            currentPointOptions.add(new Node(iLastRow, currentPoint.getCol()));
            currentPointOptions.add(new Node(currentPoint.getRow(), iLastCol));
        }

        // Giving back the options
        return currentPointOptions;
    }

    /**
     * Function to check if an obstacle is on the line
     * @param currentPoint the currentpoint of the Boebot
     * @param nextPoint the next point on the route
     * @param obstacleList the list with the obstacle
     * @return true if obstacle found false if no obstacles were found
     */
    private boolean checkObstacleOnLine(Node currentPoint, Node nextPoint, ArrayList<Obstacle> obstacleList) {
        // Looping through the array
        for(Obstacle obst : obstacleList) {
            // Checking if route if going down or up in the grid
            if (currentPoint.getRow() < nextPoint.getRow() || currentPoint.getCol() < nextPoint.getCol()) {
                // Checking if the next point is part of an obstacle
                if(currentPoint.getRow() == obst.getRow1() && currentPoint.getCol() == obst.getColumn1() && nextPoint.getRow() == obst.getRow2() && nextPoint.getCol() == obst.getColumn2()) {
                    // Obstacle found
                    return true;
                }
            } else {
                // Checking if the next point is part of an obstacle
                if(currentPoint.getRow() == obst.getRow2() && currentPoint.getCol() == obst.getColumn2() && nextPoint.getRow() == obst.getRow1() && nextPoint.getCol() == obst.getColumn1()) {
                    // Obstacle found
                    return true;
                }
            }
        }

        // No obstacle found
        return false;
    }

    /**
     * Function to filter the options for the Boebot to go to next
     * @param currentPoint the current point of the Boebot
     * @param currentNodeOptions the list with the options
     * @param obstacleList the list with the obstacles
     * @param currentNodeOptionsFiltered the list with the filtered options
     * @param iOffset the offset of the options
     * @return the list with the filtered options
     */
    private ArrayList<Node> filterObstacleOptions(Node currentPoint, ArrayList<Node> currentNodeOptions, ArrayList<Obstacle> obstacleList, ArrayList<Node> currentNodeOptionsFiltered, int iOffset) {
        // Checking if the offset is smaller than the size of the current options
        if (iOffset < currentNodeOptions.size()) {
            // Getting a single option
            Node option = currentNodeOptions.get(iOffset);

            // Checking if the option is with a obstacle
            if (!checkObstacleOnLine(currentPoint, option, obstacleList)) {
                // Option is without obstacle so adding to the list
                currentNodeOptionsFiltered.add(option);
            }

            // Updating the offset
            iOffset++;
            // Going through the function again
            return filterObstacleOptions(currentPoint, currentNodeOptions, obstacleList, currentNodeOptionsFiltered, iOffset);
        } else {
            // Returning the filtered list
            return currentNodeOptionsFiltered;
        }
    }

    /**
     * Function the check what the best option is next
     * @param lastPoint the last point of the Boebot
     * @param currentPoint the current point of the Boebot
     * @param endPoint the end point of the Boebot
     * @param currentNodeOptionsFiltered the options that the Boebot has
     * @return the Node with the best option
     */
    private Node getNextOption(Node lastPoint, Node currentPoint, Node endPoint, ArrayList<Node> currentNodeOptionsFiltered) {
        // Creating variables for the next row and column
        int iNextRow = currentPoint.getRow() + 1;
        int iNextCol = currentPoint.getCol() + 1;

//        System.out.println("Last point was " + lastPoint);

        // Checking if BoeBot is at starting point
        if (lastPoint.getRow() == 0 && lastPoint.getCol() == 0) {
            return new Node(iNextRow, currentPoint.getCol());
        } else {
            /**
             * Lastpoint (1, 2)
             * Currentpoint (2, 2)
             *
             * nextNode (2, iNextColumn(3))
             * nextNode (iNextRow(3), 2)
             */

            if (currentPoint.getRow() == lastPoint.getRow() && currentPoint.getRow() != endPoint.getRow() && currentPoint.getCol() != lastPoint.getCol()) {
                // Going up to create the stairs, fastest route
                System.out.println("We gaan omhoog voor de lol");
                return new Node(iNextRow, currentPoint.getCol());
            } else if (currentPoint.getCol() == lastPoint.getCol() && currentPoint.getCol() != endPoint.getCol() && currentPoint.getRow() != lastPoint.getRow()) {
                // Staying on the same line to make the stairs, fastest route
                System.out.println("We gaan opzij voor de lol");
                return new Node(currentPoint.getRow(), iNextCol);
            } else if (currentPoint.getRow() == endPoint.getRow()) {
                // Staying on the same row but changing columns
                System.out.println("We gaan opzij maar alleen omdat het niet anders kan");
                return new Node(currentPoint.getRow(), iNextCol);
            } else if (currentPoint.getCol() == endPoint.getCol()) {
                // Staying on the same column but changing rows
                System.out.println("We gaan omhoog maar alleen omdat het niet anders kan");
                return new Node(iNextRow, currentPoint.getCol());
//            } else if () {

            } else {
                System.out.println("Maar 1 optie");
                return currentNodeOptionsFiltered.get(0);
            }
        }
    }
}
