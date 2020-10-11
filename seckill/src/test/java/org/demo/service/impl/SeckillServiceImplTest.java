package org.demo.service.impl;

import java.util.List;

import org.demo.dto.Exposer;
import org.demo.dto.SeckillExecution;
import org.demo.entity.Seckill;
import org.demo.exception.RepeatKillException;
import org.demo.exception.SeckillCloseException;
import org.demo.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
	                   "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() throws Exception {
		long id = 1003;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}

	//测试代码完整逻辑，注意可重复执行
	@Test
	public void testSeckillLogic() throws Exception {
		long id = 1003;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()) {
			logger.info("exposer={}",exposer);
			long phone = 18327695311L;
			String md5 = exposer.getMd5();
			try {
				SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
				logger.info("execution={}",execution);
			} catch (RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SeckillCloseException e) {
				logger.error(e.getMessage());
			}
		}else {
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	
	@Test
	public void executeSeckillProcedure() {
		long seckillId = 1002;
		long userPhone = 12345678900L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()) {
			String md5 = exposer.getMd5();
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
			logger.info(execution.getStateInfo());
		}
	}
}
