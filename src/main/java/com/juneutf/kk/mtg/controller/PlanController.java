package com.juneutf.kk.mtg.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.kk.config.vender.CustomUser;
import com.juneutf.kk.config.vender.GetIntoDay;
import com.juneutf.kk.mtg.model.APIChargeModel;
import com.juneutf.kk.mtg.model.APIPurposeModel;
import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.model.PlanModel;
import com.juneutf.kk.mtg.service.APIService;
import com.juneutf.kk.mtg.service.PlanService;

import lombok.extern.slf4j.Slf4j;

/**
 * 予定（予約）に関する操作を処理するコントローラークラスです。
 */
@Slf4j
@Controller
@RequestMapping("/kk/mtg")
public class PlanController {
    @Autowired
    private PlanService planService;
    @Autowired
    private GetIntoDay getIntoDay;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private APIService apiService;

    /**
     * 予定ページにアクセスするためのGETリクエストを処理します。
     * @return 予定ページ
     */
    @GetMapping("/plan")
    public String getPlan(Model model) {
    	try {
    		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUser customUser = (CustomUser) userDetails;
            ArrayList<APIPurposeModel> purpose = apiService.selectAPIPurpose(0);
            model.addAttribute("purpose", purpose); 
            if(customUser.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            	ArrayList<APIChargeModel> jobadmin = apiService.selectAPICharge(0);
            	model.addAttribute("charge", jobadmin);    
            }else {
            	ArrayList<JobModel> jobuser = planService.selectPublicId(customUser.getId());
            	model.addAttribute("charge", jobuser);            	
			}
            return "mtg/plan/plan";
		} catch (Exception e) {
			return "error";
		}
    }

    /**
     * 予定の登録を処理するPOSTリクエストを受け付け、予定をデータベースに登録します。
     *
     * @param planModel 予定モデル
     * @param result バインディング結果
     * @param model モデルオブジェクト
     * @return 予定登録後のリダイレクト先またはエラーページ
     */
    @PostMapping("/plan")
    public String plan(@Valid PlanModel planModel, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("msg", result.getAllErrors().get(0).getDefaultMessage());
                return "mtg/plan/plan";
            }
            String toDayString = getIntoDay.setDay(planModel.getDate_plan(), planModel.getDate_day());
            planModel.setDate_day(toDayString);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUser customUser = (CustomUser) userDetails;
            planModel.setRegisterid(customUser.getId());
            // データベースに登録
            int insertPlan = planService.insertPlan(planModel);
            if (insertPlan == 0) {
                log.warn("予約内容をデータベースに登録できない！");
                return "error";
            }
            // Websocket行動
            ArrayList<JobModel> job = planService.selectPlan();
            messagingTemplate.convertAndSend("/job/notification", job);
            int nowJob = planService.selectMaxIdPlan().get(0).getMAX();
            log.info("予約内容の新規登録情報：" + planModel);
            return "redirect:/kk/mtg/getjob?id=" + nowJob;
        } catch (Exception e) {
            log.warn("新規登録予約はエラーが発生しました。");
            return "error";
        }
    }
}
