package fr.mazerty.torii.dao;

import org.jooq.DSLContext;

import javax.inject.Inject;

/**
 * Common class for all Jooq-based DAOs
 */
public abstract class JooqDao {

    @Inject
    protected DSLContext dslContext;

}
