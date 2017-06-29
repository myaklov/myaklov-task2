package com.alex.spring.dao;

import com.alex.spring.Model.TaskList;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by Alex on 28.06.2017.
 */
@Repository
public class DaoImpl implements Dao {

    private static final Logger logger= LoggerFactory.getLogger(DaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTask(TaskList taskList) {
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(taskList);
        logger.info("запись добавлена: "+taskList);

    }

    public void removeTask(int id) {

        Session session=sessionFactory.getCurrentSession();
        TaskList taskList=(TaskList) session.load(TaskList.class,new Integer(id));
        if(taskList!=null){session.delete(taskList);}
        logger.info("запись удалена "+taskList);

    }

    public void updateTask(TaskList taskList) {
        Session session=sessionFactory.getCurrentSession();
        session.update(taskList);
        logger.info("запись обновлена "+taskList);
    }

    public TaskList getTaskById(int id) {
        Session session=sessionFactory.getCurrentSession();
        TaskList taskList=(TaskList) session.load(TaskList.class,new Integer(id));
        logger.info("информация успешно получена "+taskList);
        return taskList;
    }

    public List<TaskList> getListTasks(String status) {
        Session session=sessionFactory.getCurrentSession();
        List<TaskList> list=null;

        if("done".equals(status)){
            list=session.createQuery("FROM TaskList where status='done'").list();
        } else if("not done".equals(status)){
            list=session.createQuery("FROM TaskList where status='not done'").list();
        } else {

            list = session.createQuery("from TaskList").list();
        }



        for (TaskList taskList:list){
            logger.info("Task list: "+taskList);
        }
        return list;
    }
}
