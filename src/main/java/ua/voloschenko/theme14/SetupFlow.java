package ua.voloschenko.theme14;

public class SetupFlow {

    @Step(order = 2)
    private void loadCache() {
        System.out.println("private void");
    }

    @Step(order = 1)
    public void initConfig() {
        System.out.println("public void");
    }

    @Step(order = 3)
    void startServer() {
//        System.out.println("package-private method");
         throw new RuntimeException("Runtime error");
    }

    public void doSomethingElse() {
        System.out.println("no annotation");
    }
}