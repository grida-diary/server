package org.grida.entity.user;

import org.grida.domain.core.Target;
import org.grida.domain.user.*;
import org.grida.exception.DomainRdsException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.grida.exception.DomainRdsErrorCode.USER_NOT_FOUND;

@Repository
@Transactional(readOnly = true)
public class UserEntityRepository implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Long save(
            UserAccount authentication,
            UserRole role,
            UserProfile profile,
            LocalDateTime lastActionAt
    ) {
        UserEntity userEntity = UserMapper.toUserEntity(authentication, role, profile, lastActionAt);
        entityManager.persist(userEntity);

        return userEntity.getId();
    }

    @Override
    public User find(Target target) {
        return UserMapper.toUser(findById(target.id()));
    }

    @Override
    @Transactional
    public void delete(Target target) {
        UserEntity entity = findById(target.id());
        entityManager.remove(entity);
    }

    @Override
    public boolean existByEmail(String email) {
        return entityManager.createQuery(
                        "select u.email from UserEntity u " +
                                "where u.email = :email " +
                                "order by u.id asc ",
                        String.class
                )
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public UserAccount findAccountByEmail(String email) {
        return entityManager.createQuery(
                        "select new org.grida.domain.user.UserAccount(u.email, u.password) " +
                                "from UserEntity u " +
                                "where u.email = :email",
                        UserAccount.class
                )
                .setParameter("email", email)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(USER_NOT_FOUND));
    }

    @Override
    public UserRole findRole(Target target) {
        return entityManager.createQuery(
                        "select u.role from UserEntity u " +
                                "where u.id = :id",
                        UserRole.class
                )
                .setParameter("id", target.id())
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new DomainRdsException(USER_NOT_FOUND));
    }

    private UserEntity findById(long id) {
        UserEntity entity = entityManager.find(UserEntity.class, id);
        if (entity == null) {
            throw new DomainRdsException(USER_NOT_FOUND);
        }

        return entity;
    }
}
