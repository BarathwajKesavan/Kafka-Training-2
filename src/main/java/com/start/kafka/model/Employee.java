package com.start.kafka.model;


import com.fasterxml.jackson.databind.ObjectMapper;


public class Employee {
    private Integer empId;
    private String empName;
    private Integer age;
    private Address add;
    public Employee() {
    }

    public Employee(Integer empId, String empName, Integer age, Address add) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.add = add;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", age=" + age +
                ", add=" + add +
                '}';
    }

    public static void main(String[] argv)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = new Employee();
        emp.setEmpId(2);
        emp.setEmpName("Manish Singh");
        emp.setAge(33);
        Address add = new Address("Pune");
        emp.setAdd(add);
        System.out.println(mapper.writeValueAsString(emp));
    }
}