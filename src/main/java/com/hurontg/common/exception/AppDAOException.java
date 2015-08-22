/**
 * 
 */
package com.hurontg.common.exception;

/**
 * The base class of all Data Access exceptions. This class may be sub-classed for more 
 * specific exceptions.
 * 
 * @author mansoork
 *
 */
public class AppDAOException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String UNIQUE_CONSTRAINT_VIOLATION = "UNIQUE_CONSTRAINT_VIOLATION";
	public static final String NO_RESULT_FOUND = "NO_RESULT_FOUND";
	
	private String internalCause = "";
	
	/**
	 * @param s
	 */
	public AppDAOException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 * @param root
	 */
	public AppDAOException(String s, Throwable root) {
		super(s, root);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param root
	 */
	public AppDAOException(Throwable root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	public AppDAOException(String s, Throwable root, String internalCause) {		
		super(s, root);
		this.internalCause = internalCause;
	}
	
	/**
	 * @return the internalCause
	 */
	public String getInternalCause() {
		return internalCause;
	}

	/**
	 * @param internalCause the internalCause to set
	 */
	public void setInternalCause(String internalCause) {
		this.internalCause = internalCause;
	}

	/**
	 * 
	 * @return True if this is actually a CVE
	 */
	public boolean isConstraintViolation() {
		return internalCause.equals(UNIQUE_CONSTRAINT_VIOLATION);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isNoResultFoundException() {
		return internalCause.equals(NO_RESULT_FOUND);
	}

}
