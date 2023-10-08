package com.mdc.mraft.raft;

import com.mdc.mraft.entity.RaftState;
import com.mdc.mraft.enums.RoleEnum;
import com.mdc.mraft.enums.TimeEnum;
import com.mdc.mraft.persist.Persister;
import com.mdc.mraft.raft.config.RaftConfig;
import com.mdc.mraft.service.Service;
import com.mdc.mraft.service.ServiceResponse;
import com.mdc.mraft.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: A raft object which stands for a raft peer
 * @date 2023/10/2 14:25
 */
public class Raft implements Service, RaftRpc, RaftRpcTarget {
    private static final Logger logger = LoggerFactory.getLogger(Raft.class);
    private RaftConfig config;
    private RaftState state;
    private boolean hasInitialized;
    private Persister persister;
    private Lock lock = new ReentrantLock();

    public Raft(RaftConfig config) {
        this.config = config;
        initialWith(this.config);
    }

    private void initialWith(RaftConfig config) {
        try {
            hasInitialized = true;
            config.getClients().forEach(RaftRpcSource::open);
        } finally {
            for (var client : config.getClients()) {
                try {
                    client.close();
                } catch (Exception e) {
                    logger.error("client close error: {}", e.getMessage());
                }
            }
        }
    }

    @Override
    public ServiceResponse start(Object command) {
        if (!hasInitialized) {
            throw new IllegalStateException("Raft is not initialized!");
        }
        return null;
    }

    private void tick() {
        while (!state.isKilled()) {
            try {
                lock.lock();
                if (state.getRole() == RoleEnum.FOLLOWER) {
                    if (state.getTimeoutCount() >= TimeEnum.TIMEOUT) {
                        state.setTimeoutCount(0);
                        state.setRole(RoleEnum.CANDIDATE);
                        startElection();
                    } else {
                        state.setTimeoutCount(state.getTimeoutCount() + 1);
                    }
                } else if (state.getRole() == RoleEnum.CANDIDATE) {
                    startElection();
                }
                if (state.getRole() == RoleEnum.CANDIDATE) {
                    lock.unlock();
                } else {
                    lock.unlock();
                    Thread.sleep(TimeEnum.SLEEP_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startElection() {
        state.getPersistentState().setCurrentTerm(state.getPersistentState().getCurrentTerm() + 1);
        state.getPersistentState().setVoteFor(config.getId());
        persist();
        long lastLogTerm = 0L, lastLogIndex = 0L;
        if (state.getPersistentState().getLogEntries().size() > 0) {
            lastLogTerm = state.getPersistentState().getLogEntries().getLast().getTerm();
            lastLogIndex = mapLogicIndex(state.getPersistentState().getLogEntries().size() - 1);
        } else {
            lastLogTerm = state.getPersistentState().getLastIncludedTerm();
            lastLogIndex = state.getPersistentState().getFirstLogicIndex();
        }

        var requestVoteArgs = RequestVoteArgs.builder()
                .term(state.getPersistentState().getCurrentTerm())
                .candidateId(config.getId())
                .lastLogIndex(lastLogIndex)
                .lastLogTerm(lastLogTerm);
        boolean success = false;
        long maxTerm = state.getPersistentState().getCurrentTerm();
        for (RaftRpcSource client : config.getClients()) {
            if (client.getId() != config.getId()) {
                new Thread(() -> {

                }).start();
            }
        }
    }

    /**
     * @description: Map real index to real index
     * @param:
     * @return:
     * @author ShuangShu
     * @date: 2023/10/3 15:40
     */
    private long mapLogicIndex(long realIndex) {
        long firstLogicIndex = state.getPersistentState().getFirstLogicIndex();
        return realIndex + firstLogicIndex;
    }

    /**
     * @description: Map logic index to real index
     * @param:
     * @return:
     * @author ShuangShu
     * @date: 2023/10/3 15:40
     */
    private long mapRealIndex(long logicIndex) {
        long firstLogicIndex = state.getPersistentState().getFirstLogicIndex();
        return logicIndex - firstLogicIndex;
    }

    private void persist() {
    }

    @Override
    public void snapshot(byte[] snapshot) {
    }

    @Override
    public RequestVoteResponse requestVote(RequestVoteArgs requestVoteArgs) {
        logger.info("requestVoteArgs: {}", requestVoteArgs);
        return null;
    }

    @Override
    public AppendEntryResponse appendEntry(AppendEntryArgs appendEntryArgs) {
        logger.info("appendEntryArgs: {}", appendEntryArgs);
        return null;
    }

    @Override
    public Raft getRaft() {
        return this;
    }

    @Override
    public void setRaft(Raft raft) {
    }

    @Override
    public void initialWith(int port, Raft raft) {
        config.getServer().initialWith(port, raft);
    }

    @Override
    public void open() {
        config.getServer().open();
    }

    @Override
    public void close() throws Exception {
        config.getServer().close();
    }
}
