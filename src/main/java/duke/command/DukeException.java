package duke.command;

public class DukeException extends Exception {

    String error;

    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "\t ☹ OOPS!!! " + error + "\n";
    }
}
