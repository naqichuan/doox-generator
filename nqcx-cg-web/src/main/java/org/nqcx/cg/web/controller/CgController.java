/* 
 * Copyright 2014 nqcx.org All right reserved. This software is the 
 * confidential and proprietary information of nqcx.org ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.web.controller;

import org.nqcx.cg.common.web.ConnContext;
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

//	/**
//	 * 分析返回结果
//	 *
//	 * @param dto
//	 * @return
//	 */
//	protected Map<?, ?> buildResult(DTO dto) {
//		if (dto == null) {
//			dto = new DTO();
//			// 默认调用失败
//			dto.putResult("10", "10");
//		}
//
//		MapBuilder jb = MapBuilder.newInstance().put(SUCCESS,dto.isSuccess());
//
//		if (dto.isSuccess())
//			parseSuccess(jb, dto);
//		else
//			parseError(jb, dto.getResultMap());
//
//		return jb.build();
//	}
//
//	/**
//	 * 处理成功结果
//	 *
//	 * @param mapBuilder
//	 * @param dto
//	 */
//	private <T> void parseSuccess(MapBuilder mapBuilder, DTO dto) {
//		// 1. 解析对象
//		parseSuccessObject(mapBuilder, dto.getObject());
//		// 2. 解析分页
//		// parsePageBuilder(mapBuilder, dto.getPage());
//		// 3. 解析列表
//		parseSuccessList(mapBuilder, dto.getList());
//		// 4. 解析结果
//		parseSuccessResult(mapBuilder, dto.getResultMap());
//	}
//
//	/**
//	 * 处理 object
//	 *
//	 * @param mapBuilder
//	 *
//	 * @param object
//	 */
//	private void parseSuccessObject(MapBuilder mapBuilder, Object object) {
//		if (object != null)
//			mapBuilder.put("object", object);
//	}
//
//	/**
//	 * 处理 list
//	 *
//	 * @param mapBuilder
//	 *
//	 * @param list
//	 */
//	private void parseSuccessList(MapBuilder mapBuilder, List<?> list) {
//		if (list != null && list.size() > 0)
//			mapBuilder.put("list", list);
//	}
//
//	/**
//	 * 处理 result
//	 *
//	 * @param mapBuilder
//	 *
//	 * @param map
//	 */
//	private void parseSuccessResult(MapBuilder mapBuilder, Map<?, ?> map) {
//		if (map != null)
//			mapBuilder.put("result", map);
//	}
//
//	/**
//	 * 解析错误结果
//	 *
//	 * @param mapBuilder
//	 * @param errorMap
//	 */
//	private void parseError(MapBuilder mapBuilder,
//			Map<String, Object> errorMap) {
//		if (errorMap == null || errorMap.isEmpty())
//			return;
//		else if (errorMap.size() == 1)
//			parseErrorJson(mapBuilder, errorMap.entrySet());
//		else {
//			parseMultipleErrorJson(mapBuilder, errorMap.entrySet());
//		}
//	}
//
//	/**
//	 * 处理单个错误
//	 *
//	 * @param mapBuilder
//	 * @param entrySet
//	 */
//	private void parseErrorJson(MapBuilder mapBuilder,
//			Set<Entry<String, Object>> entrySet) {
//		Entry<String, Object> entry = entrySet.iterator().next();
//		mapBuilder.putMap(putError(entry.getKey().toString())).pubArray(
//				ERROR_MULTIPLE, convertMultipleErrorJsonArray(entrySet));
//	}
//
//	/**
//	 * 处理多个错误
//	 *
//	 * @param mapBuilder
//	 * @param entrySet
//	 */
//	private void parseMultipleErrorJson(MapBuilder mapBuilder,
//			Set<Entry<String, Object>> entrySet) {
//		mapBuilder.putMap(putError("10")).pubArray(ERROR_MULTIPLE,
//				convertMultipleErrorJsonArray(entrySet));
//	}
//
//	/**
//	 * 转换多个错误到 array
//	 *
//	 * @param entrySet
//	 * @return
//	 */
//	private List<Object> convertMultipleErrorJsonArray(
//			Set<Entry<String, Object>> entrySet) {
//		List<Object> list = new ArrayList<Object>();
//		for (Entry<String, Object> error : entrySet) {
//			list.add(MapBuilder.newInstance()
//					.put(ERROR_MULTIPLE_CODE, "1x")
//					.put(ERROR_MULTIPLE_TEXT, error.getValue().toString())
//					.build());
//		}
//		return list;
//	}
}
