package com.caseyscarborough.daogen.generator;

import com.caseyscarborough.daogen.DaoGen;
import com.caseyscarborough.daogen.collector.Collector;
import com.caseyscarborough.daogen.collector.CommandLineCollector;
import com.caseyscarborough.daogen.outputter.FileOutputter;
import com.caseyscarborough.daogen.outputter.Outputter;

public class DaoGeneratorImpl implements DaoGenerator {
  @Override
  public void generate() throws Exception {
    Collector collector = new CommandLineCollector();
    DaoGen daoGen = collector.collect();
    Outputter outputter = new FileOutputter();
    outputter.output(daoGen);
  }
}
