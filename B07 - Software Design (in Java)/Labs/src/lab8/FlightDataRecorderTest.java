package lab8;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;

public class FlightDataRecorderTest {
	FlightDataRecorder fdr;
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
	//}

	@Before
	public void setUp() throws Exception {
		fdr = new FlightDataRecorder();
		//testRecord();
		fdr.record(-5.6);
		fdr.record(-3.4);
		fdr.record(-1.2);
		fdr.record(0.0);
		fdr.record(1.2);
		fdr.record(3.4);
		fdr.record(5.6);
		
	}

//	@Test
//	public void testFlightDataRecorder() {
		//fail("Not yet implemented");
//		
//	}

	@Test
	public void testRecord() {
//		fail("Not yet implemented");

		List<Double> act = fdr.getRecordedData();
		int exp = 7;
		assertEquals(exp, act.size());
		
		
	}

	@Test
	public void testGetRecordedData() {
	//	fail("Not yet implemented");
		List<Double> exp = new ArrayList<>();
		exp.add(-5.6); exp.add(-3.4); exp.add(-1.2);
		exp.add(0.0);
		exp.add(1.2); exp.add(3.4); exp.add(5.6);
		List<Double> act = fdr.getRecordedData();
		for(int i = 0; i < act.size(); i++) {
			assertEquals(exp.get(i), act.get(i), 0);
			//System.out.println("ran");
		}
	}

	@Test
	public void testGetLastDataPoints() {
		//fail("Not yet implemented");
		List<Double> act = fdr.getLastDataPoints(2);
		List<Double> exp = new ArrayList<>();
		exp.add(5.6); exp.add(3.4);
		for(int i = 0; i < 2; i++) {
			assertEquals(exp.get(i), act.get(i), 0);
			
		}
		
	}

	@Test
	public void testAverage() {
		//fail("Not yet implemented");
		double exp = 0;
		double act = fdr.average();
		assertEquals(exp,act, 0);
	}

}
 
