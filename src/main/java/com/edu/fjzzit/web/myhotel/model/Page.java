package com.edu.fjzzit.web.myhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
	/**
	 * 显示几个内容
	 */
	private int pageSize;
	/**
	 * 当前页数
	 */
	private int pageNumber;
	/**
	 * 本页内容
	 */
	private List<?> list;
	/**
	 * 总页数
	 */
	private long total;
	/**
	 * 总个数
	 * @return
	 */
	private long totalNumber;
}
