package com.DockAuto.tests.vendorportal.model;

public record VendorPortalTestData(String username,
                                   String password,
                                   String monthlyEarnings,
                                   String annualEarnings,
                                   String profitMargin,
                                   String availableInventory,
                                   String searchKeyword,
                                   int searchResultsCount) {
}


/** Not using record:
 *
 * Class
 * public class VendorPortalTestData (){
 *
 * Members
 * String username,
 * String password,
 * String monthlyEarnings,
 * String annualEarnings,
 * String profitMargin,
 * String availableInventory,
 * String searchKeyword,
 * int searchResultsCount
 *
 * No-Arg constructor
 * public VendorPortalTestData(){}
 *
 * Getters
 *
 * Setters
 *
 * }
 */