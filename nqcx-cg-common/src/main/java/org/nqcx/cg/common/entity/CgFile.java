/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.common.entity;

import java.util.ArrayList;
import java.util.List;

import org.nqcx.cg.common.util.CgFileUtils;
import org.nqcx.commons.lang.o.EntityBO;

/**
 * 
 * @author naqichuan Mar 1, 2014 4:45:01 PM
 * 
 */
public class CgFile extends EntityBO {

	private String path;
	private String name;
	private boolean isfile = true;
	private List<CgFile> cgFileList;

	public CgFile() {

	}

	public CgFile(String path, String name, boolean isFile) {
		this.setPath(path);
		this.setName(name);
		
		this.isfile = isFile;
		this.initFileList();
	}

	private void initPath() {
		if (path == null || path.length() == 0)
			path = "/";

		path = CgFileUtils.formatPath(path, false, true);

		this.updateCgFileList();
	}

	private void initName() {
		if (name == null)
			name = "";

		name = CgFileUtils.formatName(name, true, true);

		this.updateCgFileList();
	}

	private void updateCgFileList() {
		if (isDirectory()) {
			for (CgFile cf : cgFileList) {
				cf.setPath(this.path + name + "/");
			}
		}
	}

	private void initFileList() {
		if (isFile())
			this.cgFileList = null;
		else
			this.cgFileList = new ArrayList<CgFile>();
	}

	public void setPath(String path) {
		this.path = path;
		this.initPath();
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		initName();
	}

	public boolean isFile() {
		return isfile;
	}

	public boolean isDirectory() {
		return !isfile;
	}

	public void asFile() {
		isfile = true;
		this.initFileList();
	}

	public void asDirectory() {
		isfile = false;
		this.initFileList();
	}

	public boolean hasParent() {
		return !"/".equals(this.path);
	}

	public List<CgFile> getCgFileList() {
		return cgFileList;
	}

	public boolean hasCgFileList() {
		return isDirectory() && cgFileList.size() > 0;
	}

	public void addCgFile(String name, boolean isfile) {
		if (isDirectory() && name != null && name.length() > 0)
			this.cgFileList.add(new CgFile(this.path + this.name + "/", name,
					isfile));
	}
}
