package com.stysams.selfuse.starter;

/**
 * @author StysaMS
 */
public class SelfStarterSpringSericveImpl implements SelfStarterSpringService{
    @Override
    public void printStr(String str) {
        System.out.println("in starter ->" + str);
    }
}
