package com.smxknife.drools.demo04;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

/**
 * @author smxknife
 * 2020/6/19
 */
public class Main2 {
	public static void main(String[] args) {


		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("demo04/ItemConfig-a.drl"), ResourceType.DRL);

		// drools 7.x API
		InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
		kbase.addPackages(pkgs);

		ItemConfig3 itemConfig1 = new ItemConfig3();
		itemConfig1.setEntCode("00000000");
		itemConfig1.setValue(10000);
		itemConfig1.setIndustryCode("C311");
		itemConfig1.setItemCode("00-00-0000-023300-11");

		ItemConfig2 itemConfig2 = new ItemConfig2();
		itemConfig2.setEntCode("00000001");
		itemConfig2.setValue(100);
		itemConfig2.setIndustryCode("C31");
		itemConfig2.setItemCode("00-00-0000-023300-11");

		StatelessKieSession kieSession = kbase.newStatelessKieSession();
//		List cmds = new ArrayList();
//		cmds.add(CommandFactory.newInsert(itemConfig1));
//		cmds.add(CommandFactory.newInsert(itemConfig2));
//
//		kieSession.execute(CommandFactory.newBatchExecution(cmds));

		kieSession.execute(itemConfig1);
		kieSession.execute(itemConfig2);

//		KieSession kieSession = kbase.newKieSession();
//
//		kieSession.insert(itemConfig1);
//		kieSession.insert(itemConfig2);
//		kieSession.fireAllRules();
//		kieSession.dispose();

		System.out.println("\r\n\r\n\r\n");
		System.out.println(itemConfig1);
		System.out.println(itemConfig2);

	}
}
