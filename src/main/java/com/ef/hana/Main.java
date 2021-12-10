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
        HanaParse hanaParse=new HanaParse();
        List list=hanaParse.parse(filepath);
       for(int i=0;i<list.size();i++){
           System.out.println(list.get(i)+"\n");
       }
    }
}
