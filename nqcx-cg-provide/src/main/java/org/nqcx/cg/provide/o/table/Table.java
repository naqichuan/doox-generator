/*
 * Copyright 2018 nqcx.org All right reserved. This software is the
 * confidential and proprietary information of nqcx.org ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with nqcx.org.
 */

package org.nqcx.cg.provide.o.table;

import org.nqcx.cg.provide.bo.TableBO;

import java.util.List;

/**
 * @author naqichuan Feb 8, 2014 10:48:47 AM
 */
public class Table extends TableBO {

    private List<Column> columns;

    public Column getIdColumn() {
        if (columns != null) {
            for (Column c : columns) {
                if (c.isId_())
                    return c;
            }
        }
        return null;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}
