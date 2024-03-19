package com.javaapp.bankingapp;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BankingAppApplicationTests.class })
@SelectPackages(value ={"com.javaapp.bankingapp.service", "com.javaapp.bankingapp.controller",  "com.javaapp.bankingapp.repository"} )
public class AllTests {

}
