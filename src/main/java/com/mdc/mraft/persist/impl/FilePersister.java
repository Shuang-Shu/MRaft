package com.mdc.mraft.persist.impl;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mdc.mraft.entity.RaftState;
import com.mdc.mraft.entity.Snapshot;
import com.mdc.mraft.persist.Persister;

import java.io.*;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 14:09
 */
public class FilePersister implements Persister {
    private File stateFile;
    private File snapshotFile;
    private Kryo kryo = new Kryo();

    public FilePersister(String stateFile, String snapshotFile) {
        this(new File(stateFile), new File(snapshotFile));
    }

    public FilePersister(File stateFile, File snapshotFile) {
        this.stateFile = stateFile;
        this.snapshotFile = snapshotFile;
        kryo.register(RaftState.class);
        kryo.register(Snapshot.class);
    }

    @Override
    public synchronized void saveRaftState(RaftState state) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(stateFile);
             var output = new Output(fileOutputStream)) {
            kryo.writeObject(output, state);
        }
    }

    @Override
    public synchronized RaftState readRaftState() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(stateFile);
             var input = new Input(fileInputStream)) {
            return kryo.readObject(input, RaftState.class);
        }
    }

    @Override
    public synchronized long raftStateSize() {
        return stateFile.length();
    }

    @Override
    public synchronized void saveStateAndSnapshot(RaftState state, Snapshot snapshot) throws IOException {
        try (FileOutputStream stateOutputStream = new FileOutputStream(stateFile); FileOutputStream snapshotOutputStream = new FileOutputStream(snapshotFile); var stateOutput = new Output(stateOutputStream); var snapshotOutput = new Output(snapshotOutputStream)) {
            kryo.writeObject(stateOutput, state);
            kryo.writeObject(snapshotOutput, snapshot);
        }
    }

    @Override
    public synchronized Snapshot readSnapshot() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(snapshotFile); var input = new Input(fileInputStream)) {
            return kryo.readObject(input, Snapshot.class);
        }
    }

    @Override
    public synchronized long snapshotSize() {
        return snapshotFile.length();
    }
}
