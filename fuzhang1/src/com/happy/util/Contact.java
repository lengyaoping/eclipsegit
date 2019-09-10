package com.happy.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Contact {
	public static int secondCount = 30;
	/**
	 * 提交队列
	 */
	public static BlockingQueue suBQueue = new ArrayBlockingQueue(1);
	public static Queue people_total_num_queue = null;
	public static Queue initQueue = new Queue(1);

}
