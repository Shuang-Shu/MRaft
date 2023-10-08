package com.mdc.mraft;

import com.mdc.mraft.entity.RaftState;
import com.mdc.mraft.entity.Snapshot;
import com.mdc.mraft.enums.RoleEnum;
import com.mdc.mraft.persist.impl.FilePersister;
import com.mdc.mraft.raft.config.RaftConfig;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 16:40
 */
public class PersisterTest {
    RaftState raftState;
    Snapshot snapshot;
    FilePersister persister;

    @Before
    public void initialize() {
        raftState = RaftState.builder().role(RoleEnum.CANDIDATE).persistentState(
                RaftState.PersistentState.builder().currentTerm(1).voteFor(-1).logEntries(new LinkedList<>()).firstLogicIndex(-1).lastIncludedTerm(-1).lastAppendLeaderTerm(-5).build()
        ).volatileState(
                RaftState.VolatileState.builder().leaderState(
                        RaftState.LeaderState.builder().nextIndex(new long[3]).build()
                ).allServerState(
                        RaftState.AllServerState.builder().commitIndex(2).lastApplied(3).build()
                ).build()
        ).build();
        snapshot = new Snapshot();
        snapshot.setSnapshot(new byte[5]);
        persister = new FilePersister("raftstate.bin", "snapshot.bin");
    }

    @Test
    public void testPersistState() throws IOException {
        persister.saveRaftState(raftState);
        var state = persister.readRaftState();
        assert state.equals(raftState);
    }

    @Test
    public void testPersistSnapshot() throws IOException {
        persister.saveStateAndSnapshot(raftState, snapshot);
        var snapshot = persister.readSnapshot();
        assert snapshot.getSnapshot().length == 5;
    }
}
