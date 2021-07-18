package com.ming.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.server.mapper.OplogMapper;
import com.ming.server.pojo.Oplog;
import com.ming.server.service.IOplogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
