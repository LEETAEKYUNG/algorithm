package springbook.user.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
//@ContextConfiguration(locations="/test-applicationContext.xml")

public class UserDaoTestBfr4thLecture {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private DataSource dataSource;
	
	private User user1;
	private User user2;
	private User user3;
	
	
	@Before
	public void setUp(){
		this.user1 = new User("Worker3","이태경3","3", Level.BASIC, 1, 0);
		this.user2 = new User("Worker2","이태경2","2", Level.SILVER, 55, 10);
		this.user3 = new User("Worker1","이태경1","1", Level.GOLD, 100, 44);
	}
	
	@Test
	public void update(){
		dao.deleteAll();
		
		dao.add(user1);
		dao.add(user2);
		
		user1.setName("이태경5");
		user1.setPassword("2133");
		user1.setLevel(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		dao.update(user1);
		
		User user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
		
		User user2update = dao.get(user2.getId());
		checkSameUser(user2, user2update);
	}
	
	@Test
	public void sqlExceptionTranslate(){
		dao.deleteAll();
		try{
			dao.add(this.user1);
			dao.add(this.user1);
		}catch (DuplicateKeyException e) {
			SQLException sqlEx = (SQLException)e.getRootCause();
			SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
			
			assertThat(set.translate(null, null, sqlEx), is(DuplicateKeyException.class));
		}
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicateKey(){
		dao.deleteAll();
		
		dao.add(this.user1);
		dao.add(this.user1);
	}
	
	@Test
	public void getAll() throws SQLException, ClassNotFoundException{
		dao.deleteAll();
		
		List<User> users0 = dao.getAll();
		assertThat(users0.size(),is(0));
		
		dao.add(this.user1);
		List<User> users1 = dao.getAll();
		assertThat(users1.size(),is(1));
		checkSameUser(this.user1, users1.get(0));
		
		dao.add(this.user2);
		List<User> users2 = dao.getAll();
		assertThat(users2.size(),is(2));
		checkSameUser(this.user2, users2.get(0));
		checkSameUser(this.user1, users2.get(1));
		
		dao.add(this.user3);
		List<User> users3 = dao.getAll();
		assertThat(users3.size(),is(3));
		checkSameUser(this.user3, users3.get(0));
		checkSameUser(this.user2, users3.get(1));
		checkSameUser(this.user1, users3.get(2));
	}
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userGet1 = dao.get(user1.getId());
		checkSameUser(userGet1, user1);
		
		User userGet2 = dao.get(user2.getId());
		checkSameUser(userGet2, user2);
	}
	
	@Test
	public void count() throws ClassNotFoundException, SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws ClassNotFoundException, SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
				
		dao.get("1234");
	}
	
	private void checkSameUser(User user1, User user2){
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevel(), is(user2.getLevel()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
	}
}
