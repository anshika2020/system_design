package interfaces;

import models.Job;

public interface Future<T> {
    Boolean isDone();
    T get();
}
