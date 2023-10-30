package com.juneutf.kk.office.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.kk.config.vender.CustomUser;
import com.juneutf.kk.config.vender.SalesVender;
import com.juneutf.kk.mtg.model.APIChargeModel;
import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.service.APIService;
import com.juneutf.kk.mtg.service.PlanService;
import com.juneutf.kk.office.model.SalesModel;
import com.juneutf.kk.office.service.SalesSerive;

@RequestMapping("kk/office")
@Controller
public class SalesController {
	@Autowired
	private PlanService planService;
	@Autowired
	private APIService apiService;
	@Autowired
	private SalesSerive salesSerive;
	@Autowired
	private SalesVender salesVender;
	//get index salae
	@GetMapping("/sales")
	public String getIndexSales(SalesModel salesModel,Model model) {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUser customUser = (CustomUser) userDetails;
            if(customUser.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            	ArrayList<APIChargeModel> jobadmin = apiService.selectAPICharge(customUser.getId());
            	model.addAttribute("charge", jobadmin);    
            }else {
            	ArrayList<JobModel> jobuser = planService.selectPublicId(customUser.getId());
            	model.addAttribute("charge", jobuser);            	
    		}
            Calendar calendar = Calendar.getInstance();
            int monthInt = calendar.get(Calendar.MONTH) + 1;
            List<SalesModel> nowMonth =salesSerive.selectNameidSameDay(monthInt);
            model.addAttribute("nowMonth", salesVender.getToDay(nowMonth));
            model.addAttribute("nextMothText", monthInt+1+"月");
            List<SalesModel> nextMoth =salesSerive.selectNameidNotSameDay(monthInt+１);
            model.addAttribute("nextMoth",salesVender.getToDay(nextMoth));
            model.addAttribute("toNextMothText", monthInt+2+"月");
            List<SalesModel> toNextMoth =salesSerive.selectNameidNotSameDay(monthInt+2);
            model.addAttribute("toNextMoth",salesVender.getToDay(toNextMoth));
    		return "office/sales/index";
		} catch (Exception e) {
			return "error";
		}
		
	}
	@PostMapping("/sales")
	public String postSales(SalesModel salesModel) {
		try {
			salesSerive.insertSales(salesModel);
			int maxid =salesSerive.selectMaxId();
			return "redirect:/kk/office/getsales?id="+maxid;
		} catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("getsales")
	public String getSales(SalesModel salesModel,Model model) {
		try {
			SalesModel sales =  salesSerive.selectSalesById(salesModel.getId()).get(0);
			String coverNameID = salesVender.CoverListIdToListNameInterview(sales.getNameid());
			sales.setNameid(coverNameID);
			model.addAttribute("sales", sales);
			return "office/sales/getsales";
		} catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("edit")
	public String getSalesEdit(SalesModel salesModel,Model model) {
		try {
			SalesModel sales =  salesSerive.selectSalesById(salesModel.getId()).get(0);
			model.addAttribute("sales", sales);
			System.out.println(sales);
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUser customUser = (CustomUser) userDetails;
            if(customUser.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            	ArrayList<APIChargeModel> jobadmin = apiService.selectAPICharge(customUser.getId());
            	model.addAttribute("charge", jobadmin);    
            }else {
            	ArrayList<JobModel> jobuser = planService.selectPublicId(customUser.getId());
            	model.addAttribute("charge", jobuser);            	
    		}
            Calendar calendar = Calendar.getInstance();
            int monthInt = calendar.get(Calendar.MONTH) + 1;
            List<SalesModel> checkbox1 =salesSerive.selectNameidSameDay(monthInt);
            List<SalesModel> nowMonth =salesVender.checkBox(sales.getNameid(), checkbox1);
            model.addAttribute("nowMonth", salesVender.getToDay(nowMonth));
            model.addAttribute("nextMothText", monthInt+1+"月");
            List<SalesModel> checkbox2 =salesSerive.selectNameidNotSameDay(monthInt+１);
            List<SalesModel> nextMoth =salesVender.checkBox(sales.getNameid(), checkbox2);
            model.addAttribute("nextMoth",salesVender.getToDay(nextMoth));
            model.addAttribute("toNextMothText", monthInt+2+"月");
            List<SalesModel> checkbox3 =salesSerive.selectNameidNotSameDay(monthInt+2);
            List<SalesModel> toNextMoth =salesVender.checkBox(sales.getNameid(), checkbox3);
            model.addAttribute("toNextMoth",salesVender.getToDay(toNextMoth));
			return "office/sales/edit";
		} catch (Exception e) {
			return "error";
		}
	}
	@PostMapping("edit")
	public String postSalesEdit(SalesModel salesModel,Model model) {
//		try {
			System.out.println(salesModel);
			salesSerive.updateSalesById(salesModel);
			return "redirect:/kk/office/getsales?id="+salesModel.getId();
//		} catch (Exception e) {
//			return "error";
//		}
	}
}
