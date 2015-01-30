/**
 * Copyright (c) 2014 by Software Engineering Lab. of Sungkyunkwan University. All Rights Reserved.
 * 
 * Permission to use, copy, modify, and distribute this software and its documentation for
 * educational, research, and not-for-profit purposes, without fee and without a signed licensing agreement,
 * is hereby granted, provided that the above copyright notice appears in all copies, modifications, and distributions.
 */
package edu.skku.selab.blp.buglocator.analysis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.skku.selab.blp.Property;
import edu.skku.selab.blp.buglocator.analysis.BugRepoAnalyzerWithFile;
import edu.skku.selab.blp.buglocator.indexer.BugCorpusCreatorWithFile;
import edu.skku.selab.blp.buglocator.indexer.BugVectorCreatorWithFile;
import edu.skku.selab.blp.buglocator.indexer.SourceFileCorpusCreatorWithFile;

/**
 * @author Klaus Changsun Youm(klausyoum@skku.edu)
 *
 */
public class BugRepoAnalyzerWithFileTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String osName = System.getProperty("os.name");
		String productName = "swt-3.1";
		float alpha = 0.2f;
		float beta = 0.5f;
		
		if (osName.equals("Mac OS X")) {
			String bugFilePath = "./test_data/SWTBugRepository.xml";
			String sourceCodeDir = "../swt-3.1/src";
			String workDir = "./tmp";
			String outputFile = "./tmp/test_output.txt";
			
			Property.createInstance(productName, bugFilePath, sourceCodeDir, workDir, alpha, beta, outputFile);		
		} else {
			String bugFilePath = ".\\test_data\\SWTBugRepository.xml";
			String sourceCodeDir = "..\\swt-3.1\\src";
			String workDir = ".\\tmp";
			String outputFile = ".\\tmp\\test_output.txt";
			
			Property.createInstance(productName, bugFilePath, sourceCodeDir, workDir, alpha, beta, outputFile);
		}		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void verifyAnalyze() throws Exception {
		BugCorpusCreatorWithFile bugCorpusCreator = new BugCorpusCreatorWithFile();
		bugCorpusCreator.create();
		
		BugVectorCreatorWithFile bugVectorCreator = new BugVectorCreatorWithFile();
		bugVectorCreator.create();
		
		// Following function is needed to set file count for Property.getFileCount() at BugRepoAnalyzer
		SourceFileCorpusCreatorWithFile sourceFileCorpusCreator = new SourceFileCorpusCreatorWithFile();
		sourceFileCorpusCreator.create();
		
		BugRepoAnalyzerWithFile bugRepoAnalyzer = new BugRepoAnalyzerWithFile();
		bugRepoAnalyzer.analyze();
	}
}