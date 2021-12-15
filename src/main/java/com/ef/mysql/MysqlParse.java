package com.ef.mysql;

import com.ef.hana.HanaField;
import com.ef.hana.HiveField;
import com.ef.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ErFeng_V
 * @ProjectName: parse_file
 * @Date: 2021/12/15/11:19 上午
 * @Description:
 */
public class MysqlParse {

    private final List<String> filedsOne=new ArrayList<>();


    /**
     * 文本解析
     * @param filepath
     * @param encode 默认为GBK格式编码
     * @return
     */
    public List parse(String filepath, String encode)  {
        List<String> list=new ArrayList<>();
        String[] contents= FileUtils.getContentByLine(filepath,encode).split("\n");
        //因为第一段是字段名，所以从第二段开始
        for(int i=0;i<contents.length;i++){
            String []fileds=contents[i].split(",");
            //收集字段
            filedsOne.add(fileds[0]);

            //字段名
            String line=fileds[0]+"\t";
            if(fileds[2].contains(MysqlField.VARCHAR)){
                line+= HiveField.STRING+"\t";

            }else if(fileds[2].contains(MysqlField.DECIMAL)){
                line+=fileds[2]+"\t";

            }else if(fileds[2].equals(MysqlField.DATETIME) || fileds[2].equals(MysqlField.TIMESTAMP)){
                line+=HiveField.TIMESTAMP+"\t";
            }else if(fileds[2].contains(MysqlField.TINYINT)){

            }
            if(!"[NULL]".equals(fileds[fileds.length-1]) && !" ".equals(fileds[fileds.length-1])){
                line+=HiveField.COMMENT+"\t"+"'"+fileds[fileds.length-1]+"'";
            }
            list.add(line);

        }
        return list;
    }
}
