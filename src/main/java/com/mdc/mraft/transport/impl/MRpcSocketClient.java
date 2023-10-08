package com.mdc.mraft.transport.impl;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.RaftRpc;
import com.mdc.mraft.transport.*;
import com.mdc.mrpc.transport.RpcClientProxy;
import com.mdc.mrpc.transport.socket.SocketRpcTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 14:24
 */
public class MRpcSocketClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(MRpcSocketServer.class);

    RpcClientProxy clientProxy;
    RaftRpc service;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void initialWith(String host, int port) {
        clientProxy = new RpcClientProxy(new SocketRpcTransport(host, port));
        service = clientProxy.getProxy(RaftRpc.class);
    }

    @Override
    public void open() {
        if (clientProxy == null) {
            logger.error("clientProxy is null");
        }
    }

    @Override
    public RequestVoteResponse sendRequestVote(RequestVoteArgs requestVoteArgs) {
        if (service == null) {
            throw new IllegalStateException("service is null");
        }
        return service.requestVote(requestVoteArgs);
    }

    @Override
    public AppendEntryResponse sendAppendEntry(AppendEntryArgs appendEntryArgs) {
        if (service == null) {
            throw new IllegalStateException("service is null");
        }
        return service.appendEntry(appendEntryArgs);
    }

    @Override
    public Raft getRaft() {
        return null;
    }

    @Override
    public void setRaft(Raft raft) {

    }

    @Override
    public void close() throws Exception {

    }

    public String toString() {
        return "MRpcSocketClient";
    }
}
