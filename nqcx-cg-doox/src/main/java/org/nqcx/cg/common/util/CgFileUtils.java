/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.common.util;

import org.nqcx.cg.provide.o.CgFile;

import java.io.File;
import java.util.Collections;

/**
 * @author naqichuan Mar 1, 2014 4:45:27 PM
 */
public class CgFileUtils {

    /**
     * @param rootPath
     * @param path
     * @param name
     * @param onlyDirectory
     * @return
     * @author naqichuan Mar 2, 2014 12:53:12 AM
     */
    public static CgFile getCgFile(String rootPath, String path, String name,
                                   boolean onlyDirectory) {

        CgFile cgFile = new CgFile(path, name, false);

        checkPathAndName(cgFile);

        String allPath = formatPath(rootPath, false, true) + cgFile.getPath()
                + cgFile.getName();
        allPath = allPath.replace("\\", "/");

        File file = new File(allPath);
        if (file.exists()) {
            File[] pArray = file.listFiles();
            if (pArray != null && pArray.length > 0) {
                for (File pFile : pArray) {
                    if (pFile == null || onlyDirectory || pFile.isHidden()
                            || pFile.getName().equals("Servers")
                            || pFile.getName().equals("RemoteSystemsTempFiles"))
                        continue;
                    cgFile.addCgFile(pFile.getName(), pFile.isFile());
                }

                if (cgFile.getCgFileList() != null)
                    Collections.sort(cgFile.getCgFileList(),
                            (o1, o2) -> {
                                if (o1.isDirectory() && o2.isFile())
                                    return -1;
                                else if (o1.isFile() && o2.isDirectory())
                                    return 1;
                                else
                                    return 0;
                            });
            }
        } else {
            if (!"/".equals(path) || !"".equals(name)) {
                cgFile = getCgFile(rootPath, "/", "", onlyDirectory);
            }
        }
        return cgFile;

    }

    private static void checkPathAndName(CgFile cgFile) {
        String path = cgFile.getPath();
        if (cgFile.getName().length() == 0
                && (path = formatName(path, false, true)).lastIndexOf('/') >= 0) {
            cgFile.setName(path.substring(path.lastIndexOf("/") + 1));
            cgFile.setPath(path.substring(0, path.lastIndexOf("/") + 1));
        }
    }

    /**
     * 格式化路径
     *
     * @param path
     * @param begin 开始处是否要加入 /
     * @param end   结尾处理是否要加入 /
     * @return
     * @author naqichuan Mar 1, 2014 11:19:05 PM
     */
    public static String formatPath(String path, boolean begin, boolean end) {
        if (path == null)
            return "";
        String newPath = path.replace("\\", "/");

        while (newPath.indexOf("//") != -1)
            newPath = newPath.replace("//", "/");

        if (begin && !newPath.startsWith("/"))
            newPath = "/" + newPath;
        if (end && !newPath.endsWith("/"))
            newPath += "/";

        return newPath;
    }

    /**
     * 格式化名称
     *
     * @param name
     * @param begin 开始处是否要去掉 /
     * @param end   结尾处理是否要去掉 /
     * @return
     * @author naqichuan Mar 1, 2014 11:19:54 PM
     */
    public static String formatName(String name, boolean begin, boolean end) {
        if (name == null)
            return "";
        String newName = name.replace("\\", "/");

        while (newName.indexOf("//") != -1)
            newName = newName.replace("//", "/");

        if (begin && newName.startsWith("/"))
            newName = newName.substring(1);
        if (end && newName.endsWith("/"))
            newName = newName.substring(0, newName.length() - 1);

        if (newName.equals("/"))
            newName = "";

        return newName;
    }

    public static void main(String[] args) {
        // CgFile cgf = getWs("/home/nqcx/workspace");
        // cgf.setPath("abcd/");
        // cgf.setName("cccccccccc");
        // cgf.asFile();

        // System.out.println(cgf);

    }
}
