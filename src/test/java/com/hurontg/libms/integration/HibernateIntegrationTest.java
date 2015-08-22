package com.hurontg.libms.integration;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class HibernateIntegrationTest extends
		AbstractTransactionalTestNGSpringContextTests {
	private XLogger logger = XLoggerFactory
			.getXLogger(HibernateIntegrationTest.class.getName());

	protected List<TestDataHelper> testDataHelpers = new ArrayList<TestDataHelper>();
	private IDatabaseConnection dbUnitConnection = null;

	@Resource(name = "authenticationManager")
	protected AuthenticationManager authMgr;

	private DataSource datasource;

	@Inject
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeTest(alwaysRun = true)
	protected void startHibernate() throws Exception {

	}

	/**
	 * Convenient way to add multiple dbunit files in order. Note all end up in
	 * before-method
	 * 
	 * @param files
	 */
	protected void addTestDataFiles(String[] files) {
		TestDataHelper helper;
		for (String file : files) {
			helper = new TestDataHelper(file, true);
			testDataHelpers.add(helper);
		}
	}

	/**
	 * 
	 * @param files
	 */
	protected void addBeforeMethodTestDataFiles(String[] files) {
		for (String file : files) {
			TestDataHelper helper = new TestDataHelper(file);
			helper.addBeforeTestMethodOperation(InsertIdentityOperation.CLEAN_INSERT);
			testDataHelpers.add(helper);
		}
	}

	/**
	 * 
	 * @param files
	 */
	protected void addBeforeClassTestDataFiles(String[] files) {
		for (String file : files) {
			TestDataHelper helper = new TestDataHelper(file);
			helper.addBeforeTestClassOperation(InsertIdentityOperation.CLEAN_INSERT);
			testDataHelpers.add(helper);
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	protected void prepareDataSet() throws Exception {
		logger.entry();

		if (!prepareSettings()) {
			logger.info("No dataset required");
			return;
		}

		dbUnitConnection = getConnection();

		if (!(testDataHelpers.size() > 0))
			throw new RuntimeException("No dataset found");

		for (TestDataHelper helper : testDataHelpers) {
			logger.debug("PREPARING dataset for " + helper.getRawDataFile());
			InputStream input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(helper.getRawDataFile());
			ReplacementDataSet ds = new ReplacementDataSet(
					new FlatXmlDataSetBuilder().setColumnSensing(true)
							.setCaseSensitiveTableNames(false).build(input));
			ds.addReplacementObject("[NULL]", null);
			ds.addReplacementObject("[ne]", "&ne;");
			ds.addReplacementObject("[NOW]", Calendar.getInstance().getTime());
			helper.setTranslatedDataSet(ds);
		}

		logger.exit();
	}

	@AfterClass(alwaysRun = true)
	protected void tearDown() {
		if (dbUnitConnection != null) {
			try {
				dbUnitConnection.close();
			} catch (SQLException e) {
				logger.catching(e);
			}
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	protected void setupSecurityContext() {
		logger.entry();

		String principal = System.getProperty("authuser");
		if (principal == null || principal.isEmpty()) {
			logger.info("No Auth User defined from build tool.");
			return;
		}

		setupSecurityContext(principal);
		logger.exit();
	}

	/**
	 * Perform user login
	 * 
	 * @param principal
	 */
	protected void setupSecurityContext(String principal) {
		logger.entry(principal);
		UsernamePasswordAuthenticationToken preauth = new UsernamePasswordAuthenticationToken(
				principal, "password");
		Authentication auth = authMgr.authenticate(preauth);
		SecurityContextHolder.getContext().setAuthentication(auth);

		logger.info(auth.toString());
		logger.exit();
	}

	/**
	 * Perform user log out.
	 */
	protected void teardownSecurityContext() {
		logger.entry();
		SecurityContextHolder.clearContext();
		logger.exit();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeMethod(alwaysRun = true)
	protected void beforeTestMethod() throws Exception {
		logger.entry();

		if (dbUnitConnection != null) {
			for (TestDataHelper helper : testDataHelpers) {
				for (DatabaseOperation op : helper
						.getBeforeTestMethodOperations()) {
					logger.debug("RUNNING DB OPERATIONS FOR: "
							+ helper.getRawDataFile());
					op.execute(dbUnitConnection, helper.getTranslatedDataSet());
				}
			}
		}
		logger.exit();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@AfterMethod(alwaysRun = true)
	protected void afterTestMethod() throws Exception {
		logger.entry();
		if (dbUnitConnection != null) {
			for (TestDataHelper helper : testDataHelpers) {
				for (DatabaseOperation op : helper
						.getAfterTestMethodOperations()) {
					op.execute(dbUnitConnection, helper.getTranslatedDataSet());
				}
			}
		}
		logger.exit();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true, dependsOnMethods = { "prepareDataSet" })
	protected void beforeTestClass() throws Exception {
		logger.entry();

		if (dbUnitConnection != null) {
			for (TestDataHelper helper : testDataHelpers) {
				for (DatabaseOperation op : helper
						.getBeforeTestClassOperations()) {
					logger.debug("RUNNING DB OPERATIONS FOR: "
							+ helper.getRawDataFile());
					op.execute(dbUnitConnection, helper.getTranslatedDataSet());
				}
			}
		} else {
			logger.error("DB CONNECTION IS NULL, NOT RUNNING beforeTestClass operations");
		}
		logger.exit();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@AfterClass
	protected void afterTestClass() throws Exception {
		logger.entry();
		for (TestDataHelper helper : testDataHelpers) {
			for (DatabaseOperation op : helper.getAfterTestClassOperations()) {
				op.execute(dbUnitConnection, helper.getTranslatedDataSet());
			}
		}

		logger.exit();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected IDatabaseConnection getConnection() throws Exception {
		logger.entry();

		DatabaseConnection connection = null;

		Connection con = datasource.getConnection();
		connection = new DatabaseConnection(con);
		DatabaseConfig config = connection.getConfig();
		config.setProperty("http://www.dbunit.org/properties/datatypeFactory",
				new MsSqlDataTypeFactory());

		// Disable foreign key constraint checking
		con.prepareStatement(
				"EXEC sp_MSforeachtable @command1=\"ALTER TABLE ? NOCHECK CONSTRAINT ALL\"")
				.execute();

		logger.exit();

		return connection;
	}

	/**
	 * @return false indicates no DBUnit operations required.
	 * 
	 */
	protected abstract boolean prepareSettings();
}
