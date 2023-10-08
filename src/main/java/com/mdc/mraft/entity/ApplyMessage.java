package com.mdc.mraft.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyMessage {
    private boolean commandValid;
    private Object command;
    private int commandIndex;

    private boolean snapshotValid;
    private byte[] snapshot;
    private int snapshotTerm;
    private int snapshotIndex;
}
