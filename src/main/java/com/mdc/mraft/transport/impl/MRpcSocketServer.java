package com.mdc.mraft.transport.impl;

import com.esotericsoftware.kryo.KryoSerializable;
import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.RaftRpc;
import com.mdc.mraft.transport.*;
import com.mdc.mrpc.registry.impl.DefaultRegistry;
import com.mdc.mrpc.serialize.kryo.KryoSerializer;
import com.mdc.mrpc.transport.socket.SocketRpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 14:24
 */
public class MRpcSocketServer implements RpcServer {
    private static final Logger logger = LoggerFactory.getLogger(MRpcSocketServer.class);

    public class ServerHandler implements RaftRpc {
        @Override
        public RequestVoteResponse requestVote(RequestVoteArgs requestVoteArgs) {
            return MRpcSocketServer.this.getRaft().requestVote(requestVoteArgs);
        }

        @Override
        public AppendEntryResponse appendEntry(AppendEntryArgs appendEntryArgs) {
            return MRpcSocketServer.this.getRaft().appendEntry(appendEntryArgs);
        }
    }

    SocketRpcServer server;
    MRpcSocketServer.ServerHandler serverHandler = new MRpcSocketServer.ServerHandler();
    Raft raft;
    int port;

    @Override
    public Raft getRaft() {
        return this.raft;
    }

    @Override
    public void setRaft(Raft raft) {
        this.raft = raft;
    }

    @Override
    public void initialWith(int port, Raft raft) {
        this.port = port;
        this.raft = raft;
        var registry = new DefaultRegistry();
        var raftService = new ServerHandler();
        var serializer = new KryoSerializer();
        registry.regsiter(raftService);
        server = new SocketRpcServer(port);
        server.initWith(registry, serializer);
    }

    @Override
    public void open() {
        if (server == null) {
            logger.error("server is null");
            return;
        }
        new Thread(() -> {
            try {
                logger.info("raft server is openned on {}", this.port);
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void close() throws Exception {

    }
}
