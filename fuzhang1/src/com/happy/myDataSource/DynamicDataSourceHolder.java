package com.happy.myDataSource;

public class DynamicDataSourceHolder {
	// 写库数据源key
	private static final String MASTER = "master";
	// 读库数据源key
	private static final String SLAVE = "slave";
	// 使用ThreadLocal记录当前数据源Key
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	/**
	 * 设置数据源key
	 * 
	 * @param key
	 */
	public static void putDataSourceKey(String key) {
		holder.set(key);
	}

	/**
	 * 获取数据源key
	 * 
	 * @return
	 */
	public static Object getDataSourceKey() {
		return holder.get();
	}

	/**
	 * 标记写库
	 */
	public static void markMaster() {
		putDataSourceKey(MASTER);
	}

	/**
	 * 标记读库
	 */
	public static void markSlave() {
		putDataSourceKey(SLAVE);
	}

}
