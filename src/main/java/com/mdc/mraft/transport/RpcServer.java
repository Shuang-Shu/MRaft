package com.mdc.mraft.transport;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.RaftComponent;

/**
 * @author ShuangShu
 * @version 1.0
 * @description:
 * @date 2023/10/2 12:43
 */
public interface RpcServer extends RaftComponent, AutoCloseable {
    void initialWith(int port, Raft raft);

    void open();
}
