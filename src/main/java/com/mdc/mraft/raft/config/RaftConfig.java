package com.mdc.mraft.raft.config;

import com.mdc.mraft.app.ApplyChannel;
import com.mdc.mraft.transport.RpcClient;
import com.mdc.mraft.transport.RpcServer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaftConfig {
    private List<RpcClient> clients; // contains all peers
    private RpcServer server;
    private ApplyChannel channel;
    private long id;

    @Override
    public String toString() {
        return "RaftConfig{" +
                "clients=" + clients +
                ", server=" + server +
                ", channel=" + channel +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof RaftConfig) {
            var config = (RaftConfig) object;
            return config.getId() == this.getId();
        }
        return false;
    }
}
