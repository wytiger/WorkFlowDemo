package com.wytiger.workflow;

import android.util.SparseArray;

import com.wytiger.workflow.node.WorkNode;

/**
 * description ：
 * author : wuyong_cd
 * date : 2020/1/21 0021
 */
public class WorkFlow {
    /**
     * 工作流节点
     */
    private SparseArray<WorkNode> flowNodes;

    private WorkFlow(SparseArray<WorkNode> flowNodes) {
        this.flowNodes = flowNodes;
    }

    public static class Builder {
        SparseArray<WorkNode> flowNodes;

        public Builder() {
            flowNodes = new SparseArray<>();
        }

        public Builder withNode(WorkNode node) {
            flowNodes.put(node.getId(), node);
            return this;
        }

        public WorkFlow build() {
            return new WorkFlow(flowNodes);
        }
    }

    /**
     * 开始工作，默认从第一个节点
     */
    public void start() {
        if (flowNodes.size() > 0) {
            startWithNode(flowNodes.keyAt(0));
        } else {
            throw new ArrayIndexOutOfBoundsException("Node cant be empty");
        }
    }

    /**
     * 基于某个节点Id 开始工作
     *
     * @param startNodeId 节点id
     */
    public void startWithNode(int startNodeId) {
        final int startIndex = flowNodes.indexOfKey(startNodeId);
        WorkNode startNode = flowNodes.valueAt(startIndex);
        startNode.doWork(new WorkNode.WorkCallBack() {
            @Override
            public void onWorkCompleted() {
                findAndExecuteNextNodeIfExist(startIndex);
            }
        });
    }

    private void findAndExecuteNextNodeIfExist(int startIndex) {
        final int nextIndex = startIndex + 1;
        final WorkNode nextNode = flowNodes.valueAt(nextIndex);
        if (null != nextNode) {
            nextNode.doWork(new WorkNode.WorkCallBack() {
                @Override
                public void onWorkCompleted() {
                    findAndExecuteNextNodeIfExist(nextIndex);
                }
            });
        }
    }

}
