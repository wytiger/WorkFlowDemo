package com.wytiger.workflow.node;

/**
 * description ：
 * author : wuyong_cd
 * date : 2020/1/21 0021
 */
public interface Node {
    /**
     * 节点id
     *
     * @return 当前节点id
     */
    int getId();
    /**
     * 任务完成时触发
     */
    void onCompleted();
}
