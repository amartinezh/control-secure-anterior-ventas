package com.ventura.tiempos.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ventura.tiempos.domain.User;

@Repository(value = "userDao")
public class JPAUserDao implements UserDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        return em.createQuery("select p from User p order by p.id").getResultList();
    }
    
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public boolean val(String k, String p) {
    	List<User> results = em.createQuery("select u from User u where u.id = '"+k+"' and u.pass = '"+p+"'").getResultList();
    	if (results.isEmpty()) 
    		return false;
        else 
        	return true;
    }

    @Transactional(readOnly = false)
    public void saveUser(User user) {
        em.merge(user);
    }

}
