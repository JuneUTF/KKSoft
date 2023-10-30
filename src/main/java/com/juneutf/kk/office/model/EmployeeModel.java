package com.juneutf.kk.office.model;

/**
 * 操作モデルクラスはデータベースの操作情報を表します。
 */
import lombok.Data;

@Data
public class EmployeeModel {
    /**
     * 操作ID
     */
    private int id;

    /**
     * 操作が実行されたテーブルの名前です。
     */
    private String tableName;
    /**
     * 名前です。
     */
    private String nameid;
    /**
     * 稼動開始。
     */
    private String date_start;
    /**
     * 現場の終了日。
     */
    private String plan_end;

    /**
     * 操作の実行状態を示す文字列です。
     */
    private String status;
}
