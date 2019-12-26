package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.dao.CronMapper;
import com.moyuaninfo.waterinfo.service.WaterInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * @ClassName WaterInfoServiceImplQuartz
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/17 13:17
 * @Version 1.0
 **/
@Configuration
public class WaterInfoServiceImplQuartz implements SchedulingConfigurer {

    @Autowired
    private WaterInfoService waterInfoService;

    @Autowired
    private CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                try {
                    // 异步定时操作
                    waterInfoService.saveWaterInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String cron = cronMapper.getCron();
                if(StringUtils.isBlank(cron)) {
                    return null;
                }
                // 定时任务触发,可修改定时任务的执行周期
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }


}
