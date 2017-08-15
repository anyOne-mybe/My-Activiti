
package com.guan.leave.action.init.impl;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import com.guan.leave.action.init.IdentityAction;
import com.guan.leave.service.identity.GroupService;

/**
 * 初始化activiti的用户、组
 * 
 * @author ****
 * @date 2017年7月30日 新建
 */
@Named
public class IdentityActionImpl implements IdentityAction
{

    @Inject
    private IdentityService identityService;

    @Inject
    private RepositoryService repositoryService;

    @Inject
    private GroupService groupService1;

    @Override
    public void addUserAndGroup()
    {
        createUserAndGroup( "employee", "员工组", "employee", "id-yuangong", "员工甲",
                "123456789" );
        createUserAndGroup( "manager", "经理组", "manager", "id-jingli-jia", "经理甲",
                "123456789" );
        createUserAndGroup( "director", "总监组", "director", "id-zongjian", "总监甲",
                "123456789" );
        createUserAndGroup( "hr", "人事组", "hr", "id-hr", "人事甲", "123456789" );
        createUserAndGroup( "finance", "财务组", "finance", "id-caiwu", "财务甲",
                "123456789" );

        createUserAndGroup( "boss", "老板组", "boss", "id-laoban", "老板甲",
                "123456789" );

    }

    @Override
    public void initDeployment()
    {
        // 过滤重复部署，需要给name
        repositoryService.createDeployment().enableDuplicateFiltering()
                .name( "mydemo" ).addClasspathResource( "bpmn/My.bpmn20.xml" )
                .deploy();

    }

    @Override
    public List<Group> listGroups()
    {
        return groupService1.listGroups();
    }

    private void createUserAndGroup( String groupId, String groupName,
            String groupType, String userId, String userName, String password )
    {
        Group group = identityService.newGroup( groupId );
        group.setName( groupName );
        group.setType( groupType );
        identityService.saveGroup( group );

        User user = identityService.newUser( userId );
        user.setLastName( userName );
        user.setPassword( password );
        identityService.saveUser( user );
        identityService.setUserInfo( userId, "attr1", "value1" );

        identityService.createMembership( userId, groupId );

    }

}
