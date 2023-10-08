package com.mdc.mraft.transport.impl;

import com.mdc.mraft.transport.RpcServer;
import com.mdc.mrpc.registry.IRegistry;
import com.mdc.mrpc.serialize.ISerializer;
import com.mdc.mrpc.transport.socket.SocketRpcServer;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/8 23:55
 */
public class MRpcSocketServerAdaptor implements RpcServer {
    private SocketRpcServer server;

    public MRpcSocketServerAdaptor(SocketRpcServer server) {
        this.server = server;
    }

    @Override
    public void initWith(IRegistry registry, ISerializer serializer) {
        server.initWith(registry, serializer);
    }

    @Override
    public void start() {
        server.start();
    }

    @Override
    public void close() throws IOException {
    }
}
