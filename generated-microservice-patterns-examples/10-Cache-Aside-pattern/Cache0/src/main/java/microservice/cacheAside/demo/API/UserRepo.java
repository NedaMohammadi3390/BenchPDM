package microservice.cacheAside.demo.API;


org.springframework.stereotype.Repository;
import microservice.cacheAside.demo.API.User;

@Repository
public interface UserRepo  {

custom List<User> findAll();
custom User findBySid(Integer sid);
custom Integer create(User user);
custom Integer update(User user);
custom Integer disable(Integer sid);

}

