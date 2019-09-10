package com.happy.myDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDatasource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// 使用DynamicDataSourceHolder保证线程的安全，并获得数据源的Key
		return DynamicDataSourceHolder.getDataSourceKey();
	}

}
