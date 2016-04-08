package com.xinmo.shiro;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

public class CustomRedisSessionDao extends CachingSessionDAO {

    @Override
    protected void doUpdate(Session session) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void doDelete(Session session) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        // TODO Auto-generated method stub
        return null;
    }
	
	
}
