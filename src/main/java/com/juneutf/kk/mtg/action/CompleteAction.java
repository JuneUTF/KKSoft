package com.juneutf.kk.mtg.action;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.juneutf.kk.config.vender.CheckTime;
import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.service.ActionService;
import com.juneutf.kk.mtg.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompleteAction {
    @Autowired
    private PlanService planService;
    @Autowired
    private CheckTime checkTime;
    @Autowired
    private ActionService actionService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 定期的に呼び出され、アクションを実行し、WebSocketを介して通知を送信します。
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void logNumberOne() {
        // 予約を選択します。
        ArrayList<JobModel> jobModel = planService.selectPlan();
        jobModel.forEach(e -> {
            // 終了時間がまだ到達していない場合、アクションを更新します。
            if (!checkTime.checkTimeEnd(e.getDate_plan(), e.getTime_end())) {
                actionService.actionUpdateById(e.getId());
                log.info("予約内容終了： "+ e);
            }
        });
        // WebSocket行動
        ArrayList<JobModel> job = planService.selectPlan();
        messagingTemplate.convertAndSend("/job/notification", job);
    }
}
