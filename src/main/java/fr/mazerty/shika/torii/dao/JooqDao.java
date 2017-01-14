package fr.mazerty.shika.torii.dao;

import org.jooq.DSLContext;

import javax.inject.Inject;

public abstract class JooqDao {

    @Inject
    protected DSLContext dslContext;

}
