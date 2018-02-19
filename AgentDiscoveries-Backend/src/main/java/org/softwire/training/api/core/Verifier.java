package org.softwire.training.api.core;

import org.softwire.training.api.models.ErrorCode;
import org.softwire.training.api.models.FailedRequestException;
import org.softwire.training.db.daos.AgentsDao;
import org.softwire.training.db.daos.UsersDao;
import org.softwire.training.models.User;

import javax.inject.Inject;
import java.util.Optional;

public class Verifier {
    private final UsersDao usersDao;

    @Inject
    public Verifier(UsersDao usersDao) {
        this.usersDao=usersDao;
    }

    public boolean isAdmin(int userId) throws FailedRequestException {
        Optional<User> optionalUser = usersDao.getUser(userId);
        if (!optionalUser.isPresent()) {
            throw new FailedRequestException(ErrorCode.NOT_FOUND, "user wasn't found");
        }
        return optionalUser.get().isAdmin();
    }
}
