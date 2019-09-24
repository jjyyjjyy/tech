package me.jy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jy
 */
@Service
public class BService {

    @Transactional(propagation = Propagation.NEVER)
    public void assertShouldNotInExistingTx() {
        // 不应该有一个已存在的事务
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void assertShouldExsitTx() {
        // 应该有一个已存在的事务
    }

    @Transactional
    public void assertContinueTx() {
        // 在原有事务下执行(默认)
    }

    @Transactional(propagation = Propagation.NESTED)
    public void assertNewSavepoint() {
        // 创建一个savepoint, 在原有事务运行
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void assertNewTx() {
        // 新开一个事务
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void assertJoinInExistingTx() {
        // 如果当前存在事务则加入该事务, 否则以非事务方式执行
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void assertSuspendExistingTx() {
        // 挂起当前事务, 然后以非事务方式执行
    }

}
