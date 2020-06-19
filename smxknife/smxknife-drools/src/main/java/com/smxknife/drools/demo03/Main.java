package com.smxknife.drools.demo03;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

/**
 * @author smxknife
 * 2020/6/19
 */
public class Main {
	public static void main(String[] args) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("demo03/ItemConfig.drl"), ResourceType.DRL);

		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}

		// drools 7.x API
		InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
		kbase.addPackages(pkgs);

		StatelessKieSession kieSession = kbase.newStatelessKieSession();

		ItemConfig itemConfig1 = new ItemConfig();
		itemConfig1.setEntCode("00000000");
		itemConfig1.setValue(10000);
		itemConfig1.setIndustryCode("C311");
		itemConfig1.setItemCode("00-00-0000-023300-11");

		ItemConfig itemConfig2 = new ItemConfig();
		itemConfig2.setEntCode("00000001");
		itemConfig2.setValue(100);
		itemConfig2.setIndustryCode("C31");
		itemConfig2.setItemCode("00-00-0000-023300-11");

		kieSession.execute(itemConfig2);
		kieSession.execute(itemConfig1);

		System.out.println("\r\n\r\n\r\n");
		System.out.println(itemConfig1);
		System.out.println(itemConfig2);

	}
}
