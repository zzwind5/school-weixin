package com.scheduler.engine;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.core.job.JobRequest;
import com.core.job.JobSpec;
import com.core.job.JobTrigger;
import com.core.util.JsonUtil;
import com.scheduler.job.StartUpJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchedulerHelper {
	
	@Autowired private ResourceLoader resLoader;

	public JobDetail newStartUpJobDetail() {
		return JobBuilder.newJob(StartUpJob.class)
				.withIdentity(StartUpJob.class.getSimpleName(), "StartUpJob")
				.build();
	}
	
	public Trigger newStartupJobTrigger(final JobDetail job) {
        return TriggerBuilder.newTrigger()
                        .withIdentity(job.getKey().getName(), job.getKey().getGroup())
                        .startAt(DateBuilder.futureDate(10, IntervalUnit.SECOND)) // wait 10 seconds
                        .build();
    }
	
	@SuppressWarnings("unchecked")
    public JobDetail newJobDetail(final JobSpec spec) {
        return JobBuilder.newJob((Class<Job>) spec.getRunMode().getJobClass())
                        .withIdentity(spec.getName(), spec.getGroupName())
                        .build();
    }
	
	public Trigger newJobTrigger(final JobSpec spec, final JobTrigger jt) throws SchedulerException {
        String triggerName = spec.getName();
        String groupName = spec.getGroupName();
        TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger().withIdentity(triggerName, groupName);
        if (jt.isStartNow()) {
            builder.startNow();
        } else {
            builder.startAt(jt.getActualStartTime());
        }
        builder.endAt(jt.getEndTime());

        if (jt.isSimpleTrigger()) {
            builder.withSchedule(newSimpleSchedule(jt));
        } else {
            builder.withSchedule(newCronSchedule(jt.getCron()));
        }

        return builder.build();
    }

	public List<JobRequest> loadAllPredefinedJobs() {
		Resource jsonRes = resLoader.getResource("classpath:predefined-jobs-all.json");
		try {
			String jsonStr = IOUtils.toString(jsonRes.getInputStream());
			return JsonUtil.toObjectList(jsonStr, JobRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Load Json scheduler job failed.", e);
			throw new RuntimeException(e);
		}
	}
	
	private ScheduleBuilder<SimpleTrigger> newSimpleSchedule(final JobTrigger jt) {
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
        if (jt.getInterval() <= 0) {
            return ssb;
        }

        if (jt.isRepeatForever()) {
            ssb.repeatForever();
        } else {
            ssb.withRepeatCount(jt.getRepeatCount());
        }
        ssb.withIntervalInMilliseconds(jt.getIntervalInMillis());
        return ssb;
    }

    private ScheduleBuilder<CronTrigger> newCronSchedule(final String cron) {
        CronExpression cronExp = null;
        try {
            cronExp = new CronExpression(cron);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid Cron expression [" + cron + "]");
        }
        return CronScheduleBuilder.cronSchedule(cronExp);
    }
}
