import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock{
    private AtomicBoolean is_locked;
    private Thread lockingThread;
    private int counter = 0;

    public MyReentrantLock()
    {
        this.is_locked = new AtomicBoolean(false);
    }

    @Override
    public void acquire() {
        if(is_locked.compareAndSet(false, true))
        {
           this.lockingThread = Thread.currentThread();
           counter++;
        }
        else
        {
            if(this.lockingThread == Thread.currentThread())
            {
                counter++;
            }
            else
            {
                while(!is_locked.compareAndSet(false, true))
                {
                    try
                    {
                        Thread.sleep(1);//Todo maybe change to 11
                    } catch(Exception e){//TODO change exception
                        }
                }
            }
        }
    }

    @Override
    public boolean tryAcquire()
    {
        if(Thread.currentThread() == this.lockingThread)
        {
            counter++;
            return true;
        }
        else
        {
            if(counter == 0)
            {

            }
        }
        return false;
    }

    @Override
    public void release() {
        if (Thread.currentThread() == this.lockingThread)
        {
            counter--;
            if (counter == 0)
            {
                this.lockingThread = null;
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
