package com.mdc.mraft;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.raft.config.RaftConfig;
import com.mdc.mraft.transport.impl.MRpcSocketClient;
import com.mdc.mraft.transport.impl.MRpcSocketServer;
import com.mdc.mrpc.registry.IRegistry;
import com.mdc.mrpc.registry.impl.DefaultRegistry;
import com.mdc.mrpc.serialize.ISerializer;
import com.mdc.mrpc.serialize.kryo.KryoSerializer;
import com.mdc.mrpc.service.DemoService;
import com.mdc.mrpc.service.IDemoService;
import com.mdc.mrpc.transport.socket.SocketRpcServer;
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

    MRpcSocketClient client;

    @Before
    public void initialize() {
        Thread thread = new Thread(
                () -> {
                    var server = new MRpcSocketServer();
                    server.initialWith(8080, new Raft(new RaftConfig(
                            new ArrayList<>(), server, null, 6657L
                    )));
                    server.open();
                }
        );
        thread.start();
        client = new MRpcSocketClient();
        client.initialWith("localhost", 8080);
    }

    @Test
    public void basicTest() {
        client.sendAppendEntry(null);
        client.sendRequestVote(null);
    }
}
