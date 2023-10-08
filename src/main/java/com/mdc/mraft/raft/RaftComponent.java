package com.mdc.mraft.raft;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 14:13
 */
public interface RaftComponent {
    Raft getRaft();

    void setRaft(Raft raft);
}
