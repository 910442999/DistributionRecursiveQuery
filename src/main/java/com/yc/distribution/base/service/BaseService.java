package com.yc.distribution.base.service;

import com.yc.distribution.base.page.PageBean;
import com.yc.distribution.base.page.PageParam;

import java.util.List;

/**
 * Common Service
 * 
 *
 * @param <T>
 */
public interface BaseService<T> {

	int save(T t);

	int save(List<T> list);

	int update(T t);

	int delete(String id);

	int delete(List<String> ids);

	T getById(String id);
	
	List<T> listByObj(T t);

	PageBean listPage(PageParam pageParam);

	int updateNull(T t);

	PageBean listPageOther(PageParam pageParam);

	PageBean listPageByOuterTask(PageParam pageParam);

}
