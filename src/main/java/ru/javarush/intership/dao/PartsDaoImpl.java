package ru.javarush.intership.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.intership.model.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class PartsDaoImpl implements PartsDao {
    private static final Logger logger = LoggerFactory.getLogger(PartsDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> getAllParts(int pageNum) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Part")
                .setFirstResult(10 * (pageNum - 1))
                .setMaxResults(10)
                .list();
    }

    @Override
    public int getPartsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(id) from Part", Number.class).getSingleResult().intValue();
    }

    @Override
    public int getComputersCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select min(num) from Part where isNeed = true", Number.class).getFirstResult();

    }

    @Override
    public int add(Part part){
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
        return 1;
    }

    @Override
    public int edit(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
        return 1;
    }


    @Override
    public int delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getPartById(id));
        return 1;
    }

    @Override
    public Part getPartById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }
}
