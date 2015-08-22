package com.hurontg.common.persistence;

import java.util.List;

public class TSqlManyToMany {
	/**
	 * Optimized method for inserting rows in a join table without updating the
	 * entities.
	 * 
	 * @param tableName
	 * @param fromColumnName
	 * @param toColumnName
	 * @param fromId
	 * @param toIds
	 * @return
	 */
	public static String buildInsertQuery(String tableName, String leftName,
			String rightName, Long leftId, List<Long> rightIds) {
		StringBuilder query = new StringBuilder();

		StringBuilder insertClause = new StringBuilder("INSERT INTO ")
				.append(tableName).append(" (").append(leftName).append(", ")
				.append(rightName).append(") ");

		for (Long rightId : rightIds) {
			query.append(insertClause)
				.append(" SELECT ").append(leftId).append(", ").append(rightId)
				// EXCEPT prevents duplicate errors
				.append(" EXCEPT ")
				.append(" SELECT * FROM ").append(tableName)
				.append(" WHERE ").append(leftName).append(" = ").append(leftId)
				.append(" AND ").append(rightName).append(" = ").append(rightId);
			query.append(" GO ");
		}
		return query.toString();
	}
	
	/**
	 * Optimized method for inserting rows in a join table without updating the
	 * entities.
	 * 
	 * @param tableName
	 * @param fromColumnName
	 * @param toColumnName
	 * @param fromId
	 * @param toIds
	 * @return
	 */
	public static String buildInsertQuery(String tableName, String leftName,
			String rightName, String leftId, String rightId) {
		StringBuilder query = new StringBuilder();

		StringBuilder insertClause = new StringBuilder("INSERT INTO ")
				.append(tableName).append(" (").append(leftName).append(", ")
				.append(rightName).append(") ");

		query.append(insertClause)
			.append(" SELECT :").append(leftId).append(", :").append(rightId)
				// EXCEPT prevents duplicate errors
				.append(" EXCEPT ")
				.append(" SELECT * FROM ").append(tableName)
				.append(" WHERE ").append(leftName).append(" = :").append(leftId)
				.append(" AND ").append(rightName).append(" = :").append(rightId);

		return query.toString();
	}
}
