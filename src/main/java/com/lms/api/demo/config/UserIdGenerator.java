package com.lms.api.demo.config;


import java.io.Serializable;
import java.util.Properties;

import org.hibernate.service.ServiceRegistry;

import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.*;
import org.hibernate.internal.util.config.ConfigurationHelper;

public class UserIdGenerator extends SequenceStyleGenerator {
	public static final String VAlUE_PREFIX_PARAMETER = " valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT= "U0";
	private String valuePrefix;
	
	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%01d";
	private String numberformat;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		return valuePrefix+String.format(NUMBER_FORMAT_DEFAULT, super.generate(session, object));
		
	}
	
	@Override
    public void configure(Type type, Properties params,
            org.hibernate.service.ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        valuePrefix = ConfigurationHelper.getString(VAlUE_PREFIX_PARAMETER,
                params, VALUE_PREFIX_DEFAULT);
        numberformat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER,
                params, NUMBER_FORMAT_DEFAULT);
    }

}
