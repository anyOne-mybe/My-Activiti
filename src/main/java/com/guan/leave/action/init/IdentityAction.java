
package com.guan.leave.action.init;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.activiti.engine.identity.Group;

/**
 * 类说明
 * 
 * @author ****
 * @date 2017年7月30日 新建
 */
@Path( "/init" )
@Produces( MediaType.APPLICATION_JSON )
public interface IdentityAction
{
    @GET
    @Path( "/userAndGroup" )
    void addUserAndGroup();

    @GET
    @Path( "/deployment" )
    void initDeployment();

    @GET
    @Path( "/groups" )
    List<Group> listGroups();

}
