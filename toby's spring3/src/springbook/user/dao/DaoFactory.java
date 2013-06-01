package springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	@Bean
	public UserDao userDao(){
//		UserDao dao = new UserDao();
		UserDao dao = null;
//		dao.setDataSource(dataSource());
		return dao;
	}
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@1.237.79.66:1521:wiseora");
		dataSource.setUsername("KOTSA");
		dataSource.setPassword("KOTSA");
		
		return dataSource;
	}
}
