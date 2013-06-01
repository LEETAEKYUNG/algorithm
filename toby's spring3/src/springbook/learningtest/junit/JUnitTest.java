package springbook.learningtest.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.either;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class JUnitTest {
	@Autowired
	ApplicationContext context;
//	static JUnitTest testObject;
	static Set<JUnitTest> testObject =  new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test
	public void test1(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void test2(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test
	public void test3(){
		assertThat(testObject, is(not(hasItem(this))));
		testObject.add(this);
		
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
}