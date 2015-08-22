package com.hurontg.libms.integration;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.operation.DatabaseOperation;

public class TestDataHelper {
	private String rawDataFile;
	private ReplacementDataSet translatedDataSet;
	private List<DatabaseOperation> beforeTestMethodOperations = new ArrayList<DatabaseOperation>();
	private List<DatabaseOperation> afterTestMethodOperations = new ArrayList<DatabaseOperation>();
	private List<DatabaseOperation> beforeTestClassOperations = new ArrayList<DatabaseOperation>();
	private List<DatabaseOperation> afterTestClassOperations = new ArrayList<DatabaseOperation>();

	/**
	 * 
	 * @param dataFile
	 */
	public TestDataHelper(String dataFile) {
		this.rawDataFile = dataFile;
	}

	public TestDataHelper(String dataFile, boolean insert) {
		this(dataFile);
		if (insert) {
			this.addBeforeTestMethodOperation(InsertIdentityOperation.CLEAN_INSERT);
		}
	}

	/**
	 * @return the rawDataFile
	 */
	public String getRawDataFile() {
		return rawDataFile;
	}

	/**
	 * @param rawDataFile
	 *            the rawDataFile to set
	 */
	public void setRawDataFile(String rawDataFile) {
		this.rawDataFile = rawDataFile;
	}

	/**
	 * @return the translatedDataSet
	 */
	public ReplacementDataSet getTranslatedDataSet() {
		return translatedDataSet;
	}

	/**
	 * @param translatedDataSet
	 *            the translatedDataSet to set
	 */
	public void setTranslatedDataSet(ReplacementDataSet translatedDataSet) {
		this.translatedDataSet = translatedDataSet;
	}

	/**
	 * @return the beforeTestMethodOperations
	 */
	public List<DatabaseOperation> getBeforeTestMethodOperations() {
		return beforeTestMethodOperations;
	}

	/**
	 * @param beforeTestMethodOperations
	 *            the beforeTestMethodOperations to set
	 */
	public void setBeforeTestMethodOperations(
			List<DatabaseOperation> beforeTestMethodOperations) {
		this.beforeTestMethodOperations = beforeTestMethodOperations;
	}

	/**
	 * @return the afterTestMethodOperations
	 */
	public List<DatabaseOperation> getAfterTestMethodOperations() {
		return afterTestMethodOperations;
	}

	/**
	 * @param afterTestMethodOperations
	 *            the afterTestMethodOperations to set
	 */
	public void setAfterTestMethodOperations(
			List<DatabaseOperation> afterTestMethodOperations) {
		this.afterTestMethodOperations = afterTestMethodOperations;
	}

	/**
	 * @return the beforeTestClassOperations
	 */
	public List<DatabaseOperation> getBeforeTestClassOperations() {
		return beforeTestClassOperations;
	}

	/**
	 * @param beforeTestClassOperations
	 *            the beforeTestClassOperations to set
	 */
	public void setBeforeTestClassOperations(
			List<DatabaseOperation> beforeTestClassOperations) {
		this.beforeTestClassOperations = beforeTestClassOperations;
	}

	/**
	 * @return the afterTestClassOperations
	 */
	public List<DatabaseOperation> getAfterTestClassOperations() {
		return afterTestClassOperations;
	}

	/**
	 * @param afterTestClassOperations
	 *            the afterTestClassOperations to set
	 */
	public void setAfterTestClassOperations(
			List<DatabaseOperation> afterTestClassOperations) {
		this.afterTestClassOperations = afterTestClassOperations;
	}

	/**
	 * 
	 * @param op
	 */
	public void addBeforeTestMethodOperation(DatabaseOperation op) {
		beforeTestMethodOperations.add(op);
	}

	/**
	 * 
	 * @param op
	 */
	public void addAfterTestMethodOperation(DatabaseOperation op) {
		afterTestMethodOperations.add(op);
	}

	/**
	 * 
	 * @param op
	 */
	public void addBeforeTestClassOperation(DatabaseOperation op) {
		beforeTestClassOperations.add(op);
	}

	/**
	 * 
	 * @param op
	 */
	public void addAfterTestClassOperation(DatabaseOperation op) {
		afterTestClassOperations.add(op);
	}

}
