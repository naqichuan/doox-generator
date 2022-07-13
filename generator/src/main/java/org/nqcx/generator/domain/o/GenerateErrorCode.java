/*
 * Copyright 2022 nqcx.org All right reserved. This software is the confidential and proprietary information
 * of nqcx.org ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with nqcx.org.
 */

package org.nqcx.generator.domain.o;

import org.nqcx.doox.commons.lang.o.IErrorCode;

import java.util.Arrays;

/**
 * @author naqichuan 2022/7/13 下午4:47
 */
public enum GenerateErrorCode implements IErrorCode {

    E10("10","SUBJECT=调用失败！"),
    E11("11","Need create connection"),
    E12("12","Connect fail"),
    E100("100","生成代码失败！"),
    E101("101","工程路径不存在！"),
    E102("102","表不存在！");

    private String code;
    private String text;

    GenerateErrorCode(String code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getText() {
        return this.text;
    }

    /**
     * 判断自身是否与参数里的枚举相等
     *
     * @param eo eo
     * @return boolean
     */
    public boolean is(GenerateErrorCode eo) {
        return this == eo;
    }

    /**
     * 判断 this 是否在 eos 数组中
     *
     * @param eos eos
     * @return EOOUtils.contain(eos, this);
     */
    public boolean in(GenerateErrorCode[] eos) {
        if (eos == null || eos.length == 0)
            return false;

        for (GenerateErrorCode n : eos) {
            if (n == this)
                return true;
        }

        return false;
    }

    /**
     * 通过 code 取得枚举实例
     *
     * @param code code
     * @return GenerateErrorCode
     */
    public static GenerateErrorCode of(String code) {
        for (GenerateErrorCode e : GenerateErrorCode.values()) {
            if (e.code.equals(code))
                return e;
        }
        throw new IllegalArgumentException("unknown code:" + code);
    }

    /**
     * print all
     *
     * @author naqichuan 22-5-17 下午5:36
     */
    public static void print() {
        Arrays.stream(GenerateErrorCode.values()).forEach(x -> System.out.println(x.codePrefix() + x.code + "=" + x.getText()));
    }

    public static void main(String[] args) {
        print();
    }
}
