package com.stysams.selfuse.cron.service;

import org.springframework.stereotype.Service;

/**
 * @author StysaMS
 */
@Service
public class CronTaskBar implements IPollableService {
    @Override
    public void poll() {
        System.out.println("Say Bar");
    }

    @Override
    public String getCronExpression() {
        return "0/1 * * * * ?";
    }
}