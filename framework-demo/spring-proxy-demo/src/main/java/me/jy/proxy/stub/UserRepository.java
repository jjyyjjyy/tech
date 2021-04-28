package me.jy.proxy.stub;

import me.jy.proxy.domain.User;
import me.jy.proxy.repository.CrudRepository;

/**
 * @author jy
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
