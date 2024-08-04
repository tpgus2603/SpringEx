package hello.simplecrud.controller;

public class MemberForm { //멤버 가입을 위한 객체
    private String name; //html을 통해 이름값을 받아옴

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
