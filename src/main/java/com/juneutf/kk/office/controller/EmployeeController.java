package com.juneutf.kk.office.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.kk.model.APIMessengerModel;
import com.juneutf.kk.office.model.EmployeeModel;
import com.juneutf.kk.office.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk/office")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 操作情報を取得するGETメソッド（担当者リスト・内容リストを渡す）。
	 * @param model モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@GetMapping("/employeepai")
	public ResponseEntity<?> getemployeeAPI(APIMessengerModel apiMessengerModel){
		try {
			ArrayList<EmployeeModel> res = employeeService.selectEmployee();
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			apiMessengerModel.setIsData("false");
			log.warn("担当者のAPIを呼び出せない");
			return ResponseEntity.status(400).body(apiMessengerModel);
		}
	}

	/**
	 * 操作情報を取得するGETメソッド（担当者リスト・内容リストを渡す）。
	 * @param model モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@GetMapping("/employee")
	public String getemployee() {
		try {
			return "office/employee/index";
		} catch (Exception e) {
			log.warn("GetMapping employeeにエラー発生します。");
			return "error";
		}
	}

	/**
	 * 操作情報を削除するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/employee/delete")
	public String postemployeeDelete(EmployeeModel employeeModel) {
		try {
			employeeService.deleteByIdEmployee(employeeModel);
			log.info("削除情報：" + employeeModel);
			return "redirect:/kk/office/employee";
		} catch (Exception e) {
			log.warn("削除情報失敗：" +employeeModel);
			return "error";
		}
	}

	/**
	 * 操作情報を復元するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/employee/restore")
	public String postemployeeRestore(EmployeeModel employeeModel) {
		try {
			//tableNameのIDとして復元
			employeeService.restoreByIdEmployee(employeeModel);
			log.info("復元情報：" + employeeModel);
			return "redirect:/kk/office/employee";
		} catch (Exception e) {
			log.warn("復元情報失敗：" + employeeModel);
			return "error";
		}
	}

	/**
	 * 操作情報を編集するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/employee/edit")
	public String postemployeeEdit(EmployeeModel employeeModel) {
		try {
			employeeService.editByIdEmployee(employeeModel);
			log.info("編集情報：" + employeeModel);
			return "redirect:/kk/office/employee";
		} catch (Exception e) {
			log.warn("編集情報：" + employeeModel);
			return "error";
		}
	}

	/**
	 * 新しい操作情報を登録するPOSTメソッド。
	 * @param TableModel 操作モデル
	 * @return 操作情報の表示用テンプレート名
	 */
	@PostMapping("/employee/reg")
	public String postemployeeReg(EmployeeModel employeeModel) {
		try {
			employeeService.regByEmployee(employeeModel);			
			log.info("新規登録情報：" + employeeModel);
			return "redirect:/kk/office/employee";
		} catch (Exception e) {
			log.warn("新規登録情報失敗：" + employeeModel);
			return "error";
		}
	}
}
