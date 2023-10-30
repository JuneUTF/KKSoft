package com.juneutf.kk.office.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IndexOfficeController {
	@GetMapping("/office")
	public String getIndexOffice() {
		try {
			// ログイン状態の検証、ログイン済み場合はアクセス無効ー＞ｋｋページに遷移
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!authentication.getName().equals("anonymousUser")) {
				return "redirect:/kk/office/sales";
			}
			// 予約の取得
			//xu ly man hinhg
			return "office/index";
		} catch (Exception e) {
			log.warn("面談管理システムのインデックスページが表示されてない。");
			return "error";
		}
		
	}
}

