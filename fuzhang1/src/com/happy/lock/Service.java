package com.happy.lock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 */
public class Service {
	public static JedisPool pool = null;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(200);
		// 设置最大空闲数
		config.setMaxIdle(8);
		// 设置最大等待时间
		config.setMaxWaitMillis(1000 * 100);

		// 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, "106.13.4.192",6379,3000,"19951226");//0791
		//pool = new JedisPool(config, "182.61.19.102",6379,3000,"Some_pass1");//阿龙
		//pool = new JedisPool(config, "106.13.8.154", 6379, 3000, "Some_Pass1");// 服装企业交流
		//pool = new JedisPool(config, "182.61.32.71", 6379, 3000, "Some_pass1");// 服装企业交流8
	}
}