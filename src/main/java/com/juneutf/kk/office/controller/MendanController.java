package com.juneutf.kk.office.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.kk.config.vender.SalesVender;
import com.juneutf.kk.office.model.MendanModel;
import com.juneutf.kk.office.service.MendanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk/office")
public class MendanController {
	@Autowired
	private MendanService mendanService;
	@Autowired
	private SalesVender salesVender;
	@GetMapping("/mendan")
	public String getMendan(Model model) {
		try {
			List<MendanModel> menndan = mendanService.getMendanByStatus();
			for(MendanModel mendanModel : menndan) {
				String nameIdCover = salesVender.CoverListIdToListNameInterview(mendanModel.getNameid());
				mendanModel.setNameid(nameIdCover);
			}
			model.addAttribute("mendan", salesVender.coverYearMonthDayJP(menndan));
			return "office/mendan/index";
		} catch (Exception e) {
			return "error";
		}
	}
}
