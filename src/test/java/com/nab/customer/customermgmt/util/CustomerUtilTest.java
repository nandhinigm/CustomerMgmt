package com.nab.customer.customermgmt.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CustomerUtilTest {
	
	@Test
    public void test_validateName_success() throws Exception {
    	assertTrue(CustomerUtil.validateName(""));        
    }
	
	@Test
    public void test_validateName_success_validname() throws Exception {
    	assertTrue(CustomerUtil.validateName("John"));        
    }
	
	@Test
    public void test_validateName_fail() throws Exception {
    	assertFalse(CustomerUtil.validateName("3345"));        
    }

}
