package com.yc.distribution.base.service.impl;


import com.yc.distribution.base.dao.BaseDao;
import com.yc.distribution.base.entity.BaseEntity;
import com.yc.distribution.base.page.PageBean;
import com.yc.distribution.base.page.PageParam;
import com.yc.distribution.base.service.BaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-492 on 16/8/9.
 */
public abstract class BaseServiceImpl <T extends BaseEntity> implements BaseService<T> {


    protected abstract BaseDao getBaseDao();


    public PageBean listPage(PageParam pageParam) {
        return listPage(pageParam, pageParam.getParams());
    }

    public PageBean listPageOther(PageParam pageParam) {
        return listPageOther(pageParam, pageParam.getParams());
    }

    public PageBean listPageByOuterTask(PageParam pageParam) {
        return listPageByOuterTask(pageParam, pageParam.getParams());
    }

    @Override
    public int save(T t) {
        t.initId();
        return getBaseDao().insert(t);
    }

    @Override
    public int save(List<T> list) {
        return getBaseDao().batchInsert(list);
    }

    @Override
    public int update(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }

    @Override
    public int updateNull(T t) {
        return getBaseDao().updateByPrimaryKeyNull(t);
    }

    @Override
    public int delete(String id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    @Override
    public T getById(String id) {
        return (T) getBaseDao().selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<String> ids) {
        return getBaseDao().batchDeleteByIds(ids);
    }

    @Override
    public List<T> listByObj(T t) {
        return getBaseDao().listByObj(t);
    }

    /**
     * 分页查询数据，指定SQL
     * @param pageParam
     * @param paramMap
     * @return
     */
    public PageBean listPageOther(PageParam pageParam, Map<String, Object> paramMap) {
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        // 统计总记录数
        Long totalCount = totalCountOther(paramMap);

        // 校验当前页数
        int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("offset", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());
        paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());

        // 获取分页数据集
        List<Object> list = getBaseDao().listPageOther(paramMap);

        Object isCount = paramMap.get("isCount"); // 是否统计当前分页条件下的数据：1:是，其他为否
        if (isCount != null && "1".equals(isCount.toString())) {
            Map<String, Object> countResultMap = getBaseDao().countByPageParam(paramMap);
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list, countResultMap);
        } else {
            // 构造分页对象
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list);
        }
    }


    /**
     * 分页查询数据，指定SQL
     * @param pageParam
     * @param paramMap
     * @return
     */
    public PageBean listPageByOuterTask(PageParam pageParam, Map<String, Object> paramMap) {
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        // 统计总记录数
        Long totalCount = totalCountByOuterTask(paramMap);

        // 校验当前页数
        int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("offset", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());
        paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());

        // 获取分页数据集
        List<Object> list = getBaseDao().listPageByOuterTask(paramMap);

        Object isCount = paramMap.get("isCount"); // 是否统计当前分页条件下的数据：1:是，其他为否
        if (isCount != null && "1".equals(isCount.toString())) {
            Map<String, Object> countResultMap = getBaseDao().countByPageParam(paramMap);
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list, countResultMap);
        } else {
            // 构造分页对象
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list);
        }
    }

    /**
     * 分页查询数据 .
     */
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        // 统计总记录数
        Long totalCount = totalCount(paramMap);

        // 校验当前页数
        int currentPage = PageBean.checkCurrentPage(totalCount.intValue(), pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        int numPerPage = PageBean.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("offset", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("pageSize", pageParam.getNumPerPage());
        paramMap.put("startRowNum", (pageParam.getCurrentPage() - 1) * pageParam.getNumPerPage());
        paramMap.put("endRowNum", pageParam.getCurrentPage() * pageParam.getNumPerPage());

        // 获取分页数据集
        List<Object> list = getBaseDao().listPage(paramMap);

        Object isCount = paramMap.get("isCount"); // 是否统计当前分页条件下的数据：1:是，其他为否
        if (isCount != null && "1".equals(isCount.toString())) {
            Map<String, Object> countResultMap = getBaseDao().countByPageParam(paramMap);
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list, countResultMap);
        } else {
            // 构造分页对象
            return new PageBean(pageParam.getCurrentPage(), pageParam.getNumPerPage(), totalCount.intValue(), list);
        }
    }

    /**
     * 分页查询数据总条数
     * @param paramMap
     * @return
     */
    public Long totalCount(Map<String, Object> paramMap){
        if(null == paramMap){
            return 0L;
        }else{
            Long totalCount = getBaseDao().listPageCount(paramMap);
            if(null == totalCount){
                return 0L;
            }else{
                return totalCount;
            }
        }
    }

    public Long totalCountOther(Map<String, Object> paramMap){
        if(null == paramMap){
            return 0L;
        }else{
            Long totalCount = getBaseDao().listPageCountOther(paramMap);
            if(null == totalCount){
                return 0L;
            }else{
                return totalCount;
            }
        }
    }

    public Long totalCountByOuterTask(Map<String, Object> paramMap){
        if(null == paramMap){
            return 0L;
        }else{
            Long totalCount = getBaseDao().listPageCountByOuterTask(paramMap);
            if(null == totalCount){
                return 0L;
            }else{
                return totalCount;
            }
        }
    }

}
