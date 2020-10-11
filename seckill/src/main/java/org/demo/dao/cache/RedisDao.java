package org.demo.dao.cache;

import org.demo.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final JedisPool jedisPool;

	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}
	
	//可以通过反射拿到该类的属性
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	public Seckill getSeckill(long seckillId) {
		// redis操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckillId:" + seckillId;
				// 并没有实现内部序列化操作
				// get -> byte[ ] -> 反序列化 -> Object(Seckill)
				// 采用自定义序列化
				// protostuff : pojo
				byte[] bytes = jedis.get(key.getBytes());
				// 缓存重新获取到
				if(bytes != null) {
					//空对象
					Seckill seckill = schema.newMessage();
					ProtobufIOUtil.mergeFrom(bytes, seckill, schema);
					//seckill 被反序列化
					return seckill;
				}
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public String putSeckill(Seckill seckill) {
		// set Object(Seckill) -> 序列化 -> byte[ ]
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckillId:" + seckill.getSeckillId();
				byte[] bytes = ProtobufIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				int timeout = 60 * 60;//1小时
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
