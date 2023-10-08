package com.mdc.mraft.service;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: Services exposed to outside application
 * @date 2023/10/2 11:21
 */
public interface Service {
    /**
     * @description: application use this method to start a command
     * @param: command
     * @return: ServiceResponse
     * @author ShuangShu
     * @date: 2023/10/2 11:37
     */
    public ServiceResponse start(Object command);

    /**
     * @description: application use this method to get the latest snapshot
     * @param: data of snapshot
     * @return: void
     * @author ShuangShu
     * @date: 2023/10/2 11:38
     */
    public void snapshot(byte[] snapshot);
}

