package sheduler;

import worker.Worker;

public interface Scheduler {
    void schedule(Runnable task);

    Worker createScheduler ();
}
