package com.mdc.mraft.transport.impl;

import com.mdc.mraft.transport.RpcServer;
import com.mdc.mrpc.registry.IRegistry;
import com.mdc.mrpc.serialize.ISerializer;
import com.mdc.mrpc.transport.netty.NettyRpcServer;

import java.io.IOException;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/8 23:59
 */
public class MRpcNettyServerAdaptor implements RpcServer {
    private NettyRpcServer server;

    public MRpcNettyServerAdaptor(NettyRpcServer server) {
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
