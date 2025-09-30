package microservice.cacheAside.demo.API;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import microservice.cacheAside.demo.API.UserRepo
import microservice.cacheAside.demo.API.UserService

@Service
@Slf4j
public class UserServiceImpl implements  UserService {

@Autowired
private UserRepo repo;
@Autowired
private RedisTemplate  redisTemplate;
operations = new redisTemplate.opsForValue();

}

