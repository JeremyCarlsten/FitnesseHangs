package com.jcarlsten

import fitnesse.junit.DescriptionFactory
import fitnesse.junit.FitNesseRunner
import fitnesse.junit.JUnitRunNotifierResultsListener
import fitnesse.testrunner.MultipleTestsRunner
import fitnesse.testsystems.TestPage
import fitnesse.testsystems.TestSummary
import org.junit.runner.notification.RunNotifier
import org.junit.runners.model.InitializationError

class CustomFitnesseRunner extends FitNesseRunner {
    CustomFitnesseRunner(Class<?> suiteClass) throws InitializationError {
        super(suiteClass)
    }

    @Override
    protected void addTestSystemListeners(RunNotifier notifier, MultipleTestsRunner testRunner, Class<?> suiteClass, DescriptionFactory descriptionFactory) {
        testRunner.addTestSystemListener(new FitnesseTestListener(notifier, suiteClass, descriptionFactory))
        super.addTestSystemListeners(notifier, testRunner, suiteClass, descriptionFactory)
    }
}

class FitnesseTestListener extends JUnitRunNotifierResultsListener {

    FitnesseTestListener(RunNotifier notifier, Class<?> mainClass, DescriptionFactory descriptionFactory) {
        super(notifier, mainClass, descriptionFactory)
    }

    @Override
    void testStarted(TestPage test) {
        print("Running ${test.getFullPath()}.... ")
        super.testStarted(test)
    }

    @Override
    void testComplete(TestPage test, TestSummary testSummary) {

        println("Done. (Right: ${testSummary.right}, Wrong: ${testSummary.wrong}, Exeptions: ${testSummary.exceptions}, Completed: ${completedTests} / ${totalNumberOfTests})")
        super.testComplete(test, testSummary)
    }
}


