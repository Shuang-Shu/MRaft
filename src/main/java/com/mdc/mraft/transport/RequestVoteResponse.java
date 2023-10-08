package com.mdc.mraft.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:45
 */
@Data
@Builder
@AllArgsConstructor
public class RequestVoteResponse {
    boolean ok = false;
    long term;
    boolean voteGranted;
}
