package com.caseyscarborough.daogen.collector;

import com.caseyscarborough.daogen.DaoGen;

/**
 * Handles the collection of data and creation of
 * the DaoGen instance.
 */
public interface Collector {

  DaoGen collect() throws Exception;
}
