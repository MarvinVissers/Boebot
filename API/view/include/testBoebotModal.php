        <?php
            // Checking if the modal button is pressed
            if (isset($_POST["btnTestFunction"])) {
                // Getting the value from the form
                $boebot = $_POST["txtBoebotName"];
                $testFunction = $_POST["cbxTestFunction"];

                // Filling the log Model
                $logModel = new Log(null, $boebot, $testFunction);

                // Adding the test function to the database
                $logCtrl->addLogItem($logModel);
            }
        ?>

        <!-- The Boebot test modal -->
        <div class="modal fade" id="testBoebot">
            <form action="" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Test function Boeobot</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <select name="cbxTestFunction" id="" required>
                                <option value=""></option>
                                <option value="Test light left">Light - Left</option>
                                <option value="Test light right">Light - Right</option>
                                <option value="Test drive forward">Motor - Drive forward</option>
                                <option value="Test drive backward">Motor - Drive backward</option>
                                <option value="Test turn left">Wheels - Turn left</option>
                                <option value="Test turn right">Wheels - Turn Rigth</option>
                                <option value="Test all">Test all</option>
                            </select>

                            <input type="hidden" name="txtBoebotName" id="boebotNameTest" required>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <input type="submit" value="Start function" name="btnTestFunction" class="btn btn-primary">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>