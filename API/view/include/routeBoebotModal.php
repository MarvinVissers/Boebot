        <?php
            // Checking if the modal button is pressed
            if (isset($_POST["btnStartRoute"])) {
                // Getting the values of the form
                $boebot = $_POST["txtBoebotName"];
                $row1 = $_POST["txtStartX"];
                $column1 = $_POST["txtStartY"];
                $row2 = $_POST["txtEndX"];
                $column2 = $_POST["txtEndY"];
                // Text for the log
                $text = "Drive from (" . $row1 . "," . $column1 . ") to (" . $row2 . "," . $column2 . ")";

                // Filling the route model
                $routeModel = new Route(null, $boebot, $row1, $column1, $row2, $column2);
                // Filling the log model
                $logModel = new Log(null, $boebot, $text);

                // Adding the route to the database
                $routeCtrl->addRouteBoebot($routeModel);
                $logCtrl->addLogItem($logModel);
            }
        ?>

        <!-- The modal to chose a route for the Boebot -->
        <div class="modal fade" id="chooseBoebot">
            <form action="" method="post">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title">Choose endpoint Boeobot</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <label for="">Starting point</label>
                                    </div>

                                    <div class="form--inline">
                                        <div class="col-sm-6">
                                            <input type="number" name="txtStartX" id="startX" value="0" class="form-control form--label" min="0" maxlength="2">
                                            <span id="startXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtStartY" id="startY" value="0" class="form-control form--label" min="0" maxlength="2">
                                            <span id="startYFeedback" class="form__feedback"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <label for="">Ending point</label>
                                    </div>

                                    <div class="form--inline">
                                        <div class="col-sm-6">
                                            <input type="number" name="txtEndX" id="endX" class="form-control form--label" min="0" maxlength="2">
                                            <span id="EndXFeedback" class="form__feedback"></span>
                                        </div>

                                        <div class="col-sm-6">
                                            <input type="number" name="txtEndY" id="endY" class="form-control form--label" min="0" maxlength="2">
                                            <span id="EndYFeedback" class="form__feedback"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input type="hidden" name="txtBoebotName" id="boebotNameRoute" required>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <input type="submit" value="Start driving" name="btnStartRoute" id="btnStartRoute" class="btn btn-primary">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>