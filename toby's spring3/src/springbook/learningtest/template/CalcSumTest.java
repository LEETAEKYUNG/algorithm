package springbook.learningtest.template;

import java.io.IOException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilePath;
	@Before
	public void setUp(){
		this.calculator = new Calculator();
		this.numFilePath = "D:\\numbers.txt";
	}
	
	@Test
	public void sumOfNumbers() throws IOException{
		assertThat(calculator.calcSum(numFilePath), is(10));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException{
		assertThat(calculator.calcMultiply(numFilePath), is(24));
	}
	
	@Test
	public void concatenateString() throws IOException{
		assertThat(calculator.concatenate(numFilePath), is("1234"));
	}
}