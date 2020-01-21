package com.wytiger.workflowdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wytiger.workflow.WorkFlow;
import com.wytiger.workflow.Worker;
import com.wytiger.workflow.node.Node;
import com.wytiger.workflow.node.WorkNode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View v) {
        WorkFlow workFlow = new WorkFlow.Builder()
                .withNode(getFirstNode())
                .withNode(getSecondNode())
                .build();
        workFlow.start();
    }

    private WorkNode getSecondNode() {
        return WorkNode.build(1, new Worker() {
            @Override
            public void doWork(Node current) {
                Toast.makeText(MainActivity.this, "任务2", Toast.LENGTH_SHORT).show();
                current.onCompleted();
            }
        });
    }

    private WorkNode getFirstNode() {
        return WorkNode.build(1, new Worker() {
            @Override
            public void doWork(Node current) {
                Log.d("WorkFlow", "任务1");
                current.onCompleted();
            }
        });
    }
}
