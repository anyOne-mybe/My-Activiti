
package com.guan.leave.service.identity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;

/**
 * 类说明
 * 
 * @author ****
 * @date 2017年7月30日 新建
 */
@Named
public class GroupServiceImpl implements GroupService
{

    @Inject
    private IdentityService identityService;

    @Override
    public List<Group> listGroups()
    {
        return identityService.createGroupQuery().list();
    }

}
