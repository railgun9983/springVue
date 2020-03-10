package com.kk.novel.userInfo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.novel.common.annotation.requestLog;
import com.kk.novel.common.utils.AddressUtil;
import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.userInfo.entity.TLog;
import com.kk.novel.userInfo.dao.TLogDao;
import com.kk.novel.userInfo.service.TLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * (TLog)表服务实现类
 *
 * @author kk
 * @since 2020-02-17 16:01:00
 */
@Service("tLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TLogServiceImpl implements TLogService {
    @Resource
    private TLogDao tLogDao;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TLog queryById(Long id) {
        return this.tLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TLog> queryAllByLimit(int offset, int limit) {
        return this.tLogDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<TLog> queryAllByLog(TLog log) {
        return this.tLogDao.queryAll(log);
    }

    /**
     * 新增数据
     *
     * @param tLog 实例对象
     * @return 实例对象
     */
    @Override
    public TLog insert(ProceedingJoinPoint point, TLog tLog) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        requestLog logAnnotation = method.getAnnotation(requestLog.class);
        if (logAnnotation != null) {
            // 注解上的描述
            tLog.setOperation(logAnnotation.value());
        }
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        tLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            params = handleParams(params, args, Arrays.asList(paramNames));
            tLog.setParams(params.toString());
        }
        tLog.setCreateTime(DateFormat.dateToString(new Date()));
        tLog.setLocation(AddressUtil.getCityInfo(tLog.getIp()));
        // 保存系统日志
        this.tLogDao.insert(tLog);
        return tLog;
    }

    /**
     * 修改数据
     *
     * @param tLog 实例对象
     * @return 实例对象
     */
    @Override
    public TLog update(TLog tLog) {
        this.tLogDao.update(tLog);
        return this.queryById(tLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tLogDao.deleteById(id) > 0;
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Map) {
                Set set = ((Map) args[i]).keySet();
                List<Object> list = new ArrayList<>();
                List<Object> paramList = new ArrayList<>();
                for (Object key : set) {
                    list.add(((Map) args[i]).get(key));
                    paramList.add(key);
                }
                return handleParams(params, list.toArray(), paramList);
            } else {
                if (args[i] instanceof Serializable) {
                    Class<?> aClass = args[i].getClass();
                    try {
                        aClass.getDeclaredMethod("toString", new Class[]{null});
                        // 如果不抛出 NoSuchMethodException 异常则存在 toString 方法 ，安全的 writeValueAsString ，否则 走 Object的 toString方法
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
                    } catch (NoSuchMethodException e) {
                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
                    }
                } else if (args[i] instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) args[i];
                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
                } else {
                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
                }
            }
        }
        return params;
    }
}