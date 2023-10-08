package com.mdc.mraft.transport.impl;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.transport.RpcServer;
import com.mdc.mrpc.transport.socket.SocketRpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/2 14:24
 */
public class MRpcSocketTarget extends AbstractMRpcTarget {
    @Override
    protected RpcServer createRpcServer() {
        return new MRpcSocketServerAdaptor(new SocketRpcServer(port));
    }

    @Override
    public void close() throws Exception {
    }
}
