/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.nqcx.cg.common.web.ConnContext;
import org.nqcx.commons.lang.DTO;
import org.nqcx.commons.util.NqcxMapBuilder;
import org.nqcx.commons.web.WebSupport;

/**
 * 
 * @author naqichuan Feb 8, 2014 12:12:29 PM
 * 
 */
public class CgController extends WebSupport {

	/**
	 * @return
	 */
	protected String getConnNum() {
		return ConnContext.getConnContext().getConnNum();
	}

	/**
	 * 分析返回结果
	 * 
	 * @param dto
	 * @return
	 */
	protected Map<?, ?> returnResult(DTO dto) {
		if (dto == null) {
			dto = new DTO();
			// 默认调用失败
			dto.putResult("10", "10");
		}

		NqcxMapBuilder jb = NqcxMapBuilder.newInstance().put(SUCCESS,
				dto.isSuccess());

		if (dto.isSuccess())
			parseSuccess(jb, dto);
		else
			parseError(jb, dto.getResultMap());

		return jb.build();
	}

	/**
	 * 处理成功结果
	 * 
	 * @param nqcxMapBuilder
	 * @param dto
	 */
	private <T> void parseSuccess(NqcxMapBuilder nqcxMapBuilder, DTO dto) {
		// 1. 解析对象
		parseSuccessObject(nqcxMapBuilder, dto.getObject());
		// 2. 解析分页
		// parsePageBuilder(nqcxMapBuilder, dto.getPage());
		// 3. 解析列表
		parseSuccessList(nqcxMapBuilder, dto.getList());
		// 4. 解析结果
		parseSuccessResult(nqcxMapBuilder, dto.getResultMap());
	}

	/**
	 * 处理 object
	 * 
	 * @param nqcxMapBuilder
	 * 
	 * @param object
	 */
	private void parseSuccessObject(NqcxMapBuilder nqcxMapBuilder, Object object) {
		if (object != null)
			nqcxMapBuilder.put("object", object);
	}

	/**
	 * 处理 list
	 * 
	 * @param nqcxMapBuilder
	 * 
	 * @param list
	 */
	private void parseSuccessList(NqcxMapBuilder nqcxMapBuilder, List<?> list) {
		if (list != null && list.size() > 0)
			nqcxMapBuilder.put("list", list);
	}

	/**
	 * 处理 result
	 * 
	 * @param nqcxMapBuilder
	 * 
	 * @param map
	 */
	private void parseSuccessResult(NqcxMapBuilder nqcxMapBuilder, Map<?, ?> map) {
		if (map != null)
			nqcxMapBuilder.put("result", map);
	}

	/**
	 * 解析错误结果
	 * 
	 * @param nqcxMapBuilder
	 * @param errorMap
	 */
	private void parseError(NqcxMapBuilder nqcxMapBuilder,
			Map<String, Object> errorMap) {
		if (errorMap == null || errorMap.isEmpty())
			return;
		else if (errorMap.size() == 1)
			parseErrorJson(nqcxMapBuilder, errorMap.entrySet());
		else {
			parseMultipleErrorJson(nqcxMapBuilder, errorMap.entrySet());
		}
	}

	/**
	 * 处理单个错误
	 * 
	 * @param nqcxMapBuilder
	 * @param entrySet
	 */
	private void parseErrorJson(NqcxMapBuilder nqcxMapBuilder,
			Set<Entry<String, Object>> entrySet) {
		Entry<String, Object> entry = entrySet.iterator().next();
		nqcxMapBuilder.putMap(putError(entry.getKey().toString())).pubArray(
				ERROR_MULTIPLE, convertMultipleErrorJsonArray(entrySet));
	}

	/**
	 * 处理多个错误
	 * 
	 * @param nqcxMapBuilder
	 * @param entry
	 */
	private void parseMultipleErrorJson(NqcxMapBuilder nqcxMapBuilder,
			Set<Entry<String, Object>> entrySet) {
		nqcxMapBuilder.putMap(putError("10")).pubArray(ERROR_MULTIPLE,
				convertMultipleErrorJsonArray(entrySet));
	}

	/**
	 * 转换多个错误到 array
	 * 
	 * @param entrySet
	 * @return
	 */
	private List<Object> convertMultipleErrorJsonArray(
			Set<Entry<String, Object>> entrySet) {
		List<Object> list = new ArrayList<Object>();
		for (Entry<String, Object> error : entrySet) {
			list.add(NqcxMapBuilder.newInstance()
					.put(ERROR_MULTIPLE_CODE, "1x")
					.put(ERROR_MULTIPLE_TEXT, error.getValue().toString())
					.build());
		}
		return list;
	}
}
