package com.ef.hana;

import com.ef.utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/10/10:46 上午
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        String filepath="file/day.txt";
        String tablename="HR_ZT_RIGHTS";
        HanaParse hanaParse=new HanaParse(filepath,tablename);
//        List list=hanaParse.parse(filepath,"UTF-8");
//        System.out.println(list.get(0));
//        for(int i=1;i<list.size();i++){
//            System.out.println(","+list.get(i));
//        }
//        System.out.println(",ETL_TIME STRING COMMENT 'etl时间'");
//        System.out.println("\n");
//        String select=hanaParse.selectFileds("ADS"+"."+tablename);
//        System.out.println(select);
        System.out.println(hanaParse.getText());
        System.out.println(hanaParse.getJobName("JOB_HANA_BACKUP_"));
        System.out.println(hanaParse.selectFileds(tablename));

    }
}
