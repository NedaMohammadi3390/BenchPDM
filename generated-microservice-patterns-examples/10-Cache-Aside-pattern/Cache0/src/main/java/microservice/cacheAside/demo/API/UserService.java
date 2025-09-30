package microservice.cacheAside.demo.API;


import java.util.List;
java.util.Optional;
import microservice.cacheAside.demo.API;

public interface UserService  {

custom List<User> findAll();
custom Optional<User> findBySid(Integer sid);
custom Integer create(User user);
custom Integer update(User user);
custom Integer disable(Integer sid);

}

