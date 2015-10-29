package com.core.job;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class JobTrigger {
	
    private Date startTime;
    private Date endTime;
    private int delay;
    private TimeUnit delayUnit = TimeUnit.SECONDS;
    private int interval;
    private TimeUnit intervalUnit = TimeUnit.SECONDS;
    private int repeatCount = -1;
    private String cron;

    @JsonIgnore
    public boolean isSimpleTrigger() {
        return StringUtils.isBlank(cron);
    }

    @JsonIgnore
    public boolean isCronTrigger() {
        return !isSimpleTrigger();
    }

    @JsonIgnore
    public boolean isStartNow() {
        return startTime == null && delay == 0;
    }

    public JobTrigger startAfter(final int delay, final TimeUnit unit) {
//        checkArgument(delay > 0, "Invalid delay[" + delay + "]: MUST be positive.");
        this.delay = delay;
        delayUnit = unit;
        startTime = null;
        return this;
    }

    /**
     * The actural working start time considering all possible conditions.
     *
     * @return actural working start time.
     */
    @JsonIgnore
    public Date getActualStartTime() {
        if (startTime != null) {
            return startTime;
        }

        if (delay <= 0) {
            return new Date();
        }

        return new Date(System.currentTimeMillis() + delayUnit.toMillis(delay));
    }

    @JsonIgnore
    public boolean isRepeatForever() {
        return repeatCount < 0;
    }

    @JsonIgnore
    public long getIntervalInMillis() {
        return intervalUnit.toMillis(interval);
    }

}
