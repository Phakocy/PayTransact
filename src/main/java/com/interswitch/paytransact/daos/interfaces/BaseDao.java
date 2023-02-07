package com.interswitch.paytransact.daos.interfaces;

import javax.sql.DataSource;

public interface BaseDao {
    void setDataSource(DataSource dataSource);
}
