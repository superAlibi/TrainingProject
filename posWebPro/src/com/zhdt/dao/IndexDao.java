package com.zhdt.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.zhdt.util.DBUtil;

public class IndexDao extends DBUtil {
	public Map<String, Object> findOverWeekSellDetail() {
		Map<String, Object> sellDetails = new HashMap<>();
		sql = "";
		try {
			ps = getPs(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return sellDetails;

	}
}
