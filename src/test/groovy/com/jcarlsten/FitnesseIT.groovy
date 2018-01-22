package com.jcarlsten

import fitnesse.junit.FitNesseRunner
import org.junit.runner.RunWith

@RunWith(CustomFitnesseRunner.class)
@FitNesseRunner.Suite("FrontPage.IntegratioN")
@FitNesseRunner.FitnesseDir('./fitnesse')
@FitNesseRunner.OutputDir("target/fitnesse")
public class FitnesseIT {

}
