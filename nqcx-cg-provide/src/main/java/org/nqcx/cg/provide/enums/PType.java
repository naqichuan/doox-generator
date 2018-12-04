/* 
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.provide.enums;

/**
 * 
 * @author naqichuan Feb 13, 2014 10:54:08 PM
 * 
 */
public enum PType {
	SINGLE(0, "Single"), MULTIPLE(1, "Multiple");

	private int value;
	private String text;

	private PType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	public static PType get(int value) {
		for (PType p : PType.values()) {
			if (p.getValue() == value)
				return p;
		}
		throw new IllegalArgumentException("unknown value:" + value);
	}

}
