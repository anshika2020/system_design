package models;

import java.util.ArrayList;
import java.util.List;

public class Job {
    public final List<Job> downstreamJobs = new ArrayList<>();
}
