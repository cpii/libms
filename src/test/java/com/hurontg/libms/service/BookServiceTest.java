package com.hurontg.libms.service;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.Book;
import com.hurontg.libms.integration.HibernateIntegrationTest;

@ContextConfiguration(locations = { "classpath*:/applicationContext-test.xml",
		"classpath*:/libms-test-security.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class BookServiceTest extends HibernateIntegrationTest {
	XLogger logger = XLoggerFactory.getXLogger(BookServiceTest.class.getName());

	@Inject
	private BookService bookSvc;

	@Override
	protected boolean prepareSettings() {
		// addBeforeClassTestDataFiles(new String[] {
		// "dbunit/user-setup/user_setup.xml" });
		// addBeforeMethodTestDataFiles(new String[] {
		// "dbunit/purge-table/purge.xml" });

		return false;
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
	public void createVendorTest() {
		setupSecurityContext("user1");

		Integer noOfBooks = (Integer) jdbcTemplate.queryForObject(
				"select count(1) from book", Integer.class);
		Book book = new Book();
//		book.setAuthor("Jim Corbett");
		book.setCost(12.95);
		book.setTitle("The Temple Tiger");

		Book bk = bookSvc.createBook(book);

		Assert.assertEquals((++noOfBooks).intValue(), ((Integer) jdbcTemplate
				.queryForObject("select count(1) from book", Integer.class))
				.intValue());

	}
}
