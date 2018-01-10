package me.jy.composite;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

    public static void main(String[] args) {
        Directory slash = new Directory("/", 4096, null);

        Directory root = new Directory("root", 4096, slash);
        Directory etc = new Directory("etc", 4096, slash);
        Directory usr = new Directory("usr", 4096, slash);

        Directory local = new Directory("local", 4096, usr);

        CommonFile profile = new CommonFile("profile", 1024, etc);

        usr.addSubEntry(local);
        etc.addSubEntry(profile);

        slash.addSubEntry(root);
        slash.addSubEntry(etc);
        slash.addSubEntry(usr);

        System.out.println(profile.pwd());
    }
}
