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

    /**
     * 字段集合
     */
    private final List<String> filedsOne=new ArrayList<>();

    /**
     * hive的字段定义
     */
    private List filedsContents =new ArrayList<>();

    /**
     * 表名
     */
    private String tableName;

    private String jobName;

    public HanaParse(String filepath,String tableName,String encode){
        this.tableName=tableName;
        this.filedsContents=parse(filepath,encode);
    }
    public HanaParse(String filepath,String tableName){
        this.tableName=tableName;
        this.filedsContents=parse(filepath);
    }

    /**
     * 获取hive的定义字段
     * @return
     */
    public String getText(){
        String text="";
        text+=filedsContents.get(0)+"\n";
        for(int i=0;i<filedsContents.size();i++){
            text+=","+filedsContents.get(i)+"\n";
        }
        text+=","+HiveField.ETL_CONTENTS_STRING;
        return text;
    }



    /**
     * 文本解析
     * @param filepath
     * @param encode 默认为GBK格式编码
     * @return
     */
    private List parse(String filepath,String encode)  {
        List<String> list=new ArrayList<>();
        String[] contents=FileUtils.getContentByLine(filepath,encode).split("\n");
        //因为第一段是字段名，所以从第二段开始
        for(int i=1;i<contents.length;i++){
            String []fileds=contents[i].split("\t");
            //收集字段
            filedsOne.add(fileds[0]);
            //字段名
            String line=fileds[0]+"\t";
            if(fileds[2].equals(HanaField.NVARCHAR) || fileds[2].equals(HanaField.CLOB) ||  fileds[2].equals(HanaField.VARCHAR)){
                line+=HiveField.STRING+"\t";

            }else if(fileds[2].equals(HanaField.DECIMAL)){
                line+=HiveField.DECIMAL+ (fileds[3]==null?"": "("+fileds[3]+","+("[NULL]".equals(fileds[4]) ?0:fileds[4])+")")+"\t";

            }else if(fileds[2].equals(HanaField.DATE)){
                line+=HiveField.DATE+"\t";
            }else if(fileds[2].equals(HanaField.TIMESTAMP) || fileds[2].equals(HanaField.SECONDDATE)){
                line+=HiveField.TIMESTAMP+"\t";
            }
            if(!"[NULL]".equals(fileds[fileds.length-1])){
                line+=HiveField.COMMENT+"\t"+"'"+fileds[fileds.length-1]+"'";
            }
            list.add(line);

        }
        return list;
    }
    private List parse(String filepath){
        return parse(filepath,null);
    }




    /**
     * 获取查询语句
     * @param tablename
     * @return
     */
    public String selectFileds(String tablename){
        String line=HiveField.SELECT+"\n"+filedsOne.get(0);
        for(int i=1;i<filedsOne.size();i++){
            line+=","+filedsOne.get(i)+"\n";
        }
        line+=","+HanaField.ETL_CONTENTS+"\n"+"from\t"+tablename;
        return line;
    }

    /**
     * 获取job名称
     * @param prefix
     * @return
     */
    public String getJobName(String prefix){
        return prefix+this.tableName.replace(".","_");
    }
}
