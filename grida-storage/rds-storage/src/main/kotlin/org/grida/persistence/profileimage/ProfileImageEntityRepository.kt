package org.grida.persistence.profileimage

import org.grida.domain.image.ImageStatus
import org.grida.domain.profileimage.ProfileImage
import org.grida.domain.profileimage.ProfileImageRepository
import org.grida.domain.user.User
import org.grida.error.GridaException
import org.grida.error.NoSuchData
import org.grida.persistence.base.JpqlExecutor
import org.grida.persistence.image.ImageEntity
import org.grida.persistence.image.ImageJpaEntityRepository
import org.grida.persistence.image.toEntity
import org.grida.persistence.user.UserEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class ProfileImageEntityRepository(
    private val profileImageJpaEntityRepository: ProfileImageJpaEntityRepository,
    private val imageJpaEntityRepository: ImageJpaEntityRepository,
    private val jpqlExecutor: JpqlExecutor,
) : ProfileImageRepository {

    @Transactional
    override fun save(
        profileImage: ProfileImage,
        user: User,
    ): Long {
        val imageEntity = profileImage.image.toEntity()
        imageJpaEntityRepository.save(imageEntity)

        val profileImageEntity = profileImage.toEntity(user, imageEntity)
        profileImageJpaEntityRepository.save(profileImageEntity)
        return profileImageEntity.id
    }

    override fun findById(id: Long): ProfileImage {
        val profileImageEntity = jpqlExecutor.find {
            select(
                entity(ProfileImageEntity::class)
            ).from(
                entity(ProfileImageEntity::class),
                fetchJoin(ProfileImageEntity::image)
            ).where(
                path(ProfileImageEntity::id).eq(id)
            )
        }
        return profileImageEntity?.toDomain()
            ?: throw GridaException(NoSuchData(ProfileImage::class, id))
    }

    override fun findByUserIdAndStatus(userId: Long, status: ImageStatus): ProfileImage {
        val profileImageEntity = jpqlExecutor.find {
            select(
                entity(ProfileImageEntity::class)
            ).from(
                entity(ProfileImageEntity::class)
            ).whereAnd(
                path(ProfileImageEntity::user)(UserEntity::id).eq(userId),
                path(ProfileImageEntity::image)(ImageEntity::status).eq(status)
            )
        }
        return profileImageEntity?.toDomain()
            ?: throw GridaException(NoSuchData(ProfileImageEntity::class, userId))
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
        val count = jpqlExecutor.find {
            select(
                count(entity(ProfileImageEntity::class))
            ).from(
                entity(ProfileImageEntity::class)
            ).whereAnd(
                path(ProfileImageEntity::user)(UserEntity::id).eq(userId),
                path(ProfileImageEntity::image)(ImageEntity::status).eq(status)
            )
        }

        return count!! > 0
    }

    @Transactional
    override fun updateStatue(
        profileImageId: Long,
        status: ImageStatus,
    ): Long {
        val profileImageEntity = findEntityByIdWithFetchJoin(profileImageId)
            ?: throw GridaException(NoSuchData(ProfileImage::class, profileImageId))
        val image = profileImageEntity.image
        image.updateStatue(status)

        return profileImageEntity.id
    }

    private fun findEntityByIdWithFetchJoin(id: Long): ProfileImageEntity? {
        return jpqlExecutor.find {
            select(
                entity(ProfileImageEntity::class)
            ).from(
                entity(ProfileImageEntity::class),
                fetchJoin(ProfileImageEntity::image)
            ).where(
                path(ProfileImageEntity::id).eq(id)
            )
        }
    }
}
