package com.juneutf.kk.office.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.kk.mtg.model.TableModel;
import com.juneutf.kk.office.model.EmployeeModel;

/**
 * このマッパーは、employeeModel オブジェクトに関するデータベース操作を提供します。
 */
@Mapper
public interface EmployeeMapper {
    /**
     * 目的関連の employeeModel オブジェクトを取得します。
     * @return 目的関連の employeeModel オブジェクトのリスト
     */
    ArrayList<EmployeeModel> selectEmployee();

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを削除します。
     * @param TableModel 削除対象のデータを指定する TableModel オブジェクト
     * @return 削除が成功した場合は 1、失敗した場合は 0
     */
    int deleteByIdEmployee(EmployeeModel model);

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを復元します。
     * @param TableModel 復元対象のデータを指定する TableModel オブジェクト
     * @return 復元が成功した場合は 1、失敗した場合は 0
     */
    int restoreByIdEmployee(EmployeeModel model);

    /**
     * 指定されたモデルの ID および tableName に基づいてデータを編集します。
     * @param TableModel 編集対象のデータを指定する TableModel オブジェクト
     * @return 編集が成功した場合は 1、失敗した場合は 0
     */
    int editByIdEmployee(EmployeeModel model);

    /**
     * 指定されたモデルの ID および tableName に新しいデータを登録します。
     * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
     * @return 登録が成功した場合は 1、失敗した場合は 0
     */
    int regByEmployee(EmployeeModel model);
     /**
      * 内容新規登録の業務検証
      * @param TableModel 登録対象のデータを指定する TableModel オブジェクト
      * @return 存在場合はTrue、存在ない場合はFalse.
      */
      List<TableModel> checkEmployee(EmployeeModel model);
      
}
