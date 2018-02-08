package com.miloway.milonote.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by miloway on 2018/1/31.
 */

public class DimensTool {

    public static void createDimens() {
        //以此文件夹下的dimens.xml文件内容为初始值参照
        File file = new File("./app/src/main/res/values/dimens.xml");

        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();
        StringBuilder sw720 = new StringBuilder();
        StringBuilder sw800 = new StringBuilder();

        try {

            System.out.println("生成不同分辨率：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</dimen>")) {

                    //tempString = tempString.replaceAll(" ", "");

                    String start = tempString.substring(0, tempString.indexOf(">") + 1);

                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。
                    sw240.append(start).append( num * 0.75).append(end).append("\r\n");
                    sw480.append(start).append(num * 1.5).append(end).append("\r\n");
                    sw600.append(start).append(num * 1.87).append(end).append("\r\n");
                    sw720.append(start).append(num * 1.87).append(end).append("\r\n");
                    sw800.append(start).append(num * 2).append(end).append("\r\n");

                } else {
                    sw240.append(tempString).append("\r\n");
                    sw480.append(tempString).append("\r\n");
                    sw600.append(tempString).append("\r\n");
                    sw720.append(tempString).append("\r\n");
                    sw800.append(tempString).append("\r\n");

                }
                line++;
            }
            if (sw240.toString().length() > 1){
                sw240 = new StringBuilder(sw240.toString().substring(0,sw240.length()- 2));
            }
            if (sw480.toString().length() > 1){
                sw480 = new StringBuilder(sw480.toString().substring(0,sw480.length()- 2));
            }
            if (sw600.toString().length() > 1){
                sw600 = new StringBuilder(sw600.toString().substring(0,sw600.length()- 2));
            }
            if (sw720.toString().length() > 1){
                sw720 = new StringBuilder(sw720.toString().substring(0,sw720.length()- 2));
            }
            if (sw800.toString().length() > 1){
                sw800 = new StringBuilder(sw800.toString().substring(0,sw800.length()- 2));
            }


            reader.close();
            System.out.println("<!--  sw240 -->");
            System.out.println(sw240);
            System.out.println("<!--  sw480 -->");
            System.out.println(sw480);
            System.out.println("<!--  sw600 -->");
            System.out.println(sw600);
            System.out.println("<!--  sw720 -->");
            System.out.println(sw720);
            System.out.println("<!--  sw800 -->");
            System.out.println(sw800);
            String sw240file = "./app/src/main/res/values-sw240dp-land/dimens.xml";
            String sw480file = "./app/src/main/res/values-sw480dp-land/dimens.xml";
            String sw600file = "./app/src/main/res/values-sw600dp-land/dimens.xml";
            String sw720file = "./app/src/main/res/values-sw720dp-land/dimens.xml";
            String sw800file = "./app/src/main/res/values-sw800dp-land/dimens.xml";
            String w820file = "./app/src/main/res/values-w820dp/dimens.xml";
            //将新的内容，写入到指定的文件中去
            writeFile(sw240file, sw240.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw600file, sw600.toString());
            writeFile(sw720file, sw720.toString());
            writeFile(sw800file, sw800.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * 写入方法
     *
     */

    public static void writeFile(String file, String text) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.print(text);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                out.close();
            }
        }


    }
    public static void main(String[] args) {
        createDimens();
    }
}
