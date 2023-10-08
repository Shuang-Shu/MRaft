package com.mdc.mraft.persist;

import com.mdc.mraft.entity.RaftState;
import com.mdc.mraft.entity.Snapshot;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: Persister is used to persist data in disk
 * @date 2023/10/2 14:30
 */
public interface Persister {
    void saveRaftState(RaftState state) throws IOException;

    RaftState readRaftState() throws IOException;

    long raftStateSize();

    void saveStateAndSnapshot(RaftState state, Snapshot snapshot) throws IOException;

    Snapshot readSnapshot() throws IOException;

    long snapshotSize();
}
