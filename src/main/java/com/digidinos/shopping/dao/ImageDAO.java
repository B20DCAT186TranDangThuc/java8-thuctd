package com.digidinos.shopping.dao;

import com.digidinos.shopping.entity.Image;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Image save(Image image) {

        sessionFactory.getCurrentSession().save(image);
        return image;
    }
}
