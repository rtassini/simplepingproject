package com.simplepingproject.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class PropertiesReadUtilTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPropertyReadIsNotNull() {
		assertNotNull(PropertiesReadUtil.propertyRead("delay"));
	}

}
