package interfaces;

import models.Job;

public interface Executor {
    static Future<Job> submitJob(Job job) {
        return null;
    }
}
