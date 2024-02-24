package com.stysams.selfuse.cron.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author StysaMS
 */

@Service
public class CronTaskFoo implements IPollableService {
    private static final Random random = new SecureRandom();

    @Override
    public void poll() {
        System.out.println("Say Foo");
    }

    @Override
    public String getCronExpression() {
        return "0/" + (random.nextInt(9) + 1) + " * * * * ?";
    }
}