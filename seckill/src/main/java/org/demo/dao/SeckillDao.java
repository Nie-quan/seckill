package org.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.demo.entity.Seckill;

public interface SeckillDao {
	
	/**
	 * ����棬
	 * @param seckillId
	 * @param killTime
	 * @return ���Ӱ�������>1����ʾ���µļ�¼����
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * ����id������ɱ����
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ����ƫ������ѯ��ɱ��Ʒ�б�
	 * @param offer
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
	 * ʹ�ô洢����ִ����ɱ
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
}