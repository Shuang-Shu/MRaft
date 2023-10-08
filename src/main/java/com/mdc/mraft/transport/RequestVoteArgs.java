package com.mdc.mraft.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ShuangShu
 * @version 1.0
 * @description: TODO
 * @date 2023/10/3 15:44
 */
@Data
@Builder
@AllArgsConstructor
public class RequestVoteArgs implements Serializable {
    long term;
    long candidateId;
    long lastLogIndex;
    long lastLogTerm;

    public String toString() {
        return "RequestVoteArgs(term=" + this.getTerm() + ", candidateId=" + this.getCandidateId() + ", lastLogIndex=" + this.getLastLogIndex() + ", lastLogTerm=" + this.getLastLogTerm() + ")";
    }
}
