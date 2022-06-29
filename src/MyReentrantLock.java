import java.util.concurrent.atomic.AtomicBoolean;

/**
 * a class represents a reentrant lock
 */
public class MyReentrantLock implements Lock{
    private AtomicBoolean isLocked;
    private Thread lockingThread;
    private int counter;

    /**
     * class constructor
     */
    public MyReentrantLock()
    {
        this.isLocked = new AtomicBoolean(false);
        this.counter = 0;
        this.lockingThread = Thread.currentThread();
    }

    /**
     * if the lock isn't locked, locks it. if the locking thread is the one trying to lock again, allows it
     * if another thread tries to lock it, puts it to sleep
     * @throws InterruptedException if unable to put a thread to sleep
     */
    @Override
    public void acquire() {
        if(counter == 0 || Thread.currentThread() != this.lockingThread)
        {
            while(isLocked.compareAndSet(false, true)
                    && Thread.currentThread() != this.lockingThread)
            {
                try
                {
                    Thread.sleep(11);
                } catch(InterruptedException e){
                }
            }
        }
        this.lockingThread = Thread.currentThread();
        counter++;
    }

    /**
     * if acquire is possible, locks and returns true
     * else, returns false
     * @return true if locking is possible, false if not
     */
    @Override
    public boolean tryAcquire()
    {
        if(!isLocked.get() || Thread.currentThread() == this.lockingThread)
        {
            acquire();
            return true;
        }else return false;
    }

    /**
     * releases the current lock, by subtracting 1 from the lock counter.
     * if locked only once, frees the lock for ant thread.
     * @throws IllegalReleaseAttempt if lock isn't locked, or if another thread tries to release it
     */
    @Override
    public void release() {
        if (counter == 0 || Thread.currentThread()!=this.lockingThread) {
            throw new IllegalReleaseAttempt();
        } else {
            if (counter == 1)
            {
                counter--;
                isLocked.set(false);
            }
            else if (counter >1)
            {
                counter--;
            }
        }
    }

    /**
     * releases the lock
     */
    @Override
    public void close()
    {
        release();
    }

}
