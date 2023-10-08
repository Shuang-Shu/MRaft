package com.mdc.mraft.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:45
 */
@Data
@Builder
@AllArgsConstructor
public class RequestVoteResponse implements Serializable {
    boolean ok = false;
    long term;
    boolean voteGranted;
}
