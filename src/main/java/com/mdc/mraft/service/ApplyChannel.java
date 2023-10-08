package com.mdc.mraft.service;

import com.mdc.mraft.entity.ApplyMessage;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 14:26
 */
public interface ApplyChannel {
    boolean apply(ApplyMessage msg);
}
