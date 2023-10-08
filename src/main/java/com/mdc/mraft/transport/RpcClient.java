package com.mdc.mraft.transport;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.RaftComponent;
import com.mdc.mraft.raft.RaftRpc;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 14:21
 */
public interface RpcClient extends RaftComponent, AutoCloseable {
    /**
     * @description: Get the server id of corresponding server.
     * @param:
     * @return:
     * @author ShuangShu
     * @date: 2023/10/3 16:44
     */
    long getId();

    void initialWith(String host, int port);

    /**
     * @description: Open connection to corresponding RpcServer. After openning, the client
     * can send request vote and append entry rpc to the RpcServer.
     * @param:
     * @return:
     * @author ShuangShu
     * @date: 2023/10/3 16:07
     */
    void open();

    public RequestVoteResponse sendRequestVote(RequestVoteArgs requestVoteArgs);

    public AppendEntryResponse sendAppendEntry(AppendEntryArgs appendEntryArgs);
}
