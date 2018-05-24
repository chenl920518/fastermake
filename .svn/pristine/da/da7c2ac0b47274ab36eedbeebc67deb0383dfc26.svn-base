/**
 * *****************************************************************************************
 * 类描述：Redis操作接口
 *
 * @author: luohao
 * @date： 2018-05-16
 * @version 1.0
 * <p>
 * <p>
 * Version    Date                ModifiedBy                 Content
 * --------   ---------           ----------                -----------------------
 * 1.0.0        2018-05-16          luohao                    初版（重写）
 * ******************************************************************************************
 */
package com.cbt.cache;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbt.util.ApplicationContextUtil;

import java.util.Map;


/**
 * 	Redis 连接池
 * @author luohao
 *
 */
public class Redis {

    private final static Logger logger = LoggerFactory.getLogger(Redis.class);

    private static RedisUtil redisUtil =
            ApplicationContextUtil.getBean("redisUtil");

    public static boolean set(String key, String value) {
        return redisUtil.set(key, value);
    }

    public static String get(String key) {
        return redisUtil.get(key);
    }

    public static Long hset(String key, String field, String value) {
        Long res = 0L;
        //空值不存入redis
        if (StringUtils.isBlank(key) || StringUtils.isBlank(field)) {
            logger.warn("input is blank{[],[]}",key,field);
            return res;
        }
        return redisUtil.hset(key, field, value) == true ? 1L : res;
    }

    public static boolean hset(String key, Map<String,String> value) {
        //空值不存入redis
        if (StringUtils.isBlank(key) || value==null) {
            logger.warn("input is blank{[],[]}",key,value);
            return false;
        }
        return redisUtil.hmset(key,value);
    }

    public static String hget(String key, String field) {
        return (String) redisUtil.hget(key, field);
    }

    public static Long hdel(String key) {
        if (StringUtils.isBlank(key)) {
            logger.warn("input is blank{[]}",key);
            return 0L;
        }
        return redisUtil.hdel(key);
    }

