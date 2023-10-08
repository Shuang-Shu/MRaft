package com.mdc.mraft;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.config.RaftConfig;
import com.mdc.mraft.transport.AppendEntryArgs;
import com.mdc.mraft.transport.RequestVoteArgs;
import com.mdc.mraft.transport.impl.MRpcNettyTarget;
import com.mdc.mraft.transport.impl.MRpcSocketClient;
import com.mdc.mraft.transport.impl.MRpcSocketTarget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 16:40
 */
public class RpcTest {

    MRpcSocketClient socketClient;
    MRpcSocketClient nettyClient;

    AppendEntryArgs demoAppendEntryArgs = AppendEntryArgs.builder()
            .term(1)
            .leaderId(2)
            .prevLogIndex(3)
            .prevLogTerm(4)
            .leaderCommit(5)
            .build();

    RequestVoteArgs demoRequestVoteArgs = RequestVoteArgs.builder()
            .term(1)
            .candidateId(2)
            .lastLogIndex(3)
            .lastLogTerm(4)
            .build();

    @Before
    public void initialize() {
        Thread thread = new Thread(
                () -> {
                    var server = new MRpcSocketTarget();
                    server.initialWith(8080, new Raft(new RaftConfig(
                            new ArrayList<>(), server, null, 6657L
                    )));
                    server.open();
                }
        );
        thread.start();
        Thread thread1 = new Thread(
                () -> {
                    var server = new MRpcNettyTarget();
                    server.initialWith(8081, new Raft(new RaftConfig(
                            new ArrayList<>(), server, null, 6658L
                    )));
                    server.open();
                }
        );
        thread1.start();
        socketClient = new MRpcSocketClient();
        socketClient.initialWith("localhost", 8080);
        nettyClient = new MRpcSocketClient();
        nettyClient.initialWith("localhost", 8081);
    }

    @Test
    public void testSocket() {
        socketClient.sendAppendEntry(demoAppendEntryArgs);
        socketClient.sendRequestVote(demoRequestVoteArgs);
    }

    @Test
    public void testNetty() {
        nettyClient.sendAppendEntry(demoAppendEntryArgs);
        nettyClient.sendRequestVote(demoRequestVoteArgs);
    }
}
