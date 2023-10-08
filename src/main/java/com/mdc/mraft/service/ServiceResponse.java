package com.mdc.mraft.service;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 11:30
 */
public record ServiceResponse(int index, int term, boolean isLeader) {
}
