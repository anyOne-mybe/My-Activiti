
package com.guan.leave.action.activiti.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;

import com.guan.leave.action.activiti.IActivitiAction;

/**
 * 类说明
 * 
 * @author ****
 * @date 2017年7月30日 新建
 */
@Named
public class ActivitiActionImpl implements IActivitiAction
{
    @Inject
    private RepositoryService repositoryService;

    @Inject
    private TaskService taskService;

    @Inject
    private RuntimeService runtimeService;

    @Inject
    private IdentityService identityService;

    @Override
    /**
     * 启动流程
     */
    public void startLeave()
    {
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey( "My_demo" ).singleResult();

        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey( processDefinition.getKey() );

        Task task = taskService.createTaskQuery()
                .processInstanceId( processInstance.getId() ).singleResult();

        taskService.setAssignee( task.getId(), "id-yuangong" );

        Map<String, Object> args = new HashMap<>();
        args.put( "attr1", "value1" );

        taskService.complete( task.getId(), args );

    }

    @Override
    /**
     * 查询流程
     */
    public void leaveList()
    {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId( "2501" ).singleResult();

        System.out.println( instance.getBusinessKey() );

    }

    @Override
    /**
     * 查询任务列表
     */
    public void taskLists()
    {
        Group group = identityService.createGroupQuery()
                .groupMember( "id-jingli-jia" ).singleResult();

        List<Task> taskS = taskService.createTaskQuery()
                .taskCandidateGroup( group.getId() ).list();

        ProcessInstance processInstance = null;
        for ( Task task : taskS )
        {
            processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId( task.getProcessInstanceId() )
                    .singleResult();

            Object value = runtimeService.getVariable( processInstance.getId(),
                    "attr1" );
            System.out.println( value );
        }
    }

    @Override
    public void completeTasks()
    {
        Group group = identityService.createGroupQuery()
                .groupMember( "id-jingli-jia" ).singleResult();

        List<Task> taskS = taskService.createTaskQuery()
                .taskCandidateGroup( group.getId() ).list();

        ProcessInstance processInstance = null;
        for ( Task task : taskS )
        {
            processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId( task.getProcessInstanceId() )
                    .singleResult();

            // 设置用户id(评论需要用到)
            identityService.setAuthenticatedUserId(
                    "156ba0e9-d94a-4805-be8c-c119e26fc1b2" );

            // 添加评论
            taskService.addComment( task.getId(), processInstance.getId(),
                    "经理审核意见通过" );

            // 审批
            taskService.complete( task.getId() );
        }

    }

    @Override
    public void picture()
    {
        Group group = identityService.createGroupQuery()
                .groupMember( "id-jingli-jia" ).singleResult();

        List<Task> taskS = taskService.createTaskQuery()
                .taskCandidateGroup( group.getId() ).list();

        ProcessInstance processInstance = null;

        for ( Task task : taskS )
        {
            processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId( task.getProcessInstanceId() )
                    .singleResult();
            ProcessDefinitionEntity pd = (ProcessDefinitionEntity)repositoryService
                    .getProcessDefinition(
                            processInstance.getProcessDefinitionId() );

        }

    }

}
