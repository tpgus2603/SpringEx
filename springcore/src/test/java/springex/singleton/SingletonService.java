package springex.singleton;

public class SingletonService {
    private static final SingletonService instance =new SingletonService();
    public static SingletonService getInstance(){ //get을 통해서만 조회가능
        return instance;
    }
    private SingletonService(){ //외부에서 객체인스턴스가 생성되느것을 막음

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
