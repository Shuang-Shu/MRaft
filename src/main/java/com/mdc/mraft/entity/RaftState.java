package com.mdc.mraft.entity;

import com.mdc.mraft.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaftState {
    private boolean killed;
    private RoleEnum role;
    private PersistentState persistentState;
    private VolatileState volatileState;
    private int timeoutCount;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersistentState {
        private long currentTerm;
        private long voteFor;
        private Deque<LogEntry> logEntries;
        private long firstLogicIndex;
        private long lastIncludedTerm;
        private long lastAppendLeaderTerm;

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object instanceof PersistentState) {
                var state = (PersistentState) object;
                return state.getCurrentTerm() == this.getCurrentTerm() && state.getVoteFor() == this.getVoteFor() && state.getFirstLogicIndex() == this.getFirstLogicIndex() && state.getLastIncludedTerm() == this.getLastIncludedTerm() && state.getLastAppendLeaderTerm() == this.getLastAppendLeaderTerm();
            }
            return false;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LeaderState {
        private long[] nextIndex;

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object instanceof LeaderState) {
                var state = (LeaderState) object;
                for (int i = 0; i < state.getNextIndex().length; i++) {
                    if (state.getNextIndex()[i] != this.getNextIndex()[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllServerState {
        private long commitIndex;
        private long lastApplied;

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object instanceof AllServerState) {
                var state = (AllServerState) object;
                return state.getCommitIndex() == this.getCommitIndex() && state.getLastApplied() == this.getLastApplied();
            }
            return false;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VolatileState {
        private LeaderState leaderState;
        private AllServerState allServerState;

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object instanceof VolatileState) {
                var state = (VolatileState) object;
                return state.getLeaderState().equals(this.getLeaderState()) && state.getAllServerState().equals(this.getAllServerState());
            }
            return false;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof RaftState) {
            var state = (RaftState) object;
            return state.getRole() == this.getRole() && state.getPersistentState().equals(this.getPersistentState()) && state.getVolatileState().equals(this.getVolatileState());
        }
        return false;
    }
}