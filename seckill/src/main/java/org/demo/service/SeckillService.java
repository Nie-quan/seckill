package org.demo.service;

import java.util.List;

import org.demo.dto.Exposer;
import org.demo.dto.SeckillExecution;
import org.demo.entity.Seckill;
import org.demo.exception.RepeatKillException;
import org.demo.exception.SeckillCloseException;
import org.demo.exception.SeckillException;

public interface SeckillService {
	
	/**
	 * 查询所有秒杀记录 
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启输出秒杀接口地址，
	 * 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
	/**
	 * 执行秒杀操作by 存储过程
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
}
