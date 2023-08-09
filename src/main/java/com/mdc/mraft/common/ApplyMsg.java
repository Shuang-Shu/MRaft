package com.mdc.mraft.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyMsg {
    private boolean commandValid;
    private Object command;
    private int commandIndex;

    private boolean snapshotValid;
    private byte[] snapshot;
    private int snapshotTerm;
    private int snapshotIndex;
}
