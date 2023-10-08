package com.mdc.mraft.transport.impl;

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
 * @date 2023/10/8 23:42
 */
public abstract class AbstractMRpcTarget implements RaftRpcTarget {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractMRpcTarget.class);

    public class ServerHandler implements RaftRpc {
        @Override
        public RequestVoteResponse requestVote(RequestVoteArgs requestVoteArgs) {
            return AbstractMRpcTarget.this.getRaft().requestVote(requestVoteArgs);
        }

        @Override
        public AppendEntryResponse appendEntry(AppendEntryArgs appendEntryArgs) {
            return AbstractMRpcTarget.this.getRaft().appendEntry(appendEntryArgs);
        }
    }

    protected RpcServer server;
    protected AbstractMRpcTarget.ServerHandler serverHandler = new AbstractMRpcTarget.ServerHandler();
    protected Raft raft;
    protected int port;

    @Override
    public final Raft getRaft() {
        return this.raft;
    }

    @Override
    public final void setRaft(Raft raft) {
        this.raft = raft;
    }

    @Override
    public final void initialWith(int port, Raft raft) {
        this.port = port;
        this.raft = raft;
        var registry = new DefaultRegistry();
        var raftService = new ServerHandler();
        var serializer = new KryoSerializer();
        registry.regsiter(raftService);
        // initial the server implement
        server = createRpcServer();
        server.initWith(registry, serializer);
    }

    protected abstract RpcServer createRpcServer();

    @Override
    public final void open() {
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
