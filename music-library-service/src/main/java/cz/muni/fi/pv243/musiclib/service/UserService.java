package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.entity.User;
import cz.muni.fi.pv243.musiclib.service.generic.GenericCRUDService;

/**
 * @author <a href="mailto:martin.styk@gmail.com">Martin Styk</a>
 */
public interface UserService extends GenericCRUDService<User, Long> {

    User findByEmail(String email);

}
