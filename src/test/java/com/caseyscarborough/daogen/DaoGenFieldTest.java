package com.caseyscarborough.daogen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DaoGenFieldTest {

  private DaoGenField field1;
  private DaoGenField field2;

  @Before
  public void setUp() {
    DaoGenClass daoGenClass = new DaoGenClass();
    daoGenClass.setClassName("User");
    field1 = new DaoGenField(daoGenClass);
    field1.setColumnName("FIRST_NAME");
    field1.setFieldName("firstName");
    field1.setIdColumn(false);
    field1.setType("String");

    field2 = new DaoGenField(daoGenClass);
    field2.setColumnName("ID");
    field2.setFieldName("id");
    field2.setIdColumn(true);
    field2.setType("Integer");
  }

  @Test
  public void testGetResultSetType() {
    assertEquals("String", field1.getResultSetType());
    assertEquals("Int", field2.getResultSetType());
  }

  @Test
  public void testConstructorSetter() {
    assertEquals("this.firstName = firstName;", field1.getConstructorSetter());
    assertEquals("this.id = id;", field2.getConstructorSetter());
  }

  @Test
  public void testGetterMethod() {
    assertEquals("\n    public String getFirstName() {\n        return firstName;\n    }\n", field1.getGetterMethod());
    assertEquals("\n    public Integer getId() {\n        return id;\n    }\n", field2.getGetterMethod());
  }

  @Test
  public void testSetterMethod() {
    assertEquals("\n    public void setFirstName(String firstName) {\n        this.firstName = firstName;\n    }\n", field1.getSetterMethod());
    assertEquals("\n    public void setId(Integer id) {\n        this.id = id;\n    }\n", field2.getSetterMethod());
  }

  @Test
  public void testResultSetSetter() {
    assertEquals("user.setFirstName(resultSet.getString(\"FIRST_NAME\"));", field1.getResultSetSetter());
    assertEquals("user.setId(resultSet.getInt(\"ID\"));", field2.getResultSetSetter());
  }

  @Test
  public void testFieldDeclaration() {
    assertEquals("private String firstName;", field1.getFieldDeclaration());
    assertEquals("private Integer id;", field2.getFieldDeclaration());
  }

  @Test
  public void testToString() {
    assertEquals("firstName='\" + firstName + \"'\" +", field1.getToString());
    assertEquals("id='\" + id + \"'\" +", field2.getToString());
  }

  @Test
  public void testGetBindSetter() {
    assertEquals("statement.setString(1, user.getFirstName());", field1.getBindSetter(1));
    assertEquals("statement.setInt(2, user.getId());", field2.getBindSetter(2));
  }
}
