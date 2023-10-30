package com.juneutf.kk.mtg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.service.PlanService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IndexMTGController {
	@Autowired
	private PlanService planService;

	/**
	 * ホームページへのアクセスを処理します。
	 *
	 * @return "index" - ホームページへ
	 */
	@GetMapping("/mtg")
	public String getIndexMTG(Model model) {
		try {
			// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!authentication.getName().equals("anonymousUser")) {
				return "redirect:/kk/mtg/job";
			}
			// 予約の取得
			ArrayList<JobModel> job = planService.selectPlan();
			// タイムリーフに予約内容の渡す
			model.addAttribute("job", job);
			return "mtg/index";
		} catch (Exception e) {
			log.error("予約内容を取得出来ませんでした。");
			return "error";
		}
	}
}
