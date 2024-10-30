package org.grida.persistence.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaEntityRepository : JpaRepository<UserEntity, Long>
