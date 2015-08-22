package com.hurontg.libms.service;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.hurontg.libms.integration.HibernateIntegrationTest;

@ContextConfiguration(locations = { "classpath*:/applicationContext-test.xml",
		"classpath*:/libms-test-security.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class GenericServiceTest extends HibernateIntegrationTest {
	XLogger logger = XLoggerFactory.getXLogger(GenericServiceTest.class
			.getName());

	@Override
	protected boolean prepareSettings() {
		// addBeforeClassTestDataFiles(new String[] { "dbunit/user-setup/user_setup.xml" });

		return false; // return true if dbunit files are to be loaded.
	}

	/**
	 * 
	 */
	@AfterMethod(groups = { "integration" })
	public void cleanupAfterTest() {
		teardownSecurityContext();
	}

	/**
	 * A test to test if the test is passing the test
	 */
	@Test(groups = { "integration" })
	@Rollback(false)
	public void testTest() {
//		setupSecurityContext("admin_1");
		
		logger.debug("Testing framework appears to be working!");

	}
}
