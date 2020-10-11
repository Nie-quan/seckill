package org.demo.web;

import java.util.Date;
import java.util.List;

import org.demo.dto.Exposer;
import org.demo.dto.SeckillExecution;
import org.demo.dto.SeckillResult;
import org.demo.entity.Seckill;
import org.demo.enums.SeckillStatEnum;
import org.demo.exception.RepeatKillException;
import org.demo.exception.SeckillCloseException;
import org.demo.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/seckill")// url:/模块/资源/{id}/细分
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    
    // 获取所有秒杀商品的信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
    	//获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("lists",list);
        return "list";  //    /WEB-INF/jsp/list.jsp
    }
    
    // 进入商品秒杀页面
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(null == seckillId){
            // 重定向
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if(null == seckill){
            // 请求转发
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }
    
    // 暴露秒杀地址
    // ajax json
    @RequestMapping(value = "/{seckillId}/exposer",
    		        method = RequestMethod.POST,
    		        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            // 封装结果
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }
    
    // 执行秒杀
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
    		        method = RequestMethod.POST,
    		        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
                                                    @PathVariable("md5")String md5,
                                                    @CookieValue(value = "killPhone",required = false) Long phone){
        if(phone == null){
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try{
            // 存储过程调用
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch (SeckillCloseException e1){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch (RepeatKillException e2){
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
    }
    
    // 获取系统当前时间
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}

