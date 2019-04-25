package com.yc.distribution.base.dao;

import java.util.List;
import java.util.Map;


/**
 * 数据访问层基础支撑接口.
 */
public interface BaseDao<T> {

    /**
     * 函数功能说明 ：单条插入数据. 修改者名字：Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    int insert(T entity);

    /**
     * 函数功能说明 ： 批量插入数据. 修改者名字：Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int batchInsert(List<T> list);

    /**
     * 函数功能说明 ：根据id单条更新数据. 修改者名字： Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param entity
     * @参数：@return
     * @return：int
     * @throws
     */
    int updateByPrimaryKey(T entity);

    int updateByPrimaryKeyNull(T entity);

    /**
     * 函数功能说明 ： 根据id批量更新数据. 修改者名字：Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int batchUpdateByIds(List<T> list);

    /**
     * 函数功能说明 ： 根据id查询数据. 修改者名字： Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param id
     * @参数：@return
     * @return：T
     * @throws
     */
    T selectByPrimaryKey(String id);


    List<T> listByObj(T t);

    /**
     * 函数功能说明 ： 根据id删除数据. 修改者名字： Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param id
     * @参数：@return
     * @return：int
     * @throws
     */
    int deleteByPrimaryKey(String id);

    /**
     * 函数功能说明 ： 根据id批量删除数据. 修改者名字： Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param list
     * @参数：@return
     * @return：int
     * @throws
     */
    int batchDeleteByIds(List<String> list);

    /**
     * 函数功能说明 ： 分页查询数据 . 修改者名字： Along 修改日期： 2016-5-11 修改内容：
     * 
     * @参数：@param pageParam
     * @参数：@param paramMap
     * @参数：@return
     * @return：PageBean
     * @throws
     */
    List<Object> listPage(Map<String, Object> paramMap);
    /**
     * 函数功能说明 ： 查询数据总数 .
     *
     * @参数：@param paramMap
     * @参数：@return
     * @return：Long
     * @throws
     */
    Long listPageCount(Map<String, Object> paramMap);


    List<Object> listPageOther(Map<String, Object> paramMap);

    Long listPageCountOther(Map<String, Object> paramMap);

    List<Object> listPageByOuterTask(Map<String, Object> paramMap);

    Long listPageCountByOuterTask(Map<String, Object> paramMap);


    Map<String, Object> countByPageParam(Map<String, Object> paramMap);

}
