package springbook.user.test;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoConnectionCountingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		UserDao dao2 = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		User user2 = new User();

		user.setId("zone1");
		user.setName("���°�");
		user.setPassword("123");
		
		user2.setId("zone2");
		user2.setName("���°�1");
		user2.setPassword("1231");
		
		dao.add(user);
		dao2.add(user2);
		System.out.println(user.getId()+"��ϼ���");
		
		User user3 = dao.get(user.getId());
//		User user4 = dao2.get(user2.getId());
		System.out.println(user3.getName());
		System.out.println(user3.getPassword());
		System.out.println(user3.getId()+"��ȸ ����");
		
//		System.out.println(user4.getName());
//		System.out.println(user4.getPassword());
//		System.out.println(user4.getId()+"��ȸ ����");
		
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection counter : "+ccm.getCounter());
	}

}
