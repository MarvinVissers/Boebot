<?php
/**
 * @author Marvin Vissers
 */
class BoeBot {
    // Creating the variables
    private $id;
    private $name;
    private $status;

    /**
     * Creates a new instance of BoeBot
     *
     * @param id id of the BoeBot
     * @param name name of the BoeBot
     * @param status the status of the BoeBot
     */
    public function __construct($id, $name, $status) {
        // Setting the given values equal to the variables in the class
        $this->id = $id;
        $this->name = $name;
        $this->status = $status;
    }

    public function getId() {
        return $this->id;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function getName() {
        return $this->name;
    }

    public function setName($name) {
        $this->name = $name;
    }

    public function getStatus() {
        return $this->status;
    }

    public function setStatus($status) {
        $this->status = $status;
    }
}
?>