package com.mdc.mraft.transport;

import com.mdc.mraft.entity.LogEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:57
 */
@Data
@Builder
@AllArgsConstructor
public class AppendEntryArgs implements Serializable {
    long term;
    long leaderId;
    long prevLogIndex;
    long prevLogTerm;
    Collection<LogEntry> logEntries;
    long leaderCommit;
    boolean heartBeat;

    // for snapshotting
    boolean isSnapshot;
    long lastIncludedIndex;
    long lastIncludedTerm;
    byte[] snapshotData;

    public String toString() {
        return "AppendEntryArgs(term=" + this.getTerm() + ", leaderId=" + this.getLeaderId() + ", prevLogIndex=" + this.getPrevLogIndex() + ", prevLogTerm=" + this.getPrevLogTerm() + ", ...)";
    }
}