    public static Long hdel(String key, String field) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(field)) {
            logger.warn("input is blank{[],[]}",key,field);
            return 0L;
        }
        return redisUtil.hdel(key, field);
    }
    /*重写
	private static final Logger logger = Logger.getLogger(Redis.class);
	private static JedisPool pool = null;

	*//**
     * 构建redis连接池
     *
     * @return JedisPool
     *//*
	public static void getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			//最大连接数
			config.setMaxTotal(Integer.parseInt(SysParamUtil.getParam("redis.maxTotal")));
			//最大空闲数
			config.setMaxIdle(Integer.parseInt(SysParamUtil.getParam("redis.maxIdle")));
			config.setMinIdle(25);
			//最大等待毫秒数
			config.setMaxWaitMillis(Long.parseLong(SysParamUtil.getParam("redis.maxWaitMillis")));
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			// 在连接还回给pool时，是否提前进行validate操作  
			config.setTestOnReturn(true);
			//如果一个连接300秒内没有任何的返回Jedis将关闭这个连接
			pool = new JedisPool(config, SysParamUtil.getParam("redis.host"), Integer.parseInt(SysParamUtil.getParam("redis.port")),Integer.parseInt(SysParamUtil.getParam("redis.timeout")),SysParamUtil.getParam("redis.auth"));
		}
	}
	
	
	
	*//**
     * 返还到连接池
     *
     * @param redis
     *//*
    @SuppressWarnings("deprecation")
	public static void returnResource_old(Jedis redis) {
        if (redis != null) {
            redis.close();
        }
    }
    @SuppressWarnings("deprecation")
	public static void returnResource(Jedis redis) {
        if (redis != null) {
        	try
        	{
        		redis.resetState();
               pool.returnResourceObject(redis);
        	}
        	catch(Exception e)
        	{
        		pool.returnBrokenResource(redis);  
        	}
           
        }
    }
    
	public static void closeJedisPool(Jedis jedis) {
		// System.out.println(Client.isBroken());
		if (jedis.isConnected()) {
			try {
				System.out.println("退出" + jedis.toString() + ":"
						+ jedis.quit());
				jedis.disconnect();
			} catch (Exception e) {
				System.out.println("退出失败");
				e.printStackTrace();
			}
			jedis.close();
		}
	} 
    
    *//**
     * 获取redis哈希内容
     * @param key
     * @param field
     * @param value
     * @return
     *//*
	 @SuppressWarnings("deprecation")
	public static String hget(String key, String field) {
		String value = null;
		// 键值为空值不能读取
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field)) {
			return value;
		}
		Jedis jedis = null;
		try {
			getPool();
			jedis = pool.getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			logger.error("Redis_Hget_117:" ,e);
			logger.error("hget()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
			pool.returnBrokenResource(jedis);
			// pool.returnBrokenResource(jedis);
			// e.printStackTrace();
			return value;
		}
		try {
			// 释放redis对象
			returnResource(jedis);
		} catch (Exception e) {
			logger.error("Redis_Hget_128:" , e);
			logger.error("hget()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
			pool.returnBrokenResource(jedis);

		}
		return value;
	}
	 
	 *//**
     * 设置redis哈希内容, 对哈希进行hset不会修改已经设置的key过期时间
     * @param key
     * @param field
     * @param value
     * @return
     *//*
	 @SuppressWarnings("deprecation")
	public static Long hset(String key,String field,String value){
        Long res = 0L;
        
        //空值不存入redis
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
        	return res;
        }
        Jedis jedis = null;
        try {
            getPool();
            jedis = pool.getResource();
            if (jedis.exists(key)) {					
            	//设置内容
            	res = jedis.hset(key, field, value);
			}else{
				res = jedis.hset(key, field, value);
			}
            //设置过期时间:(时间单位:秒),2880*60=172800
			jedis.expire(key, Integer.parseInt(SysParamUtil.getParam("redis.expiredTime")));
        } catch (Exception e) {
        	logger.error("Redis_Hset_164:",e);
        	logger.error("hset()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
      	    pool.returnBrokenResource(jedis);
        }
        try{
            //释放redis对象
     	   returnResource(jedis);
         }
         catch (Exception e) {
         	logger.error("Redis_Hset_173:",e);
         	logger.error("hset()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
         	pool.returnBrokenResource(jedis);
         	
         }
         return res;
    }
	 
	 *//**
     * 删除redis哈希内容
     * @param key
     * @return
     *//*
	 @SuppressWarnings("deprecation")
	public static Long hdel(String key, String field) {
		Long res = 0L;
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field)) {
			return res;
		}
		Jedis jedis = null;
		try {
			getPool();
			jedis = pool.getResource();
			res = jedis.hdel(key, field);
		} catch (Exception e) {
			// 释放redis对象
			logger.error("Redis_Hset_199:" ,e);
			logger.error("hdel()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
			pool.returnBrokenResource(jedis);
		}
		try {
			// 释放redis对象
			returnResource(jedis);
		} catch (Exception e) {
			logger.error("Redis_Hset_207:" , e);
			logger.error("hdel()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
			pool.returnBrokenResource(jedis);
		}
		return res;
	}
	 
	 *//**
     * 删除redis哈希内容
     * @param key
     * @return
     *//*
	 @SuppressWarnings("deprecation")
	public static Long hdel(String key){
        Long res = 0L;
        if(StringUtils.isBlank(key)){
        	return res;
        }
        Jedis jedis = null;
        try {
            getPool();
            jedis = pool.getResource();
            res = jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
        	logger.error("hdel()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
        	logger.error("Redis_Hset_233:" ,e);
			pool.returnBrokenResource(jedis);
        }
        try {
			// 释放redis对象
			returnResource(jedis);
		} catch (Exception e) {
			logger.error("Redis_Hset_240:" ,e);
			logger.error("hdel()>>>>>--------redisPool info,numActive：" + pool.getNumActive()+", numIdle："+pool.getNumIdle()+", numWaiters："+pool.getNumWaiters());
			pool.returnBrokenResource(jedis);
		}
        return res;
    }
	 
 	*//**
     *  根据key获取redis的内容
     * @param key
     * @return
     *//*
	@SuppressWarnings("deprecation")
	public static String get(String key) {
		String res = null;

		if(StringUtils.isBlank(key)){
			return res;
		}
        Jedis jedis = null;
		try {
			getPool();
			jedis = pool.getResource();
			res = jedis.get(key);
		} catch (Exception e) {
			// 释放redis对象
			 returnResource(jedis);
	          //pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
		return res;
	}
	
	*//**
     * 判断key是否存在
     * @param key
     * @return
     *//*
	@SuppressWarnings("deprecation")
	public static Boolean exists(String key) {
		Boolean res = null;
		if(StringUtils.isBlank(key)){
			return false;
		}
        Jedis jedis = null;
		try {
			getPool();
			jedis = pool.getResource();
			res = jedis.exists(key);
		} catch (Exception e) {
			// 释放redis对象
			 returnResource(jedis);
	          //pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
		return res;
	}
	
	*/

}
