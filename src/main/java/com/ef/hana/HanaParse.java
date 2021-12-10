package com.ef.hana;

import com.ef.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/10/10:39 上午
 * @Description: hana字段转换成hive字段
 */
public class HanaParse {

    public List parse(String filepath)  {
        List<String> list=new ArrayList<>();
        String[] contents=FileUtils.getContentByLine(filepath).split("\n");
        for(int i=0;i<contents.length;i++){
            //System.out.println(contents[i]);
            String []fileds=contents[i].split(",");
            //字段名
            String line=fileds[0]+"\t";
            if(fileds[2].equals(HanaField.NVARCHAR)){
                line+=HiveField.STRING+"\t";

            }else if(fileds[2].equals(HanaField.DECIMAL)){
                line+=HiveField.DECIMAL+ (fileds[3]==null?"": "("+fileds[3]+","+(fileds[4]==null?0:fileds[4])+")")+"\t";

            }else if(fileds[2].equals(HanaField.DATE)){
                line+=HiveField.DATE+"\t";
            }else if(fileds[2].equals(HanaField.TIMESTAMP)){
                line+=HiveField.TIMESTAMP+"\t";
            }

            line+=HiveField.COMMENT+"\t"+"\""+fileds[fileds.length-1]+"\"";
            list.add(line);

        }
        return list;
    }
}
