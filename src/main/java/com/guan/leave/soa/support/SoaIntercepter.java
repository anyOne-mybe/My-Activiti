
package com.guan.leave.soa.support;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.google.gson.Gson;

/**
 * 类说明
 * 
 * @author ****
 * @date 2017年8月2日 新建
 */
@Named
public class SoaIntercepter extends AbstractPhaseInterceptor
{

    public SoaIntercepter()
    {
        super( Phase.POST_PROTOCOL );
    }

    public SoaIntercepter( String phase )
    {
        super( phase );
    }

    @Override
    public void handleMessage( Message message ) throws Fault
    {
        Map<String, List> headers = (Map<String, List>)message
                .get( Message.PROTOCOL_HEADERS );

        Gson gson = new Gson();
        String js = gson.toJson( headers );

        System.out.println( js );

        headers.put( "authent",
                Collections.singletonList( "Base64 adfjimmi;adf" ) );

    }

}
