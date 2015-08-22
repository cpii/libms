/**
 * 
 */
package com.hurontg.common.exception;

/**
 * Base class for all Service related (business logic) exceptions. 
 * @author mansoork
 *
 */
public class AppServiceException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param s
	 */
	public AppServiceException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 * @param root
	 */
	public AppServiceException(String s, Throwable root) {
		super(s, root);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param root
	 */
	public AppServiceException(Throwable root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

}
