package low_level_design;

import interfaces.Executor;
import interfaces.Future;
import models.Job;

import java.util.*;

public class DependencyJobScheduler {
    /*
    example 1:
A -> B -> C
exmaple 2:
A1 -> -> C1
     B
A2 -> -> C2

A1, A2 -> Executor.submitJob -> both A1 A2 are done
-> submit B for execution -> B is done
-> submit C1, C2 for execution

*/
    void scheduleAllJobs(final List<Job> rootJobs) throws InterruptedException {
            //x1, z1, A2, C2
        // check edge case
        if(rootJobs.size()<1) return;
        for(int i=0;i<rootJobs.size();i++){
            Job  j = rootJobs.get(i);
            Future<Job> future_job = Executor.submitJob(j);
            int timeoutCnt = 3;
            while(future_job.isDone() && timeoutCnt>0){
                scheduleAllJobs(j.downstreamJobs);
                Thread.sleep(5000);
                timeoutCnt--;
            }

        }

//            // now we need to take run pre-req
//            for(int i=1;i<rootJobs.size();i++){
//                jobs.put(rootJobs.get(i-1),rootJobs.get(i));
//                Future<Job> rootJob =  Executor.submitJob(jobs);
//                // first confirm if the root job is done then look for root job.downStream job and submit job
//                if(rootJob.isDone()){
//                    Executor.submitJob(job);
//                }
//            }


        }
    }
// List a : ScheduleJob(a)
/*scheduleAllJobs(List<Job> (A1, D2, A3))

Job(A1).downstreamJobs -> Job(B)
Job(B).downstreamJobs -> List(Job(C1), Job(C2))

Future<Job> A1_future = Executor.submitJob(A1)
Future<Job> D2_future = Executor.submitJob(D2)
Future<Job> A3_future = Executor.submitJob(A3)
A1_future.isDone()
D2_future.isDone()
Future<Job> B_future = Executor.submitJob(B)
B_future.isDone()
->
*/

