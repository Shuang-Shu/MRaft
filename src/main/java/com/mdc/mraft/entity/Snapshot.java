package com.mdc.mraft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snapshot {
    private byte[] snapshot;
}
