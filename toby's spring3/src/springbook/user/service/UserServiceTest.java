package springbook.user.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static springbook.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")

public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	List<User> users; 
	
	@Before
	public void setUp(){
		users = Arrays.asList(
					new User("ltk","이태경","p1",Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
					new User("wise","위세","p2",Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
					new User("jyp","박진영","p3",Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1),
					new User("sm","이수만","p4",Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
					new User("yg","양현석","p5",Level.GOLD, 100, Integer.MAX_VALUE)
				);
	}
	
	@Test
	public void upgradeLevels(){
		userDao.deleteAll();
		for(User user:users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	}

	private void checkLevel(User user, boolean upgraded){
		User userUpdate = userDao.get(user.getId());
		if(upgraded==true){
			assertThat(user.getLevel().nextLevel(), is(userUpdate.getLevel()));
		}else{
			assertThat(user.getLevel(), is(userUpdate.getLevel()));
		}
	}
}