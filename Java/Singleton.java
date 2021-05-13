// 인스턴스가 절대적으로 한개만 존재하는 것을 보증할 때,
// 전역 인스턴스기 때문에 어디서든 접근이 쉬움
// 싱글턴은 필요시에만, 남발 x

final class Singleton {
    private static Singleton s = new Singleton(); // getInstance에서 사용될 수 있도록 인스턴스가 미리 생성되어 있어야 한다
    private Singleton(){
      //...
    }
    public static Singleton getInstance(){ //인스턴스 생성없이 호출해야 해서 static으로 만든다
        if(s == null){
            s = new Singleton();
        }
        return s;
    }
}
class SingletonTest{
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
    }
}
// 기초 싱글턴 예제로 생성자의 접근 제어자를 private하고, 
// getInstance로 받아쓴다.
// 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴.
// 고정된 메모리 영역을 얻으면서 한번의 new 인스턴스를 사용하여 메모리 낭비를 방지.
// 그러나 다중 쓰레드에서 인스턴스가 1개 이상일 수 있다. 그래서
// 그래서 synchronized를 통해 해결해줄 수 있으나 매번 synchronized해주어서 효율성이 떨어진다.
  
public class ThreadSafeLayInitialization {
    private static ThreadSafeLayInitialization instance;

    private ThreadSafeLayInitialization(){}

    public static synchronized ThreadSafeLayInitialization getInstance(){ #synchronized 통해 thread-safe하게만듦
        if (instance == null){
            instance = new ThreadSafeLayInitialization();
        }
        return instance;
    }
}

// 이런 경우 

public static synchronized ThreadSafeLayInitialization getInstance(){ #synchronized 통해 thread-safe하게만듦
        if (instance == null){
            instance = new ThreadSafeLayInitialization();
        }
        return instance;
// 부분을 
public static ThreadSafeLayInitialization getInstance(){ 
        if (instance == null){
          synchronized (ThreadSafeLazyInitialization.class){
            if(instance == null){
              instance = new ThreadSafeLayInitialization();
            }
        }
        return instance;
//이런 식으로 바꿔준다면 처음 생성이후 synchronized하지 않는다.

// 마지막으로 가장 흔히 쓰이는 방법으로는
          public class Something {
    private Something() {
    }
 
    private static class LazyHolder {
        public static final Something INSTANCE = new Something();
    }
 
    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
// 클래스 안에 클래스를 두고, INSTANCE가 static 이기에 클래스 로딩 시점에서 한번만 호출되며, 
//final이기 때문에 값이 다시 할당되지 않는 것을 이용하는 방법이다.

출처: https://jeong-pro.tistory.com/86 [기본기를 쌓는 정아마추어 코딩블로그]
