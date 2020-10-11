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
	 * ��ѯ������ɱ��¼ 
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��ѯ������ɱ��¼
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ���������ɱ�ӿڵ�ַ��
	 * �������ϵͳʱ�����ɱʱ��
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
	
	/**
	 * ִ����ɱ����by �洢����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5) 
			throws SeckillException,RepeatKillException,SeckillCloseException;
}
