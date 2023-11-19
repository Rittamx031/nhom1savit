package edu.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@SuppressWarnings("unchecked")
@Service
public class SessionService {

    @Autowired
    HttpSession session ;
    public void set(String name,Object value){
        session.setAttribute(name, value);
    }
    public <T> T get(String name){
        return (T)session.getAttribute(name);
    }
    public void remove(String name){
        session.removeAttribute(name);
    }
    
}
