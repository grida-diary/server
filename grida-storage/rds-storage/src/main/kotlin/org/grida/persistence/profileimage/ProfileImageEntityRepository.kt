package org.grida.persistence.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.ProfileImage
import org.grida.domain.profileimage.ProfileImageRepository
import org.grida.domain.user.User
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.grida.persistence.base.JpqlExecutor
import org.grida.persistence.user.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Repository
@Transactional(readOnly = true)
class ProfileImageEntityRepository(
    private val profileImageJpaEntityRepository: ProfileImageJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor,
) : ProfileImageRepository {

    @Transactional
    override fun save(
        profileImage: ProfileImage,
        user: User,
    ): Long {
        val profileImageEntity = profileImage.toEntity(user)
        profileImageJpaEntityRepository.save(profileImageEntity)
        return profileImageEntity.id
    }

    override fun findById(id: Long): ProfileImage? {
        val profileImageEntity = profileImageJpaEntityRepository.findById(id)
        return profileImageEntity.getOrNull()?.toDomain()
    }

    override fun findByUserIdAndStatus(userId: Long, status: ImageStatus): ProfileImage? {
        val profileImageEntity = jpqlExecutor.find {
            select(
                entity(ProfileImageEntity::class)
            ).from(
                entity(ProfileImageEntity::class)
            ).whereAnd(
                path(ProfileImageEntity::user)(UserEntity::id).eq(userId),
                path(ProfileImageEntity::status).eq(status)
            )
        }
        return profileImageEntity?.toDomain()
    }

    override fun findAllByUserId(userId: Long): List<ProfileImage> {
        val profileImageEntities = jpqlExecutor.findAll {
            select(
                entity(ProfileImageEntity::class)
            ).from(
                entity(ProfileImageEntity::class)
            ).where(
                path(ProfileImageEntity::user)(UserEntity::id).eq(userId),
            )
        }

        return profileImageEntities.map { it.toDomain() }
    }

    override fun existsByUserIdAndStatus(userId: Long, status: ImageStatus): Boolean {
        return findByUserIdAndStatus(userId, status)?.let { true } ?: false
    }

    @Transactional
    override fun updateStatue(
        profileImageId: Long,
        status: ImageStatus,
    ): Long {
        val profileImageEntity = profileImageJpaEntityRepository.findById(profileImageId)
            .orElseThrow { GridaException(NoSuchData(ProfileImage::class, profileImageId)) }
        profileImageEntity.updateStatue(status)
        return profileImageEntity.id
    }
}
