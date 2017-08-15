
package com.guan.leave.action.activiti;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 类说明
 * 
 * @author ****
 * @date 2017年7月30日 新建
 */
@Path( "/activiti" )
@Produces( MediaType.APPLICATION_JSON )
public interface IActivitiAction
{
    @GET
    @Path( "/startLeave" )
    void startLeave();

    @GET
    @Path( "/leaveList" )
    void leaveList();

    @GET
    @Path( "/tasks" )
    void taskLists();

    @GET
    @Path( "/completeTasks" )
    void completeTasks();

    @GET
    @Path( "/picture" )
    void picture();

}
