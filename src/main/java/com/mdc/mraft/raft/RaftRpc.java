package com.mdc.mraft.raft;

import com.mdc.mraft.transport.AppendEntryArgs;
import com.mdc.mraft.transport.AppendEntryResponse;
import com.mdc.mraft.transport.RequestVoteArgs;
import com.mdc.mraft.transport.RequestVoteResponse;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: Rpc interface for Raft
 * @date 2023/10/7 21:05
 */
public interface RaftRpc {
    RequestVoteResponse requestVote(RequestVoteArgs requestVoteArgs);

    AppendEntryResponse appendEntry(AppendEntryArgs appendEntryArgs);
}
