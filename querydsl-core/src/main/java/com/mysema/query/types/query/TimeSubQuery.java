/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.types.query;

import com.mysema.query.QueryMetadata;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.SubQueryExpression;
import com.mysema.query.types.SubQueryExpressionImpl;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.TimeExpression;

/**
 * Time typed single result subquery
 *
 * @author tiwe
 *
 * @param <A>
 */
public final class TimeSubQuery<A extends Comparable<?>> extends TimeExpression<A> implements SubQueryExpression<A>{

    private static final long serialVersionUID = -64156984110154969L;

    private final SubQueryExpressionImpl<A> subQueryMixin;

    public TimeSubQuery(Class<A> type, QueryMetadata md) {
        super(type);
        subQueryMixin = new SubQueryExpressionImpl<A>(type, md);
    }

    @Override
    public <R,C> R accept(Visitor<R,C> v, C context) {
        return v.visit(this, context);
    }

    @Override
    public boolean equals(Object o) {
       return subQueryMixin.equals(o);
    }

    @Override
    public Predicate exists() {
        return subQueryMixin.exists();
    }

    @Override
    public QueryMetadata getMetadata() {
        return subQueryMixin.getMetadata();
    }

    @Override
    public int hashCode(){
        return subQueryMixin.hashCode();
    }

    @Override
    public Predicate notExists() {
        return subQueryMixin.notExists();
    }

}
