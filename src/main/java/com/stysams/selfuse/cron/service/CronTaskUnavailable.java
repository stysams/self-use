package com.stysams.selfuse.cron.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author StysaMS
 */

@Service
public class CronTaskUnavailable implements IPollableService {
    private String cronExpression = "-";
    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("-", "0/1 * * * * ?");
        map.put("0/1 * * * * ?", "-");
    }

    @Override
    public void poll() {
        System.out.println("Say Unavailable");
    }

    @Override
    public String getCronExpression() {
        return (cronExpression = map.get(cronExpression));
    }
}