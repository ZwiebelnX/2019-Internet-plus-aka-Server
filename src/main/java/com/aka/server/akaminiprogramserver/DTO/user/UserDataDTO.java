package com.aka.server.akaminiprogramserver.DTO.user;

/**
 * <p>Title: UserDataDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:53
 */
public class UserDataDTO {
    private String realName;
    private String grade;
    private String gender;
    private String age;
    private String college;
    private String phone;

    public void setAge(String age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAge() {
        return age;
    }

    public String getCollege() {
        return college;
    }

    public String getGender() {
        return gender;
    }

    public String getGrade() {
        return grade;
    }

    public String getPhone() {
        return phone;
    }

    public String getRealName() {
        return realName;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
