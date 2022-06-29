import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    private AtomicBoolean is_locked;
    private Thread lockingThread;
    private int counter;

    public MyReentrantLock()
    {
        this.is_locked = new AtomicBoolean(false);
        this.counter = 0;
        this.lockingThread = Thread.currentThread();
    }

    @Override
    public void acquire() {
        if(Thread.currentThread() != this.lockingThread)
        {
            while(is_locked.compareAndSet(false, true)
                    && Thread.currentThread() != this.lockingThread)
            {
                try
                {
                    Thread.sleep(10);
                } catch(InterruptedException e){}//TODO change exception
            }
           this.lockingThread = Thread.currentThread();
        }
        counter++;
    }

    @Override
    public boolean tryAcquire()
    {
        if(this.is_locked.compareAndSet(false, true)
                || Thread.currentThread() == this.lockingThread)
        {
            acquire();
            return true;
        }
        return false;
    }

    @Override
    public void release() {
        if (Thread.currentThread() != this.lockingThread || this.is_locked.get() == false)
        {
            throw new IllegalReleaseAttempt();
        }
        else
        {
            if (counter > 1)
            {
                counter--;
            }
            else
            {
                counter--;
                is_locked.set(false);
                this.lockingThread = null;
            }
        }
    }

    @Override
    public void close()
    {
        release();
    }
}
