package com.wytiger.workflow.node;

import com.wytiger.workflow.Worker;

/**
 * description ：
 * author : wuyong_cd
 * date : 2020/1/21 0021
 */
public class WorkNode implements Node {
    /**
     * 节点id
     */
    private int nodeId;

    /**
     * 节点工作者
     */
    private Worker worker;

    /**
     * 工作回调
     */
    private WorkCallBack callBack;

    public static WorkNode build(int nodeId, Worker worker) {
        return new WorkNode(nodeId, worker);
    }

    /**
     * @param worker 调用者传入，即真正执行要做的事情
     */
    public WorkNode(int nodeId, Worker worker) {
        this.nodeId = nodeId;
        this.worker = worker;
    }

    /**
     * 由workFlow来决定调用
     *
     * @param callBack 当调用onCompleted 之后回调给WorkFlow
     */
    public void doWork(WorkCallBack callBack) {
        this.callBack = callBack;
        worker.doWork(this);
    }

    @Override
    public int getId() {
        return nodeId;
    }

    @Override
    public void onCompleted() {
        if (null != callBack) {
            callBack.onWorkCompleted();
        }
    }

    public interface WorkCallBack {

        /**
         * 当前任务完成
         */
        void onWorkCompleted();

    }
}

