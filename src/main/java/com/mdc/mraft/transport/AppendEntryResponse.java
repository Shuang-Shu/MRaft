package com.mdc.mraft.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:58
 */
@Data
@Builder
@AllArgsConstructor
public class AppendEntryResponse implements Serializable {
    long term;
    boolean success;
    long id;
    long lastCommitIndex;
}
