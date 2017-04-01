/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.entity.ws;

import java.util.List;

import org.nqcx.cg.common.entity.CgFile;
import org.nqcx.commons.lang.o.EntityBO;

/**
 * 
 * @author naqichuan Feb 9, 2014 10:13:31 PM
 * 
 */
public class Ws extends EntityBO {

	private String path;

	private List<CgFile> cgFileList;

	public Ws() {

	}

	public Ws(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<CgFile> getCgFileList() {
		return cgFileList;
	}

	public void setCgFileList(List<CgFile> cgFileList) {
		this.cgFileList = cgFileList;
	}

}
