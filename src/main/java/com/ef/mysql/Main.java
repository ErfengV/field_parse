package com.ef.mysql;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/15/11:29 上午
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        String filepath="file/day02.txt";
        String tablename="HR_ZT_RIGHTS";

        MysqlParse mysqlParse=new MysqlParse();
        List list=mysqlParse.parse(filepath,"UTF-8");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
