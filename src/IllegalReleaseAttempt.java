public class IllegalReleaseAttempt extends IllegalMonitorStateException{

    public IllegalReleaseAttempt() {}

    public IllegalReleaseAttempt(String message) {
        super(message);
    }
}
