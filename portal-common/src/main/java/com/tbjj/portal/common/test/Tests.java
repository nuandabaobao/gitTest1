package com.tbjj.portal.common.test;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * @author Fu JinHui
 */
public class Tests {

	/**
	 * http://codego.net/5012083/
	 * 解决被代理的InjectMocks对象，不能被mock的问题
	 * */
	public static final Object unwrapProxy(Object bean) throws Exception {
		if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
			Advised advised = (Advised) bean;
			bean = advised.getTargetSource().getTarget();
		}
		return bean;
	}
}
