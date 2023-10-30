package com.juneutf.kk.config.vender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juneutf.kk.office.model.MendanModel;
import com.juneutf.kk.office.model.SalesModel;
import com.juneutf.kk.office.service.SalesSerive;

@Component
public class SalesVender {
	@Autowired
	private SalesSerive salesSerive;
	/**面談対象IDとして名前に変更
	 * @param String list id 面談対象*/
	public String CoverListIdToListNameInterview(String inputString){
		String outPutString = "";
		List<SalesModel> nameId = salesSerive.selectNameIdFUll();
		// 配列の変更
		String[] stringArray = inputString.split(",");
		for (String e : stringArray) {
			//String　to int 変更
			int intValue = Integer.parseInt(e);
		    for(SalesModel element:nameId) {
		    	if(intValue == element.getId()) {
		    		outPutString+=(element.getNameid()+"、");
		    	}
		    }
		}
		//最後の「、」を削除
		if (outPutString.endsWith("、")) {
			outPutString = outPutString.substring(0, outPutString.length() - 1);
		}
		return outPutString;
	}
	public List<SalesModel> getToDay(List<SalesModel> salesModel) {
		for (SalesModel model : salesModel) {
            if (model.getDate_start() != null) {
                // Kiểm tra xem date_start có giá trị ngày không
            	String[] parts = model.getDate_start().split("-");
                model.setDate_start(parts[2]+"日");
            }
        }
		return salesModel;
	}
	/**
	 * 面談対象の社員番号として編集する時、checkboxに検証すること
	 * @param nameID 番号のSales ID
	 * @param Sales Model checkboox
	 * */
	public List<SalesModel> checkBox(String inputString, List<SalesModel> salesModel) {
		String[] stringArray = inputString.split(",");
		for (String element : stringArray) {
        	//String　to int 変更
			int intValue = Integer.parseInt(element);
			for(SalesModel eModel : salesModel) {
				if(eModel.getId() == intValue) {
					eModel.setCheckBox(true);
				}
			}
		}
		return salesModel;
	}
	public List<MendanModel> coverYearMonthDayJP(List<MendanModel> mendanModels){
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日", Locale.JAPANESE);
        for(MendanModel model : mendanModels) {
        	LocalDate date = LocalDate.parse(model.getDate_plan(), inputFormatter);
        	String japaneseDate = date.format(outputFormatter);
        	model.setDate_plan(japaneseDate);
        }
        return mendanModels;
	}
}
