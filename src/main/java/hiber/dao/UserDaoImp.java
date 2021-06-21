package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int series) {
//        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
//        "from User u left join fetch u.car where u.car.model=:model_param and u.car.series=:series_param");
/////////////////////////////////////join'ы не нужны???/////////////////////////////
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "from User u where u.car.model=:model_param and u.car.series=:series_param");
        query.setParameter("model_param", model);
        query.setParameter("series_param", series);
        return query.getResultList();
    }

}
