/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.codegen;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.junit.Test;

/**
 * HibernateProcessorTest provides.
 * 
 * @author tiwe
 * @version $Id$
 */
public class SerializerTest {

    private ClassModel type;

    private Writer writer = new StringWriter();

    private Map<String, Object> model = new HashMap<String, Object>();

    public SerializerTest() {
        type = new ClassModel(
                "com.mysema.query.DomainSuperClass",
                "com.mysema.query", 
                "com.mysema.query.DomainClass",
                "DomainClass");
        
        FieldModel field = new FieldModel("field", new TypeModel(){
            @Override
            public FieldType getFieldType() {
                return FieldType.STRING;
            }
            @Override
            public String getName() {
                return String.class.getName();
            }
            @Override
            @Nullable
            public String getKeyTypeName() {
                return null;
            }
            @Override
            public String getPackageName() {
                return String.class.getPackage().getName();
            }
            @Override
            public String getSimpleName() {
                return String.class.getSimpleName();
            }
            @Override
            @Nullable
            public String getValueTypeName() {
                return null;
            }}, "field");
        type.addField(field);
        ParameterModel param = new ParameterModel("name", "java.lang.String");
        type.addConstructor(new ConstructorModel(Collections.singleton(param)));
    }

    @Test
    public void testDomainTypesAsOuterClasses() throws Exception {
        model.put("type", type);
        model.put("pre", "");
        model.put("include", "");
        model.put("package", "com.mysema.query");
        model.put("classSimpleName", "Test");

        // as outer classes
        Serializers.DOMAIN.serialize(model, writer);
    }

}
