package com.mdc.mraft.common;

import com.mdc.mraft.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaftState {
    private Lock lock = new ReentrantLock();
    // TODO rpc peers
    private int me;
    private boolean dead;
    // TODO how to implement channel in Java?
    private RoleEnum role;
    private PersistentState persistentState;
    private VolatileState volatileState;
    private int timeoutCount;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PersistentState {
    private long currentTerm;
    private int voteFor;
    private LogEntry[] logEntries;
    private long firstLogicIndex;
    private long lastIncludedTerm;
    private long lastAppendLeaderTerm;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class LeaderState {
    private long[] nextIndex;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class AllServerState {
    private long commitIndex;
    private long lastApplied;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class VolatileState {
    private LeaderState leaderState;
    private AllServerState allServerState;
}