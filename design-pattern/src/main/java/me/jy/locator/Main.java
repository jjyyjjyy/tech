package me.jy.locator;

/**
 * @author jy
 * @date 2017/12/25
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext();

        assert new CatService().execute().equals(
            context.lookup("CatService", CatService.class).execute());
        assert new HuskyService().execute().equals(
            context.lookup("HuskyService", Service.class).execute());

    }

}
