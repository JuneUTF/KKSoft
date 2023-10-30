package com.juneutf.kk.office.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.mtg.model.TableModel;
import com.juneutf.kk.office.mapper.EmployeeMapper;
import com.juneutf.kk.office.model.EmployeeModel;
import com.juneutf.kk.office.service.EmployeeService;

@Service
public class EmployeeImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper mapper;
    /**
     * 目的関連の employeeModel オブジェクトを取得します。
     * @return 目的関連の employeeModel オブジェクトのリスト
     */
    public ArrayList<EmployeeModel> selectEmployee(){
    	return mapper.selectEmployee();
    }

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを削除します。
     * @param TableModel 削除対象のデータを指定する TableModel オブジェクト
     * @return 削除が成功した場合は 1、失敗した場合は 0
     */
    public int deleteByIdEmployee(EmployeeModel model){
    	return mapper.deleteByIdEmployee(model);
    }

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを復元します。
     * @param TableModel 復元対象のデータを指定する TableModel オブジェクト
     * @return 復元が成功した場合は 1、失敗した場合は 0
     */
    public int restoreByIdEmployee(EmployeeModel model){
    	return mapper.restoreByIdEmployee(model);
    }

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを編集します。
     * @param TableModel 編集対象のデータを指定する TableModel オブジェクト
     * @return 編集が成功した場合は 1、失敗した場合は 0
     */
    public int editByIdEmployee(EmployeeModel model){
    	return mapper.editByIdEmployee(model);
    }

    /**
     * 指定されたモデルの ID および tableName に新しいデータを登録します。
     * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
     * @return 登録が成功した場合は 1、失敗した場合は 0
     */
    public int regByEmployee(EmployeeModel model){
    	return mapper.regByEmployee(model);
    }
     /**
      * 内容新規登録の業務検証
      * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
      * @return 存在場合はTrue、存在ない場合はFalse.
      */
      public List<TableModel> checkEmployee(EmployeeModel model){
      	return mapper.checkEmployee(model);
      }
}
