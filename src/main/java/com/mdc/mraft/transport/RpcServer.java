package com.mdc.mraft.transport;

import com.mdc.mrpc.registry.IRegistry;
import com.mdc.mrpc.serialize.ISerializer;

import java.io.Closeable;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/8 23:49
 */
public interface RpcServer extends Closeable {
    public void initWith(IRegistry registry, ISerializer serializer);

    public void start();
}
