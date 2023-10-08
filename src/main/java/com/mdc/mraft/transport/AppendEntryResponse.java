package com.mdc.mraft.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:58
 */
@Data
@Builder
@AllArgsConstructor
public class AppendEntryResponse {
    long term;
    boolean success;
    long id;
    long lastCommitIndex;
}
