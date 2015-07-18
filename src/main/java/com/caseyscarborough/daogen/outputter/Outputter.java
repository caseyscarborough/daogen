package com.caseyscarborough.daogen.outputter;

import com.caseyscarborough.daogen.DaoGen;

/**
 * Handles the outputting of the Dao.
 */
public interface Outputter {

  void output(DaoGen daoGen) throws Exception;
}
