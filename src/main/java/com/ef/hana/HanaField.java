package com.ef.hana;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/10/10:41 上午
 * @Description: haha的字段
 */
public class HanaField {

    public static final String NVARCHAR = "NVARCHAR";
    public static final String DECIMAL  = "DECIMAL";
    public static final String DATE     = "DATE";
    public static final String TIMESTAMP= "TIMESTAMP";
    public static final String CLOB="CLOB";
    public static final String SECONDDATE="SECONDDATE";
    public static final String VARCHAR="VARCHAR";



    public static final String ETL_CONTENTS ="to_char(CURRENT_TIMESTAMP,'yyyy-mm-dd HH24:mi:ss') AS ETL_TIME";



}
