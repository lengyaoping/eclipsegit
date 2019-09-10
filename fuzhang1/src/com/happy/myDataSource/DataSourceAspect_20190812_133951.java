package com.happy.myDataSource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

public class DataSourceAspect {
	/**
	 * 在进入service前执行
	 * 
	 * @param point
	 *            切面对象
	 */
	public void before(JoinPoint point) {
		// 获取当前执行的方法名
		String methodName = point.getSignature().getName();
		if (isSlave(methodName)) {
			// 标记为读库
			DynamicDataSourceHolder.markSlave();
		} else {
			// 标记为写库
			DynamicDataSourceHolder.markMaster();
		}

	}

	/**
	 * 判断是否为读库
	 * 
	 * @param methodName
	 * @return
	 */
	private boolean isSlave(String methodName) {
		return StringUtils.startsWithAny(methodName, "query", "find", "get",
				"comb", "list", "export", "load");
	}

}
