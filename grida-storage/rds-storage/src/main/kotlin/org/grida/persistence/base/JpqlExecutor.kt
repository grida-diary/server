package org.grida.persistence.base

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.querymodel.jpql.JpqlQueryable
import com.linecorp.kotlinjdsl.querymodel.jpql.delete.DeleteQuery
import com.linecorp.kotlinjdsl.querymodel.jpql.select.SelectQuery
import com.linecorp.kotlinjdsl.querymodel.jpql.update.UpdateQuery
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.support.hibernate.extension.createQuery
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class JpqlExecutor(
    private val entityManager: EntityManager,
    private val jpqlRenderContext: RenderContext
) {

    fun <T : Any> find(
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): T? {
        return entityManager
            .createQuery(jpql(init), jpqlRenderContext)
            .apply {
                firstResult = 0
                maxResults = 1
            }
            .resultList
            .firstOrNull()
    }

    fun <T : Any> findAll(
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): List<T> {
        return entityManager
            .createQuery(jpql(init), jpqlRenderContext)
            .resultList
            .filterNotNull()
    }

    fun <T : Any> findPage(
        offset: Int,
        limit: Int,
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): List<T> {
        return entityManager
            .createQuery(jpql(init), jpqlRenderContext)
            .apply {
                firstResult = offset
                maxResults = limit
            }
            .resultList
            .filterNotNull()
    }

    @Transactional
    fun <T : Any, Q> update(
        init: Jpql.() -> JpqlQueryable<UpdateQuery<T>>
    ): Int {
        return entityManager
            .createQuery(jpql(init), jpqlRenderContext)
            .executeUpdate()
    }

    @Transactional
    fun <T : Any, Q> delete(
        init: Jpql.() -> JpqlQueryable<DeleteQuery<T>>
    ): Int {
        return entityManager
            .createQuery(jpql(init), jpqlRenderContext)
            .executeUpdate()
    }
}
