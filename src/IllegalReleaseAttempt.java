/**
 * exception for releasing an unlocked lock,
 * or if a thread that didn't lock the lock tries to release it
 */
public class IllegalReleaseAttempt extends IllegalMonitorStateException{

    public IllegalReleaseAttempt() {}

    public IllegalReleaseAttempt(String message) {
        super(message);
    }
}
