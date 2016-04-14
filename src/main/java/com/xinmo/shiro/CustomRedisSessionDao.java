package com.xinmo.shiro;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

public class CustomRedisSessionDao extends CachingSessionDAO {
    private static String sessionPrefix = "sid-";
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
        Serializable sessionId = sessionPrefix+generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session=null;
        try {
            String name = getActiveSessionsCacheName();
            session= (Session) super.getCacheManager().getCache(name).get(sessionId.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
	
	
}
