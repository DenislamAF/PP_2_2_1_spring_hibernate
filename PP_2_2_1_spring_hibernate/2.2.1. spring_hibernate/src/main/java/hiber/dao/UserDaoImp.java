package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserWithCar(String model, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("FROM User user LEFT OUTER JOIN FETCH user.car WHERE user.car.model =:carModel and user.car.series =:carSeries");
      query.setParameter("carModel", model);
      query.setParameter("carSeries", series);
      return (User) query.getSingleResult();
   }
}
