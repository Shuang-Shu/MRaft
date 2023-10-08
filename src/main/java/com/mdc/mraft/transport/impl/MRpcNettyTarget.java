package com.mdc.mraft.transport.impl;

import com.mdc.mraft.raft.Raft;
import com.mdc.mraft.transport.RaftRpcTarget;
import com.mdc.mraft.transport.RpcServer;
import com.mdc.mrpc.transport.socket.SocketRpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/8 23:40
 */
public class MRpcNettyTarget extends AbstractMRpcTarget {
    @Override
    protected RpcServer createRpcServer() {
        return new MRpcSocketServerAdaptor(new SocketRpcServer(port));
    }
}
