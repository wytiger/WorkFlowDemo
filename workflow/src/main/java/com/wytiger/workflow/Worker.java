package com.wytiger.workflow;


import com.wytiger.workflow.node.Node;

/**
 * description ：
 * author : wuyong_cd
 * date : 2020/1/21 0021
 */
public interface Worker {
    /**
     * 执行任务
     *
     * @param current 当前节点
     */
    void doWork(Node current);
}
